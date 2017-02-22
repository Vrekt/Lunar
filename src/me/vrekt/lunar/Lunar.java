package me.vrekt.lunar;

import me.vrekt.lunar.sound.SoundManager;
import me.vrekt.lunar.state.GameState;

public class Lunar {

	private Game game;
	private SoundManager soundManager;

	/**
	 * Initialize the game.
	 * 
	 * @param title
	 * @param width
	 * @param height
	 * @param tickRate
	 */
	public void initializeGame(String title, int width, int height, int tickRate) {
		game = new Game(title, width, height, tickRate);

		soundManager = new SoundManager();

	}

	/**
	 * Initialize the game.
	 * 
	 * @param title
	 * @param width
	 * @param height
	 * @param state
	 * @param tickRate
	 */
	public void initializeGame(String title, int width, int height, GameState state, int tickRate) {
		game = new Game(title, width, height, state, tickRate);

		soundManager = new SoundManager();

	}

	/**
	 * Get the game object.
	 * 
	 * @return game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Get the soundManager.
	 * 
	 * @return soundManager
	 */
	public SoundManager getSoundManager() {
		return soundManager;
	}

}
