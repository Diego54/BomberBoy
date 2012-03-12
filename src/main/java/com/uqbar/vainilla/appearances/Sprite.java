package com.uqbar.vainilla.appearances;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.exceptions.GameException;

public class Sprite implements Appearance {

	private static Map<String, BufferedImage> IMAGE_CACHE = new HashMap<String, BufferedImage>();

	private String baseImagePath;
	private BufferedImage baseImage;
	private AffineTransform transformation;
	private BufferedImage currentImage;
	private int dx, dy;
	private Rectangle croppedZone;

	// ****************************************************************
	// ** STATICS
	// ****************************************************************

	public static Sprite fromImageFile(File imageFile) {

		BufferedImage image = IMAGE_CACHE.get(imageFile.getPath());

		if(image == null) {
			try {
				image = ImageIO.read(imageFile);
			}
			catch(Exception e) {
				throw new GameException("The file '" + imageFile + "' was not found");
			}

			image = adaptImage(image);

			IMAGE_CACHE.put(imageFile.getPath(), image);
		}

		return new Sprite(image, imageFile.getPath());
	}

	public static Sprite fromImage(String imageFileName) {

		BufferedImage image = IMAGE_CACHE.get(imageFileName);

		if(image == null) {
			try {
				image = ImageIO.read(Sprite.class.getResource(imageFileName));
			}
			catch(Exception e) {
				throw new GameException("The resource '" + imageFileName + "' was not found");
			}

			image = adaptImage(image);

			IMAGE_CACHE.put(imageFileName, image);
		}

		return new Sprite(image, imageFileName);
	}

	protected static BufferedImage adaptImage(BufferedImage image) {
		BufferedImage answer = GraphicsEnvironment
			.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice()
			.getDefaultConfiguration()
			.createCompatibleImage(image.getWidth(), image.getHeight(), Transparency.TRANSLUCENT);

		Graphics graphics = answer.getGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();

		return answer;
	}

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	protected Sprite(BufferedImage image, String imagePath) {
		this.setBaseImagePath(imagePath);
		this.setDx(0);
		this.setDy(0);
		this.flattenTo(image);

		this.setCroppedZone(new Rectangle(image.getWidth(), image.getHeight()));
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return Math.abs(this.getBaseImage().getWidth() * this.getTransformation().getScaleX());
	}

