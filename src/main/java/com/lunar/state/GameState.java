package com.lunar.state;

import java.awt.Graphics;

public abstract class GameState {

    protected int priority = 0;

    /**
     * Initializes the GameState.
     */
    public GameState(int priority) {
        this.priority = priority;
    }

    public abstract void onDraw(Graphics graphics);

    public abstract void onTick();

    /**
     * @return the priority.
     */
    public int getPriority() {
        return priority;
    }

}
