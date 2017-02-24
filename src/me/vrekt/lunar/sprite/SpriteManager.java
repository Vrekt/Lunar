package me.vrekt.lunar.sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.vrekt.lunar.world.dir.Direction;

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
	 * Returns an array of Images for animations. Using this would be perfect
	 * for getting multiple sequences of images for animations.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param direction
	 * @param spriteCount
	 * @return
	 */
	public BufferedImage[] getMultipleSprites(int x, int y, int width, int height, Direction direction,
			int spriteCount) {
		BufferedImage[] frames = new BufferedImage[spriteCount];

		int frameCount = 0;

		while (frameCount < spriteCount) {

			switch (direction) {
			case UP:
				frames[frameCount] = getSectionAt(x, y, width, height);
				frameCount++;
				y -= height;
				break;
			case DOWN:
				frames[frameCount] = getSectionAt(x, y, width, height);
				frameCount++;
				y += height;
				break;
			case LEFT:
				frames[frameCount] = getSectionAt(x, y, width, height);
				frameCount++;
				x -= width;
				break;
			case RIGHT:
				frames[frameCount] = getSectionAt(x, y, width, height);
				frameCount++;
				x += width;
				break;
			}

		}

		return frames;

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
