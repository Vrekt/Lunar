# About Lunar
Lunar is a 2D game engine written in java. Its made for simplicity and ease of use. 
Some things to consider before using Lunar.

	- Must be using java 8.
	- This engine is not meant for:
		- 3D games, mobile, games with lots of physics.
	- This engine is meant for:
		- Creating casual 2D games.
		- Creating prototypes fast.
		- Hobby developers.
	- Lunar is still being worked on and is nowhere near finished.
	- Development will slow overtime but will eventually pick back up.

Thank you for using Lunar.

# Lunar Tutorial

Lets begin!

The `Game` class is where everything in your game starts. It houses the gameloop, window preferences, and much more.
Lets start by creating new instance of `Game`.

```java
Game game = new Game(title, width, height, tickRate);
Game game = new Game(title, width, height, GameState, tickRate);
Game game = new Game(String title, int width, int height, FramePreferences pref, int tickRate);
Game game = new Game(String title, int width, int height, FramePreferences pref, GameState state, int tickRate)
```
         - title is the title of the window.
	 - width, height is the dimensions of the window.
	 - tickRate is the rate at which the game runs. Something good as a default is 60.
	 - GameState is the default GameStart to start with, this is so you dont have to push one to the stack later.
	 - FramePreferences is an object which holds all settings for the window.
	

