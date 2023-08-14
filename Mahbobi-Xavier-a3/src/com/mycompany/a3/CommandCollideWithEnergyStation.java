package com.mycompany.a3;
import com.codename1.ui.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandCollideWithEnergyStation extends Command {
	private GameWorld gw;
	
	public CommandCollideWithEnergyStation(GameWorld gw) {
		super("Collide With Energy Station");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event) {
		//gw.energyStationCollision();
	}
}
