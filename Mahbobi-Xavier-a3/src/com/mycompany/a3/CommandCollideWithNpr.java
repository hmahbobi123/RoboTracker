package com.mycompany.a3;
import com.codename1.ui.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandCollideWithNpr extends Command {
	private GameWorld gw;
	
	public CommandCollideWithNpr(GameWorld gw) {
		super("Collide With NPR");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event) {
		gw.CollisionRobot();
	}
}
