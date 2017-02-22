package me.vrekt.lunar.sound;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SoundManager {

	private List<Sound> gameSounds = new ArrayList<Sound>();

	/**
	 * Adds a sound.
	 * 
	 * @param sound
	 */
	public void addSound(Sound sound) {
		gameSounds.add(sound);
	}

	/**
	 * Adds a sound.
	 * 
	 * @param ID
	 * @param file
	 */
	public void addSound(int ID, File file) {
		gameSounds.add(new Sound(ID, file));
	}

	/**
	 * Gets a sound via ID.
	 * 
	 * @param ID
	 * @return
	 */
	public Sound getSound(int ID) {
		Optional<Sound> stream = gameSounds.stream().filter(sound -> sound.getID() == ID).findAny();
		return stream.isPresent() ? stream.get() : null;
	}

	/**
	 * Removes a sound from the list.
	 * 
	 * @param ID
	 */
	public void removeSound(int ID) {
		Optional<Sound> stream = gameSounds.stream().filter(sound -> sound.getID() == ID).findAny();
		if (stream.isPresent()) {
			gameSounds.remove(stream.get());
		}
	}

	/**
	 * Plays a sound with a clip.
	 * 
	 * @param sound
	 */
	public void playSound(Sound sound) {
		PlayingSound playing = new PlayingSound();
		playing.play(sound);
	}

	/**
	 * Plays a sound with a clip.
	 * 
	 * @param file
	 */
	public void playSound(File file) {
		PlayingSound playing = new PlayingSound();
		playing.play(file);
	}

}
