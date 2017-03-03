package me.minz1.lunar.inventory;

import me.minz1.lunar.inventory.item.Item;

public class Slot {

	private Item item;
	private int slotID;

	/**
	 * Initialize the Slot.
	 * 
	 * @param slotID
	 */
	public Slot(int slotID) {
		this.slotID = slotID;
	}

	/**
	 * Get the slotID.
	 * 
	 * @return
	 */
	public int getSlotID() {
		return slotID;
	}

	/**
	 * Get the item in the slot.
	 * 
	 * @return
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Set the item.
	 * 
	 * @param item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Check if we have an item.
	 * 
	 * @return
	 */
	public boolean hasItem() {
		return item != null && item.getAmount() > 0;
	}

}
