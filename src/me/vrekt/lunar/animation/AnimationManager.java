package me.vrekt.lunar.animation;

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
     * Get the animations.
     */
    public List<Animation> getAnimations() {
        return animationInfo;
    }

    /**
     * Get the current playing Animation.
     */
    public Animation getCurrentPlayingAnimation() {
        return animationInfo.stream().filter(animation -> animation.isRunning()).findAny().orElse(null);
    }

    /**
     * Get the animation via ID.
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
        animationInfo.forEach(animation -> animation.stopAnimation());
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
        animationInfo.forEach(animation -> animation.stopAnimation());
        anim.startAnimation();
    }
}
