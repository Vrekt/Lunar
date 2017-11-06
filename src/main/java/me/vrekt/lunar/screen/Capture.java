package me.vrekt.lunar.screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Capture {

    /**
     * @return Image of the screenshot.
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
    public static void screenshotAndSave(int width, int height, File saveTo, String imageType) {
        BufferedImage image = screenshot(width, height);
        try {
            ImageIO.write(image, imageType, saveTo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the screenshot.
     */
    public static void screenshotAndSave(int width, int height, String saveTo, String imageType) {
        screenshotAndSave(width, height, new File(saveTo), imageType);
    }

    /**
     * Take a screenshot from the X and Y.
     *
     * @return Image of the screenshot.
     */
    public static BufferedImage screenshotPart(int x, int y, int width, int height) {
        Rectangle r = new Rectangle(x, y, width, height);

        try {
            Robot robot = new Robot();
            return robot.createScreenCapture(r);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    }

    /**
     * Take a screenshot from the X and Y and save it.
     */
    public static void screenshotPartAndSave(int x, int y, int width, int height, File saveTo, String imageType) {
        BufferedImage image = screenshotPart(x, y, width, height);
        try {
            ImageIO.write(image, imageType, saveTo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Take a screenshot from the X and Y and save it.
     */
    public static void screenshotPartAndSave(int x, int y, int width, int height, String saveTo, String imageType) {
        screenshotPartAndSave(x, y, width, height, new File(saveTo), imageType);
    }

}
