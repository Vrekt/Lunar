package me.vrekt.lunar.entity;

import java.awt.Graphics;

import me.vrekt.lunar.collision.BoundingBox;
import me.vrekt.lunar.location.Location;

public abstract class Entity {

	public Location location;
	public int x, y, z;
	public int width, height;
	public int entityID;

	public BoundingBox boundingBox;

	/**
	 * Initialize the entity.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param entityID
	 */
	public Entity(int x, int y, int width, int height, int entityID) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;
		this.entityID = entityID;
		this.location = new Location(x, y);

		boundingBox = new BoundingBox(x, y, width, height);

	}

	/**
	 * Initialize the entity.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param entityID
	 * @param bb
	 */
	public Entity(int x, int y, int width, int height, int entityID, BoundingBox bb) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;
		this.entityID = entityID;
		this.location = new Location(x, y);

		boundingBox = bb;

	}

	public abstract void drawEntity(Graphics graphics);

	public abstract void updateEntity();

	public void updateBoundingBox() {
		boundingBox.update(x, y, width, height);
	}

}
