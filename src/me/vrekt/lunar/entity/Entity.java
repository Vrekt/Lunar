package me.vrekt.lunar.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import me.vrekt.lunar.collision.BoundingBox;
import me.vrekt.lunar.location.Location;
import me.vrekt.lunar.tile.Tile;
import me.vrekt.lunar.utilities.Utilities;
import me.vrekt.lunar.world.World;
import me.vrekt.lunar.world.dir.Direction;

public abstract class Entity {

	protected Location location;
	protected int x, y;
	protected int width, height;
	protected int entityID;

	protected BoundingBox boundingBox;
	protected BufferedImage texture;

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

	/**
	 * Initialize the entity.
	 * 
	 * @param sprite
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param entityID
	 */
	public Entity(BufferedImage texture, int x, int y, int width, int height, int entityID) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;
		this.entityID = entityID;
		this.location = new Location(x, y);

		this.texture = texture;

		boundingBox = new BoundingBox(x, y, width, height);

	}

	/**
	 * Initialize the entity.
	 * 
	 * @param sprite
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param entityID
	 * @param bb
	 */
	public Entity(BufferedImage texture, int x, int y, int width, int height, int entityID, BoundingBox bb) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;
		this.entityID = entityID;
		this.location = new Location(x, y);

		this.texture = texture;

		boundingBox = bb;

	}

	public abstract void drawEntity(Graphics graphics);

	public abstract void updateEntity();

	/**
	 * Update the entity boundingBox.
	 */
	public void updateBoundingBox() {
		boundingBox.update(x, y, width, height);
	}

	/**
	 * Get the location.
	 * 
	 * @return location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Get the X
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the Y
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Get the width of the entity.
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the height of the entity.
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Get the entityID
	 * 
	 * @return
	 */
	public int getEntityID() {
		return entityID;
	}

	/**
	 * Get the boundingBox
	 * 
	 * @return
	 */
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	/**
	 * Get the texture.
	 * 
	 * @return
	 */
	public BufferedImage getTexture() {
		return texture;
	}

	/**
	 * Get the tiles in the entity line of sight.
	 * 
	 * @param facing
	 * @param direction
	 * @return
	 */
	public List<Tile> getLineOfSight(World world, Direction facing, int distance) {

		int roundedX = 0, roundedY = 0;
		Tile reference = null;

		List<Tile> list = new ArrayList<Tile>();

		while (distance != 0) {

			roundedX = facing == Direction.RIGHT ? roundedX + width
					: facing == Direction.LEFT ? roundedX - width : roundedX;
			roundedY = facing == Direction.DOWN ? roundedY + height
					: facing == Direction.UP ? roundedY - height : roundedY;
			distance--;

			roundedX = Utilities.roundToDimensions(x, width);
			roundedY = Utilities.roundToDimensions(y, height);

			reference = world.getTileAt(roundedX, roundedY);
			if (reference == null) {
				continue;
			}

			list.add(reference);

		}

		return list;

	}

}
