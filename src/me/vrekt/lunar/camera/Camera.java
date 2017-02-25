package me.vrekt.lunar.camera;

public class Camera {

	private int width, height;
	private int camX, camY;

	public Camera(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;

		this.camX = x;
		this.camY = y;

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
		return camX;
	}

	/**
	 * Get the cameras Y.
	 * 
	 * @return
	 */
	public int getCamY() {
		return camY;
	}

	/**
	 * Set the cameras X.
	 * 
	 * @param camX
	 */
	public void setCamX(int camX) {
		this.camX = camX;
	}

	/**
	 * Set the cameras Y.
	 * 
	 * @param camY
	 */
	public void setCamY(int camY) {
		this.camY = camY;
	}

}
