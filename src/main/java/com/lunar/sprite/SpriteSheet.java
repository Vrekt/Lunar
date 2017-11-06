package com.lunar.sprite;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;
	private int ID;

	/**
	 * Initialize the SpriteSheet
	 */
	public SpriteSheet(BufferedImage sheet, int ID) {
		this.sheet = sheet;
		this.ID = ID;
	}

	/**
	 * Get the sheet.
	 */
	public BufferedImage getSheet() {
		return sheet;
	}

	/**
	 * Get the ID.
	 */
	public int getID() {
		return ID;
	}

}
