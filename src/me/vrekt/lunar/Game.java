package me.vrekt.lunar;

import me.vrekt.lunar.input.InputListener;
import me.vrekt.lunar.input.MouseInput;
import me.vrekt.lunar.state.GameState;
import me.vrekt.lunar.window.FramePreferences;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable {
    private JFrame frame;
    private int width, height, fps;

    private Thread thread;
    private boolean running = true;

    private Graphics graphics;

    private List<GameState> stack;
    private int ticks = 0;

    /**
     * Initialize the project.
     *
     * @param title    The string on the window's title bar.
     * @param width    Width of the window
     * @param height   Height of the window
     * @param tickRate Determines how fast the game loop is.
     */
    public Game(String title, int width, int height, int tickRate) {
        this.width = width;
        this.height = height;

        stack = new ArrayList<>();

        this.ticks = tickRate;

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setFocusable(true);

        frame.addKeyListener(new InputListener());
        frame.addMouseListener(new MouseInput());
    }

    /**
     * Initialize the project.
     *
     * @param title    The string on the window's title bar.
     * @param width    Width of the window
     * @param state    A game state (if you have that)
     * @param height   Height of the window
     * @param tickRate Determines how fast the game loop is.
     */
    public Game(String title, int width, int height, GameState state, int tickRate) {
        this.width = width;
        this.height = height;

        stack = new ArrayList<>();
        addToStack(state);

        this.ticks = tickRate;

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setFocusable(true);

        frame.addKeyListener(new InputListener());
        frame.addMouseListener(new MouseInput());
    }

    /**
     * Initialize the game.
     *
     * @param title    The string on the window's title bar.
     * @param width    Width of the window
     * @param height   Height of the window
     * @param pref     The window preferences.
     * @param tickRate Determines how fast the game loop is.
     */
    public Game(String title, int width, int height, FramePreferences pref, int tickRate) {
        this.width = width;
        this.height = height;

        stack = new ArrayList<>();

        this.ticks = tickRate;

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(pref.getCloseOperation());
        frame.setLocationRelativeTo(pref.getRelativeLocation());
        frame.setResizable(pref.isResizable());
        frame.setFocusable(pref.isFocusable());

        frame.addKeyListener(new InputListener());
        frame.addMouseListener(new MouseInput());
    }

    /**
     * Initialize the game.
     *
     * @param title    The string on the window's title bar.
     * @param width    Width of the window
     * @param state    A game state (if you have that)
     * @param height   Height of the window
     * @param pref     The window preferences.
     * @param tickRate Determines how fast the game loop is.
     */
    public Game(String title, int width, int height, FramePreferences pref, GameState state, int tickRate) {
        this.width = width;
        this.height = height;

        stack = new ArrayList<>();
        addToStack(state);

        this.ticks = tickRate;

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(pref.getCloseOperation());
        frame.setLocationRelativeTo(pref.getRelativeLocation());
        frame.setResizable(pref.isResizable());
        frame.setFocusable(pref.isFocusable());

        frame.addKeyListener(new InputListener());
        frame.addMouseListener(new MouseInput());
    }

    /**
     * Start the game thread.
     */

    public synchronized void start() {
        frame.setVisible(true);
        frame.createBufferStrategy(3);

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Stop the game thread.
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
        BufferStrategy frameStrategy = frame.getBufferStrategy();
        if (frameStrategy == null) {
            frame.createBufferStrategy(3);
            frameStrategy = frame.getBufferStrategy();
        }
        graphics = frameStrategy.getDrawGraphics();
        graphics.clearRect(0, 0, width, height);

        stack.forEach(state -> state.onDraw(graphics));

        graphics.dispose();
        frameStrategy.show();

    }

    /**
     * Update all game objects.
     */

    private void onTick() {
        stack.forEach(GameState::onTick);
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
     */
    public void addToStack(GameState state) {
        stack.add(state);
        stack.sort((state1, state2) -> {

            if (state1.getPriority() < state2.getPriority()) {
                return 1;
            }

            if (state1.getPriority() > state2.getPriority()) {
                return -1;
            }

            return 0;
        });
    }

    /**
     * Remove the state from the stack.
     *
     * @param state the state that should be removed from the game stack.
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

    /**
     * Add a key listener
     */
    public void addKeyListener(KeyListener listener) {
        frame.addKeyListener(listener);
    }

    /**
     * Clear all key listeners. NOTE: this method will also remove the default listener.
     */
    public void clearKeyListeners() {
        KeyListener[] listeners = frame.getKeyListeners();
        for (KeyListener listener : listeners) {
            removeKeyListener(listener);
        }
    }

    /**
     * Remove the given key listener
     */
    public void removeKeyListener(KeyListener listener) {
        frame.removeKeyListener(listener);
    }

    /**
     * Add a mouse listener
     */
    public void addMouseListener(MouseListener listener) {
        frame.addMouseListener(listener);
    }

    /**
     * Clear all mouse listeners. NOTE: this method will also remove the default listener.
     */
    public void clearMouseListeners() {
        MouseListener[] listeners = frame.getMouseListeners();
        for (MouseListener listener : listeners) {
            removeMouseListener(listener);
        }
    }

    /**
     * Remove the given mouse listener
     */
    public void removeMouseListener(MouseListener listener) {
        frame.removeMouseListener(listener);
    }
}
