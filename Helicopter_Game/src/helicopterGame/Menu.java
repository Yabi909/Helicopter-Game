package helicopterGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playbutton = new Rectangle(Main.WIDTH/2 -50, 150, 100, 50);
	
	public Rectangle quitbutton = new Rectangle(10, Main.HEIGHT-90 , 100, 50);
	
	public void Render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("Expresso Shack",Font.BOLD, 50);
		g.setFont(fnt0);
		Color mycolour = new Color(205,92,92);
		g.setColor(mycolour);
		g.drawString("Helicopter Game", Main.WIDTH/2 -200, 50);
		
		Font fnt1 = new Font("arial",Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Play", playbutton.x+19, playbutton.y+30);
		g2d.draw(playbutton);
		
		Font fnt3 = new Font("arial",Font.BOLD, 20);
		g.setFont(fnt3);
		Color mycolour2 = new Color(153,0,0);
		g.setColor(mycolour2);
		g.drawString("Quit", quitbutton.x+19, quitbutton.y+30);
		g2d.draw(quitbutton);
		
	}

}
