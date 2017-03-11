package me.vrekt.lunar;

import me.vrekt.lunar.asset.AssetManager;
import me.vrekt.lunar.sound.SoundManager;
import me.vrekt.lunar.state.GameState;

public class Lunar {
	private Game game;
	private SoundManager soundManager;
	private AssetManager assetManager;

	/**
	 * Initialize the game.
	 */
	public void initializeGame(String title, int width, int height, int tickRate) {
		game = new Game(title, width, height, tickRate);

		soundManager = new SoundManager();
		assetManager = new AssetManager();
	}

	/**
	 * Initialize the game.
	 */
	public void initializeGame(String title, int width, int height, GameState state, int tickRate) {
		game = new Game(title, width, height, state, tickRate);

		soundManager = new SoundManager();
		assetManager = new AssetManager();
	}

	/**
	 * Returns the game object of this instance of lunar.
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Returns the soundManager.
	 */
	public SoundManager getSoundManager() {
		return soundManager;
	}

	/**
	 * Returns the assetManager
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}
}
