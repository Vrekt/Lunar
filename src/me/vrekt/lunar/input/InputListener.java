package me.vrekt.lunar.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputListener implements KeyListener {

	private static boolean keyData[] = new boolean[256];

	/**
	 * Return if the key is down.
	 */
	public static boolean isKeyDown(int key) {
		return keyData[key];
	}

	@Override
	public void keyPressed(KeyEvent event) {
		keyData[event.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent event) {
		keyData[event.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}
}
