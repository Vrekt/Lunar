package com.lunar.asset;

import com.lunar.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class AssetManager {

	private final List<Tile> TILES = new ArrayList<>();

	/**
	 * Add a tile.
	 */
	public void addTile(Tile tile) {
		TILES.add(tile);
	}

	/**
	 * Remove the tile.
	 */
	public void removeTile(Tile tile) {
		TILES.remove(tile);
	}

	/**
	 * @param ID the tile ID.
	 * @return the Tile with the ID.
	 */
	public Tile getByID(int ID) {
		return TILES.stream().filter(tile -> tile.getID() == ID).findAny().orElse(null);
	}

	/**
	 * @return list of all TILES.
	 */
	public List<Tile> getTILES() {
		return TILES;
	}
}
