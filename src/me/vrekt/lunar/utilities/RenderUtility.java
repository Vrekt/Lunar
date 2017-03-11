package me.vrekt.lunar.utilities;

import me.vrekt.lunar.vec.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderUtility {
	/**
	 * Draw the texture.
	 */
	public static void drawTexture(Graphics graphics, BufferedImage image, int x, int y) {
		graphics.drawImage(image, x, y, null);
	}

	/**
	 * Draw the texture.
	 */
	public static void drawTexture(Graphics graphics, BufferedImage image, Vector2f location) {
		graphics.drawImage(image, location.getX(), location.getY(), null);
	}
}
