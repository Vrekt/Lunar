package me.vrekt.lunar.sprite;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteManager {

	private BufferedImage spriteSheet;

	public SpriteManager(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public BufferedImage getSectionAt(int x, int y, int width, int height) {
		return spriteSheet.getSubimage(x, y, width, height);
	}

	public static BufferedImage load(String path) {
		try {
			return ImageIO.read(new FileInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
