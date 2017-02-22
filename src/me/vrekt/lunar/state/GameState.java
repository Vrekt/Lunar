package me.vrekt.lunar.state;

import java.awt.Graphics;

public abstract class GameState {

	public int priority = 0;

	public GameState(int priority) {
		this.priority = priority;
	}

	public abstract void onDraw(Graphics graphics);

	public abstract void onTick();

}
