package me.vrekt.lunar.entity.living;

import me.vrekt.lunar.entity.Entity;

public abstract class LivingEntity extends Entity {

	protected float health;
	protected double speed;

	public LivingEntity(int x, int y, int width, int height, int entityID, float health, double speed) {
		super(x, y, width, height, entityID);

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
	 * @param the
	 *            health to set
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
	 * @param the
	 *            speed to set
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
