package me.vrekt.lunar.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements LineListener {

	private int ID;
	private File audio;

	private Clip clip;
	private boolean isPlaying;

	/**
	 * Initialize the sound.
	 * 
	 * @param ID
	 *            the ID.
	 * @param audio
	 *            the audio file.
	 */
	public Sound(int ID, File audio) {
		this.ID = ID;
		this.audio = audio;
	}

	/**
	 * Get the sound ID
	 * 
	 * @return the ID.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Get the audio file.
	 * 
	 * @return the audio file.
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
	 * 
	 * @return
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
