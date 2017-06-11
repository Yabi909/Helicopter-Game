package helicopterGame;

import java.awt.image.BufferedImage;

public class Media {
	
	private SpriteSheet sprite;
	public BufferedImage[] player = new BufferedImage[3];
	public BufferedImage[] missile = new BufferedImage[3];
	public BufferedImage[] enemy = new BufferedImage[3];
	
	public Media(Main main){
		sprite = new SpriteSheet(main.getSpriteSheet());
		getMedia();
	}
	
	private void getMedia(){
		player[0] = sprite.grabimage(1, 1, 32, 32);
		player[1] = sprite.grabimage(1, 2, 32, 32);
		player[2] = sprite.grabimage(1, 3, 32, 32);
		
		
		missile[0] = sprite.grabimage(2, 1, 32, 32);
		missile[1] = sprite.grabimage(2, 2, 32, 32);
		missile[1] = sprite.grabimage(2, 3, 32, 32);
		
		
		enemy[0] = sprite.grabimage(3, 1, 32, 32);
		enemy[1] = sprite.grabimage(3, 2, 32, 32);
		enemy[1] = sprite.grabimage(3, 3, 32, 32);
		
		
	}

}
