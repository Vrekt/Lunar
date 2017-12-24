package com.lunar.tile;

import com.lunar.collision.BoundingBox;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage texture;
    private int width, height, x, y;
    private int ID;

    private TileProperties properties;


    /**
     * Initialize this tile.
     *
     * @param texture the texture of the tile.
     * @param ID      the tiles unique ID.
     */
    public Tile(BufferedImage texture, int ID) {
        this.texture = texture;

        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.ID = ID;
    }


    /**
     * Initialize this tile.
     *
     * @param texture    the texture of this tile.
     * @param ID         the tiles unique ID.
     * @param properties the properties of the tile.
     */
    public Tile(BufferedImage texture, int ID, TileProperties properties) {
        this.texture = texture;

        this.width = texture.getWidth();
        this.height = texture.getHeight();

        this.properties = properties;
        this.ID = ID;
    }

    /**
     * Initialize this tile.
     *
     * @param texture    the texture of this tile.
     * @param ID         the tiles unique ID.
     * @param width      the width of the tile.
     * @param height     the height of the tile.
     * @param x          the X coordinate of the tile.
     * @param y          the Y coordinate of the tile.
     * @param properties the properties of the tile.
     */
    public Tile(BufferedImage texture, int ID, int width, int height, int x, int y, TileProperties properties) {
        this(texture, ID, properties);

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
    }

    /**
     * Initialize this tile.
     *
     * @param texture    the texture of this tile.
     * @param ID         the tiles unique ID.
     * @param x          the X coordinate of the tile.
     * @param y          the Y coordinate of the tile.
     * @param properties the properties of the tile.
     */
    public Tile(BufferedImage texture, int ID, int x, int y, TileProperties properties) {
        this(texture, ID, properties);

        this.x = x;
        this.y = y;
    }

    /**
     * @return the tiles texture.
     */
    public BufferedImage getTexture() {
        return texture;
    }

    /**
     * @return the tiles width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the tiles height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the tiles X coordinate.
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
     * @return the tiles Y coordinate.
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
     * @return the tiles unique ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * @return if this tile is solid or not.
     */
    public boolean isSolid() {
        return properties.isSolid;
    }

    /**
     * Set if the tile is solid or not.
     */
    public void setSolid(boolean isSolid) {
        properties.setSolid(isSolid);
    }

    /**
     * @return if the tile is visible or not.
     */
    public boolean isVisible() {
        return properties.isVisible;
    }

    /**
     * Set if the tile is visible or not.
     */
    public void setVisible(boolean visible) {
        properties.setVisible(visible);
    }

    /**
     * @return if this tile is passableor not.
     */
    public boolean isPassable() {
        return properties.isPassable;
    }

    /**
     * Set if this tile is passable or not.
     */
    public void setPassable(boolean passable) {
        properties.setPassable(passable);
    }

    public TileProperties getProperties() {
        return properties;
    }

    public void setProperties(TileProperties properties) {
        this.properties = properties;
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

    public static class TileProperties {
        /**
         * The tile properties
         * TODO: Add more
         **/
        private boolean isVisible, isSolid, isPassable;

        public TileProperties(boolean isVisible) {
            this.isVisible = isVisible;
        }

        public TileProperties(boolean isVisible, boolean isSolid) {
            this(isVisible);
            this.isSolid = isSolid;
        }

        public TileProperties(boolean isVisible, boolean isSolid, boolean isPassable) {
            this(isVisible, isSolid);
            this.isPassable = isPassable;
        }

        public boolean isVisible() {
            return isVisible;
        }

        public boolean isSolid() {
            return isSolid;
        }

        public boolean isPassable() {
            return isPassable;
        }

        public void setVisible(boolean visible) {
            isVisible = visible;
        }

        public void setSolid(boolean solid) {
            isSolid = solid;
        }

        public void setPassable(boolean passable) {
            isPassable = passable;
        }
    }

}
