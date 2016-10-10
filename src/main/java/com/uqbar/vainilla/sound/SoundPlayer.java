package com.uqbar.vainilla.sound;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class SoundPlayer {

	public static final SoundPlayer INSTANCE = new SoundPlayer();

	public static final Encoding ENCODING = AudioFormat.Encoding.PCM_SIGNED;
	public static final float SAMPLE_RATE = 44100;
	public static final int SAMPLE_SIZE_IN_BYTES = 2;
	public static final int CHANNELS = 2;

	private static final int NUM_SAMPLES = 44100 * 2;

	private AudioFormat format = new AudioFormat(44100.0f, 16, 2, true, false);

	// ****************************************************************
	// ** STATICS
	// ****************************************************************

	protected static short toShortSample(float value) {
		return (short) ((value > 1 ? 1 : value < -1 ? -1 : value) * Short.MAX_VALUE);
	}

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	public AudioFormat normalizeFormat(AudioFormat format) {
		return new AudioFormat(SoundPlayer.ENCODING, SoundPlayer.SAMPLE_RATE, SoundPlayer.SAMPLE_SIZE_IN_BYTES * 8,
				format.getChannels(), format.getChannels() * SoundPlayer.SAMPLE_SIZE_IN_BYTES, SoundPlayer.SAMPLE_RATE,
				false //
		);
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	protected synchronized SoundPlay enqueueSound(final Sound sound, final float volume) {
		final SoundPlay play = new SoundPlay(sound, volume);
		new Thread() {
			public void run() {
				try {
					playSound(play, volume);
				} catch (Exception e) {
					System.out.println("Error trying to reproduce sound: " + sound.toString());
					e.printStackTrace();
				}
			}
		}.start();
		
		return play;
	}

	protected void playSound(SoundPlay play, float volume) throws Exception {
		Logger.getGlobal().log(Level.INFO, "Started playing sound");
		
		SourceDataLine line = AudioSystem.getSourceDataLine(format);

		line.open(format, 4410);
		line.start();

		boolean shouldContinue = true;

		while (shouldContinue) {
			int bytesToWrite = line.available();

			if (bytesToWrite > 0) {
				int samplesToWrite = bytesToWrite / 2;
				float[] buffer = new float[NUM_SAMPLES];
				byte[] answer = new byte[2 * NUM_SAMPLES];

				shouldContinue = !play.writeSamples(samplesToWrite, buffer);

				for (int i = 0; i < samplesToWrite; i++) {

					short sample = toShortSample(buffer[i]);

					answer[i * 2] = (byte) (sample | 0xff);
					answer[i * 2 + 1] = (byte) (sample >> 8);
				}

				int writtenBytes = 0;

				while (writtenBytes != bytesToWrite) {
					writtenBytes += line.write(answer, writtenBytes, bytesToWrite - writtenBytes);
				}
			}
		}
		
		line.close();
		
		Logger.getGlobal().log(Level.INFO, "Finished playing sound");
	}

//	private byte[] readBytes(int bytesToWrite) {
//		int samplesToWrite = bytesToWrite / 2;
//		float[] buffer = new float[NUM_SAMPLES];
//		byte[] answer = new byte[2 * NUM_SAMPLES];
//
//		synchronized (this) {
//			Iterator<SoundPlay> iterator = this.buffers.iterator();
//
//			while (iterator.hasNext()) {
//				if (iterator.next().writeSamples(samplesToWrite, buffer)) {
//					iterator.remove();
//				}
//			}
//		}
//
//		int bufferSize = this.buffers.size();
//		if (bufferSize > 0) {
//			for (int i = 0; i < samplesToWrite; i++) {
//
//				short sample = toShortSample(buffer[i]);
//
//				answer[i * 2] = (byte) (sample | 0xff);
//				answer[i * 2 + 1] = (byte) (sample >> 8);
//			}
//		}
//
//		return answer;
//	}
}