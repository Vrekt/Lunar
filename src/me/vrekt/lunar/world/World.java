package me.vrekt.lunar.world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import me.vrekt.lunar.entity.Entity;
import me.vrekt.lunar.tile.DrawableTile;
import me.vrekt.lunar.tile.Tile;

public abstract class World {

	protected List<Entity> worldEntities = new ArrayList<Entity>();
	protected List<DrawableTile> worldTiles = new ArrayList<DrawableTile>();
	protected String name;

	protected int width, height;

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
	 * Add a tile
	 */
	public void addTile(Tile tile, int x, int y) {
		worldTiles.add(new DrawableTile(x, y, tile));
	}

	/**
	 * Get an entity by ID.
	 */
	public Entity getByID(int EID) {
		Optional<Entity> stream = worldEntities.stream().filter(entity -> entity.entityID == EID).findAny();
		return stream.isPresent() ? stream.get() : null;
	}

	/**
	 * Draw all world entities
	 */
	public void drawAllEntities(Graphics graphics) {
		graphics.setColor(Color.red);
		worldEntities.stream().forEach(entity -> graphics.fillRect(entity.x, entity.y, entity.width, entity.height));
	}

	/**
	 * Draw all tiles.
	 * 
	 * @param graphics
	 */
	public void drawAllTiles(Graphics graphics) {
		for (DrawableTile t : worldTiles) {
			graphics.drawImage(t.getTile().getTexture(), t.getX(), t.getY(), null);
		}
		// worldTiles.forEach(tile -> tile.getTile().drawTile(graphics,
		// tile.getX(), tile.getY()));
	}

	/**
	 * @return the worldEntities
	 */
	public List<Entity> getWorldEntities() {
		return worldEntities;
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
