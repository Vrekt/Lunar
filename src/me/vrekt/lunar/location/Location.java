package me.vrekt.lunar.location;

import me.vrekt.lunar.world.World;

public class Location {

	private int x, y;
	private boolean onGround;

	private World world;

	public Location(int x, int y, boolean onGround) {
		this.x = x;
		this.y = y;

		this.onGround = onGround;

	}

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Location(int x, int y, World world) {
		this.x = x;
		this.y = y;

		this.world = world;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y The y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Return if we are on ground or not.
	 */
	public boolean isOnGround() {
		return onGround;
	}

	/**
	 * Set if we are on ground or not.
	 */
	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

	/**
	 * Get the world.
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Set the world.
	 */
	public void setWorld(World world) {
		this.world = world;
	}

	/**
	 * Clone this location.
	 */
	public Location clone() {
		return new Location(x, y);
	}

	/**
	 * Get the distance between two points.
	 */
	public double distance(Location location) {
		double dX = location.getX() - x;
		double dY = location.getY() - y;
		return Math.sqrt(dX * dX + dY * dY);
	}

	/**
	 * Get the distance between two points.
	 */
	public double distance(int x, int y) {
		double dX = x - this.x;
		double dY = y - this.y;
		return Math.sqrt(dX * dX + dY * dY);
	}

	/**
	 * Add to this location.
	 */
	public Location add(Location other) {
		return new Location(x + other.getX(), y + other.getY());
	}

	/**
	 * Subtract this location by another.
	 */
	public Location subtract(Location other) {
		return new Location(x - other.getX(), y - other.getY());
	}

	/**
	 * Multiply this location by another.
	 */
	public Location multiply(Location other) {
		return new Location(x * other.getX(), y * other.getY());
	}

	/**
	 * Divide this location by another.
	 */
	public Location divide(Location other) {
		return new Location(x / other.getX(), y / other.getY());
	}
}
