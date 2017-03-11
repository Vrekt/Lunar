package me.vrekt.lunar.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound implements LineListener {

	private int ID;
	private File audio;

	private Clip clip;
	private boolean isPlaying;

	/**
	 * Initialize the sound.
	 *
	 * @param ID    The ID.
	 * @param audio The audio file.
	 */
	public Sound(int ID, File audio) {
		this.ID = ID;
		this.audio = audio;
	}

	/**
	 * Get the sound ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Get the audio file.
	 */
	public File getAudio() {
		return audio;
	}

	public Clip getClip() {
		return clip;
	}

	/**
	 * Play the audio.
	 */
	public void play() {
		try {
			isPlaying = true;
			AudioInputStream stream = AudioSystem.getAudioInputStream(audio);
			AudioFormat format = stream.getFormat();

			DataLine.Info fi = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(fi);
			this.clip = clip;

			clip.addLineListener(this);
			clip.open(stream);
			clip.start();

		} catch (UnsupportedAudioFileException e) {
			System.out.println("ERROR PLAYING SOUND. /n");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR PLAYING SOUND. /n");
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			System.out.println("ERROR PLAYING SOUND. /n");
			e.printStackTrace();
		}
	}

	/**
	 * Stop playing the audio.
	 */
	public void stop() {
		if (isPlaying) {
			clip.stop();
			clip.close();
			isPlaying = false;
		}
	}

	/**
	 * Get if we are playing.
	 */
	public boolean isPlaying() {
		return isPlaying;
	}

	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();

		if (type == LineEvent.Type.STOP) {
			isPlaying = false;
			clip.close();
		}
	}
}
