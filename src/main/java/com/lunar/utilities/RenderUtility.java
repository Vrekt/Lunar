package com.lunar.utilities;

import com.lunar.location.Location;

import java.awt.Graphics;
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
    public static void drawTexture(Graphics graphics, BufferedImage image, Location location) {
        graphics.drawImage(image, location.getX(), location.getY(), null);
    }
}
