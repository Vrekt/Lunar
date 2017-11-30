package com.lunar.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

    private int frameCount;
    private int currentFrame;
    private int frameDelay;

    private boolean running = false;
    private BufferedImage[] frames;

    private boolean loop = false;
    private int ID;

    /**
     * Initialize the Animation.
     *
     * @param animations    the animation frames.
     * @param perFrameDelay the delay per frame.
     * @param loop          if the animation should loop or not.
     */
    public Animation(BufferedImage[] animations, int perFrameDelay, boolean loop) {
        frames = new BufferedImage[animations.length];
        System.arraycopy(animations, 0, frames, 0, frames.length);

        currentFrame = 0;
        frameCount = 0;

        this.frameDelay = perFrameDelay;
        this.loop = loop;
    }

    /**
     * Initialize the Animation.
     *
     * @param animations    the animation frames.
     * @param perFrameDelay the delay per frame.
     * @param loop          if the animation should loop or not.
     * @param ID            the animation ID.
     */
    public Animation(BufferedImage[] animations, int perFrameDelay, boolean loop, int ID) {
        this(animations, perFrameDelay, loop);
        this.ID = ID;
    }

    /**
     * Start the animation.
     */
    public void startAnimation() {

        currentFrame = 0;
        frameCount = 0;

        running = true;
    }

    /**
     * Stop the animation.
     */
    public void stopAnimation() {
        currentFrame = 0;
        frameCount = 0;

        running = false;
    }

    /**
     * Update the current frame.
     */
    public void updateAnimation() {
        if (!running) return;
        frameCount++;

        // make sure we've waiting long enough for the next frame.
        if (frameCount >= frameDelay) {
            frameCount = 0;

            currentFrame++;
            // animation is finished.
            if (currentFrame >= frames.length) {
                if (loop) {
                    startAnimation();
                    return;
                }
                stopAnimation();
                currentFrame--;
            }

        }
    }

    /**
     * Get the current frame.
     */
    public int getCurrentFrame() {
        return currentFrame;
    }

    /**
     * @return if the animation is running or not.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @return the ID of the animation.
     */
    public int getID() {
        return ID;
    }

    /**
     * Draw the current frame.
     */
    public void drawCurrentFrame(Graphics graphics, int x, int y) {
        graphics.drawImage(frames[currentFrame], x, y, null);
    }

    /**
     * Set if we should loop or not.
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
