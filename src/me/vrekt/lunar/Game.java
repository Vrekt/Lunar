package me.vrekt.lunar;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;

import me.vrekt.lunar.input.InputListener;
import me.vrekt.lunar.state.GameState;

public class Game implements Runnable {

	private JFrame frame;
	private int width, height, fps;

	private Thread thread;
	private boolean running = true;

	private Graphics graphics;
	private BufferStrategy frameStrat;

	private List<GameState> stack;

	private int ticks = 0;

	/**
	 * Initialize the project.
	 * 
	 * @param title
	 * @param width
	 * @param height
	 * @param useStack
	 *            if you have multiple GameStates that need updated every
	 *            tick/draw.
	 * @param tickRate
	 *            how fast the game loop is.
	 */
	public Game(String title, int width, int height, int tickRate) {
		this.width = width;
		this.height = height;

		stack = new ArrayList<GameState>();

		this.ticks = tickRate;

		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setFocusable(true);

		frame.addKeyListener(new InputListener());

	}

	/**
	 * Initialize the project.
	 * 
	 * @param title
	 * @param width
	 * @param height
	 * @param state
	 * @param useStack
	 *            if you have multiple GameStates that need updated every
	 *            tick/draw.
	 * @param tickRate
	 *            how fast the game loop is.
	 */
	public Game(String title, int width, int height, GameState state, int tickRate) {
		this.width = width;
		this.height = height;

		stack = new ArrayList<GameState>();
		addToStack(state);

		this.ticks = tickRate;

		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setFocusable(true);

		frame.addKeyListener(new InputListener());

	}

	/**
	 * Start the thread.
	 */

	public synchronized void start() {

		frame.setVisible(true);
		frame.createBufferStrategy(3);

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Stop the thread.
	 */

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * The game-loop, handles drawing, updating, etc.
	 */

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double ticksDelta = 1000000000 / ticks;
		double d = 0;

		long now = System.currentTimeMillis();
		int frCount = 0;

		while (running) {
			long current = System.nanoTime();
			d += (current - lastTime) / ticksDelta;
			lastTime = current;
			while (d >= 1) {
				onTick();
				d--;
			}

			onDraw();
			frCount++;

			if (System.currentTimeMillis() - now >= 1000) {
				now += 1000;
				fps = frCount;
				frCount = 0;
			}

		}
		stop();
	}

	/**
	 * Draw all game objects.
	 */

	private void onDraw() {
		frameStrat = frame.getBufferStrategy();
		graphics = frameStrat.getDrawGraphics();
		graphics.clearRect(0, 0, width, height);

		stack.forEach(state -> state.onDraw(graphics));

		graphics.dispose();
		frameStrat.show();

	}

	/**
	 * Update all game objects.
	 */

	private void onTick() {

		stack.forEach(state -> state.onTick());

	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the FPS
	 */
	public int getFPS() {
		return fps;
	}

	/**
	 * Add a state to the stack.
	 * 
	 * @param state
	 */
	public void addToStack(GameState state) {
		stack.add(state);
		stack.sort(new Comparator<GameState>() {

			@Override
			public int compare(GameState state1, GameState state2) {

				if (state1.priority < state2.priority) {
					return 1;
				}

				if (state1.priority > state2.priority) {
					return -1;
				}

				return 0;
			}
		});
	}

	/**
	 * Remove the state from the stack.
	 * 
	 * @param state
	 */
	public void removeFromStack(GameState state) {
		stack.remove(state);
	}

	/**
	 * Clear the stack.
	 */
	public void clearStack() {
		stack.clear();
	}

}
