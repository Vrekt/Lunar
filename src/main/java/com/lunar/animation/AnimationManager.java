package com.lunar.animation;

import com.lunar.utilities.Logger;
import com.sun.istack.internal.Nullable;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimationManager {

    private List<Animation> animationFrames;
    private Animation playingAnimation;

    public AnimationManager(Animation[] animations) {
        animationFrames = Arrays.asList(animations);
    }

    public AnimationManager(List<Animation> animations) {
        animationFrames = new ArrayList<>(animations);
    }

    public AnimationManager(Animation animation) {
        animationFrames = new ArrayList<>();
        animationFrames.add(animation);
    }

    /**
     * @return all the animations.
     */
    public List<Animation> getAnimationFrames() {
        return animationFrames;
    }

    /**
     * @return the current playing animation.
     */
    @Nullable
    public Animation getPlayingAnimation() {
        return playingAnimation == null ? null : playingAnimation.isRunning() ? playingAnimation : null;
    }

    /**
     * Update the current playing animation.
     */
    public void updatePlayingAnimation() {
        if (playingAnimation == null) {
            Logger.logWarning("Attempted to update a null animation frame.");
            return;
        }
        playingAnimation.updateAnimation();

    }

    /**
     * Draw the current playing animation.
     *
     * @param graphics the graphics object
     * @param x        the X coordinate.
     * @param y        the Y coordinate.
     */
    public void drawPlayingAnimation(Graphics graphics, int x, int y) {
        if (playingAnimation == null) {
            Logger.logWarning("Attempted to draw a null animation frame.");
            return;
        }
        playingAnimation.drawCurrentFrame(graphics, x, y);

    }

    /**
     * @return if we have an animation playing.
     */
    public boolean hasPlayingAnimation() {
        return playingAnimation != null && playingAnimation.isRunning();
    }

    /**
     * @param id the animation ID.
     * @return the animation with the supplied ID.
     */
    @Nullable
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
        if (animation == null) {
            Logger.logWarning("Attempted to play a null animation frame.");
            return;
        }

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
        if (animation == null) {
            Logger.logWarning("Attempted to play a null animation frame.");
            return;
        }

        if (animation.isRunning()) {
            return;
        }
        animationFrames.forEach(Animation::stopAnimation);
        animation.startAnimation();
        playingAnimation = animation;
    }
}
