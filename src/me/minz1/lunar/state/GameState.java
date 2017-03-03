package me.minz1.lunar.state;

import java.awt.Graphics;

public abstract class GameState {

	private int priority = 0;

	/**
	 * Initializes the GameState.
	 * 
	 * @param priority
	 */
	public GameState(int priority) {
		this.priority = priority;
	}

	public abstract void onDraw(Graphics graphics);

	public abstract void onTick();

	public int getPriority() {
		return priority;
	}

}
