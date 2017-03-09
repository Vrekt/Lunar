package me.minz1.lunar.raytrace;

import me.minz1.lunar.tile.Tile;
import me.minz1.lunar.world.World;
import me.minz1.lunar.world.dir.Direction;

public interface RayTracer {

	/**
	 * Get the next solid tile.
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param dir
	 * @param width
	 * @param height
	 * @return
	 */
	public Tile getNextSolidTile(World world, int x, int y, Direction dir, int width, int height);

	/**
	 * Get the next tile with the matching ID.
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param dir
	 * @param ID
	 * @param width
	 * @param height
	 * @return
	 */
	public Tile getNextTileWithID(World world, int x, int y, Direction dir, int ID, int width, int height);

	/**
	 * Get the next solid tile with a distance cap.
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param distance
	 * @param dir
	 * @param width
	 * @param height
	 * @return
	 */
	public Tile getNextSolidTile(World world, int x, int y, int distance, Direction dir, int width, int height);

	/**
	 * Get the next tile with the matching ID (Distance cap).
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param distance
	 * @param dir
	 * @param ID
	 * @param width
	 * @param height
	 * @return
	 */
	public Tile getNextTileWithID(World world, int x, int y, int distance, Direction dir, int ID, int width,
			int height);

}
