package me.vrekt.lunar.entity;

import java.awt.Graphics;

import me.vrekt.lunar.collision.BoundingBox;

public abstract class Entity {

	protected int x, y, width, height;
	protected int entityID;

	protected BoundingBox boundingBox;

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

		boundingBox = new BoundingBox(Math.min(x, y), Math.min(x, y), Math.max(x, y), Math.max(x, y));

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

		boundingBox = bb;

	}

	public abstract void drawEntity(Graphics graphics);

	public abstract void updateEntity();

}
