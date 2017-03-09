package me.minz1.lunar.input;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	private static Point lastClick;
	private static boolean isMouseDown;

	private static Component enteredComponent;

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

	/**
	 * Get the click coordinates.
	 * 
	 * @return
	 */
	public static Point getLastClick() {
		return lastClick;
	}

	/**
	 * Returns if the mouse is down.
	 * 
	 * @return
	 */
	public static boolean isMouseDown() {
		return isMouseDown;
	}

	/**
	 * Get the component the mouse entered. This can return null if the mouse
	 * exited the component.
	 * 
	 * @return
	 */
	public static Component getEnteredComponent() {
		return enteredComponent;
	}

}
