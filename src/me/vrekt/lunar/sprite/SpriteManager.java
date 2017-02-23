package me.vrekt.lunar.sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteManager {

	private BufferedImage spriteSheet;

	/**
	 * Initialize
	 * 
	 * @param spriteSheet
	 */
	public SpriteManager(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	/**
	 * Get the sprite sheet.
	 * 
	 * @return
	 */
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	/**
	 * Return an image from the section selected.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public BufferedImage getSectionAt(int x, int y, int width, int height) {
		return spriteSheet.getSubimage(x, y, width, height);
	}

	/**
	 * Load the spriteSheet.
	 * 
	 * @param path
	 * @return
	 */
	public static BufferedImage load(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
