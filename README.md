# About Lunar
Lunar is a 2D game engine written in java. Its made for simplicity and ease of use. 
Some things to consider before using Lunar.

* Must be using java 8.
* This engine is not meant for:
	* 3D games, mobile, games with lots of physics.
* This engine is meant for:
	* Creating casual 2D games.
	* Creating prototypes fast.
	* Hobby developers.
* Lunar is still being worked on and is nowhere near finished.
* Development will slow overtime but will eventually pick back up.

Thank you for using Lunar.

# Lunar Tutorial

Lets begin!

The `Game` class is where everything in your project starts. It houses the gameloop, window preferences, and much more.
Lets start by creating new instance of `Game`.

```java
Game game = new Game(title, width, height, tickRate);
Game game = new Game(title, width, height, GameState, tickRate);
Game game = new Game(String title, int width, int height, FramePreferences pref, int tickRate);
Game game = new Game(String title, int width, int height, FramePreferences pref, GameState state, int tickRate)
```

* `title` is the title of the window.
* `tickRate` is the rate at which the game runs. Something good as a default is `60`.
* `GameState` is the default GameStart to start with, this is so you dont have to push one to the stack later.
* `FramePreferences` is an object which holds all settings for the window.

Now we need to 'start' the game.
Simply do ` game.start(); ` to start the game.

# Game States

GameStates are the base of your project. This is where you will store everything preferably. 
Things like worlds, entities, etc are stored here. GameStates also include two methods:
` onDraw(Graphics graphics); `
` onTick(); `

` onDraw(Graphics graphics); ` handles drawing things, not automatically though. Its up to you to handle what to call.
For example in the constructor of the drawing method you would draw the world.

` onTick(); ` handles updating things, stuff like entities, maybe the time, etc. This is also not automatic, its up to you.
For example in the constructor of the tick method you would update the world and its entities.

GameStates also include a priority field. This is the order in which GameStates are called.
If I had a GameState with a priority of 0, its getting called first. If I had another GameState with a priority of 3,
then everything before that is called first. This is useful for if your GameState isnt as important and doesn't need to be called
frequently. 

Here is an example of a basic GameState.

```java
public class MainState extends GameState {
	private Player myPlayer;
	private MyWorld world;

	public MainState(int priority) {
		super(priority);

		myPlayer = new Player(params);
		world = new MyWorld("world1", 600, 400);
		world.addEntity(player);

	}

	@Override
	public void onDraw(Graphics graphics) {
		world.onDraw(graphics);
	}

	@Override
	public void onTick() {
		player.updateEntity();
		world.onTick();
	}
}
```

# Entities
Entities are 'things'. For example this can be a player, monster or for example a chest, item, etc.
But living things are defined differently, you don't define them with the entity class.
For living things there is ` LivingEntity `.

Entities have the option for sprites/textures.
It's not always needed but for living things its required.
Lets create a simple player object.

```java
public class MyPlayer extends LivingEntity {
	public MyPlayer(int x, int y, int width, int height, int entityID, float health, double speed) {
		super(x, y, width, height, entityID, health, speed);
	}

	/**
	* Lets draw a simple red box that represents our player.
	*/
	@Override
	public void drawEntity(Graphics graphics) {
		graphics.setColor(Color.red);
		graphics.fillRect(x, y, width, height);
	}

	@Override
	public void updateEntity() {
		// update the entity, for example handle movement, collisions, etc.
	}
}
```

Lets go over the params.
* `x`, `y` is the starting coordinates of the player.
* `width`, `height` is the dimensions of the player.
* `entityID` is the unique ID of the entity. Useful for finding entities, filtering them, etc.
* `health` is the health of the player.
* `speed` is how fast the player should move.

# Sound
Playing sounds is very easy.
First start we must create a new ` Sound ` object.

```java
Sound mySound = new Sound(int ID, File audioFile);
```
* `ID` is the ID of the sound.
* `audioFile` is the file which contains the audio.

Now to play our sound we must create new instance of SoundManager. Its best to have already created one and stored it.

```java
mySoundManager.playAudio(mySound);
```

If creating a new Sound object is not feasible you can just use:
```java
mySoundManager.playAudio(audioFile);
```

Stopping the playback is easy too.
```java
mySoundManager.stopPlayingSound(sound);
```

