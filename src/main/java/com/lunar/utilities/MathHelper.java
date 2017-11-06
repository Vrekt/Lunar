package com.lunar.utilities;

import com.lunar.location.Location;

/**
 * Created by Vrekt on 10/17/2017.
 */
public class MathHelper {

    /**
     * Round.
     */
    public static int roundToDimensions(int value, int nearestValue) {
        return Math.round(value / nearestValue) * nearestValue;
    }

    /**
     * Calculate the Pythagorean distance from (x, y) to (xx, yy)
     */
    public static double distance(double x, double y, double xx, double yy) {
        double dx = xx - x;
        double dy = yy - y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Calculate the Manhattan distance from (x, y) to (xx, yy). Useful for tile based movement.
     */
    public static double manhattanDistance(double x, double y, double xx, double yy) {
        return Math.abs((x - xx) + (y - yy));
    }

    /**
     * Clamp a value between a lower and an upper bound
     */
    public static double clamp(double value, double lower, double upper) {
        return Math.max(Math.min(value, upper), lower);
    }

    /**
     * Linearly interpolate between (xi, yi) and (xf, yf) at time t. t should be incremented, and
     * should satisfy 0 <= t <= 1.
     */
    public static Location lerp(int xi, int yi, int xf, int yf, double t) {
        t = t % 1.0;
        return new Location(xi + (int) ((xf - xi) * t), yi + (int) ((yf - yi) * t));
    }

    /**
     * Linearly interpolate between (xi, yi) and (xf, yf) at time t. t should be incremented, and
     * should satisfy 0 <= t <= 1.
     */
    public static Location lerp(Location a, Location b, double t) {
        t = t % 1.0;
        return new Location(a.getX() + (int) ((b.getX() - a.getX()) * t), a.getY() + (int) ((b.getY() - a.getY()) * t));
    }

    /**
     * Linearly interpolate between a and b at time t. t should be incremented, and
     * should be satisfy 0 <= t <= 1.
     */
    public static double lerp(double a, double b, double t) {
        t = t % 1.0;
        return a + (b - a) * t;
    }

}
