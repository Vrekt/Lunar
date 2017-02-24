package me.vrekt.lunar.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

	private int frameCount;
	private int currentFrame;
	private int frameDelay;

	private boolean running = false;
	private BufferedImage[] frames;

	private boolean loop = false;

	/**
	 * Initialize the animation.
	 * 
	 * @param animations
	 * @param perFrameDelay
	 * @param loop
	 */
	public Animation(BufferedImage[] animations, int perFrameDelay, boolean loop) {
		frames = new BufferedImage[animations.length];
		for (int i = 0; i < frames.length; i++) {
			frames[i] = animations[i];
		}

		currentFrame = 0;
		frameCount = 0;

		this.frameDelay = perFrameDelay;
		this.loop = loop;

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
						currentFrame = 0;
						return;
					}
					stopAnimation();
					currentFrame--;
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
