package com.lunar.collision;

import java.awt.Rectangle;

public class BoundingBox {

	/**
	 * The x origin of this BoundingBox.
	 */
	public int x;

	/**
	 * The y origin of this BoundingBox.
	 */
	public int y;

	/**
	 * The width of this BoundingBox.
	 */
	public int width;

	/**
	 * The height of this BoundingBox.
	 */
	public int height;

	/**
	 * The bounds of this BoundingBox.
	 */
	public Rectangle bounds;

	/**
	 * Initialize the BoundingBox.
	 * @param x The x origin to initialize this BoundingBox with.
	 * @param y The y origin to initialize this BoundingBox with.
	 * @param width The width to initialize this BoundingBox with.
	 * @param height The height to initialize this BoundingBox with.
	 */
	public BoundingBox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.bounds = new Rectangle(x, y, width, height);
	}

	/**
	 * Set the values of this BoundingBox.
	 * @param x The x origin to set.
	 * @param y The y origin to set.
	 * @param width The width to set.
	 * @param height The height to set.
	 */
	public void update(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		bounds.setBounds(x, y, width, height);
	}

	/**
	 * Return if the current BoundingBox intersects with another.
	 * @param box The other BoundingBox to check against.
	 * @return True if the passed in BoundingBox intersects with this one,
	 * otherwise false.
	 */
	public boolean doesIntersect(BoundingBox box) {
		return bounds.intersects(box.bounds);
	}
}
