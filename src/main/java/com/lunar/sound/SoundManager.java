package com.lunar.sound;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SoundManager {

    private final List<Sound> GAME_SOUNDS = new ArrayList<>();

    /**
     * Adds a sound.
     *
     * @param sound The sound Object.
     */
    public void addSound(Sound sound) {
        GAME_SOUNDS.add(sound);
    }

    /**
     * Adds a sound.
     *
     * @param ID   The ID
     * @param file The audio file.
     */
    public void addSound(int ID, File file) {
        GAME_SOUNDS.add(new Sound(ID, file));
    }

    /**
     * Gets a sound via ID.
     *
     * @param ID The ID of the Sound.
     */
    public Sound getSound(int ID) {
        return GAME_SOUNDS.stream().filter(sound -> sound.getID() == ID).findAny().orElse(null);
    }

    /**
     * Check if the sound exists.
     *
     * @return if the sound exists.
     */
    public boolean doesSoundExist(int ID) {
        return getSound(ID) != null;
    }

    /**
     * Removes a sound from the list.
     *
     * @param ID The ID of the Sound.
     */
    public void removeSound(int ID) {
        Sound s = getSound(ID);
        if (GAME_SOUNDS.contains(s)) {
            GAME_SOUNDS.remove(s);
        }
    }

    /**
     * Remove the sound.
     */
    public void removeSound(Sound sound) {
        if (GAME_SOUNDS.contains(sound)) {
            GAME_SOUNDS.remove(sound);
        }
    }

    /**
     * Plays a sound with a clip.
     *
     * @param sound The Sound object.
     */
    public void playSound(Sound sound) {
        sound.play();
    }

    /**
     * Plays a sound with a clip.
     */
    public void playSound(int ID) {
        getSound(ID).play();
    }

    /**
     * Plays a sound with a clip. This does not support stopping.
     *
     * @param file The audio file.
     */
    public void playSound(File file) {
        Sound sound = new Sound(0, file);
        sound.play();
    }

    /**
     * Stop playing the sound.
     */
    public void stopPlayingSound(Sound sound) {
        sound.stop();
    }

    /**
     * Stop playing the sound.
     */
    public void stopPlayingSound(int ID) {
        getSound(ID).stop();
    }

}
