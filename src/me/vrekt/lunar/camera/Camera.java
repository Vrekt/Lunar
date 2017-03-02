package me.vrekt.lunar.camera;

import me.vrekt.lunar.vec.Vector2f;

public class Camera {

	private int width, height;
	private Vector2f camera;

	/**
	 * Initialize the camera.
	 * 
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 */
	public Camera(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;

		camera = new Vector2f(x, y);

	}

	/**
	 * Initialize the camera.
	 * 
	 * @param width
	 * @param height
	 * @param vec
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
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the height.
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Get the cameras X.
	 * 
	 * @return
	 */
	public int getCamX() {
		return camera.getX();
	}

	/**
	 * Get the cameras Y.
	 * 
	 * @return
	 */
	public int getCamY() {
		return camera.getY();
	}

	/**
	 * Set the cameras X.
	 * 
	 * @param camX
	 */
	public void setCamX(int camX) {
		camera.setX(camX);
	}

	/**
	 * Set the cameras Y.
	 * 
	 * @param camY
	 */
	public void setCamY(int camY) {
		camera.setY(camY);
	}

}
