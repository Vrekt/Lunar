package me.vrekt.lunar.animation;

import java.awt.*;
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
	 * Initialize the animation.
	 *
	 * @param animations
	 * @param perFrameDelay
	 * @param loop
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
	 * Initializes the Animation with an ID. Only use this if you're using the
	 * AnimationManager.
	 *
	 * @param animations
	 * @param perFrameDelay
	 * @param loop
	 * @param ID
	 */
	public Animation(BufferedImage[] animations, int perFrameDelay, boolean loop, int ID) {
		frames = new BufferedImage[animations.length];
		System.arraycopy(animations, 0, frames, 0, frames.length);

		currentFrame = 0;
		frameCount = 0;

		this.frameDelay = perFrameDelay;
		this.loop = loop;
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
		if (running) {
			frameCount++;

			if (frameCount >= frameDelay) {
				frameCount = 0;

				currentFrame++;
				if (currentFrame >= frames.length) {
					if (loop) {
						startAnimation();
					} else {
						stopAnimation();
						currentFrame--;
					}
				}

			}

		}

	}

	/**
	 * Get the current frame.
	 *
	 * @return
	 */
	public int getCurrentFrame() {
		return currentFrame;
	}

	/**
	 * Check if the animation is running.
	 *
	 * @return
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Get the ID.
	 *
	 * @return
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Draw the current frame.
	 *
	 * @param graphics
	 * @param x
	 * @param y
	 */
	public void drawCurrentFrame(Graphics graphics, int x, int y) {
		graphics.drawImage(frames[currentFrame], x, y, null);
	}

	/**
	 * Set if we should loop or not.
	 *
	 * @param loop
	 */
	public void setLoop(boolean loop) {
		this.loop = loop;
	}

}
