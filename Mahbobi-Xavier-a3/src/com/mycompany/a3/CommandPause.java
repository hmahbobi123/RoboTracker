package com.mycompany.a3;
import com.codename1.ui.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class CommandPause extends Command{
	private GameWorld gw;
	
	public CommandPause(GameWorld gw) {
		super("Game Paused");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event) {
		if (gw.getGamePause() == true) {
			gw.setGamePause(false);
		}
		else {
			gw.setGamePause(true);
		}
	}
}
