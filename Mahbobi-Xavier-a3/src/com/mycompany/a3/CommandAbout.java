package com.mycompany.a3;
import com.codename1.ui.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.table.TableLayout;
import com.mycompany.a3.GameWorld;

public class CommandAbout extends Command{
	private GameWorld gw; 
	
	public CommandAbout(GameWorld gw) {
		super("About");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event) {
		Dialog aboutBox = new Dialog("About", new TableLayout(4,1));
		
		Command confirmCommand = new Command("Confirm");
		aboutBox.add(new Label("Xavier Henry Mahbobi"));
		aboutBox.add(new Label("CSC 133"));
		aboutBox.add(new Label("Robo Tracker"));
		aboutBox.add(new Label("Version 2.0"));
		Command confirm = Dialog.show("", aboutBox, confirmCommand);
		if (confirm == confirmCommand) {
			return;
		}
	}
}
