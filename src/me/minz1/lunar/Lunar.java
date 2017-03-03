package me.minz1.lunar;

import me.minz1.lunar.asset.AssetManager;
import me.minz1.lunar.sound.SoundManager;
import me.minz1.lunar.state.GameState;

public class Lunar {

	private Game game;
	private SoundManager soundManager;
	private AssetManager assetManager;

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
		assetManager = new AssetManager();

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
		assetManager = new AssetManager();

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

	/**
	 * Get the assetManager
	 * 
	 * @return assetManager
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}

}
