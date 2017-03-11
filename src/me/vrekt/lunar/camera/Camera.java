package me.vrekt.lunar.camera;

import me.vrekt.lunar.vec.Vector2f;

public class Camera {

	private int width, height;
	private Vector2f camera;

	/**
	 * Initialize the camera.
	 */
	public Camera(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;

		camera = new Vector2f(x, y);

	}

	/**
	 * Initialize the camera.
	 */
	public Camera(int width, int height, Vector2f vec) {
		this.width = width;
		this.height = height;

		camera = vec;
	}

	public Vector2f getCamera() {
		return camera;
	}

	/**
	 * Get the width;
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Get the cameras X.
	 */
	public int getCamX() {
		return camera.getX();
	}

	/**
	 * Set the cameras X.
	 */
	public void setCamX(int camX) {
		camera.setX(camX);
	}

	/**
	 * Get the cameras Y.
	 */
	public int getCamY() {
		return camera.getY();
	}

	/**
	 * Set the cameras Y.
	 */
	public void setCamY(int camY) {
		camera.setY(camY);
	}
}
