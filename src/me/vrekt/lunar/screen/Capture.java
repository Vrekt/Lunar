package me.vrekt.lunar.screen;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Capture {

	/**
	 * Return the screenshot.
	 * 
	 * @param width
	 * @param height
	 * @return
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
	 * 
	 * @param width
	 * @param height
	 * @param saveTo
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
