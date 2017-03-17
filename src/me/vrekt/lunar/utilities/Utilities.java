package me.vrekt.lunar.utilities;

public class Utilities {
	/**
	 * Round.
	 */
	public static int roundToDimensions(int value, int nearestValue) {
		return Math.round(value / nearestValue) * nearestValue;
	}

	public static double distance(int x, int y, int xx, int yy) {
        double dx = xx - x;
        double dy = yy - y;
	    return Math.sqrt(dx*dx + dy*dy);
    }
}