	@Override
	public double getHeight() {
		return Math.abs(this.getBaseImage().getHeight() * this.getTransformation().getScaleY());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Sprite copy() {
		Sprite sprite = new Sprite(this.getCurrentImage(), this.getBaseImagePath());
		sprite.setDx(this.getDx());
		sprite.setDy(this.getDy());
		sprite.setCroppedZone(this.getCroppedZone());

		return sprite;
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	public Sprite move(int x, int y) {
		this.setDx(this.getDx() + x);
		this.setDy(this.getDy() + y);

		return this;
	}

	// ****************************************************************
	// ** TRANSFORM OPERATIONS
	// ****************************************************************

	public Sprite scale(double scale) {
		return this.scale(scale, scale);
	}

	public Sprite scale(double scaleX, double scaleY) {
		this.getTransformation().scale(scaleX, scaleY);

		this.updateCurrentImage();

		return this;
	}

	public Sprite scaleHorizontally(double scale) {
		return this.scale(scale, 1);
	}

	public Sprite scaleVertically(double scale) {
		return this.scale(1, scale);
	}

	public Sprite scaleTo(double width, double height) {

		double horizontalScale = width / this.getWidth();
		double verticalScale = height / this.getHeight();

		return this.scale(horizontalScale, verticalScale);
	}

	public Sprite scaleHorizontallyTo(double width, boolean keepAspectRatio) {
		double scale = width / this.getWidth();

		return this.scale(scale, keepAspectRatio ? scale : 1);
	}

	public Sprite scaleVerticallyTo(int height, boolean keepAspectRatio) {
		double scale = height / this.getHeight();

		return this.scale(keepAspectRatio ? scale : 1, scale);
	}

	public Sprite flipHorizontally() {
		this.getTransformation().translate(this.getWidth(), 0);
		this.scaleHorizontally(-1);

		this.updateCurrentImage();

		return this;
	}

	public Sprite flipVertically() {
		this.getTransformation().translate(0, this.getHeight());
		this.scaleVertically(-1);

		this.updateCurrentImage();

		return this;
	}

	public Sprite crop(int width, int height) {
		return this.crop(0, 0, width, height);
	}

	public Sprite crop(int x, int y, int width, int height) {
		this.flattenTo(this.getCurrentImage().getSubimage(x, y, width, height));

		this.setCroppedZone(new Rectangle(this.getCroppedZone().x + x, this.getCroppedZone().y + y, width, height));

		return this;
	}

	public Sprite repeat(double horizontalRepetitions, double verticalRepetitions) {
		double horizontalIterations = Math.ceil(horizontalRepetitions);
		double verticalIterations = Math.ceil(verticalRepetitions);
		double width = this.getWidth();
		double height = this.getHeight();
		BufferedImage newImage = new BufferedImage( //
			(int) (width * horizontalRepetitions), //
			(int) (height * verticalRepetitions), //
			this.getCurrentImage().getType() //
		);
		Graphics graphics = newImage.getGraphics();

		for(int i = 0; i < horizontalIterations; i++ ) {
			for(int j = 0; j < verticalIterations; j++ ) {
				graphics.drawImage(this.getCurrentImage(), i * (int) width, j * (int) height, null);
			}
		}

		graphics.dispose();

		this.flattenTo(newImage);

		return this;
	}

	public Sprite repeatHorizontally(double repetitions) {
		return this.repeat(repetitions, 1);
	}

	public Sprite repeatVertically(double repetitions) {
		return this.repeat(1, repetitions);
	}

	public Sprite repeatHorizontallyToCover(double width) {
		return this.repeatHorizontally(width / this.getWidth());
	}

	public Sprite repeatVerticallyToCover(double height) {
		return this.repeatVertically(height / this.getHeight());
	}

	public Sprite repeatToCover(double width, double height) {
		return this.repeat(width / this.getWidth(), height / this.getHeight());
	}

	protected void flattenTo(BufferedImage image) {
		this.setBaseImage(image);
		this.setCurrentImage(image);
		this.setTransformation(new AffineTransform());
	}

	protected void updateCurrentImage() {
		AffineTransformOp transformOp = new AffineTransformOp(this.getTransformation(), AffineTransformOp.TYPE_BILINEAR);

		// this.setCurrentImage(transformOp.filter(this.getBaseImage(), null));
		this.setCurrentImage(transformOp.filter(this.getBaseImage(),
			new BufferedImage(
				(int) this.getWidth(),
				(int) this.getHeight(),
				this.getBaseImage().getType()
			)
			));

	}

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	@Override
	public void update(double delta) {
	}

	@Override
	public void render(GameComponent<?> component, Graphics2D graphics) {
		graphics.drawImage(
			this.getCurrentImage(),
			(int) component.getX() + this.getDx(),
			(int) component.getY() + this.getDy(),
			null
			);
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	protected String getBaseImagePath() {
		return this.baseImagePath;
	}

	protected void setBaseImagePath(String baseImagePath) {
		this.baseImagePath = baseImagePath;
	}

	protected BufferedImage getBaseImage() {
		return this.baseImage;
	}

	protected void setBaseImage(BufferedImage image) {
		this.baseImage = image;
	}

	protected AffineTransform getTransformation() {
		return this.transformation;
	}

	protected void setTransformation(AffineTransform transformation) {
		this.transformation = transformation;
	}

	protected BufferedImage getCurrentImage() {
		return this.currentImage;
	}

	protected void setCurrentImage(BufferedImage currentImage) {
		this.currentImage = currentImage;
	}

	protected int getDx() {
		return this.dx;
	}

	protected void setDx(int dx) {
		this.dx = dx;
	}

	protected int getDy() {
		return this.dy;
	}

	protected void setDy(int dy) {
		this.dy = dy;
	}

	protected Rectangle getCroppedZone() {
		return this.croppedZone;
	}

	protected void setCroppedZone(Rectangle croppedZone) {
		this.croppedZone = croppedZone;
	}
}