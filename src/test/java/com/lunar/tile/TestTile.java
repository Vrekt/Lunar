package com.lunar.tile;

import com.lunar.tile.Tile;

import java.awt.image.BufferedImage;

public class TestTile extends Tile {
    public static int TILE_COUNTER = 0;
    public TestTile() {
        this(1, 1);
    }

    public TestTile(int width, int height) {
        super(new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY), TILE_COUNTER++);
    }
}
