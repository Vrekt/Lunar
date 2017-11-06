package me.vrekt.lunar.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputListener implements KeyListener {

	private static boolean keyData[] = new boolean[256];

    private static Long keyStart[] = new Long[256];

    /**
     * Return if the key is down.
     */
    public static boolean isKeyDown(int key) {
        return keyData[key];
	}

    /*
    * Returns the amount of time the key has been depressed.
    * @return The amount of time the key has been depressed, in milliseconds.
    */
    public static long keyDownDuration(int key) {
        Long start = keyStart[key];
        if (keyStart[key] != null) {
            return System.currentTimeMillis() - start;
        } else {
            return 0;
        }
    }

	@Override
	public void keyPressed(KeyEvent event) {
		keyData[event.getKeyCode()] = true;
        keyStart[event.getKeyCode()] = System.currentTimeMillis();
    }

	@Override
	public void keyReleased(KeyEvent event) {
		keyData[event.getKeyCode()] = false;
        keyStart[event.getKeyCode()] = null;
    }

	@Override
	public void keyTyped(KeyEvent event) {
	}
}
