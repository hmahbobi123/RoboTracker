package com.mycompany.a3;
import com.codename1.ui.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandStrategy extends Command{
	private GameWorld gw;
	
	public CommandStrategy(GameWorld gw) {
		super("Changing Strategy");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent event) {
		gw.changeStrategy();
	}
}
