package com.lunar.world;

import com.lunar.entity.Entity;
import com.lunar.location.Location;
import com.lunar.raycast.RayCast;
import com.lunar.tile.Tile;
import com.lunar.world.entity.MutableEntity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class World extends MapRenderer {
    protected final List<MutableEntity> ENTITY_ACTION_LIST = new ArrayList<>();
    protected final List<Entity> WORLD_ENTITIES = new ArrayList<>();

    protected String name;
    protected int width, height, tileWidth, tileHeight, worldAnchorX, worldAnchorY = 0;
    protected WorldGridRenderer gridRenderer;

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
        this(name, width, height);

        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;

        gridRenderer = new WorldGridRenderer(width, height, tileWidth, tileHeight);

    }

    /**
     * Gets the name of the world
     */
    public final String getName() {
        return name;
    }

    /**
     * Add an entity to the world.
     * NOTE: Do not use this method for adding entities! Queue them first.
     *
     * @param entity the entity.
     */
    public final void addEntity(Entity entity) {
        WORLD_ENTITIES.add(entity);
    }

    /**
     * Remove an entity from the world.
     * NOTE: Do not use this method for removing entities! Queue them first.
     *
     * @param entity the entity that should be removed from the world.
     */
    public final void removeEntity(Entity entity) {
        WORLD_ENTITIES.remove(entity);
    }

    /**
     * Queue an entity to be added.
     *
     * @param entity the entity.
     */
    public final void queueEntityForAdd(Entity entity) {
        MutableEntity mutableEntity = new MutableEntity(entity, MutableEntity.EntityAction.ADD);
        ENTITY_ACTION_LIST.add(mutableEntity);
    }

    /**
     * Queue an entity to be removed.
     *
     * @param entity the entity.
     */
    public final void queueEntityForRemoval(Entity entity) {
        MutableEntity mutableEntity = new MutableEntity(entity, MutableEntity.EntityAction.REMOVE);
        ENTITY_ACTION_LIST.add(mutableEntity);
    }

    public final void finishQueueActions() {
        // This logic could be moved to implementations of MutableEntity and use a
        // factory to create them.
        for (MutableEntity mut : ENTITY_ACTION_LIST) {
            switch (mut.getAction()) {
                case REMOVE:
                    WORLD_ENTITIES.remove(mut.getThisEntity());
                    break;
                case ADD:
                    WORLD_ENTITIES.add(mut.getThisEntity());
                    break;
            }
        }
        // clear the list, we're done.
        ENTITY_ACTION_LIST.clear();
    }

    /**
     * @param entityID the entityID.
     * @return the entity with the given ID.
     */
    public final Entity getEntity(int entityID) {
        return WORLD_ENTITIES.stream().filter(entity -> entity.getEntityID() == entityID).findAny().orElse(null);
    }

    /**
     * @param x the x
     * @param y the y
     * @return true if an entity is at the given x and y.
     */
    public final boolean isEntityAt(int x, int y) {
        return WORLD_ENTITIES.stream().anyMatch(entity -> entity.getX() == x && entity.getY() == y);
    }

    /**
     * @param x the x
     * @param y the y
     * @return the entity at the given x and y.
     */
    public final Entity getEntityAt(int x, int y) {
        return WORLD_ENTITIES.stream().filter(entity -> entity.getX() == x && entity.getY() == y).findAny().orElse(null);
    }

    /**
     * @return all the entities in the world.
     */
    public final List<Entity> getWorldEntities() {
        return WORLD_ENTITIES;
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
     * Get the tile width
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * Get the tile height
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * Perform a ray cast from (originX, originY) in the direction of (dirX, dirY) for the given distance.
     */
    public RayCast.RayCastResult rayCast(int x, int y, int dirX, int dirY, float distance) {
        return new RayCast(this).doRayCast(x, y, dirX, dirY, distance);
    }

    /**
     * Perform a ray cast from (originX, originY) to (targetX, targetY).
     */
    public RayCast.RayCastResult rayCast(int x, int y, int targetX, int targetY) {
        return new RayCast(this).doRayCast(x, y, targetX, targetY);
    }

    /**
     * Set the anchor point of the world, this is the top left most point in the world
     * as an offset from the top left point of the screen.
     */
    public void setWorldAnchorX(int worldAnchorX) {
        this.worldAnchorX = worldAnchorX;
    }

    /**
     * Set the anchor point of the world, this is the top left most point in the world
     * as an offset from the top left point of the screen.
     */
    public void setWorldAnchorY(int worldAnchorY) {
        this.worldAnchorY = worldAnchorY;
    }

    /**
     * Get the location of the x location of the top left most point in the world
     * as an offset from the top left x point of the world.
     */
    public int getWorldAnchorX() {
        return worldAnchorX;
    }

    /**
     * Get the location of the y location of the top left most point in the world
     * as an offset from the top left y point of the world.
     */
    public int getWorldAnchorY() {
        return worldAnchorY;
    }

    /**
     * Translate from screen space to world space and check if the tile that contains
     * the pixel at the given coordinate is passable.
     */
    public boolean isPointPassable(int pixelX, int pixelY) {
        int tileX = (pixelX - worldAnchorX) / tileWidth;
        int tileY = (pixelY - worldAnchorY) / tileHeight;
        if (tileX < 0 || tileX >= width
                || tileY < 0 || tileY >= height) {
            return false;
        }

        Tile tile = get(tileX, tileY);
        if (tile == null) {
            // No tile, return true?
            return true;
        }

        // Right now solidity is the only measure we have of "passability"
        return !tile.isSolid();
    }

    /**
     * Translate from world space to screen space
     */
    public Location worldToScreenLocation(Location worldLocation) {
        return worldToScreenLocation(worldLocation.getX(), worldLocation.getY());
    }

    /**
     * Translate from world space to screen space
     */
    public Location worldToScreenLocation(int worldX, int worldY) {
        return new Location(worldAnchorX + worldX * tileWidth, worldAnchorY + worldY * tileHeight);
    }

    /**
     * Translate from screen space to world space
     */
    public Location screenToWorldLocation(int pixelX, int pixelY) {
        return new Location((pixelX - worldAnchorX) / tileWidth, (pixelY - worldAnchorY) / tileHeight);
    }

    /**
     * Gets executed when you the world is drawn.
     */
    public abstract void onDraw(Graphics graphics);

    /**
     * Gets executed when the world ticks
     */
    public void onTick() {
        finishQueueActions();
    }
}
