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

@Deprecated
public class PlayingSound implements LineListener {

	private Clip playing;

	/**
	 * Play the audio.
	 * 
	 * @param sound
	 *            the sound object.
	 */
	public void play(Sound sound) {
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(sound.getAudio());
			AudioFormat format = stream.getFormat();

			DataLine.Info fi = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(fi);
			this.playing = clip;

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
	 * Play the audio.
	 * 
	 * @param file
	 *            the audio file.
	 */
	public void play(File file) {
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			AudioFormat format = stream.getFormat();

			DataLine.Info fi = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(fi);
			this.playing = clip;

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

	public void stopClip() {
		playing.stop();
		playing.close();
	}

	/**
	 * Checks if the sound has stopped playing and closes the Clip.
	 */
	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();

		if (type == LineEvent.Type.STOP) {
			playing.close();
		}

	}

}
