package com.lunar.world;

import com.lunar.entity.Entity;
import com.lunar.location.Location;
import com.lunar.tile.Tile;
import com.lunar.utilities.Logger;
import com.lunar.world.dir.Direction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vrekt on 11/14/2017.
 */
public abstract class WorldMap {

    protected final Map<Location, Tile> TILE_MAP = new HashMap<>();

    /**
     * Add a tile.
     *
     * @param x    the x
     * @param y    the y
     * @param tile the tile.
     */
    public final void set(int x, int y, Tile tile) {
        TILE_MAP.put(new Location(x, y), tile);
    }

    /**
     * Add a tile.
     *
     * @param tile the tile.
     */
    public final void set(Tile tile) {
        set(tile.getX(), tile.getY(), tile);
    }

    /**
     * Add multiple tiles in one operation.
     *
     * @param tile      the tile
     * @param startX    the starting X point.
     * @param startY    the starting Y point.
     * @param direction which way to add the tiles.
     * @param amount    the amount of times to add the tile.
     * @param threaded  if you want to run the operation on a separate thread.
     */
    public final void setMultiple(Tile tile, int startX, int startY, Direction direction, int amount, boolean threaded) {
        if (threaded) {
            Thread thread = new Thread(() -> setMultiple(tile, startX, startY, direction, amount));
            thread.start();
        } else {
            setMultiple(tile, startX, startY, direction, amount);
        }
    }

    /**
     * Add multiple tiles in one operation.
     *
     * @param tile      the tile
     * @param startX    the starting X point.
     * @param startY    the starting Y point.
     * @param direction which way to add the tiles.
     * @param amount    the amount of times to add the tile.
     */
    private void setMultiple(Tile tile, int startX, int startY, Direction direction, int amount) {
        int iterations = 0;

        int width = tile.getWidth();
        int height = tile.getHeight();

        while (iterations < amount) {
            iterations++;
            tile.setY(startY);
            tile.setX(startX);
            set(tile);

            startX = direction == Direction.RIGHT ? startX + width : direction == Direction.LEFT ? startX - width : startX;
            startY = direction == Direction.DOWN ? startY + height : direction == Direction.UP ? startY - height : startY;

        }

        Logger.logInformation("Finished batch tile add operation.");
    }

    /**
     * Remove a tile.
     *
     * @param x the x
     * @param y the y
     */
    public final void remove(int x, int y) {
        Location location = new Location(x, y);
        if (TILE_MAP.containsKey(location)) {
            TILE_MAP.remove(location, TILE_MAP.get(location));
        }
    }

    /**
     * Remove multiple tiles in one operation.
     *
     * @param tile      the tile
     * @param startX    the starting X point.
     * @param startY    the starting Y point.
     * @param direction which way to add the tiles.
     * @param amount    the amount of times to add the tile.
     * @param threaded  if you want to run the operation on a separate thread.
     */
    public final void removeMultiple(Tile tile, int startX, int startY, Direction direction, int amount, boolean threaded) {
        if (threaded) {
            Thread thread = new Thread(() -> removeMultiple(tile, startX, startY, direction, amount));
            thread.start();
        } else {
            removeMultiple(tile, startX, startY, direction, amount);
        }
    }

    /**
     * Remove multiple tiles in one operation.
     *
     * @param tile      the tile
     * @param startX    the starting X point.
     * @param startY    the starting Y point.
     * @param direction which way to add the tiles.
     * @param amount    the amount of times to add the tile.
     */
    private void removeMultiple(Tile tile, int startX, int startY, Direction direction, int amount) {
        int iterations = 0;

        int width = tile.getWidth();
        int height = tile.getHeight();

        while (iterations <= amount) {
            iterations++;
            remove(startX, startY);

            startX = direction == Direction.RIGHT ? startX + width : direction == Direction.LEFT ? startX - width : startX;
            startY = direction == Direction.DOWN ? startY + height : direction == Direction.UP ? startY - height : startY;

        }

        Logger.logInformation("Finished batch tile remove operation.");
    }

    /**
     * @param x the x
     * @param y the y
     * @return the tile at the X and Y (can be null).
     */
    public final Tile get(int x, int y) {
        Location location = new Location(x, y);
        return TILE_MAP.getOrDefault(location, null);
    }

    /**
     * @param entity the entity
     * @return the tile the entity is on (can be null).
     */
    public final Tile getTileEntityIsOn(Entity entity) {
        return get(entity.getX(), entity.getY());
    }

}
