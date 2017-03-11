package me.vrekt.lunar.state;

import java.awt.*;

public abstract class GameState {

	private int priority = 0;

	/**
	 * Initializes the GameState.
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
