package me.vrekt.lunar.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Sound implements LineListener {

	private int ID;
	private File audio;

	private Clip clip;

    /**
     * Initialize the sound.
     *
     * @param ID        The ID.
     * @param audioPath The audio file's path.
     */
    public Sound(int ID, String audioPath) {
        this(ID, new File(audioPath));
    }

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
     * This method is called automatically by play().
     * The only reason for an end user to call load()
     * is if they wish to provide custom error handling for
     * any of the exceptions that play() would otherwise log to the console.
     * <p>
     * Note: this method opens the Clip object that this object holds, so trying to call load()
     * on another Sound object using the same file path while this object's isLoaded() returns
     * true may cause problems.
     *
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public void load() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream stream = AudioSystem.getAudioInputStream(audio);
        AudioFormat format = stream.getFormat();

        DataLine.Info fi = new DataLine.Info(Clip.class, format);
        this.clip = (Clip) AudioSystem.getLine(fi);
        clip.addLineListener(this);
        clip.open(stream);
    }

    /**
     * If providing custom exception handling
     * for load(), call unload() after an exception has been caught.
     * Otherwise, there is no reason for an end user to call this method, as
     * it is called automatically by stop().
     *
     * @return A boolean indicating whether or not the operation was successful.
     */
    public boolean unload() {
        if (this.isPlaying()) { // Can't unload mid-playback
            return false;
        }
        if (clip != null) {
            clip = null;
        }
        return true;
    }

    /**
     * Play the audio.
     */
    public void play() {
        try {
            if (!isLoaded()) {
                load();
            }
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println("ERROR PLAYING SOUND. /n");
            e.printStackTrace();
            this.stop();
        }
    }

	/**
	 * Stop playing the audio.
     */
    public void stop() {
        if (clip != null) {
            if (clip.isOpen()) {
                clip.close();
            }
            if (clip.isRunning()) {
                clip.stop();
            }
            this.unload();
        }
    }

	/**
	 * Get if we are playing.
     */
    public boolean isPlaying() {
        if (clip != null) {
            return clip.isRunning();
        } else {
            return false;
        }
    }

    /**
     * Returns true after load() has been called but before stop() has been called.
     *
     * @return Whether or not this object has loaded its resources.
     */
    public boolean isLoaded() {
        if (clip != null) {
            return clip.isOpen();
        } else {
            return false;
        }
    }

	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();

		if (type == LineEvent.Type.STOP) {
			clip.close();
		}
	}
}
