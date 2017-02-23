package me.vrekt.lunar.tile;

public class DrawableTile {

	private int x, y;
	private Tile tile;

	/**
	 * Initialize the drawabletile.
	 * 
	 * @param x
	 * @param y
	 * @param tile
	 */
	public DrawableTile(int x, int y, Tile tile) {
		this.x = x;
		this.y = y;

		this.tile = tile;
	}

	/**
	 * Get the X coordinate.
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the Y coordinate.
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Get the tile.
	 * 
	 * @return
	 */
	public Tile getTile() {
		return tile;
	}

}
