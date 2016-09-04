package com.uqbar.vainilla.appearances;

import java.awt.Color;

@SuppressWarnings("unchecked")
public abstract class Shape<T extends Shape<?>> implements Appearance {

	protected Color color;
	private double offsetX;
	private double offsetY;

	public Shape() {
		super();
	}

	public double getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(double offsetX) {
		this.offsetX = offsetX;
	}

	public double getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(double offsetY) {
		this.offsetY = offsetY;
	}

	public T center() {
		this.setOffsetX(-this.getWidth() / 2);
		this.setOffsetY(-this.getHeight() / 2);
		return (T) this;
	}

	public T centerBottom() {
		this.setOffsetX(-this.getWidth() / 2);
		this.setOffsetY(-this.getHeight());
		return (T) this;
	}

}