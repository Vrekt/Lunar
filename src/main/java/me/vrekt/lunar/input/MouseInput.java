package me.vrekt.lunar.input;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	private static Point lastClick;
	private static boolean isMouseDown;

	private static Component enteredComponent;

	/**
	 * Get the click coordinates.
	 */
	public static Point getLastClick() {
		return lastClick;
	}

	/**
	 * Returns if the mouse is down.
	 */
	public static boolean isMouseDown() {
		return isMouseDown;
	}

	/**
	 * Get the component the mouse entered. This can return null if the mouse
	 * exited the component.
	 */
	public static Component getEnteredComponent() {
		return enteredComponent;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		lastClick = e.getPoint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		enteredComponent = e.getComponent();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		enteredComponent = null;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		isMouseDown = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isMouseDown = false;
	}
}
