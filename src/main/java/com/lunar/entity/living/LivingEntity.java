package com.lunar.entity.living;

import com.lunar.entity.Entity;

import java.awt.image.BufferedImage;

public abstract class LivingEntity extends Entity {

    protected float health;
    protected int speed;

    /**
     * Initialize the entity.
     */
    public LivingEntity(double x, double y, int width, int height, int entityID, float health, int speed) {
        super(x, y, width, height, entityID);

        this.health = health;
        this.speed = speed;
    }

    /**
     * Initialize the entity.
     */
    public LivingEntity(BufferedImage sprite, double x, double y, int width, int height, int entityID, float health, int speed) {
        super(sprite, x, y, width, height, entityID);

        this.health = health;
        this.speed = speed;
    }

    /**
     * @return the health
     */
    public float getHealth() {
        return health;
    }

    /**
     * Sets the health to this value.
     */
    public void setHealth(float health) {
        this.health = health;
    }

    /**
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Return if the entity is dead.
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Damage the entity
     */
    public void damageEntity(float amount) {
        health = health - amount < 0 ? 0 : health - amount;
    }

    /**
     * Slow the entity
     */
    public void slow(double amount) {
        speed -= amount;
    }

    /**
     * Increase the speed of the entity
     */
    public void speed(double amount) {
        speed += amount;
    }

    /**
     * Scale the speed by the given amount
     */
    public void scaleSpeed(double amount) {
        speed *= amount;
    }

}