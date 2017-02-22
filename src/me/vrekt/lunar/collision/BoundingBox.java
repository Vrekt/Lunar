package me.vrekt.lunar.collision;

public class BoundingBox {

	public int minX, minY, maxX, maxY;

	public BoundingBox(int minX, int minY, int maxX, int maxY) {
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public boolean collides(int x, int y) {
		return x >= minX && x <= maxX && y >= minY && y <= maxY;
	}

}
