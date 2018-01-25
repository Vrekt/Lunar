# TODO
Things im going to be implementing slowly overtime.

Different 'drawing methods'. Onto the frame itself and a JPanel. (difficulty 2/10)
Slighty modified game loop to remove thread.sleep and other minor improvements (difficulty 2/10)
Maybe delete the Lunar file and restructure the whole library. (//)
Change all integers to use doubles. (8/10) (requires alot of redesign)
Use graphics2D instead of graphics (2/10) (lots of redesign/changing)
Update the whole wiki and add more documentation.


# About Lunar
Lunar is a 2D game engine written in java. Its made for simplicity and ease of use. 
Some things to consider before using Lunar.

* Must be using java 8.
* This library is not meant for:
	* 3D games, mobile, games with lots of physics.
* This library is meant for:
	* Beginner game developers.
	* Creating casual 2D games.
	* Creating prototypes fast.
	* Hobby developers.
* Lunar is still being worked on and is nowhere near finished.
* Development will slow overtime but will eventually pick back up.

# Tutorial
To use Lunar download the latest release and import it into your project.
Looking for a simple tutorial on getting started with Lunar? Check out the [wiki](https://github.com/Vrekt/Lunar/wiki).

# Contributing

When contributing and submitting requests for your changes to be merged please make sure to test your code and leave a note if the wiki needs to be updated!

# Testing

Use the bundled gradle distro to run tests. This should only be necessary when contributing.

```bash
./gradlew test
```
