# Getting started

To start you want to create a new Lunar object. This keeps track of the Game and the SoundManager.

```java
Lunar lunar = new Lunar(title, width, height, tickRate);
```

Or

```java
Lunar lunar = new Lunar(title, width, height, gamestate, tickRate);
```

The bottom object initializes the game with a given GameState.
Once this is done you need to start the game.

```java
lunar.getGame().start();
```

Alternatively if you would like to keep track of the Game and your own SoundManager in a different class this can be done easily.

```java
Game game = new Game(title, width, height, tickRate);
Game game = new Game(title, width, height, gamestate, tickRate);

SoundManager mySoundManager = new SoundManager();
```

# Tick rate

A good tickrate is 60 or above.
The higher the tickrate the faster everything is drawn/updated.
A faster tickrate would move the player faster than say a 60 tickrate.

# Game states

Before you start your game you want to push an existing GameState.
GameStates keep track of your entities and all things needed for that level.

For example, Level1 would contain our Player object and a simple Mob object (both extending LivingEntity).
Priority indicates when it should tick/draw, this is useful for multiple GameStates. 0 is the first to update/draw and everything after is next.


```java
public class Level1 extends GameState {

public MainHandler(int priority) {
		super(priority);
	}

	private Player player = new Player(300, 100, 64, 64, 2, 20, 3);

	@Override
	public void onDraw(Graphics graphics) {
		player.drawEntity(graphics);
	}

	@Override
	public void onTick() {
		player.updateEntity();
	}
}
```

# Entities

Each entity in our game needs to extend Entity or LivingEntity.
For entities that move around and interact with things you would want to extend LivingEntity.
LivingEntity has health/speed values whereas Entity only has position values.

Lets create our Player object.

```java
public class Player extends LivingEntity {

	public Player(int x, int y, int width, int height, int entityID, float health, double speed) {
		super(x, y, width, height, entityID, health, speed);
	}

	@Override
	public void drawEntity(Graphics graphics) {
		// For our player we are going to make a simple red box.
		graphics.setColor(Color.red);
		graphics.fillRect(x, y, width, height);
	}
	
	// easy method of movement.
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

Lets break it down.
``` 
drawEntity()
```
Handles drawing our entity and everything else needed for the Player/Entity.

```
updateEntity()
```

Handles updating our entity, such as moving, health/special effects stuff, etc.

# Sounds

Playing a sound is very easy. To start create a new Sound object, this holds an ID and the Audio File.

```java
int ID = 1;
File audioFile = new File("myFile.wav");
Sound mySound = new Sound(ID, audioFile);
```

Managing sounds in a game is very important. The SoundManager holds an ArrayList of sounds and you can even get and remove sounds via ID!

Now lets play our sound. Grab your SoundManager either with
```java
SoundManager sm = Lunar.getSoundManager();
```

Or

```java
SoundManager sm = new SoundManager();
/
SoundManager sm = MyClass.getSoundManager();
```

Lets play the sound!
```java
sm.playSound(sound);
```
You can also play a sound with just the audio file!
```java
sm.playSound(file);
```
# Tiles and worlds.

A tile holds four values. the ID, the texture, width and height. Currently there is no use for the ID (yet).
A tile can represent a wall, the floor, just about anything.
To create a tile do this:
```java
Tile ourTile = new Tile(int ID, texture);
```
The ID can be set to anything since its currently not used.
To get our texture we can use the SpriteManager.

Firstly lets initialize the SpriteManager with our spritesheet.

```java
SpriteManager sm = new SpriteManager(SpriteManager.load("pathToSpriteSheet.png");
```
Great! Now we can create our tile.

```java
Tile tile = new Tile(0, sm.getSectionAt(x, y, width, height);
```
If we have our texture (for example a wall texture) at 0,0 and its 64x64 that is what we plug in.

Since we have a tile now lets create a new world.
A world needs 3 values, the name, width and height.

```java
public class OurWorld extends World {

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
Now that we have our world we can add the tile to it.

```java
OurWorld world = new OurWorld("world1", 600, 400);
world.addTile(tile, x, y);
```

You can also add multiple tiles at once. with World#addBatchTiles(Tile tile, int x, int y, Direction direction, int multiplier);
X and Y indicate the starting position, in our case 0, 0. Direction indicates the direction we are going, in our case RIGHT.
Finally, multiplier is how many times we add the tile, in our case 9.

```java
world.addBatchTiles(tile, 0, 0, Direction.RIGHT, 9);
```


The X and Y values indicate where to draw the tile.
Now in our onDraw method we can draw the tile.

```java
@Override
public void onDraw(Graphics graphics) {
   drawAllTiles(graphics);
}
```

the World class holds many useful functions so dont forget about them! These include:
```java
drawAllTiles();
drawAllEntities();
```

# AssetManager

Use this class to manage all your tiles and sprites.

	


