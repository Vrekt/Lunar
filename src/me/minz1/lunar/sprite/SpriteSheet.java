package me.minz1.lunar.sprite;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;
	private int ID;

	/**
	 * Initialize the SpriteSheet
	 * 
	 * @param sheet
	 * @param ID
	 */
	public SpriteSheet(BufferedImage sheet, int ID) {
		this.sheet = sheet;
		this.ID = ID;
	}

	/**
	 * Get the sheet.
	 * 
	 * @return
	 */
	public BufferedImage getSheet() {
		return sheet;
	}

	/**
	 * Get the ID.
	 * 
	 * @return
	 */
	public int getID() {
		return ID;
	}

}
