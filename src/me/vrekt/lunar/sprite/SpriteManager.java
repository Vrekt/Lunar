package me.vrekt.lunar.sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import me.vrekt.lunar.world.dir.Direction;

public class SpriteManager {

	private List<SpriteSheet> spriteSheets = new ArrayList<>();
	private BufferedImage spriteSheet;

	/**
	 * Initialize the spriteManager.
	 * 
	 * @param spriteSheet
	 */
	public SpriteManager(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	/**
	 * Initializes the spriteManager with a SpriteSheet.
	 * 
	 * @param sheet
	 */
	public SpriteManager(SpriteSheet sheet) {
		spriteSheets.add(sheet);
	}

	/**
	 * Initializes the spriteManager with an array of SpriteSheets.
	 * 
	 * @param sheets
	 */
	public SpriteManager(SpriteSheet[] sheets) {
		List<SpriteSheet> list = Arrays.asList(sheets);
		list.forEach(sheet -> spriteSheets.add(sheet));
	}

	/**
	 * Initializes the spriteManager with a list of SpriteSheets.
	 * 
	 * @param sheets
	 */
	public SpriteManager(List<SpriteSheet> sheets) {
		sheets.forEach(sheet -> spriteSheets.add(sheet));
		sheets.clear();
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
	 * Get a spriteSheet via ID.
	 * 
	 * @param ID
	 * @return
	 */
	public SpriteSheet getSheet(int ID) {
		Optional<SpriteSheet> stream = spriteSheets.stream().filter(sheet -> sheet.getID() == ID).findAny();
		return stream.isPresent() ? stream.get() : null;
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
	 * Return a section of the image.
	 * 
	 * @param image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage getSectionAt(BufferedImage image, int x, int y, int width, int height) {
		return image.getSubimage(x, y, width, height);
	}

	/**
	 * Return an image from one of the spriteSheets in the list.
	 * 
	 * @param ID
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public BufferedImage getSectionAt(int ID, int x, int y, int width, int height) {
		BufferedImage i = getSheet(ID).getSheet();
		return i.getSubimage(x, y, width, height);
	}

	/**
	 * Returns an array of Images.
	 * 
	 * @param ID
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param direction
	 * @param spriteCount
	 * @return
	 */
	public BufferedImage[] getMultipleSprites(int ID, int x, int y, int width, int height, Direction direction,
			int spriteCount) {
		BufferedImage i = getSheet(ID).getSheet();

		BufferedImage[] frames = new BufferedImage[spriteCount];

		int frameCount = 0;

		while (frameCount < spriteCount) {

			frames[frameCount] = getSectionAt(i, x, y, width, height);
			x = direction == Direction.RIGHT ? x + width : direction == Direction.LEFT ? x - width : x;
			y = direction == Direction.DOWN ? y + height : direction == Direction.UP ? y - height : y;
			frameCount++;

		}

		return frames;

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

			frames[frameCount] = getSectionAt(x, y, width, height);
			x = direction == Direction.RIGHT ? x + width : direction == Direction.LEFT ? x - width : x;
			y = direction == Direction.DOWN ? y + height : direction == Direction.UP ? y - height : y;
			frameCount++;

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
