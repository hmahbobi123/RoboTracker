package com.mycompany.a3;
import com.codename1.ui.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandLeftTurn extends Command{
	private GameWorld gw; 
	
	public CommandLeftTurn(GameWorld gw) {
		super("Left Turn");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event) {
		gw.steering('l');
	}
		
	
}
