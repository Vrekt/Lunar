package me.vrekt.lunar.world;

import me.vrekt.lunar.entity.Entity;
import me.vrekt.lunar.location.Location;
import me.vrekt.lunar.tile.Tile;
import me.vrekt.lunar.world.dir.Direction;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class World {
    protected final Map<Location, Tile> worldInfo = new HashMap<>();
    protected final List<Entity> worldEntities = new ArrayList<>();
    protected String name;

    protected int width, height, tileWidth, tileHeight;
    private WorldGrid grid;

    /**
     * Initialize the world.
     *
     * @param name   Name of the world
     * @param width  Width of the world
     * @param height Height of the world
     */
    public World(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    /**
     * Initialize the world. Use this constructor for grids.
     *
     * @param name       Name of the world
     * @param width      Width of the world
     * @param height     Height of the world
     * @param tileWidth  the width of the tiles.
     * @param tileHeight the height of the tiles.
     */
    public World(String name, int width, int height, int tileWidth, int tileHeight) {
        this.name = name;
        this.width = width;
        this.height = height;

        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;

        this.grid = new WorldGrid(width, height, tileWidth, tileHeight);

    }

    /**
     * Initialize the world. Use this constructor for grids.
     *
     * @param name   Name of the world.
     * @param width  Width of the world.
     * @param height Height of the world.
     * @param grid   the WorldGrid
     */
    public World(String name, int width, int height, WorldGrid grid) {
        this.name = name;
        this.width = width;
        this.height = height;

        this.grid = grid;

    }

    /**
     * Gets the name of the world
     */
    public final String getName() {
        return name;
    }

    /**
     * Add an entity to the world.
     */
    public final void addEntity(Entity entity) {
        worldEntities.add(entity);
    }

    /**
     * Remove the entity from the world.
     *
     * @param entity the entity that should be removed from the world.
     */
    public final void removeEntity(Entity entity) {
        worldEntities.remove(entity);
    }

    /**
     * Add a tile
     */
    public final void addTile(int x, int y, Tile tile) {
        worldInfo.put(new Location(x, y), tile);
    }

    /**
     * Add a tile.
     */
    public final void addTile(Tile tile) {
        worldInfo.put(new Location(tile.getX(), tile.getY()), tile);
    }

    /**
     * Add multiple tiles in one direction, easier for making worlds/maps.
     *
     * @param x          coordinate of the tile
     * @param y          coordinate of the tile
     * @param direction  the direction to draw the tiles to
     * @param tileAmount indicates how many tiles to draw in the direction.
     */
    public final void addBatchTiles(Tile tile, int x, int y, Direction direction, int tileAmount) {
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
     */
    public final void removeTileAt(int x, int y) {
        Location loc = new Location(x, y);
        if (worldInfo.containsKey(loc)) {
            worldInfo.remove(loc);
        }
    }

    /**
     * Get an entity by ID.
     */
    public final Entity getEntity(int entityID) {
        return worldEntities.stream().filter(entity -> entity.getEntityID() == entityID).findAny().orElse(null);
    }

    /**
     * Get the tile the entity is standing on.
     */
    public final Tile getTileFromEntity(Entity entity) {
        return getTileAt(entity.getX(), entity.getY());
    }

    /**
     * Draw all world entities
     */
    public final void drawAllEntities(Graphics graphics) {
        worldEntities.forEach(entity -> graphics.drawImage(entity.getTexture(), entity.getX(), entity.getY(), null));
    }

    /**
     * Draw all tiles.
     */
    public final void drawAllTiles(Graphics graphics) {
        for (Location key : worldInfo.keySet()) {
            Tile tile = worldInfo.get(key);
            graphics.drawImage(tile.getTexture(), key.getX(), key.getY(), null);
        }
    }

    /**
     * Check if an entity is at this X and Y.
     */
    public final boolean isEntityAt(int x, int y) {
        return worldEntities.stream().anyMatch(entity -> entity.getX() == x && entity.getY() == y);
    }

    /**
     * Get the entity at the X and Y.
     */
    public final Entity getEntityAt(int x, int y) {
        return worldEntities.stream().filter(entity -> entity.getX() == x && entity.getY() == y).findAny().orElse(null);
    }

    /**
     * Gets a list of all entities in the world
     *
     * @return the entities in the world
     */
    public final List<Entity> getWorldEntities() {
        return worldEntities;
    }

    /**
     * Get the tile at the specified X and Y.
     */
    public final Tile getTileAt(int x, int y) {
        Optional<Location> stream = worldInfo.keySet().stream()
                .filter(location -> location.getX() == x && location.getY() == y).findAny();
        return stream.isPresent() ? worldInfo.get(stream.get()) : null;
    }

    /**
     * Returns the width of the world.
     */
    public final int getWidth() {
        return width;
    }

    /**
     * Returns the height of the world.
     */
    public final int getHeight() {
        return height;
    }

    /**
     * @return the world grid.
     */
    public WorldGrid getGrid() {
        return grid;
    }

    /**
     * Set the world grid.
     *
     * @param grid the world grid.
     */
    public void setGrid(WorldGrid grid) {
        this.grid = grid;
    }

    /**
     * Gets executed when you the world is drawn.
     */
    public abstract void onDraw(Graphics graphics);

    /**
     * Gets executed when the world ticks
     */
    public abstract void onTick();
}
