package me.vrekt.lunar.entity;

import java.awt.*;

/**
 * Created by rickboss on 10/21/17.
 */
public class TestEntity extends Entity {
    public static int ENTITY_COUNTER = 0;

    public TestEntity() {
        super(0, 0, 0, 0, ENTITY_COUNTER++);
    }

    @Override
    public void drawEntity(Graphics graphics) {
        // noop
    }

    @Override
    public void updateEntity() {
        // noop
    }
}
