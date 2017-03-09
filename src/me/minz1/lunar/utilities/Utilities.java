package me.minz1.lunar.utilities;

public class Utilities {

	/**
	 * Round.
	 * 
	 * @param value
	 * @param f
	 * @return
	 */
	public static int roundToDimensions(int value, int f) {
		return Math.round(value / f) * f;
	}

}
