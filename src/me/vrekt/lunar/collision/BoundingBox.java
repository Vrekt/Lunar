package me.vrekt.lunar.collision;

import java.awt.Rectangle;

public class BoundingBox {

	public int x, y, width, height;
	public Rectangle bounds;

	public BoundingBox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.bounds = new Rectangle(x, y, width, height);

	}

	public void update(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		bounds.setBounds(x, y, width, height);
	}

	public boolean doesIntersect(BoundingBox box) {
		return bounds.intersects(box.bounds);
	}

}
