package helicopterGame;

import java.awt.Rectangle;

public class Game {
	
	public double x, y;
	
	public Game (double x, double y ){
		this.x = x;
		this.y = y;
		
	}
	
	public Rectangle getBounds(int width, int height){
		return new Rectangle((int)x, (int)y, width, height);
		
	}

}
