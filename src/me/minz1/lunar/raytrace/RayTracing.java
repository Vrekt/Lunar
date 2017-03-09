package me.minz1.lunar.raytrace;

import me.minz1.lunar.tile.Tile;
import me.minz1.lunar.world.World;
import me.minz1.lunar.world.dir.Direction;

public class RayTracing implements RayTracer {

	private boolean rayTraceRunning = false;

	@Override
	public Tile getNextSolidTile(World world, int x, int y, Direction dir, int width, int height) {
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

			reference.setX(roundedX);
			reference.setY(roundedY);
			hasTile = reference.isSolid();

		}
		rayTraceRunning = false;
		return hasTile ? reference : null;
	}

	@Override
	public Tile getNextTileWithID(World world, int x, int y, Direction dir, int ID, int width, int height) {
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

			reference.setX(roundedX);
			reference.setY(roundedY);
			hasTile = reference.getID() == ID;

		}

		rayTraceRunning = false;
		return hasTile ? reference : null;
	}

	@Override
	public Tile getNextSolidTile(World world, int x, int y, int distance, Direction dir, int width, int height) {

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

			reference.setX(roundedX);
			reference.setY(roundedY);
			hasTile = reference.isSolid();

		}

		rayTraceRunning = false;
		return hasTile ? reference : null;
	}

	@Override
	public Tile getNextTileWithID(World world, int x, int y, int distance, Direction dir, int ID, int width,
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
			reference.setX(roundedX);
			reference.setY(roundedY);
			hasTile = reference.getID() == ID;

		}

		rayTraceRunning = false;
		return hasTile ? reference : null;
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
		return Math.round(value / f) * f;
	}

}
