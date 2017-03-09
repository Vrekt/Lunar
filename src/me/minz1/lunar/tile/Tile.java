package me.minz1.lunar.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.minz1.lunar.collision.BoundingBox;

public class Tile {

	private BufferedImage texture;
	private int width, height;

	private boolean isSolid = false;
	private int ID;

	private int x, y;

	/**
	 * Initialize the tile.
	 * 
	 * @param ID
	 * @param texture
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
	 * 
	 * @param ID
	 * @param texture
	 * @param width
	 * @param height
	 */
	public Tile(BufferedImage texture, int ID, int width, int height, boolean isSolid) {
		this.texture = texture;

		this.width = width;
		this.height = height;

		this.isSolid = isSolid;
		this.ID = ID;

	}

	/**
	 * Initialize the Tile.
	 * 
	 * @param texture
	 * @param ID
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 * @param isSolid
	 */
	public Tile(BufferedImage texture, int ID, int width, int height, int x, int y, boolean isSolid) {
		this.texture = texture;

		this.width = width;
		this.height = height;

		this.x = x;
		this.y = y;

		this.isSolid = isSolid;
		this.ID = ID;
	}

	/**
	 * Initialize the tile.
	 * 
	 * 
	 * @param texture
	 * @param x
	 * @param y
	 * @param isSolid
	 * @param ID
	 */
	public Tile(BufferedImage texture, int x, int y, boolean isSolid, int ID) {
		this.texture = texture;

		this.width = texture.getWidth();
		this.height = texture.getHeight();

		this.x = x;
		this.y = y;

		this.isSolid = isSolid;
		this.ID = ID;
	}

	/**
	 * Get the texture.
	 * 
	 * @return texture
	 */
	public BufferedImage getTexture() {
		return texture;
	}

	/**
	 * Get the width
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the height.
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Get the ID.
	 * 
	 * @return
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Return if the tile is solid.
	 * 
	 * @return
	 */
	public boolean isSolid() {
		return isSolid;
	}

	/**
	 * Set if the tile is solid or not.
	 * 
	 * @param isSolid
	 */
	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	/**
	 * Get the X.
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the Y.
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the X.
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Set the Y.
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Draw the tile.
	 * 
	 * @param graphics
	 * @param x
	 * @param y
	 */
	public void drawTile(Graphics graphics, int x, int y) {
		graphics.drawImage(texture, x, y, null);
	}

	/**
	 * Create a boundingBox that represents this tile.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public BoundingBox createBounds(int cX, int cY) {
		return new BoundingBox(cX, cY, width, height);
	}

	/**
	 * Create a boundingBox that represents this tile.
	 * 
	 * @return
	 */
	public BoundingBox createBounds() {
		return new BoundingBox(x, y, width, height);
	}

}
