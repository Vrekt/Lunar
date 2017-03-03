package me.minz1.lunar.vec;

public class Vector2f {

	private int x, y;

	/**
	 * Initialize.
	 * 
	 * @param x
	 * @param y
	 */
	public Vector2f(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the X.
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the Y.
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the X.
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Set the Y.
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Add to this vector.
	 * 
	 * @param other
	 * @return
	 */
	public Vector2f add(Vector2f other) {
		return new Vector2f(x + other.getX(), y + other.getY());
	}

	/**
	 * Subtract this vector by another.
	 * 
	 * @param other
	 * @return
	 */
	public Vector2f subtract(Vector2f other) {
		return new Vector2f(x - other.getX(), y - other.getY());
	}

	/**
	 * Multiply this vector by another.
	 * 
	 * @param other
	 * @return
	 */
	public Vector2f multiply(Vector2f other) {
		return new Vector2f(x * other.getX(), y * other.getY());
	}

	/**
	 * Dive this vector by another.
	 * 
	 * @param other
	 * @return
	 */
	public Vector2f divide(Vector2f other) {
		return new Vector2f(x / other.getX(), y / other.getY());
	}

	/**
	 * Get the distance.
	 * 
	 * @param other
	 * @return
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
