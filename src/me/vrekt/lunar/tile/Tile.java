package me.vrekt.lunar.tile;

import me.vrekt.lunar.collision.BoundingBox;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage texture;
    private int width, height;

    private boolean isVisible = true;
    private boolean isSolid = false;
    private int ID;

    private int x, y;

    /**
     * Initialize the tile.
     */
    public Tile(BufferedImage texture, int ID) {
        this.texture = texture;

        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.ID = ID;
    }

    /**
     * Initialize the tile.
     */
    public Tile(BufferedImage texture, int ID, boolean isSolid) {
        this.texture = texture;

        this.width = texture.getWidth();
        this.height = texture.getHeight();

        this.isSolid = isSolid;
        this.ID = ID;
    }

    /**
     * Initialize the tile.
     */
    public Tile(BufferedImage texture, int ID, boolean isSolid, boolean isVisible) {
        this.texture = texture;

        this.width = texture.getWidth();
        this.height = texture.getHeight();

        this.isVisible = isVisible;
        this.isSolid = isSolid;
        this.ID = ID;
    }

    /**
     * Initialize the tile.
     */
    public Tile(BufferedImage texture, int ID, int width, int height, boolean isSolid, boolean isVisible) {
        this(texture, ID, isSolid, isVisible);

        this.width = width;
        this.height = height;

    }

    /**
     * Initialize the Tile.
     */
    public Tile(BufferedImage texture, int ID, int width, int height, int x, int y, boolean isSolid, boolean isVisible) {
        this(texture, ID, width, height, isSolid, isVisible);

        this.x = x;
        this.y = y;

    }

    /**
     * Initialize the tile.
     */
    public Tile(BufferedImage texture, int x, int y, boolean isSolid, boolean isVisible, int ID) {
        this(texture, ID, isSolid, isVisible);

        this.x = x;
        this.y = y;

    }

    /**
     * Get the texture.
     */
    public BufferedImage getTexture() {
        return texture;
    }

    /**
     * Get the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * Return if the tile is solid.
     */
    public boolean isSolid() {
        return isSolid;
    }

    /**
     * Set if the tile is solid or not.
     */
    public void setSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }

    /**
     * Get the X.
     */
    public int getX() {
        return x;
    }

    /**
     * Set the X.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Set the Y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return if the tile is visible or not.
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Set if the tile is visible or not.
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * Draw the tile.
     */
    public void drawTile(Graphics graphics, int x, int y) {
        graphics.drawImage(texture, x, y, null);
    }

    /**
     * Create a boundingBox that represents this tile.
     */
    public BoundingBox createBounds(int cX, int cY) {
        return new BoundingBox(cX, cY, width, height);
    }

    /**
     * Create a boundingBox that represents this tile.
     */
    public BoundingBox createBounds() {
        return new BoundingBox(x, y, width, height);
    }


}
