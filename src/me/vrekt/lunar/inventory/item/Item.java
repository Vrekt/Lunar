package me.vrekt.lunar.inventory.item;

import java.awt.image.BufferedImage;

public class Item {

	private String name;
	private BufferedImage texture;

	private int amount;
	private int maxAmount;

	/**
	 * Initialize the item.
	 */
	public Item(String name, BufferedImage texture, int amount, int maxAmount) {
		this.name = name;
		this.texture = texture;

		this.amount = amount;
		this.maxAmount = maxAmount;

	}

	/**
	 * Get the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the texture.
	 */
	public BufferedImage getTexture() {
		return texture;
	}

	/**
	 * Set the texture.
	 */
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	/**
	 * Get the amount.
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Set the amount.
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * Get the max amount this item can carry.
	 */
	public int getMaxAmount() {
		return maxAmount;
	}

	/**
	 * Set the max amount.
	 */
	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

	/**
	 * Return if we can carry more or not.
	 */
	public boolean canCarry(int items) {
		return amount + items < maxAmount;
	}

	/**
	 * Give the item.
	 */
	public void giveAmount(int items) {
		amount += items;
	}

	/**
	 * Take the item.
	 */
	public void takeAmount(int items) {
		amount -= items;
	}
}
