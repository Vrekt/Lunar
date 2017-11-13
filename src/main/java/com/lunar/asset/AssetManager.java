package com.lunar.asset;

import com.lunar.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class AssetManager {

	private List<Tile> tiles = new ArrayList<>();

	/**
	 * Add a tile.
	 */
	public void addTile(Tile tile) {
		tiles.add(tile);
	}

	/**
	 * Remove the tile.
	 */
	public void removeTile(Tile tile) {
		tiles.remove(tile);
	}

	/**
	 * @param ID the tile ID.
	 * @return the Tile with the ID.
	 */
	public Tile getByID(int ID) {
		return tiles.stream().filter(tile -> tile.getID() == ID).findAny().orElse(null);
	}

	/**
	 * @return list of all tiles.
	 */
	public List<Tile> getTiles() {
		return tiles;
	}
}
