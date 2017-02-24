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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getCamX() {
		return camX;
	}

	public int getCamY() {
		return camY;
	}

	public void setCamX(int camX) {
		this.camX = camX;
	}
	
	public void setCamY(int camY) {
		this.camY = camY;
	}
	
}
