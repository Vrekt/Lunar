package com.lunar.camera;

import com.lunar.location.Location;

public class Camera {

    private int width, height;
    private Location camera;

    /**
     * Initialize the camera.
     */
    public Camera(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;

        camera = new Location(x, y);

    }

    /**
     * Initialize the camera.
     */
    public Camera(int width, int height, Location vec) {
        this.width = width;
        this.height = height;

        camera = vec;
    }

    public Location getCamera() {
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
