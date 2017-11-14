package com.lunar.animation;

import java.util.Arrays;
import java.util.List;

public class AnimationManager {

    private List<Animation> animationInfo;

    /**
     * Initialize the AnimationManager
     */
    public AnimationManager(Animation[] animations) {
        animationInfo = Arrays.asList(animations);
    }

    /**
     * Initializes the AnimationManager
     */
    public AnimationManager(List<Animation> animations) {
        animationInfo = animations;
    }

    /**
     * @return all the animations.
     */
    public List<Animation> getAnimations() {
        return animationInfo;
    }

    /**
     * @return the current animation frame that's playing.
     */
    public Animation getCurrentPlayingAnimation() {
        return animationInfo.stream().filter(Animation::isRunning).findAny().orElse(null);
    }

    /**
     * @param ID the animation ID.
     * @return the animation with the supplied ID.
     */
    public Animation getViaID(int ID) {
        return animationInfo.stream().filter(anim -> anim.getID() == ID).findAny().orElse(null);
    }

    /**
     * Start the animation, stops all other animations.
     */
    public void startAnimation(int ID) {
        Animation anim = getViaID(ID);
        if (anim.isRunning()) {
            return;
        }
        animationInfo.forEach(Animation::stopAnimation);
        anim.startAnimation();
    }

    /**
     * Start the animation, stops all other animations.
     *
     * @param anim the animation
     */
    public void startAnimation(Animation anim) {
        if (anim.isRunning()) {
            return;
        }
        animationInfo.forEach(Animation::stopAnimation);
        anim.startAnimation();
    }
}
