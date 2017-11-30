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
     * @param name The name of the audio.
     * @param file The audio file.
     */
    public void addSound(String name, File file) {
        GAME_SOUNDS.add(new Sound(name, file));
    }

    /**
     * Gets a sound via ID.
     *
     * @param name The name of the Sound.
     */
    public Sound getSound(String name) {
        return GAME_SOUNDS.stream().filter(sound -> sound.getName().equals(name)).findAny().orElse(null);
    }

    /**
     * Check if the sound exists.
     *
     * @return if the sound exists.
     */
    public boolean doesSoundExist(String name) {
        return getSound(name) != null;
    }

    /**
     * Removes a sound from the list.
     *
     * @param name The ID of the Sound.
     */
    public void removeSound(String name) {
        Sound s = getSound(name);
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
    public void playSound(String name) {
        getSound(name).play();
    }

    /**
     * Plays a sound with a clip. This does not support stopping.
     *
     * @param file The audio file.
     */
    public void playSound(File file) {
        Sound sound = new Sound("TEMP_" + file.getName(), file);
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
    public void stopPlayingSound(String name) {
        getSound(name).stop();
    }

}
