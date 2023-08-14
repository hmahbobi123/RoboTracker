package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {
	private Media m;
	
	//slide 36 sound, added play and pause to handle that requirement 
	//the same as what you provided us on the slides in class 
	public Sound(String fileName) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
			m = MediaManager.createMedia(is, "audio/wav");
		} 
		catch (Exception err) { err.printStackTrace(); }
		}
	
	public void play() {
		m.setTime(0);
		m.play();
	}
	
	public void pause() {
		m.pause();
	}
}
