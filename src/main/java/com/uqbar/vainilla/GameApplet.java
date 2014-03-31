package com.uqbar.vainilla;

import java.applet.Applet;
import java.util.Properties;

import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.utils.AppletResourceProvider;

@SuppressWarnings("serial")
public class GameApplet extends Applet {

	protected GamePlayer player;

	/**
	 * Sobreescribir si no se quiere usar la clase como parametro
	 * 
	 * @return
	 */
	protected Game buildGame() {
		try {
			return (Game) Class.forName(this.getParameter("gameClass"))
					.newInstance();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void init() {
		super.init();
		logStart();
		player = new GamePlayer(this.buildGame());
		
		this.add(player);
		this.setSize(player.getSize());
		Thread thread = new Thread(player);
		thread.setPriority(Thread.NORM_PRIORITY+1);
		thread.start();
		
	}

	private void logStart() {
		System.out.println("Vainilla Applet started: " + this.getParameter("gameClass"));
		System.out.println("using opengl= " + System.getProperty("sun.java2d.opengl"));
		Properties sys = System.getProperties();
		for(Object key : sys.keySet()) {
			System.out.println(key.toString() + "="+sys.get(key));
		}
	}

}
