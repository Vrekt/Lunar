package me.vrekt.lunar.physics;

import me.vrekt.lunar.world.dir.Direction;

// This can potentially relocate into {project}.world.physics.* if we end up using this solely in World.
/**
 * This object can potentially be passed into World and create custom variable velocity.
 */
public class Velocity {
    private Direction direction;
    private float magnitude;

    /**
     * Creates velocity object based on the direction and magnitude.
     */
    public Velocity(Direction direction, float magnitude) {
        this.direction = direction;
        this.magnitude = magnitude;
    }

    /**
     * Gets the direction velocity is currently pointing at.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction that the game's velocity will point at.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Gets the acceleration constant of velocity.
     */
    public float getMagnitude() {
        return magnitude;
    }

    /**
     * Sets the magnitude of velocity to something else.
     */
    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    // TODO Add object manipulation functions tha will be executed in loop
}
