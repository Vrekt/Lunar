# Setting up

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
