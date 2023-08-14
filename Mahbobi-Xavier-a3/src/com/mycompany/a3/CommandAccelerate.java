package com.mycompany.a3;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.table.TableLayout;

public class CommandAccelerate extends Command {
	private GameWorld gw;
	
	public CommandAccelerate(GameWorld gw) {
		super("Accelerate");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent event) {
		if (!gw.getGamePause()) {
			gw.accelerate();
		}
		
	}
}
