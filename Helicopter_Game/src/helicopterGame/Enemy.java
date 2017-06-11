package helicopterGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import helicopter.anim.Animation;
import helicopterGame.Groups.GroupA;
import helicopterGame.Groups.GroupB;

public class Enemy extends Game implements GroupB {
	
	
	private Media med;
	Random r = new Random();
	private int speed = r.nextInt(4)+1;
	private Main main;
	private Controller cont;
	boolean boom = false;
	
	
	public Enemy(double x, double y, Media med, Main main, Controller cont){
		
		super(x,y);
		this.med = med;
		this.cont = cont;
		this.main = main;
		
	}
	
	public void tick(){
		x-= speed;
		
		if(x<0){
			speed = r.nextInt(4)+1;
			x = Main.WIDTH;
			y = r.nextInt(Main.HEIGHT -53);
			
		}
		
		if(main.isGameOver()==true){
			speed = r.nextInt(1)+1;;
		}
		
		for(int i = 0; i < main.lista.size(); i++){
			
			GroupA ga = main.lista.get(i);
			if(Motion.Collission(this, ga)){
				cont.removeEntity(ga);
				cont.removeEntity(this);
				main.setEnemy_killed(main.getEnemy_killed()+1);
				main.setscore(main.getscore()+1);
			}
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	public void render(Graphics g){
		
		g.drawImage(med.enemy[0], (int)x, (int)y, null);
	}
	
	public double getX(){
		return x;
	}
	
	public void setX(double x){
		this.x=x;
		
	}

	public double getY() {
		
		return y;
	}

}
