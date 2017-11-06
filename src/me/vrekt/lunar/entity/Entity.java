package me.vrekt.lunar.entity;

import me.vrekt.lunar.collision.BoundingBox;
import me.vrekt.lunar.location.Location;
import me.vrekt.lunar.tile.Tile;
import me.vrekt.lunar.utilities.MathHelper;
import me.vrekt.lunar.world.World;
import me.vrekt.lunar.world.dir.Direction;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity {

    protected Location location;
    protected int x, y;
    protected int width, height;
    protected int entityID;

    protected BoundingBox boundingBox;
    protected BufferedImage texture;

    /**
     * Initialize the entity; this is the primary constructor.
     *
     * @param x        The x position of this entity.
     * @param y        The y position of this entity.
     * @param width    The width of this entity.
     * @param height   The height of this entity.
     * @param entityID A number uniquely identifiying this enemy.
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
     * Convenience constructor that allows you to
     * specify a BoundingBox to use with this entity.
     *
     * @param x        The x position of this entity.
     * @param y        The y position of this entity.
     * @param width    The width of this entity.
     * @param height   The height of this entity.
     * @param entityID A number uniquely identifying this enemy.
     * @param bb       The collision box for this entity.
     */
    public Entity(int x, int y, int width, int height, int entityID, BoundingBox bb) {
        this(x, y, width, height, entityID);
        boundingBox = bb;

    }

    /**
     * Convenience constructor that allows you to provide a
     * BufferedImage to use as a texture for this entity.
     *
     * @param texture  The image to use as a texture for this entity.
     * @param x        The x position of this entity.
     * @param y        The y position of this entity.
     * @param width    The width of this entity.
     * @param height   The height of this entity.
     * @param entityID A number uniquely identifying this enemy.
     */
    public Entity(BufferedImage texture, int x, int y, int width, int height, int entityID) {
        this(x, y, width, height, entityID);

        this.texture = texture;

    }

    /**
     * Convenience constructor that allows you to supply both a BufferedImage
     * and a BoundingBox.
     *
     * @param texture  The image to use as a texture for this entity.
     * @param x        The x position of this entity.
     * @param y        The y position of this entity.
     * @param width    The width of this entity.
     * @param height   The height of this entity.
     * @param entityID A number uniquely identifying this enemy.
     * @param bb       The collision box for this entity.
     */
    public Entity(BufferedImage texture, int x, int y, int width, int height, int entityID, BoundingBox bb) {
        this(x, y, width, height, entityID, bb);

        this.texture = texture;

    }

    /**
     * Draw the entity's visual representation here
     *
     * @param graphics A Graphics object for this method to draw to. It
     *                 is the responsibility of the caller to make sure the Graphics object
     *                 passed in is displayed on the screen.
     */
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
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Get the X
     */
    public int getX() {
        return x;
    }

    /**
     * Get the Y
     */
    public int getY() {
        return y;
    }

    /**
     * Set x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get the width of the entity.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the entity.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the entityID
     */
    public int getEntityID() {
        return entityID;
    }

    /**
     * Get the boundingBox
     */
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    /**
     * Get the texture.
     */
    public BufferedImage getTexture() {
        return texture;
    }

    /**
     * Set the texture
     */
    public void setTexture(BufferedImage newTexture) {
        texture = newTexture;
    }

    /**
     * Get the entities line of sight.
     *
     * @return List of tiles representing what the entity can "see".
     */
    public List<Tile> getLineOfSight(World world, Direction facing, int distance) {

        int roundedX = 0, roundedY = 0;
        Tile reference;

        List<Tile> list = new ArrayList<>();

        while (distance != 0) {

            roundedX = facing == Direction.RIGHT ? roundedX + width
                    : facing == Direction.LEFT ? roundedX - width : roundedX;
            roundedY = facing == Direction.DOWN ? roundedY + height
                    : facing == Direction.UP ? roundedY - height : roundedY;
            distance--;

            roundedX = MathHelper.roundToDimensions(roundedX, width);
            roundedY = MathHelper.roundToDimensions(roundedY, height);

            reference = world.getTileAt(roundedX, roundedY);
            if (reference == null) {
                continue;
            }

            list.add(reference);

        }
        return list;
    }

}
