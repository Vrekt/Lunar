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
