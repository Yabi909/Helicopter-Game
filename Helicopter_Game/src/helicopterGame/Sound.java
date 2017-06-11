package helicopterGame;


import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound {
	
	private static Sound instance = new Sound();

	private Sound(){}
	
	public static Sound getInstance(){
		return instance;
	}
	
	public void playSound(){
		
		try {
	         // Open an audio input stream.           
	          File soundFile = new File("C:\\Users\\User1\\Documents\\Yabi\\Project\\hh.wav"); //you could also get the sound file with an URL
	          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
	         // Get a sound clip resource.
	         Clip clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioIn);
	         clip.start();
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
		
	}


}
