package me.vrekt.lunar.utilities;

public class Utilities {
	/**
	 * Round.
	 */
	public static int roundToDimensions(int value, int f) {
		return Math.round(value / f) * f;
	}
}
