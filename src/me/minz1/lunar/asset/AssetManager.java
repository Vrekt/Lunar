package me.minz1.lunar.asset;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import me.minz1.lunar.tile.Tile;

public class AssetManager {

	private List<Tile> tiles = new ArrayList<Tile>();

	/**
	 * Add a tile.
	 * 
	 * @param tile
	 */
	public void addTile(Tile tile) {
		tiles.add(tile);
	}

	/**
	 * Remove the tile.
	 * 
	 * @param tile
	 */
	public void removeTile(Tile tile) {
		tiles.remove(tile);
	}

	/**
	 * Get the tile by ID.
	 * 
	 * @param ID
	 * @return
	 */
	public Tile getByID(int ID) {
		Optional<Tile> stream = tiles.stream().filter(tile -> tile.getID() == ID).findAny();
		return stream.isPresent() ? stream.get() : null;
	}

	/**
	 * Get all the tiles.
	 * 
	 * @return
	 */
	public List<Tile> getTiles() {
		return tiles;
	}

}
