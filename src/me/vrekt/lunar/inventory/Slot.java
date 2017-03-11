package me.vrekt.lunar.inventory;

import me.vrekt.lunar.inventory.item.Item;

public class Slot {

	private Item item;
	private int slotID;

	/**
	 * Initialize the Slot.
	 */
	public Slot(int slotID) {
		this.slotID = slotID;
	}

	/**
	 * Get the slotID.
	 */
	public int getSlotID() {
		return slotID;
	}

	/**
	 * Get the item in the slot.
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Set the item.
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Check if we have an item.
	 */
	public boolean hasItem() {
		return item != null && item.getAmount() > 0;
	}
}
