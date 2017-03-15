package me.vrekt.lunar.sprite;

import me.vrekt.lunar.tile.Tile;
import me.vrekt.lunar.world.dir.Direction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SpriteManager {

    private List<SpriteSheet> spriteSheets = new ArrayList<>();
    private BufferedImage spriteSheet;

    /**
     * Initialize the spriteManager.
     */
    public SpriteManager(BufferedImage spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    /**
     * Initializes the spriteManager with a SpriteSheet.
     */
    public SpriteManager(SpriteSheet sheet) {
        spriteSheets.add(sheet);
    }

    /**
     * Initializes the spriteManager with an array of SpriteSheets.
     */
    public SpriteManager(SpriteSheet[] sheets) {
        List<SpriteSheet> list = Arrays.asList(sheets);
        list.forEach(sheet -> spriteSheets.add(sheet));
    }

    /**
     * Initializes the spriteManager with a list of SpriteSheets.
     */
    public SpriteManager(List<SpriteSheet> sheets) {
        sheets.forEach(sheet -> spriteSheets.add(sheet));
        sheets.clear();
    }

    /**
     * Return a section of the image.
     */
    public static BufferedImage getSectionAt(BufferedImage image, int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }

    /**
     * Load the spriteSheet.
     */
    public static BufferedImage load(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the sprite sheet.
     */
    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    /**
     * Get a spriteSheet via ID.
     */
    public SpriteSheet getSheet(int ID) {
        Optional<SpriteSheet> stream = spriteSheets.stream().filter(sheet -> sheet.getID() == ID).findAny();
        return stream.isPresent() ? stream.get() : null;
    }

    /**
     * Return an image from the section selected.
     */
    public BufferedImage getSectionAt(int x, int y, int width, int height) {
        return spriteSheet.getSubimage(x, y, width, height);
    }

    /**
     * Return an image from one of the spriteSheets in the list.
     */
    public BufferedImage getSectionAt(int ID, int x, int y, int width, int height) {
        BufferedImage i = getSheet(ID).getSheet();
        return i.getSubimage(x, y, width, height);
    }

    /**
     * Returns an array of Images.
     */
    public BufferedImage[] getMultipleSprites(int ID, int x, int y, int width, int height, Direction direction,
                                              int spriteCount) {
        BufferedImage i = getSheet(ID).getSheet();

        BufferedImage[] frames = new BufferedImage[spriteCount];

        int frameCount = 0;

        while (frameCount < spriteCount) {

            frames[frameCount] = getSectionAt(i, x, y, width, height);
            x = direction == Direction.RIGHT ? x + width : direction == Direction.LEFT ? x - width : x;
            y = direction == Direction.DOWN ? y + height : direction == Direction.UP ? y - height : y;
            frameCount++;

        }

        return frames;

    }

    /**
     * Returns an array of Images for animations. Using this would be perfect
     * for getting multiple sequences of images for animations.
     */
    public BufferedImage[] getMultipleSprites(int x, int y, int width, int height, Direction direction,
                                              int spriteCount) {
        BufferedImage[] frames = new BufferedImage[spriteCount];

        int frameCount = 0;

        while (frameCount < spriteCount) {

            frames[frameCount] = getSectionAt(x, y, width, height);
            x = direction == Direction.RIGHT ? x + width : direction == Direction.LEFT ? x - width : x;
            y = direction == Direction.DOWN ? y + height : direction == Direction.UP ? y - height : y;
            frameCount++;

        }

        return frames;
    }

    /**
     * Gets multiple sprites and then converts each of them to a tile and adds them to the list.
     *
     * @param solidTiles indicates whether the tiles are solid or not.
     * @param idsToUse   each tile will get an ID from this array.
     * @return list of tiles.
     */
    public List<Tile> getAndCreateMultipleTiles(int x, int y, int width, int height, Direction direction, int spriteCount, boolean solidTiles, int[] idsToUse) {
        BufferedImage[] images = getMultipleSprites(x, y, width, height, direction, spriteCount);
        List<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < images.length; i++) {
            BufferedImage image = images[i];
            Tile tile = new Tile(image, idsToUse[i], solidTiles);

            tiles.add(tile);

        }

        return tiles;

    }

    /**
     * Gets multiple sprites and then converts each of them to a tile and adds them to the list.
     * Please note if you are storing or ordering these tiles based on ID then DONT use this method.
     * Each tile will follow in a sequence (1, 2, 3, 4, etc), this could cause problems with other tiles with the same ID.
     *
     * @param solidTiles indicates whether the tiles are solid or not.
     * @return
     */
    public List<Tile> getAndCreateMultipleTiles(int x, int y, int width, int height, Direction direction, int spriteCount, boolean solidTiles) {
        BufferedImage[] images = getMultipleSprites(x, y, width, height, direction, spriteCount);
        List<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < images.length; i++) {
            BufferedImage image = images[i];
            Tile tile = new Tile(image, i, solidTiles);

            tiles.add(tile);

        }

        return tiles;

    }

}
