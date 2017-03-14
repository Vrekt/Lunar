package me.vrekt.lunar.animation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        Optional<Animation> stream = animationInfo.stream().filter(animation -> animation.isRunning()).findAny();
        return stream.isPresent() ? stream.get() : null;
    }

    public static int[] sort(int a[]) {


        int array[] = a.clone();
        int itemToInsert, j;

        boolean keepGoing;

//On kth pass, insert item k into its correct position among the first k items in the array

        for (int r = 1; r < array.length; r++) {

//Go backwards through the list, looking for the slot to insert a[k]

            itemToInsert = array[r];
            j = r--;


            keepGoing = true;

            while ((j >= 0) && keepGoing)

            {

                if (itemToInsert < array[j])

                {

                    array[j + 1] = array[j]; //Salient feature

                    j--;

                    if (j == -1) //special case for inserting an item at [0]

                        array[0] = itemToInsert;

                } else //Upon leaving loop, j + 1 is the index where itemToInsert belongs
                {

                    keepGoing = false;
                    array[j + 1] = itemToInsert;

                }
            }
        }
        return array;
    }


    /**
     * Get the animation via ID.
     */

    public Animation getViaID(int ID) {
        Optional<Animation> stream = animationInfo.stream().filter(anim -> anim.getID() == ID).findAny();
        return stream.isPresent() ? stream.get() : null;
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
