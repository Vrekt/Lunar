package me.vrekt.lunar.inventory;

import me.vrekt.lunar.inventory.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventory {

	private List<Slot> inventory = new ArrayList<Slot>();

	private String name;
	private int size;

	/**
	 * Initialize the inventory.
	 */
	public Inventory(String name, int size) {
		this.name = name;
		this.size = size;
	}

	/**
	 * Get the contents.
	 */
	public List<Slot> getInventory() {
		return inventory;
	}

	/**
	 * Get the inventory name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the size.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Get a slot with the item.
	 */
	public Slot getSlotWithItem(Item item) {
		Optional<Slot> stream = inventory.stream().filter(slot -> slot.getItem().getName().equals(item.getName()))
				.findAny();
		return stream.isPresent() ? stream.get() : null;
	}

	/**
	 * Check if we have an empty slot.
	 */
	public boolean hasEmptySlot() {
		return inventory.stream().anyMatch(slot -> !slot.hasItem());
	}

	/**
	 * Get an empty slot.
	 */
	public Slot getEmptySlot() {
		return inventory.stream().filter(slot -> !slot.hasItem()).findAny().get();
	}

	/**
	 * Get a slot via ID.
	 */
	public Slot getViaID(int ID) {
		Optional<Slot> stream = inventory.stream().filter(slot -> slot.getSlotID() == ID).findAny();
		return stream.isPresent() ? stream.get() : null;
	}

	/**
	 * Move the item to the slot.
	 */
	public void moveItemToSlot(Item item, Slot original, Slot moveTo) {
		original.setItem(null);
		if (!moveTo.hasItem()) {
			moveTo.setItem(item);
		} else {
			moveTo.getItem().setAmount(item.getAmount());
		}
	}

	/**
	 * Move the item to the slot.
	 */
	public void moveItemToSlot(Item item, int original, int moveTo) {
		Slot slot = getViaID(original);
		Slot move = getViaID(moveTo);

		slot.setItem(null);
		if (!move.hasItem()) {
			move.setItem(item);
		} else {
			move.getItem().setAmount(item.getAmount());
		}

	}

	/**
	 * Add an item.
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
