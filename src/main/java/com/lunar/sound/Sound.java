package com.lunar.sound;

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

    private String name;
    private File audio;

    private Clip clip;

    /**
     * Initialize the sound.
     *
     * @param name      The name of the audio.
     * @param audioPath The audio file's path.
     */
    public Sound(String name, String audioPath) {
        this(name, new File(audioPath));
    }

    /**
     * Initialize the sound.
     *
     * @param name  The name.
     * @param audio The audio file.
     */
    public Sound(String name, File audio) {
        this.name = name;
        this.audio = audio;
    }

    /**
     * @return the name of the audio.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the audio file.
     */
    public File getAudio() {
        return audio;
    }

    /**
     * @return the audio clip.
     */
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
        if (this.isPlaying()) { // Can't unload mname-playback
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
        return clip != null && clip.isRunning();
    }

    /**
     * Returns true after load() has been called but before stop() has been called.
     *
     * @return Whether or not this object has loaded its resources.
     */
    public boolean isLoaded() {
        return clip != null && clip.isOpen();
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.STOP) {
            clip.close();
        }
    }
}
