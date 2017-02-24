package me.vrekt.lunar.raytrace;

import me.vrekt.lunar.location.Location;
import me.vrekt.lunar.tile.Tile;

public class TileInfo {

	private Location location;
	private Tile tile;

	public TileInfo(Location location, Tile tile) {
		this.location = location;
		this.tile = tile;
	}

	/**
	 * Get the location.
	 * 
	 * @return
	 */
	public Location getLocation() {
		return location;
	}

	/***
	 * Get the tile.
	 * 
	 * @return
	 */
	public Tile getTile() {
		return tile;
	}

}
