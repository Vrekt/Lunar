package me.vrekt.lunar.utilities;

public class Utilities {
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
	    return Math.sqrt(dx*dx + dy*dy);
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
}
