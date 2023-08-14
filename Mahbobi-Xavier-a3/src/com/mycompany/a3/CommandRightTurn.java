package com.mycompany.a3;
import com.codename1.ui.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandRightTurn extends Command {
	private GameWorld gw;
	
	public CommandRightTurn(GameWorld gw) {
		super("Right Turn");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event) {
		gw.steering('r');
	}
}
