# Getting Started!
To start you want to initialize the Lunar class.
```java
Lunar lunar = new Lunar();
```

This class holds everything you will need for your game.
Alternatively you can create a direct object for Game as well as the SoundManager and AssetManager.

```java
Game game = new Game(title, width, height, tickRate);
Game game = new Game(title, width, height, GameState, tickRate);

SoundManager sm = new SoundManager();
AssetManager am = new AssetManager();
```

Using the ` lunar ` object starting the game is simple.
```java
lunar.initializeGame(title, width, height, tickRate);
lunar.initializeGame(title, width, height, GameState, tickRate);

lunar.getGame().start();
```

Using the ` game ` object starting the game is just as easy.
```java
game.start();
```

Lets go over the fields.
title - This indicates the title of the window.
width, height - the dimensions of the window.

tickRate indicates how fast the game is drawn/updated. A good tickrate is 64 or above.

gameState; this is not required when starting the game. Choosing to add this will not require you to add a gameState manually.

# GameStates

GameStates are the base of your game. Each GameState will hold two methods ` onTick(); ` and ` onDraw(Graphics graphics); `.
In your gameState you may keep track of worlds, players and other entities.
Here is an example:

```java
public class MainState extends GameState {
	private Player player;
	private Level1 world;

	public MainState(int priority) {
		super(priority);

		player = new Player(params);
		world = new Level1("world1", 600, 400);
		world.addEntity(player);

	}

	@Override
	public void onDraw(Graphics graphics) {
		world.onDraw(graphics);
	}

	@Override
	public void onTick() {
		player.updateEntity();
		player.updateBoundingBox();

		world.onTick();

	}
}
```

` priority ` indicates which GameState to draw/update first, this is useful for multiple GameStates.
0 will be the first to draw/update and then everything next.

# Entities

Entities are for example your player or an enemy. To create a custom entity start by extending ` LivingEntity `. LivingEntity indicates its 'living' and requires health/speed values.

Entity classes have the option to use a sprite, although not required be sure to remember using built in draw functions for entities use the sprite.

```java
public class MyPlayer extends LivingEntity {
	public Player(int x, int y, int width, int height, int entityID, float health, double speed) {
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

	/**
	* Basic movement for our Player.
	*/
	@Override
	public void updateEntity() {
		boolean w, a, s, d;
		w = InputListener.isKeyDown(KeyEvent.VK_W);
		a = InputListener.isKeyDown(KeyEvent.VK_A);
		s = InputListener.isKeyDown(KeyEvent.VK_S);
		d = InputListener.isKeyDown(KeyEvent.VK_D);

		if (w) {
			y -= speed;
		}

		if (a) {
			x -= speed;
		}

		if (s) {
			y += speed;
		}

		if (d) {
			x += speed;
		}
	}
}
```

Lets go over the fields.

x, y - the position of the player.
width, height - dimensions of the player.

entityID - in each world entities are managed by their ID. Think of this as a unique number for each entity.
If we have a player and a mob its up to you which ID they're assigned.

health - the health of the entity.
speed - the speed of the entity.

# Sounds

To play a sound you want to create a new ` Sound ` object.
Each sound has an ID and the audio file.

```java
Sound sound = new Sound(int ID, File audio);
```

To play the sound simply get your SoundManager instance. Either via your ` lunar ` object or making a new instance.
```java
SoundManager sm = lunar.getSoundManager();

sm.playAudio(sound);
```
You can also keep track of all your game sounds within the SoundManager.
Alternatively if you don't want to create a new Sound object you can play the file directly.
```java
sm.playAudio(file);
```

# Input
Every game needs input and Lunar provides Mouse and Keyboard input.
For example, to check if a certain key is pressed:

```java
InputListener.isKeyPressed(KeyEvent.KEY);
```

MouseInput has many useful methods, as documented here:

```java
/**
 * Gets the click coordinates.
 */
public static Point getLastClick() {
	return lastClick;
}

/**
 * @return true of the mouse is down, false otherwise.
 */
public static boolean isMouseDown() {
	return isMouseDown;
}

/**
 * Get the component the mouse entered. This can return null if the mouse
 * exited the component.
 */
public static Component getEnteredComponent() {
	return enteredComponent;
}
```

# Worlds

Creating a custom world is very easy.
Start by extending ` World `.

```java
public class MyWorld extends World {

	public Level1(String name, int width, int height) {
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

Each class extending World can use many useful methods within the World class to make things easier.
These include:

adding entities
adding tiles, adding multiple tiles at once in one method.
drawing all entities and tiles
getting an entity via their ID.
and many more!

# Tiles, Sprites
Tiles are essential for a textured game.

Each tile holds an ID, the texture, if its solid or not and their width/height.
If we want a tile for a Wall this can be done easily.

To start lets load our SpriteSheet.

```java
SpriteManager sm = new SpriteManager(SpriteManager.load("pathToSheet.png"));
```

Now we can easily get certain textures at certain points in our spritesheet.
For example if we have the Wall texture at 0, 0 and its 64x64 we can get it using:

```java
BufferedImage wall = sm.getSectionAt(0, 0, 64, 64);
```

Now we have our wall texture.
Lets now create the tile.

```java
Tile tile = new Tile(wall, 0, true);
```
As you can see we created the tile with our wall texture, an ID of 0 and the isSolid flag set to true.
Now we can add this to our world

```java
world.addTile(tile, x, y);
```
The AssetManager holds all of our game tiles. Simply get it using the ` lunar ` object or creating a new instance.

```java
AssetManager am = new AssetManager();
```

Lets store our tile:

```java
am.addTile(tile);
```

AssetManager includes the ability to get a tile via ID as well as remove and add tiles.

# RayTracing

RayTracing can be used to find for example a wall or a certain tile.
If we wanted to check if there is a solid tile in front of the player we can!
Start by making new instance of the RayTracing.

```java
RayTracing rayTrace = new RayTracing();
```

Now lets check if there are any solid blocks in front of us.
Our direction is RIGHT and lets go to a distance of 4.

```java
Tile tile = rayTrace.getNextSolidTile(myWorld, player.getX(), player.getY(), 4, Direction.RIGHT, 64, 64);
```
Now we have our tile. If the tile is null this indicates no tile was found.

# AnimationManager
The AnimationManager is very useful in situations where you have many images (such as a player sprite walking each direction with each leg).

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

And that's it! 
