package me.vrekt.lunar.location;

public class Location {

	private int x, y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Clone this location.
	 */
	public Location clone() {
		return new Location(x, y);
	}

	/**
	 * Get the distance between two points.
	 * 
	 * @param location
	 * @return
	 */
	public double distance(Location location) {
		double dX = location.getX() - x;
		double dY = location.getY() - y;
		return Math.sqrt(dX * dX + dY * dY);
	}

	/**
	 * Get the distance between two points.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public double distance(int x, int y) {
		double dX = x - this.x;
		double dY = y - this.y;
		return Math.sqrt(dX * dX + dY * dY);
	}

}
