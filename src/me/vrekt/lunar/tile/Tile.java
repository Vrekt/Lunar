package me.vrekt.lunar.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	private int ID;
	private BufferedImage texture;

	private int width, height;

	/**
	 * Initialize the tile.
	 * 
	 * @param ID
	 * @param texture
	 */
	public Tile(int ID, BufferedImage texture) {
		this.ID = ID;
		this.texture = texture;

		this.width = texture.getWidth();
		this.height = texture.getHeight();

	}

	/**
	 * Initialize the tile.
	 * 
	 * @param ID
	 * @param texture
	 * @param width
	 * @param height
	 */
	public Tile(int ID, BufferedImage texture, int width, int height) {
		this.ID = ID;
		this.texture = texture;

		this.width = width;
		this.height = height;

	}

	/**
	 * Get the ID
	 * 
	 * @return ID
	 */
	public int getID() {
		return ID;
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
	 * Draw the tile.
	 * 
	 * @param graphics
	 * @param x
	 * @param y
	 */
	public void drawTile(Graphics graphics, int x, int y) {
		graphics.drawImage(texture, x, y, null);
	}

}
