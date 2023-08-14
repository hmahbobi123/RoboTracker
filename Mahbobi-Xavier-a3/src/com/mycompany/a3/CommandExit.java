package com.mycompany.a3;
import com.codename1.ui.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandExit extends Command {
	private GameWorld gw; 
	
	public CommandExit(GameWorld gw) {
		super("Exit");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event) {
		Command yesCommand = new Command("Yes");
		Command noCommand  = new Command("No");
		
		Label YesNo = new Label("");
		Command exitCommand = Dialog.show("Do you want to exit?", YesNo, yesCommand, noCommand);
		if (exitCommand == yesCommand) {
			System.exit(0);
		}
		else if (exitCommand == noCommand){
			return;
		}
	}	
	
}
