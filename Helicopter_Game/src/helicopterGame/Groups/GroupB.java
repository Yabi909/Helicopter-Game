package helicopterGame.Groups;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface GroupB {
	
	public void tick();
	
	public double getX();
	public double getY();
	
	public void render(Graphics g);
	public Rectangle getBounds();

}
