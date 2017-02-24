package me.vrekt.lunar.world;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import me.vrekt.lunar.entity.Entity;
import me.vrekt.lunar.location.Location;
import me.vrekt.lunar.tile.Tile;
import me.vrekt.lunar.world.dir.Direction;

public abstract class World {

	protected HashMap<Location, Tile> worldInfo = new HashMap<Location, Tile>();
	protected List<Entity> worldEntities = new ArrayList<Entity>();
	protected String name;

	protected int width, height;

	public World(String name, int width, int height) {
		this.name = name;

		this.width = width;
		this.height = height;

	}

	public World(File file, ArrayList<Tile> tiles) {

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
	 * Add multiple tiles in one direction, easier for making worlds/maps.
	 * 
	 * @param x
	 * @param y
	 * @param direction
	 * @param multiplier
	 *            indicates how many tiles to draw in the direction.
	 */
	public void addBatchTiles(Tile tile, int x, int y, Direction direction, int multiplier) {
		int width = tile.getWidth();
		int height = tile.getHeight();

		while (multiplier > 0) {
			multiplier--;

			switch (direction) {
			case UP:
				worldInfo.put(new Location(x, y), tile);
				y -= height;
				break;
			case DOWN:
				worldInfo.put(new Location(x, y), tile);
				y += height;
				break;
			case LEFT:
				worldInfo.put(new Location(x, y), tile);
				x -= width;
				break;
			case RIGHT:
				worldInfo.put(new Location(x, y), tile);
				x += width;
				break;
			}

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
	public Entity getByID(int EID) {
		Optional<Entity> stream = worldEntities.stream().filter(entity -> entity.getEntityID() == EID).findAny();
		return stream.isPresent() ? stream.get() : null;
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
