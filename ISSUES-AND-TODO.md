# Todo
- Add an event where the user can register and unregister events. 
The event system would use an annotation for the method.
For example:

```java
@EvenTarget
public void onEvent(MyCustomEvent event) {
  //code
}
```

- Different types of entities, "HumanEntity", etc.
- Inventory system
- Maybe make entity/tile x and y values doubles? (would cause trouble with drawing and lots of other stuff)
- Multiplayer, client -> server TCP communication. (Do not trust the client for most things though to prevent cheats/exploits?)
- 2D physics, gravity, particle system.
- Implement new gameloop (fixed timestep, other implementations). Current loop causes performance issues.
- Improve performance.

# Issues
Drawing with a panel instead of straight to the window? This prevents the issue with drawing to (0, 0) being out of frame.
Getting entities/tiles at a certain location can be trivial, requires the x/y to match up which isn't always possible.

