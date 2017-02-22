package me.vrekt.lunar.sound;

import java.io.File;

public class Sound {

	private int ID;
	private File audio;

	/**
	 * Initialize the sound.
	 * 
	 * @param ID
	 * @param audio
	 */
	public Sound(int ID, File audio) {
		this.ID = ID;
		this.audio = audio;
	}

	/**
	 * Get the sound ID
	 * 
	 * @return
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Get the audio file.
	 * 
	 * @return
	 */
	public File getAudio() {
		return audio;
	}

}
