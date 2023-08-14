package com.mycompany.a3;
import com.codename1.ui.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class CommandCollideWithDrone extends Command{
	private GameWorld gw; 
	
	public CommandCollideWithDrone(GameWorld gw) {
		super("Collide With Drone");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event) {
		gw.CollisionDrone();
	}
	
	
}
