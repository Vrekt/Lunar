package com.lunar.physics;

import java.util.Optional;

// This can potentially relocate into {project}.world.physics.* if we end up using this solely in World.

/**
 * This object can potentially be passed into World and create custom variable velocity.
 * <p>
 * X and Y velocites should be <i>per second</i> or <i>per tick</i>, we should decide on that perhapes...
 */
public class Velocity {
    // If these optionals are not set then y velocity is not touched. x==0 would be down and y==0 would be left and so on... These velocity values should be absolute and not relative, and should not be update every tick, or decay.
    // Technically we can use null but using null to represent something is really icky for me
    private Optional<Double> directionX;
    private Optional<Double> directionY;

    private float magnitude;

    /**
     * Creates velocity object based on the direction (x and y) and magnitude.
     */
    public Velocity(Optional<Double> directionX, Optional<Double> directionY, float magnitude) {
        this.directionX = directionX;
        this.directionY = directionY;
        this.magnitude = magnitude;
    }

    /**
     * Gets the X component of the velocity.
     */
    public Optional<Double> getDirectionX() {
        return directionX;
    }

    /**
     * Sets the X component of the velocity.
     */
    public void setDirectionX(Optional<Double> directionX) {
        this.directionX = directionX;
    }

    /**
     * Gets the Y component of the velocity.
     */
    public Optional<Double> getDirectionY() {
        return directionY;
    }

    /**
     * Sets the Y component of the velocity.
     */
    public void setDirectionY(Optional<Double> directionY) {
        this.directionY = directionY;
    }

    /**
     * Gets the magnitude of velocity.
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
}