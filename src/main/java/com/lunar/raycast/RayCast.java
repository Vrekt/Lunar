package com.lunar.raycast;

import com.lunar.location.Location;
import com.lunar.tile.Tile;
import com.lunar.utilities.MathHelper;
import com.lunar.world.World;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to perform ray casting within the given world.
 *
 * Adapted from this great article
 *   https://www.codeproject.com/articles/15604/ray-casting-in-a-2d-tile-based-environment
 *
 * Created by Rick on 3/16/2017.
 */
public class RayCast {
    public class RayCastResult {
        private boolean collided;
        private Location location;
        private Tile collidedTile;

        public RayCastResult(Tile t, Location l, boolean c) {
            collidedTile = t;
            location = l;
            collided = c;
        }

        /**
         * Whether the ray cast collided with a tile
         */
        public boolean didCollide() {
            return collided;
        }

        /**
         * The location of either the tile that the ray cast collided with
         * or the end of the ray.
         *
         * @return the location
         */
        public Location getLocation() {
            return location;
        }

        /**
         * The tile that was collided with. Can be null in the case of no collision
         *
         * @return the collided tile.
         */
        public Tile getCollidedTile() {
            return collidedTile;
        }

        @Override
        public String toString() {
            return String.format("RayCastResult[collided: %b, location: (%d, %d), tile: %s]",
                    collided, location.getX(), location.getY(), collidedTile == null ? "null" : collidedTile.toString());
        }
    }

    private final World world;
    private int stepSize;

    public RayCast(World world) {
        this.world = world;
        this.stepSize = 1;
    }

    private List<Location> BresenhamLine(int xi, int yi, int xf, int yf) {
        int dist = (int) Math.ceil(MathHelper.distance(xi, yi, xf, yf));
        List<Location> list = new ArrayList<>(dist);

        boolean steep = Math.abs(yf - yi) > Math.abs(xf - xi);
        int temp;
        if (steep) {
            temp = xi;
            xi = yi;
            yi = temp;
            temp = xf;
            xf = yf;
            yf = temp;
        }
        if (xi > xf) {
            temp = xi;
            xi = xf;
            xf = temp;
            temp = yi;
            yi = yf;
            yf = temp;
        }

        int dx = xf - xi;
        int dy = Math.abs(yf - yi);
        int error = 0;
        int yStep = yi < yf ? stepSize : -stepSize;
        int y = yi;

        for (int x = xi; x < xf; x++) {
            if (steep) {
                list.add(new Location(y, x));
            } else {
                list.add(new Location(x, y));
            }
            error += dy;
            if (2 * error >= dx) {
                y += yStep;
                error -= dx;
            }
        }

        return list;
    }

    /**
     * Perform a ray cast from (originX, originY) in the direction of (dirX, dirY) for the given distance.
     */
    public RayCastResult doRayCast(int originX, int originY, int dirX, int dirY, float distance) {
        return doRayCast(originX, originY, originX + (int) (dirX * distance), originY + (int) (dirY * distance));
    }

    /**
     * Perform a ray cast from (originX, originY) to (targetX, targetY).
     */
    public RayCastResult doRayCast(int originX, int originY, int targetX, int targetY) {
        List<Location> ray = BresenhamLine(originX, originY, targetX, targetY);

        for (Location location : ray) {
            if (!world.isPointPassable(location.getX(), location.getY())) {
                Location worldLoc = world.screenToWorldLocation(location.getX(), location.getY());
                return new RayCastResult(world.get(worldLoc.getX(), worldLoc.getY()), worldLoc, true);
            }
        }

        return new RayCastResult(null, world.screenToWorldLocation(targetX, targetY), false);
    }

    /**
     * Set the step size of the ray tracing. Default is 1. A value of 2 will look at half the points,
     * therefore having lower accuracy but quicker computation.
     */
    public void setStepSize(int stepSize) {
        if (stepSize <= 0) {
            stepSize = 1;
        }
        this.stepSize = stepSize;
    }

    /**
     * Get the step size of the ray tracing.
     */
    public int getStepSize() {
        return stepSize;
    }

    /**
     * Draw the given ray cast result. Draws a line from (x1, y1) to the location of the result, and draws
     * a square at the origin of the ray.
     */
    public static void draw(Graphics graphics, World world, int originX, int originY, RayCastResult result) {
        Location worldLoc = world.worldToScreenLocation(result.getLocation());
        graphics.fillRect(originX, originY, 3, 3);
        graphics.drawLine(originX, originY, worldLoc.getX() + world.getTileWidth() / 2, worldLoc.getY() + world.getTileHeight() / 2);
    }
}
