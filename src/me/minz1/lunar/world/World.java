package me.minz1.lunar.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import me.minz1.lunar.entity.Entity;
import me.minz1.lunar.location.Location;
import me.minz1.lunar.tile.Tile;
import me.minz1.lunar.world.dir.Direction;

public abstract class World {

	protected HashMap<Location, Tile> worldInfo = new HashMap<Location, Tile>();
	protected List<Entity> worldEntities = new ArrayList<Entity>();
	protected String name;

	protected int width, height;

	/**
	 * Initialize the world.
	 * 
	 * @param name
	 * @param width
	 * @param height
	 */
	public World(String name, int width, int height) {
		this.name = name;

		this.width = width;
		this.height = height;

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Add an entity to the world.
	 */
	public void addEntity(Entity entity) {
		worldEntities.add(entity);
	}

	/**
	 * Remove the entity from the world.
	 * 
	 * @param entity
	 */
	public void removeEntity(Entity entity) {
		worldEntities.remove(entity);
	}

	/**
	 * Add a tile
	 */
	public void addTile(int x, int y, Tile tile) {
		worldInfo.put(new Location(x, y), tile);
	}

	/**
	 * Add a tile.
	 * 
	 * @param tile
	 */
	public void addTile(Tile tile) {
		worldInfo.put(new Location(tile.getX(), tile.getY()), tile);
	}

	/**
	 * Add multiple tiles in one direction, easier for making worlds/maps.
	 * 
	 * @param x
	 * @param y
	 * @param direction
	 * @param tileAmount
	 *            indicates how many tiles to draw in the direction.
	 */
	public void addBatchTiles(Tile tile, int x, int y, Direction direction, int tileAmount) {
		int width = tile.getWidth();
		int height = tile.getHeight();

		while (tileAmount > 0) {
			tileAmount--;

			worldInfo.put(new Location(x, y), tile);
			x = direction == Direction.RIGHT ? x + width : direction == Direction.LEFT ? x - width : x;
			y = direction == Direction.DOWN ? y + height : direction == Direction.UP ? y - height : y;

		}

	}

	/**
	 * Remove the tile.
	 * 
	 * @param x
	 * @param y
	 */
	public void removeTileAt(int x, int y) {
		Location loc = new Location(x, y);
		if (worldInfo.containsKey(loc)) {
			worldInfo.remove(loc);
		}
	}

	/**
	 * Get an entity by ID.
	 */
	public Entity getEntity(int EID) {
		Optional<Entity> stream = worldEntities.stream().filter(entity -> entity.getEntityID() == EID).findAny();
		return stream.isPresent() ? stream.get() : null;
	}

	/**
	 * Get the tile the entity is standing on.
	 * 
	 * @param entity
	 * @return
	 */
	public Tile getTileFromEntity(Entity entity) {
		return getTileAt(entity.getX(), entity.getY());
	}

	/**
	 * Draw all world entities
	 */
	public void drawAllEntities(Graphics graphics) {
		worldEntities.stream()
				.forEach(entity -> graphics.drawImage(entity.getTexture(), entity.getX(), entity.getY(), null));
	}

	/**
	 * Draw all tiles.
	 * 
	 * @param graphics
	 */
	public void drawAllTiles(Graphics graphics) {
		for (Location key : worldInfo.keySet()) {
			Tile tile = worldInfo.get(key);
			graphics.drawImage(tile.getTexture(), key.getX(), key.getY(), null);
		}
	}

	/**
	 * Check if an entity is at this X and Y.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isEntityAt(int x, int y) {
		return worldEntities.stream().filter(entity -> entity.getX() == x && entity.getY() == y).findAny().isPresent();
	}

	/**
	 * Get the entity at the X and Y.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Entity getEntityAt(int x, int y) {
		return worldEntities.stream().filter(entity -> entity.getX() == x && entity.getY() == y).findAny().get();
	}

	/**
	 * @return the worldEntities
	 */
	public List<Entity> getWorldEntities() {
		return worldEntities;
	}

	/**
	 * Return the tile at the specified X and Y.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Tile getTileAt(int x, int y) {
		Optional<Location> stream = worldInfo.keySet().stream()
				.filter(location -> location.getX() == x && location.getY() == y).findAny();
		return stream.isPresent() ? worldInfo.get(stream.get()) : null;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	public abstract void onDraw(Graphics graphics);

	public abstract void onTick();

}
