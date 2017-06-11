package helicopterGame;



import java.awt.Color;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.Timer;

import helicopterGame.Groups.GroupA;
import helicopterGame.Groups.GroupB;


public class Main extends Canvas implements ActionListener, KeyListener, MouseListener, Subject
{

	
	private static final long serialVersionUID = 1L;
	public static Main Helicopter;
	public final static int WIDTH = 800;
	public final static int HEIGHT = 600;
	public final static int SCALE = 2;
	public Renderer renderer;
	
	private Menu menu;
	
	public int ticks, yMotion;
	private boolean gameOver;
	
	
	
	ArrayList<Integer> highScores = new ArrayList<Integer>();
	
	double x =0;
	double y =0;
	private boolean is_shooting = false;
	private BufferedImage sprite = null;
	private BufferedImage background = null;
	private static Player player;
	private Controller controller;
	
	public int topscore = 0;
	private Media med;
	private int enemy_count = 5;
	private int enemy_killed = 0;
	
	static int score = 0;
	
	public LinkedList<GroupA> lista;	
	public LinkedList<GroupB> listb;
	public boolean started = false;
	
	public static int healthbar = 151;
	private List<Observer> listofObservers = new ArrayList<Observer>();


	public static enum STATE{
		MENU,
		PLAY,
		
	};
	
	public static STATE State = STATE.MENU;
	

	public void init(){
		BufferedImageLoader load = new BufferedImageLoader();
		try{
			sprite = load.loadimage("/sprite6.png");
			background = load.loadimage("/background.png");
		}catch(IOException e){
			e.printStackTrace();
		}

	    Sound object = Sound.getInstance();
	    object.playSound();

	      
		addKeyListener(new KeyInput (this));
		
		med = new Media(this);

		
		controller= new Controller(med, this);
		player = new Player(200,200, med, this,controller);
		
		controller.createEnemy(enemy_count);
		menu = new Menu();
		lista = controller.getGroupA();
		listb = controller.getGroupB();
		
	}

	public Main(){
		JFrame jframe = new JFrame();
		Timer timer = new Timer(20, this);
		renderer = new Renderer();
	
		init();
	
		jframe.add(renderer);
		jframe.setTitle("Helicopter Game");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);
		
		timer.start();

	}	
	
	
		 
	   

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		
		if(State == STATE.MENU){
			gameOver = false;
			score = 0;
		}
		
		if(State == STATE.PLAY){
		
		
		  player.tick();
		  controller.Tick();
		  if(score<0){
			  score=score +1;
			  gameOver = true;
			  
			
		  }
		  
		  if (enemy_killed >= enemy_count){
			  enemy_count +=2;
			  enemy_killed =0;
			  controller.createEnemy(enemy_count);
		  }
		  
		if(isGameOver()){
			notifyObservers();				
		}
		
		}
		
		renderer.repaint();
		
	}
	
	
	

	public void repaint(Graphics g)
	{
		
		g.drawImage(background, 0, 0, null);
		
		if(State == STATE.PLAY){
		  player.render(g);
		  controller.render(g);	
		  
		  g.setColor(Color.GRAY);
		  g.fillRect(5, 5, 150, 15);
		  
		  g.setColor(Color.GREEN);
		  g.fillRect(5, 5, healthbar, 15);
		  
		  g.setColor(Color.white);
		  g.drawRect(5, 5, 150, 15);
		  
		  if(healthbar < 20){
				g.setColor(Color.RED);
				  g.drawRect(5, 5, 150, 15);
			}
		  

		  if (gameOver)
		  {
			  g.setColor(Color.BLACK);
			  g.setFont(new Font("Arial", 1, 50));
			  
			  g.drawString("Game Over!", WIDTH/2 - 150, HEIGHT / 2 - 200);
			  
			  g.setColor(Color.BLACK);
			  g.setFont(new Font("Arial", 1, 20));
			  
			  g.drawString("Score: ", 50, HEIGHT / 2 - 100);
			  g.drawString("Press Enter to return to Menu", 50, HEIGHT / 2 );
			  g.drawString(String.valueOf(score), 230, HEIGHT / 2 - 100);
			  if (score>topscore){
				  topscore = score;  
			  } 
			  g.setColor(Color.BLACK);
			  g.setFont(new Font("Arial", 1, 20));
			  g.drawString("HighScore: ", 50, HEIGHT / 2 -50);
			  g.drawString(String.valueOf(topscore), 230, HEIGHT / 2 -50);
			 
		  }
		  
		  if (!gameOver)
		  {
			  g.setColor(Color.BLACK);
			  g.setFont(new Font("Arial", 1, 50));
			  g.drawString(String.valueOf(score), WIDTH - 95, 50);
		  }
		  }else if(State == STATE.MENU){
			  menu.Render(g);
		  }
		
	}

	public static void main(String[] args) 
	{
		Helicopter = new Main();
		Helicopter.registerObserver(player);

	}
	
	public void keyReleased(KeyEvent e)
	{
        int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP){
			player.setvely(0);} else if(code == KeyEvent.VK_SPACE){
				is_shooting = false;
			}
	
	}
	
	public boolean isGameOver() {
		
		return gameOver;
		
		
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
	
		if(!gameOver){
		if(State == STATE.PLAY){
	    
		if (code == KeyEvent.VK_UP){
			player.setvely(-10);} else if(code == KeyEvent.VK_SPACE && !is_shooting){
				is_shooting = true;
				controller.addEntity(new Bullet(player.getX(), player.gety(), med, this));
				
				try {
			         // Open an audio input stream.           
			          File soundFile = new File("C:\\Users\\User1\\Documents\\Yabi\\Project\\boom.wav"); //you could also get the sound file with an URL
			          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
			         // Get a sound clip resource.
			         Clip clip = AudioSystem.getClip();
			         // Open audio clip and load samples from the audio input stream.
			         clip.open(audioIn);
			         clip.start();
			      } catch (UnsupportedAudioFileException f) {
			         f.printStackTrace();
			      } catch (IOException f) {
			         f.printStackTrace();
			      } catch (LineUnavailableException f) {
			         f.printStackTrace();
			      }
				}
		
			}
		} else if (gameOver){

			if(code == KeyEvent.VK_ENTER){
				Main.State = Main.STATE.MENU;
			}
			
		}
	
		}

	
	public BufferedImage getSpriteSheet(){
		return sprite;
	}
	
	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	public void setscore(int score) {
		this.score = score;
	}

	public int getscore() {
		return score;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x_location = e.getX();
		int y_location = e.getY();
		if(State == STATE.MENU){
        if(x_location>Main.WIDTH/2 -50 && x_location<Main.WIDTH/2 +50){
			
			if(y_location> 130 && y_location< 230){
				Main.State = Main.STATE.PLAY;
				healthbar = 150;
				player.sety(WIDTH/2 - 150);
				
			}
		}
        
        if(x_location>=10 && x_location<=110){
			
			if(y_location> 520 && y_location< 600){
				System.exit(1);
			}
		}
		}
		
	}

	@Override
	public void registerObserver(Observer observer) {
		listofObservers.add(observer);
		
	}

	@Override
	public void removeObserver(Observer observer) {
		listofObservers.remove(observer);
		
	}

	@Override
	public void notifyObservers() {
		for(Observer observer : listofObservers){
			observer.update();
		}
		
	}
	
	public List<Observer> getListofObservers() {
		return listofObservers;
	}

	public void setListofObservers(List<Observer> listofObservers) {
		this.listofObservers = listofObservers;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}