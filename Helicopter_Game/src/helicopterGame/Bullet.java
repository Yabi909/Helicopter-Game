package helicopterGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import helicopterGame.Groups.GroupA;

public class Bullet extends Game implements GroupA {
	
	
	private Media med;
	private Main main;
	
	
	public Bullet(double x, double y, Media med, Main main){
		
		super(x, y);
		this.med = med;
		this.main = main;
	}
	
	public void tick(){
		x+=10;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
		
	}
	
	public void render(Graphics g){
		g.drawImage(med.missile[0], (int)x, (int)y, null);
	}
	
	
	
	public double getX(){
		return x;
	}

	
	public double getY() {
		return y;
	}

}
