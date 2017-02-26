package me.vrekt.lunar.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import me.vrekt.lunar.inventory.item.Item;

public class Inventory {

	private List<Slot> inventory = new ArrayList<Slot>();

	private String name;
	private int size;

	/**
	 * Initialize the inventory.
	 * 
	 * @param name
	 * @param size
	 */
	public Inventory(String name, int size) {
		this.name = name;
		this.size = size;
	}

	/**
	 * Get the contents.
	 * 
	 * @return
	 */
	public List<Slot> getInventory() {
		return inventory;
	}

	/**
	 * Get the inventory name.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the size.
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Get a slot with the item.
	 * 
	 * @param item
	 * @return
	 */
	public Slot getSlotWithItem(Item item) {
		Optional<Slot> stream = inventory.stream().filter(slot -> slot.getItem().getName().equals(item.getName()))
				.findAny();
		return stream.isPresent() ? stream.get() : null;
	}

	/**
	 * Check if we have an empty slot.
	 * 
	 * @return
	 */
	public boolean hasEmptySlot() {
		return inventory.stream().filter(slot -> !slot.hasItem()).findAny().isPresent();
	}

	/**
	 * Get an empty slot.
	 * 
	 * @return
	 */
	public Slot getEmptySlot() {
		return inventory.stream().filter(slot -> !slot.hasItem()).findAny().get();
	}

	/**
	 * Get a slot via ID.
	 * 
	 * @param ID
	 * @return
	 */
	public Slot getViaID(int ID) {
		Optional<Slot> stream = inventory.stream().filter(slot -> slot.getSlotID() == ID).findAny();
		return stream.isPresent() ? stream.get() : null;
	}

	/**
	 * Add an item.
	 * 
	 * @param item
	 * @return
	 */
	public boolean addItem(Item item) {

		Slot slot = getSlotWithItem(item);
		if (slot != null) {
			Item si = slot.getItem();
			if (si.canCarry(item.getAmount())) {
				si.giveAmount(item.getAmount());
				slot.setItem(si);
				return true;

			} else {
				if (!hasEmptySlot()) {
					return false;
				}
				Slot empty = getEmptySlot();
				empty.setItem(item);
				return true;
			}

		} else {
			if (!hasEmptySlot()) {
				return false;
			}
			Slot empty = getEmptySlot();
			empty.setItem(item);
			return true;
		}

	}

}
