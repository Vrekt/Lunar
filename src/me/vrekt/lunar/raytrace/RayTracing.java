package me.vrekt.lunar.raytrace;

import me.vrekt.lunar.location.Location;
import me.vrekt.lunar.tile.Tile;
import me.vrekt.lunar.world.World;
import me.vrekt.lunar.world.dir.Direction;

public class RayTracing implements RayTracer {

	private boolean rayTraceRunning = false;

	@Override
	public TileInfo getNextSolidTile(World world, int x, int y, Direction dir, int width, int height) {
		boolean hasTile = false;
		TileInfo tile = null;

		rayTraceRunning = true;

		while (!hasTile && rayTraceRunning) {
			switch (dir) {
			case UP:
				y -= height;
				hasTile = world.getTileAt(x, y).isSolid();
				break;
			case DOWN:
				y += height;
				hasTile = world.getTileAt(x, y).isSolid();
				break;
			case RIGHT:
				x += height;
				hasTile = world.getTileAt(x, y).isSolid();
				break;
			case LEFT:
				x -= height;
				hasTile = world.getTileAt(x, y).isSolid();
				break;
			}
		}

		Tile t = world.getTileAt(x, y);
		tile = new TileInfo(new Location(x, y), t);

		rayTraceRunning = false;
		return tile;
	}

	@Override
	public TileInfo getNextTileWithID(World world, int x, int y, Direction dir, int ID, int width, int height) {
		boolean hasTile = false;
		TileInfo tile = null;

		rayTraceRunning = true;

		while (!hasTile && rayTraceRunning) {
			switch (dir) {
			case UP:
				y -= height;
				hasTile = world.getTileAt(x, y).getID() == ID;
				break;
			case DOWN:
				y += height;
				hasTile = world.getTileAt(x, y).getID() == ID;
				break;
			case RIGHT:
				x += height;
				hasTile = world.getTileAt(x, y).getID() == ID;
				break;
			case LEFT:
				x -= height;
				hasTile = world.getTileAt(x, y).getID() == ID;
				break;
			}
		}

		Tile t = world.getTileAt(x, y);
		tile = new TileInfo(new Location(x, y), t);

		rayTraceRunning = false;
		return tile;
	}

	@Override
	public TileInfo getNextSolidTile(World world, int x, int y, int distance, Direction dir, int width, int height) {

		boolean hasTile = false;
		TileInfo tile = null;

		rayTraceRunning = true;

		while (!hasTile && rayTraceRunning && distance > 0) {
			switch (dir) {
			case UP:
				distance--;
				y -= height;
				hasTile = world.getTileAt(x, y).isSolid();
				break;
			case DOWN:
				distance--;
				y += height;
				hasTile = world.getTileAt(x, y).isSolid();
				break;
			case RIGHT:
				distance--;
				x += height;
				hasTile = world.getTileAt(x, y).isSolid();
				break;
			case LEFT:
				distance--;
				x -= height;
				hasTile = world.getTileAt(x, y).isSolid();
				break;
			}
		}

		Tile t = world.getTileAt(x, y);
		tile = new TileInfo(new Location(x, y), t);

		rayTraceRunning = false;
		return tile;

	}

	@Override
	public TileInfo getNextTileWithID(World world, int x, int y, int distance, Direction dir, int ID, int width,
			int height) {
		boolean hasTile = false;
		TileInfo tile = null;

		rayTraceRunning = true;

		while (!hasTile && rayTraceRunning && distance > 0) {
			switch (dir) {
			case UP:
				distance--;
				y -= height;
				hasTile = world.getTileAt(x, y).getID() == ID;
				break;
			case DOWN:
				distance--;
				y += height;
				hasTile = world.getTileAt(x, y).getID() == ID;
				break;
			case RIGHT:
				distance--;
				x += height;
				hasTile = world.getTileAt(x, y).getID() == ID;
				break;
			case LEFT:
				distance--;
				x -= height;
				hasTile = world.getTileAt(x, y).getID() == ID;
				break;
			}
		}

		Tile t = world.getTileAt(x, y);
		tile = new TileInfo(new Location(x, y), t);

		rayTraceRunning = false;
		return tile;
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

}
