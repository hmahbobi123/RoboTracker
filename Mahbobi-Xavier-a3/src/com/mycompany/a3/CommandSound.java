package com.mycompany.a3;
import com.codename1.ui.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class CommandSound extends Command {
	private GameWorld gw;
	
	public CommandSound(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event) {
		gw.toggleSound();
		if (gw.getSoundValue() == true) {
			gw.getBgSound().play();
		}
		else {
			gw.getBgSound().pause();
		}
	}
}
