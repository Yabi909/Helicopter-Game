package helicopterGame;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import helicopterGame.Groups.GroupA;
import helicopterGame.Groups.GroupB;

public class Controller {
	
	GroupA a;
	GroupB b;
	
	private LinkedList<GroupA> la = new LinkedList<GroupA>();
	private LinkedList<GroupB> lb = new LinkedList<GroupB>();
	
	private Media med;
	Random r= new Random();
	
	private Main main;
	
	public Controller(Media tex, Main main){
		
		this.med = tex;
		this.main = main;
		
	}
	
	public void createEnemy(int enemy_count){
		
		for(int i =0; i< enemy_count; i++){
			addEntity(new Enemy(800, r.nextInt(Main.HEIGHT -53),med,main,this));
		}
		
	}

	
	public void Tick(){
		//GroupA
		for(int i = 0; i<la.size(); i++){
			a = la.get(i);
			
			a.tick();
		}
		//GroupB
		for(int i = 0; i<lb.size(); i++){
			b = lb.get(i);
			
			b.tick();
		}
		
		
	}
	
	public void render(Graphics g){
		//GroupA
		for(int i = 0; i<la.size(); i++){
			a = la.get(i);
			
			a.render(g);
		}
		
		//GroupB
		for(int i = 0; i<lb.size(); i++){
			b = lb.get(i);
					
			b.render(g);
		}
		
	}
	
	public void addEntity(GroupA item){
		la.add(item);
	}
	
	public void removeEntity(GroupA item){
		la.remove(item);
	}
	
	public void addEntity(GroupB item){
		lb.add(item);
	}
	
	public void removeEntity(GroupB item){
		lb.remove(item);
	}
	
	public LinkedList<GroupA> getGroupA(){
		return la;
	}
	
	public LinkedList<GroupB> getGroupB(){
		return lb;
	}
	

}
