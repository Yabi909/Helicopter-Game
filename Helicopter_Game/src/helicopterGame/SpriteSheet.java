package helicopterGame;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage grabimage(int col, int row, int height, int width){
		BufferedImage img = image.getSubimage((col*32)-32, (row*32)-32, width, height);
		return img;
	}

}