# Input
Lunar provides mouse and keyboard support.

To see if a key is pressed do:
```java
InputListener.isKeyPressed(KeyEvent.KEY);
```

Checking if the mouse is down is simple too.
```java
MouseInput.isMouseDown(); 
```
Getting the point of the last click goes like this:
```java
MouseInput.getLastClick();
```

# Worlds
Worlds are essential for handling entities, tiles, etc.
Lets create a new World.

```java
public class MyWorld extends World {

	public MyWorld(String name, int width, int height) {
		super(name, width, height);
	}

	@Override
	public void onDraw(Graphics graphics) {

	}

	@Override
	public void onTick() {

	}
}
```
	- name is the name of the world.
	- width, height is the dimensions of it.

The World class holds many useful methods.
These include:
* adding/removing entities.
* Drawing all entities/tiles at once.
* etc.

# Tiles/Sprites
Tiles are also essential for the game. 
Tiles hold many values which gives it characteristics. 
These include the unique ID, texture, dimensions, if its solid, etc.

Lets start by creating a new tile with a custom texture.
First we must load our SpriteSheet.

```java
SpriteManager sm = new SpriteManager(SpriteManager.load("pathToSheet.png"));
```

Now we can get our custom texture. My texture is at 0, 0 and its 64x64.
```java
BufferedImage myTexture = sm.getSectionAt(0, 0, 64, 64);
```

Now lets create the tile.
```java
Tile tile = new Tile(myTexture, 0, true);
```

Now we have a tile with the ID 0 and our custom texture we loaded.
If we want to store all our tiles we can use the ` AssetManager `.
```java
AssetManager am = new AssetManager();
```

Lets store our tile:

```java
am.addTile(tile);
```

# Animations
The AnimationManager handles the animations.

Lets create a new SpriteManager with our character spritesheet.

```java
private SpriteManager characters = new SpriteManager(
			SpriteManager.load("C:\\Users\\Vrekt\\Desktop\\characters.png"));
```

Now we can start, lets create an animation for each direction.

```java
private Animation up;
private Animation down;
private Animation left;
private Animation right;
```

Now with the SpriteManager we can put the getMultipleSprites method to use.

```java
BufferedImage[] bup = characters.getMultipleSprites(0, 96, 32, 32, Direction.RIGHT, 3);
BufferedImage[] bdown = characters.getMultipleSprites(0, 0, 32, 32, Direction.RIGHT, 3);
BufferedImage[] bleft = characters.getMultipleSprites(0, 32, 32, 32, Direction.RIGHT, 3);
BufferedImage[] bright = characters.getMultipleSprites(0, 64, 32, 32, Direction.RIGHT, 3);
```

Now we can initialize the Animations.

```java
up = new Animation(bup, 20, true, 0);
down = new Animation(bdown, 20, true, 1);
left = new Animation(bleft, 20, true, 2);
right = new Animation(bright, 20, true, 3);
```

Lets add them to a list.

```java
List<Animation> animations = new ArrayList<Animation>();
animations.add(up);
animations.add(down);
animations.add(left);
animations.add(right);
```

Finally lets initialize the AnimationManager with the animations.

```java
am = new AnimationManager(animations);
```

In our player class we have a field named 'playerDirection' which keeps track of which way the player is facing, lets use this for our animation.

```java
Direction d = player.getDirectionFacing();
```

Now what we can do is start an animation based on which way we are facing.

```java
am.startAnimation(d == Direction.UP ? up : d == Direction.DOWN ? down : d == Direction.LEFT ? left : right);
```

Now lets draw the current frame of the animation.

```java
am.getCurrentPlayingAnimation().drawCurrentFrame(graphics, player.getX(), player.getY());
```

Now in our tick method we can update the animation.

```java
if (am.getCurrentPlayingAnimation() != null) {
	am.getCurrentPlayingAnimation().updateAnimation();
}
```

# Using the Capture utility.
The Capture class provides easy access to getting screenshots.
To screenshot do:

```java
Capture.screenshotAndSave(int width, int height, File saveTo, String imageType);
```
* `imageType` is the extension, for example .png. bmp.

This screenshots then it saves it.

Say we don't wanna screenshot the whole screen for that we can use ` screenshotPart `.

```java
Capture.screenshotPart(int x, int y, int width, int height);
```
