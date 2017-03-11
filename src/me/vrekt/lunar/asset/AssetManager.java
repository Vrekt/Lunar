package me.vrekt.lunar.asset;

import me.vrekt.lunar.tile.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AssetManager {

	private List<Tile> tiles = new ArrayList<Tile>();

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
	 * Get the tile by ID.
	 */
	public Tile getByID(int ID) {
		Optional<Tile> stream = tiles.stream().filter(tile -> tile.getID() == ID).findAny();
		return stream.isPresent() ? stream.get() : null;
	}

	/**
	 * Get all the tiles.
	 */
	public List<Tile> getTiles() {
		return tiles;
	}
}
