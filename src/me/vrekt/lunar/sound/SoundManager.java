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
	 *            the sound Object.
	 */
	public void addSound(Sound sound) {
		gameSounds.add(sound);
	}

	/**
	 * Adds a sound.
	 * 
	 * @param ID
	 *            the ID
	 * @param file
	 *            the audio file.
	 */
	public void addSound(int ID, File file) {
		gameSounds.add(new Sound(ID, file));
	}

	/**
	 * Gets a sound via ID.
	 * 
	 * @param ID
	 *            the ID of the Sound.
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
	 *            the ID of the Sound.
	 */
	public void removeSound(int ID) {
		Sound s = getSound(ID);
		if (gameSounds.contains(s)) {
			gameSounds.remove(s);
		}
	}

	/**
	 * Remove the sound.
	 * 
	 * @param sound
	 */
	public void removeSound(Sound sound) {
		if (gameSounds.contains(sound)) {
			gameSounds.remove(sound);
		}
	}

	/**
	 * Plays a sound with a clip.
	 * 
	 * @param sound
	 *            the Sound object.
	 */
	public void playSound(Sound sound) {
		sound.play();
	}

	/**
	 * Plays a sound with a clip.
	 * 
	 * @param ID
	 */
	public void playSound(int ID) {
		getSound(ID).play();
	}

	/**
	 * Plays a sound with a clip. This does not support stopping.
	 * 
	 * @param file
	 *            the audio file.
	 */
	public void playSound(File file) {
		Sound sound = new Sound(0, file);
		sound.play();
	}

	/**
	 * Stop playing the sound.
	 * 
	 * @param sound
	 */
	public void stopPlayingSound(Sound sound) {
		sound.stop();
	}

	/**
	 * Stop playing the sound.
	 * 
	 * @param ID
	 */
	public void stopPlayingSound(int ID) {
		getSound(ID).stop();
	}

}
