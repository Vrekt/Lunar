package com.lunar.sprite;

import com.lunar.tile.Tile;
import com.lunar.utilities.Logger;
import com.lunar.world.dir.Direction;
import com.sun.istack.internal.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpriteManager {

    private final List<SpriteSheet> SPRITE_SHEETS = new ArrayList<>();
    private SpriteSheet current;

    public SpriteManager(String path, String name) {
        BufferedImage image = load(path);
        if (image == null) {
            Logger.logCritical("Attempted to load a null spritesheet.");
            return;
        }
        SpriteSheet sheet = new SpriteSheet(name, image);
        this.current = sheet;
        SPRITE_SHEETS.add(sheet);
    }

    public SpriteManager(BufferedImage sheet, String name) {
        SpriteSheet sh = new SpriteSheet(name, sheet);
        this.current = sh;
        SPRITE_SHEETS.add(sh);
    }

    public SpriteManager(SpriteSheet sheet) {
        this.current = sheet;
        SPRITE_SHEETS.add(sheet);
    }

    public SpriteManager(SpriteSheet[] sheets) {
        List<SpriteSheet> list = Arrays.asList(sheets);
        SPRITE_SHEETS.addAll(list);
    }

    public SpriteManager(List<SpriteSheet> sheets) {
        SPRITE_SHEETS.addAll(sheets);
        sheets.clear();
    }

    /**
     * Set the current spritesheet to use.
     *
     * @param current the spritesheet.
     */
    public void setCurrent(SpriteSheet current) {
        this.current = current;
    }

    /**
     * Get a part of the image.
     *
     * @param image  the image
     * @param x      the X coordinate.
     * @param y      the Y coordinate.
     * @param width  the width
     * @param height the height
     * @return the sub section of the image.
     */
    public static BufferedImage getSectionAt(BufferedImage image, int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }

    /**
     * Load a image from the file path.
     *
     * @param path the path to the file.
     * @return the image that was loaded.
     */
    @Nullable
    public static BufferedImage load(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param name the name of the spritesheet.
     * @return the spritesheet with the corresponding name.
     */
    @Nullable
    public SpriteSheet getSheet(String name) {
        return SPRITE_SHEETS.stream().filter(sheet -> sheet.getName().equals(name)).findAny().orElse(null);
    }

    /**
     * Get a part of the image.
     *
     * @param x      the X coordinate.
     * @param y      the Y coordinate.
     * @param width  the width
     * @param height the height
     * @return the sub section of the image.
     */
    @Nullable
    public BufferedImage getSectionAt(int x, int y, int width, int height) {
        if (current == null) {
            Logger.logWarning("Attempted to get a section from a null sheet.");
            return null;
        }
        return current.getTexture().getSubimage(x, y, width, height);
    }


    /**
     * Get a part of the image.
     *
     * @param name   the name of the spritesheet.
     * @param x      the X coordinate.
     * @param y      the Y coordinate.
     * @param width  the width
     * @param height the height
     * @return the sub section of the image.
     */
    public BufferedImage getSectionAt(String name, int x, int y, int width, int height) {
        BufferedImage i = getSheet(name).getTexture();
        return i.getSubimage(x, y, width, height);
    }

    /**
     * Get multiple images from an image.
     *
     * @param name        the name of the spritesheet.
     * @param x           the X coordinate.
     * @param y           the Y coordinate.
     * @param width       the width
     * @param height      the height
     * @param direction   the direction
     * @param spriteCount the amount of sprites to get.
     * @param xOffset     the offset to add/subtract since spacing between sprites can vary.
     * @param yOffset     the offset to add/subtract since spacing between sprites can vary.
     * @return an array of sprites.
     */
    public BufferedImage[] getMultipleSprites(String name, int x, int y, int width, int height, Direction direction,
                                              int spriteCount, int xOffset, int yOffset) {

        BufferedImage image = getSheet(name).getTexture();
        BufferedImage[] frames = new BufferedImage[spriteCount];

        int newWidth, newHeight;
        newWidth = width + xOffset;
        newHeight = height + yOffset;

        for (int frameCount = 0; frameCount < spriteCount; frameCount++) {
            frames[frameCount] = getSectionAt(image, x, y, width, height);
            x = direction == Direction.RIGHT ? x + newWidth : direction == Direction.LEFT ? x - newWidth : x;
            y = direction == Direction.DOWN ? y + newHeight : direction == Direction.UP ? y - newHeight : y;
        }

        return frames;
    }


    /**
     * Get multiple images from an image.
     *
     * @param x           the X coordinate.
     * @param y           the Y coordinate.
     * @param width       the width
     * @param height      the height
     * @param direction   the direction
     * @param spriteCount the amount of sprites to get.
     * @param xOffset     the offset to add/subtract since spacing between sprites can vary.
     * @param yOffset     the offset to add/subtract since spacing between sprites can vary.
     * @return an array of sprites.
     */
    public BufferedImage[] getMultipleSprites(int x, int y, int width, int height, Direction direction,
                                              int spriteCount, int xOffset, int yOffset) {
        BufferedImage[] frames = new BufferedImage[spriteCount];

        int newWidth, newHeight;
        newWidth = width + xOffset;
        newHeight = height + yOffset;

        for (int frameCount = 0; frameCount < spriteCount; frameCount++) {
            frames[frameCount] = getSectionAt(x, y, width, height);
            x = direction == Direction.RIGHT ? x + newWidth : direction == Direction.LEFT ? x - newWidth : x;
            y = direction == Direction.DOWN ? y + newHeight : direction == Direction.UP ? y - newHeight : y;
        }
        return frames;
    }

    /**
     * Get multiple tiles from an image.
     *
     * @param x             the X coordinate.
     * @param y             the Y coordinate.
     * @param width         the width
     * @param height        the height
     * @param direction     the direction
     * @param spriteCount   the amount of sprites to get.
     * @param areTilesSolid if the tiles are solid.
     * @param xOffset       the offset to add/subtract since spacing between sprites can vary.
     * @param yOffset       the offset to add/subtract since spacing between sprites can vary..
     * @param tileIds       the array of Ids to use, this can be null.
     * @return a list of tiles created from the textures.
     */
    public List<Tile> getTilesFromSprites(int x, int y, int width, int height, Direction direction, int spriteCount, boolean
            areTilesSolid, int xOffset, int yOffset, int[] tileIds) {
        // get the sprites.
        BufferedImage[] images = getMultipleSprites(x, y, width, height, direction, spriteCount, xOffset, yOffset);
        if (tileIds == null) {
            tileIds = new int[images.length];
        }
        List<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < images.length; i++) {
            // loop through all the textures and create them.
            BufferedImage texture = images[i];
            Tile tile = new Tile(texture, tileIds[i], new Tile.TileProperties(true, areTilesSolid));
            tiles.add(tile);
        }
        return tiles;
    }

    /**
     * Get multiple tiles from an image.
     *
     * @param name          the name of the spritesheet.
     * @param x             the X coordinate.
     * @param y             the Y coordinate.
     * @param width         the width
     * @param height        the height
     * @param direction     the direction
     * @param spriteCount   the amount of sprites to get.
     * @param areTilesSolid if the tiles are solid.
     * @param xOffset       the offset to add/subtract since spacing between sprites can vary.
     * @param yOffset       the offset to add/subtract since spacing between sprites can vary.
     * @param tileIds       the array of Ids to use, this can be null.
     * @return a list of tiles created from the textures.
     */
    public List<Tile> getTilesFromSprites(String name, int x, int y, int width, int height, Direction direction, int spriteCount, boolean
            areTilesSolid, int xOffset, int yOffset, int[] tileIds) {
        // get the sprites.
        BufferedImage[] images = getMultipleSprites(name, x, y, width, height, direction, spriteCount, xOffset, yOffset);
        if (tileIds == null) {
            tileIds = new int[images.length];
        }
        List<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < images.length; i++) {
            // loop through all the textures and create them.
            BufferedImage texture = images[i];
            Tile tile = new Tile(texture, tileIds[i], new Tile.TileProperties(true, areTilesSolid));
            tiles.add(tile);
        }
        return tiles;
    }

    /**
     * Get multiple tiles from an image.
     *
     * @param name          the name of the spritesheet.
     * @param x             the X coordinate.
     * @param y             the Y coordinate.
     * @param width         the width
     * @param height        the height
     * @param direction     the direction
     * @param spriteCount   the amount of sprites to get.
     * @param areTilesSolid if the tiles are solid.
     * @param xOffset       the offset to add/subtract since spacing between sprites can vary.
     * @param yOffset       the offset to add/subtract since spacing between sprites can vary.
     * @return a list of tiles created from the textures.
     */
    public List<Tile> getTilesFromSprites(String name, int x, int y, int width, int height, Direction direction, int spriteCount, boolean
            areTilesSolid, int xOffset, int yOffset) {
        return getTilesFromSprites(name, x, y, width, height, direction, spriteCount, areTilesSolid, xOffset, yOffset, null);
    }

}
