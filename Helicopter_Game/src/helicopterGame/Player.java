package helicopterGame;

import java.awt.Graphics;
import java.awt.Rectangle;


import helicopter.anim.Animation;
import helicopterGame.Groups.GroupA;
import helicopterGame.Groups.GroupB;

public class Player extends Game implements GroupA, Observer {
	
	private double height;
	private double width;
	
	private double velx = 0;
	private double vely = 0;
	private boolean collision;

	
	private Main main;
	
	
	private Media med;
	Controller controller;
	Player player;
	Animation animation;
	private Subject sub;
	
	
	public Player(double x, double y, Media med, Main main, Controller controller){
		super(x,y);
		this.med = med;
		this.main = main;
		
		this.controller = controller;
		
		animation = new Animation(10, med.player[1], med.player[2]);
		
	}
	
	public void tick(){
		setX(getX() + velx);
		y+=vely;
		y+= 3;
		
		if(y<=0)
			y=0;
		if(y>=547 )
			y=547;
		
		if(y<1||y>546){
			main.healthbar -= 5;
		}
		
		
		for(int i=0; i< main.listb.size(); i++){
			
			GroupB gb = main.listb.get(i);
			
			if(Motion.Collission(this, gb)){
				
				controller.removeEntity(gb);
				main.healthbar -= 20;
				
				main.setEnemy_killed(main.getEnemy_killed()+1);
				if(main.isGameOver() == false){
					main.score -=2;
				}
				if(main.healthbar < 1){
					main.setGameOver(true);
					vely = 2;
					
				}
			}
			
		}
		
		
		
		
	}
	
	public void render(Graphics g){
		
		
		if(main.isGameOver()){
			animation.drawAnimation(g,x,y,0);
			} else{
				g.drawImage(med.player[0], (int)getX(), (int)y, null);
			}
		
		
	} 
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
		
	}
	
	public double getx(){
		return getX();
	}
	
	public double gety(){
		return y;
	}
	
	public void setx(double x){
		this.setX(x);
	}

	public void sety(double y){
		this.y=y;
	}
	
	public void setvelx(double velx){
		this.velx = velx;
		
	}
	
	public void setvely(double vely){
		this.vely = vely;
		
	}
	
	

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public void Rectangle(double x2, double y2, int width, int height){
		this.x = (int)x;
		this.y = (int)y;
		this.width = 32;
		this.height = 32;
		
	}

	@Override
	public double getY() {
		
		return y;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}


	@Override
	public void update() {
		
		animation.runAnimation();
	}

	public Subject getSubject() {
		return sub;
	}

	public void setSubject(Subject sub) {
		this.sub = sub;
	}
	

}
