package me.vrekt.lunar.entity.living;

import me.vrekt.lunar.entity.Entity;

import java.awt.image.BufferedImage;

public abstract class LivingEntity extends Entity {

	protected float health;
	protected double speed;

	/**
	 * Initialize the entity.
	 */
	public LivingEntity(int x, int y, int width, int height, int entityID, float health, double speed) {
		super(x, y, width, height, entityID);

		this.health = health;
		this.speed = speed;
	}

	/**
	 * Initialize the entity.
	 */
	public LivingEntity(BufferedImage sprite, int x, int y, int width, int height, int entityID, float health, double speed) {
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
	public void setHealth(int health) {
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
	public void damageEntity(int amount) {
		health = health - amount < 0 ? 0 : health - amount;
	}

	/**
	 * Slow the entity
	 */
	public void slow(int amount) {
		speed -= amount;
	}

	/**
	 * Increase the speed of the entity
	 */
	public void speed(int amount) {
		speed += amount;
	}

}
