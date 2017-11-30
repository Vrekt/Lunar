package com.lunar.sprite;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private String name;
    private BufferedImage texture;

    public SpriteSheet(String name, BufferedImage texture) {
        this.name = name;
        this.texture = texture;
    }

    /**
     * @return the textures.
     */
    public BufferedImage getTexture() {
        return texture;
    }

    /**
     * @return the name of the sheet.
     */
    public String getName() {
        return name;
    }
}
