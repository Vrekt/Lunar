package me.vrekt.lunar.inventory.item;

import java.awt.image.BufferedImage;

public class Item {

	private String name;
	private BufferedImage texture;

	private int amount;
	private int maxAmount;

	/**
	 * Initialize the item.
	 * 
	 * @param name
	 * @param texture
	 * @param amount
	 * @param maxAmount
	 */
	public Item(String name, BufferedImage texture, int amount, int maxAmount) {
		this.name = name;
		this.texture = texture;

		this.amount = amount;
		this.maxAmount = maxAmount;

	}

	/**
	 * Get the name.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the texture.
	 * 
	 * @return
	 */
	public BufferedImage getTexture() {
		return texture;
	}

	/**
	 * Get the amount.
	 * 
	 * @return
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Get the max amount this item can carry.
	 * 
	 * @return
	 */
	public int getMaxAmount() {
		return maxAmount;
	}

	/**
	 * Return if we can carry more or not.
	 * 
	 * @param items
	 * @return
	 */
	public boolean canCarry(int items) {
		return amount + items < maxAmount;
	}

	/**
	 * Set the name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the texture.
	 * 
	 * @param texture
	 */
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	/**
	 * Set the amount.
	 * 
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * Give the item.
	 * 
	 * @param items
	 */
	public void giveAmount(int items) {
		amount += items;
	}

	/**
	 * Take the item.
	 * 
	 * @param items
	 */
	public void takeAmount(int items) {
		amount -= items;
	}

	/**
	 * Set the max amount.
	 * 
	 * @param maxAmount
	 */
	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

}
