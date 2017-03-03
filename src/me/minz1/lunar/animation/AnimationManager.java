package me.minz1.lunar.animation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AnimationManager {

	private List<Animation> animationInfo;

	/**
	 * Initialize the AnimationManager
	 * 
	 * @param animations
	 */
	public AnimationManager(Animation[] animations) {
		animationInfo = Arrays.asList(animations);

	}

	/**
	 * Initializes the AnimationManager
	 * 
	 * @param animations
	 */
	public AnimationManager(List<Animation> animations) {
		animationInfo = animations;
	}

	/**
	 * Get the animations.
	 * 
	 * @return
	 */
	public List<Animation> getAnimations() {
		return animationInfo;
	}

	/**
	 * Get the current playing Animation.
	 * 
	 * @return
	 */
	public Animation getCurrentPlayingAnimation() {
		Optional<Animation> stream = animationInfo.stream().filter(animation -> animation.isRunning()).findAny();
		return stream.isPresent() ? stream.get() : null;
	}

	/**
	 * Get the animation via ID.
	 * 
	 * @param ID
	 * @return
	 */
	public Animation getViaID(int ID) {
		Optional<Animation> stream = animationInfo.stream().filter(anim -> anim.getID() == ID).findAny();
		return stream.isPresent() ? stream.get() : null;
	}

	/**
	 * Start the animation, stops all other animations.
	 * 
	 * @param ID
	 */
	public void startAnimation(int ID) {
		Animation anim = getViaID(ID);
		if (anim.isRunning()) {
			return;
		}
		animationInfo.forEach(animation -> animation.stopAnimation());
		anim.startAnimation();

	}

	/**
	 * Start the animation, stops all other animations.
	 * 
	 * @param animation
	 */
	public void startAnimation(Animation anim) {
		if (anim.isRunning()) {
			return;
		}
		animationInfo.forEach(animation -> animation.stopAnimation());
		anim.startAnimation();
	}

}
