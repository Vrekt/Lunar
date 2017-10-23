package me.vrekt.lunar.physics;

import me.vrekt.lunar.world.dir.Direction;

// This can potentially relocate into {project}.world.physics.* if we end up using this solely in World.
/**
 * This object can potentially be passed into World and create custom variable gravity.
 */
public class Gravity {
    private Direction direction;
    private float acceleration;

    /**
     * Creates gravity object based on the direction and acceleration.
     */
    public Gravity(Direction direction, float acceleration) {
        this.direction = direction;
        this.acceleration = acceleration;
    }

    /**
     * Gets the direction gravity is currently pointing at.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction that the game's gravity will point at.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Gets the acceleration constant of gravity.
     */
    public float getAcceleration() {
        return acceleration;
    }

    /**
     * Sets the acceleration constant of gravity to something else.
     */
    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    // TODO Add object manipulation functions tha will be executed in loop
}
