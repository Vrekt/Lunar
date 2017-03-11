package me.vrekt.lunar.raytrace;

import me.vrekt.lunar.tile.Tile;
import me.vrekt.lunar.world.World;
import me.vrekt.lunar.world.dir.Direction;

public interface RayTracer {

	/**
	 * Get the next solid tile.
	 */
	Tile getNextSolidTile(World world, int x, int y, Direction dir, int width, int height);

	/**
	 * Get the next tile with the matching ID.
	 */
	Tile getNextTileWithID(World world, int x, int y, Direction dir, int ID, int width, int height);

	/**
	 * Get the next solid tile with a distance cap.
	 */
	Tile getNextSolidTile(World world, int x, int y, int distance, Direction dir, int width, int height);

	/**
	 * Get the next tile with the matching ID (Distance cap).
	 */
	Tile getNextTileWithID(World world, int x, int y, int distance, Direction dir, int ID, int width, int height);

}
