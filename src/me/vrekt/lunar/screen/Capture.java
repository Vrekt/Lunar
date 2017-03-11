package me.vrekt.lunar.screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Capture {

	/**
	 * Return the screenshot.
	 */
	public static BufferedImage screenshot(int width, int height) {
		Rectangle r = new Rectangle(0, 0, width, height);

		try {
			Robot robot = new Robot();
			return robot.createScreenCapture(r);
		} catch (AWTException e) {
			e.printStackTrace();
		}

		return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	}

	/**
	 * Save the screenshot.
	 */
	public static void screenshotAndSave(int width, int height, String saveTo, String imageType) {
		Rectangle r = new Rectangle(0, 0, width, height);

		try {
			Robot robot = new Robot();
			ImageIO.write(robot.createScreenCapture(r), imageType, new File(saveTo));
		} catch (AWTException | IOException e) {
			e.printStackTrace();
		}

	}
}
