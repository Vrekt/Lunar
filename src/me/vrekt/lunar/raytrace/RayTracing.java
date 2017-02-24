package me.vrekt.lunar.raytrace;

import me.vrekt.lunar.location.Location;
import me.vrekt.lunar.tile.Tile;
import me.vrekt.lunar.world.World;
import me.vrekt.lunar.world.dir.Direction;

public class RayTracing implements RayTracer {

	private boolean rayTraceRunning = false;

	@Override
	public TileInfo getNextSolidTile(World world, int x, int y, Direction dir, int width, int height) {
		Tile reference = null;

		boolean hasTile = false;
		rayTraceRunning = true;

		int roundedX = 0;
		int roundedY = 0;

		while (!hasTile && rayTraceRunning) {
			x = dir == Direction.RIGHT ? x + width : dir == Direction.LEFT ? x - width : x;
			y = dir == Direction.DOWN ? y + height : dir == Direction.UP ? y - height : y;

			roundedX = roundToDimensions(x, width);
			roundedY = roundToDimensions(y, height);

			reference = world.getTileAt(roundedX, roundedY);
			if (reference == null) {
				continue;
			}

			hasTile = reference.isSolid();

		}
		rayTraceRunning = false;
		return hasTile ? new TileInfo(new Location(roundedX, roundedY), reference) : null;
	}

	@Override
	public TileInfo getNextTileWithID(World world, int x, int y, Direction dir, int ID, int width, int height) {
		Tile reference = null;

		boolean hasTile = false;
		rayTraceRunning = true;

		int roundedX = 0;
		int roundedY = 0;

		while (!hasTile && rayTraceRunning) {
			x = dir == Direction.RIGHT ? x + width : dir == Direction.LEFT ? x - width : x;
			y = dir == Direction.DOWN ? y + height : dir == Direction.UP ? y - height : y;

			roundedX = roundToDimensions(x, width);
			roundedY = roundToDimensions(y, height);

			reference = world.getTileAt(roundedX, roundedY);
			if (reference == null) {
				continue;
			}

			hasTile = reference.getID() == ID;

		}

		rayTraceRunning = false;
		return hasTile ? new TileInfo(new Location(roundedX, roundedY), reference) : null;
	}

	@Override
	public TileInfo getNextSolidTile(World world, int x, int y, int distance, Direction dir, int width, int height) {

		Tile reference = null;

		boolean hasTile = false;
		rayTraceRunning = true;

		int roundedX = 0;
		int roundedY = 0;

		while (!hasTile && rayTraceRunning && distance > 0) {
			x = dir == Direction.RIGHT ? x + width : dir == Direction.LEFT ? x - width : x;
			y = dir == Direction.DOWN ? y + height : dir == Direction.UP ? y - height : y;
			distance--;

			roundedX = roundToDimensions(x, width);
			roundedY = roundToDimensions(y, height);

			reference = world.getTileAt(roundedX, roundedY);
			if (reference == null) {
				continue;
			}

			hasTile = reference.isSolid();

		}

		rayTraceRunning = false;
		return hasTile ? new TileInfo(new Location(roundedX, roundedY), reference) : null;
	}

	@Override
	public TileInfo getNextTileWithID(World world, int x, int y, int distance, Direction dir, int ID, int width,
			int height) {
		Tile reference = null;

		boolean hasTile = false;
		rayTraceRunning = true;

		int roundedX = 0;
		int roundedY = 0;

		while (!hasTile && rayTraceRunning && distance > 0) {
			x = dir == Direction.RIGHT ? x + width : dir == Direction.LEFT ? x - width : x;
			y = dir == Direction.DOWN ? y + height : dir == Direction.UP ? y - height : y;

			distance--;

			roundedX = roundToDimensions(x, width);
			roundedY = roundToDimensions(y, height);

			reference = world.getTileAt(roundedX, roundedY);
			if (reference == null) {
				continue;
			}

			hasTile = reference.getID() == ID;

		}

		rayTraceRunning = false;
		return hasTile ? new TileInfo(new Location(roundedX, roundedY), reference) : null;
	}

	/**
	 * Check if the rayTrace is running.
	 * 
	 * @return
	 */
	public boolean isRayTraceRunning() {
		return rayTraceRunning;
	}

	/**
	 * Stop the rayTrace.
	 */
	public void stopRayTrace() {
		rayTraceRunning = false;
	}

	/**
	 * Round.
	 * 
	 * @param value
	 * @param f
	 * @return
	 */
	private int roundToDimensions(int value, int f) {
		return (int) Math.round(value / f) * f;
	}

}
