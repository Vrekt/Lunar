package me.vrekt.lunar.utilities;

public class Utilities {
	/**
	 * Round.
	 */
	public static int roundToDimensions(int value, int nearestValue) {
		return Math.round(value / nearestValue) * nearestValue;
	}
}
