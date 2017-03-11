package me.vrekt.lunar.vec;

/**
 * To be honest it isn't even a vector, it doesn't have direction or force. It's more like a utility to calculate points.
 */
public class Vector2f {

	private int x, y;

	/**
	 * Initialize.
	 */
	public Vector2f(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the X.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set the X.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get the Y.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the Y.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Add to this vector.
	 */
	public Vector2f add(Vector2f other) {
		return new Vector2f(x + other.getX(), y + other.getY());
	}

	/**
	 * Subtract this vector by another.
	 */
	public Vector2f subtract(Vector2f other) {
		return new Vector2f(x - other.getX(), y - other.getY());
	}

	/**
	 * Multiply this vector by another.
	 */
	public Vector2f multiply(Vector2f other) {
		return new Vector2f(x * other.getX(), y * other.getY());
	}

	/**
	 * Dive this vector by another.
	 */
	public Vector2f divide(Vector2f other) {
		return new Vector2f(x / other.getX(), y / other.getY());
	}

	/**
	 * Get the distance.
	 */
	public double distance(Vector2f other) {
		double dX = other.getX() - x;
		double dY = other.getY() - y;
		return Math.sqrt(dX * dX + dY * dY);
	}

	/**
	 * Clone this vector.
	 */
	public Vector2f clone() {
		return new Vector2f(x, y);
	}

}
