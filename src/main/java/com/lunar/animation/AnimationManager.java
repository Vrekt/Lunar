package com.lunar.animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimationManager {

    private List<Animation> animationFrames;
    private Animation playingAnimation;

    /**
     * Initialize the AnimationManager
     */
    public AnimationManager(Animation[] animations) {
        animationFrames = Arrays.asList(animations);
    }

    /**
     * Initializes the AnimationManager
     */
    public AnimationManager(List<Animation> animations) {
        animationFrames = new ArrayList<>(animations);
    }

    /**
     * @return all the animations.
     */
    public List<Animation> getAnimationFrames() {
        return animationFrames;
    }

    /**
     * @return the current animation frame that's playing.
     */
    public Animation getPlayingAnimation() {
        return playingAnimation;
    }

    /**
     * @param id the animation ID.
     * @return the animation with the supplied ID.
     */
    public Animation getAnimationByID(int id) {
        return animationFrames.stream().filter(anim -> anim.getID() == id).findAny().orElse(null);
    }

    /**
     * Play an animation, this will also stop all other animations that are currently playing.
     *
     * @param id the animation ID.
     */
    public void playAnimation(int id) {
        Animation animation = getAnimationByID(id);
        if (animation.isRunning()) {
            return;
        }
        animationFrames.forEach(Animation::stopAnimation);
        animation.startAnimation();
        playingAnimation = animation;
    }

    /**
     * Start the animation, stops all other animations.
     *
     * @param animation the animation to play.
     */
    public void playAnimation(Animation animation) {
        if (animation.isRunning()) {
            return;
        }
        animationFrames.forEach(Animation::stopAnimation);
        animation.startAnimation();
        playingAnimation = animation;
    }
}
