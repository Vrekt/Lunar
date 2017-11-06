package me.vrekt.lunar.world;

import java.awt.*;

/**
 * Created by rickboss on 10/21/17.
 */
public class TestWorld extends World {
    public TestWorld() {
        this("test_world", 400, 400, 20, 20);
    }

    public TestWorld(String name, int width, int height, int tileWidth, int tileHeight) {
        super(name, width, height, tileWidth, tileHeight);
    }

    @Override
    public void onDraw(Graphics graphics) {
        // noop
    }
}
