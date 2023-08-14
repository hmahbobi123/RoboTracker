package com.mycompany.a3;
import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound implements Runnable{
	private Media m;
	
	//slide 36 sound, added play and pause to handle that requirement 
	//Literally the same as what you provided us on the slides in class 
	public BGSound(String fileName) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
			m = MediaManager.createMedia(is, "audio/wav", this);
		} 
		catch (Exception err) { err.printStackTrace(); }
		}

	public void play() {
		m.play();
	}
	
	public void pause() {
		m.pause();
	}
	
	
	public void run(){
		m.setTime(0); 
		play();
	}
	
}