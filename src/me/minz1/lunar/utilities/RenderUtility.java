package me.minz1.lunar.utilities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.minz1.lunar.vec.Vector2f;

public class RenderUtility {

	/**
	 * Draw the texture.
	 * 
	 * @param graphics
	 * @param image
	 * @param x
	 * @param y
	 */
	public static void drawTexture(Graphics graphics, BufferedImage image, int x, int y) {
		graphics.drawImage(image, x, y, null);
	}

	/**
	 * Draw the texture.
	 * 
	 * @param graphics
	 * @param image
	 * @param location
	 */
	public static void drawTexture(Graphics graphics, BufferedImage image, Vector2f location) {
		graphics.drawImage(image, location.getX(), location.getY(), null);
	}

}
