package com.lunar.world;

import com.lunar.entity.Entity;
import com.lunar.location.Location;
import com.lunar.tile.Tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrekt on 11/14/2017.
 */
public abstract class MapRenderer extends WorldMap {

    /**
     * Draw all the entities.
     *
     * @param worldEntities the entities in the world.
     */
    public final void drawAllEntities(List<Entity> worldEntities, Graphics graphics) {
        worldEntities.forEach(entity -> graphics.drawImage(entity.getTexture(), entity.getX(), entity.getY(), null));
    }

    /**
     * Draw all the tiles.
     */
    public final void drawAllTiles(Graphics graphics) {
        for (Location key : TILE_MAP.keySet()) {
            Tile tile = TILE_MAP.get(key);
            graphics.drawImage(tile.getTexture(), key.getX(), key.getY(), null);
        }
    }

    public class WorldGridRenderer {
        private int tileWidth, tileHeight;
        private int width, height;

        private List<Rectangle> worldGrid;

        /**
         * Initialize
         *
         * @param width      the width of the world
         * @param height     the height of the world
         * @param tileWidth  the width of the tiles
         * @param tileHeight the height of the tiles.
         */
        public WorldGridRenderer(int width, int height, int tileWidth, int tileHeight) {
            this.tileWidth = tileWidth;
            this.tileHeight = tileHeight;

            this.width = width;
            this.height = height;

            worldGrid = generateGrid();

        }

        /**
         * Generate the grid based on width and height.
         */
        public List<Rectangle> generateGrid() {
            List<Rectangle> list = new ArrayList<>();

            for (int x = 0; x <= width; x += tileWidth) {
                for (int y = 0; y <= height; y += tileHeight) {
                    list.add(new Rectangle(x, y, tileWidth, tileHeight));
                }
            }

            return list;

        }

        /**
         * Draw the grid.
         */
        public void drawGrid(Graphics graphics) {
            worldGrid.forEach(rec -> graphics.drawRect((int) rec.getX(), (int) rec.getY(), (int) rec.getWidth(), (int) rec.getHeight()));
        }

        /**
         * This can return null if the grid has not been generated.
         *
         * @return a list of rectangles that represent the grid.
         */
        public List<Rectangle> getWorldGrid() {
            return worldGrid;
        }

        /**
         * @return the tile width
         */
        public int getTileWidth() {
            return tileWidth;
        }

        /**
         * @return the tile height
         */
        public int getTileHeight() {
            return tileHeight;
        }

        /**
         * @return the width
         */
        public int getWidth() {
            return width;
        }

        /**
         * @return the height
         */
        public int getHeight() {
            return height;
        }
    }
}
