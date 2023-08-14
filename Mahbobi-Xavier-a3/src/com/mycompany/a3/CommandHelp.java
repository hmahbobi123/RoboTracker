package com.mycompany.a3;
import com.codename1.ui.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.table.TableLayout;

public class CommandHelp extends Command{
	private GameWorld gw; 
	
	public CommandHelp(GameWorld gw) {
		super("Help");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event) {
		Dialog helpBox = new Dialog("Help", new TableLayout(9, 2));
		Command confirmCommand = new Command("Confirm");
		
		helpBox.add(new Label("Command"));
		helpBox.add(new Label("Hotkeys"));
		helpBox.add(new Label("Accelerate: press a "));
		helpBox.add(new Label("A"));
		helpBox.add(new Label("Brake:"));
		helpBox.add(new Label("B"));
		helpBox.add(new Label("Left Turn:"));
		helpBox.add(new Label("L"));
		helpBox.add(new Label("Right Turn:"));
		helpBox.add(new Label("R"));
		helpBox.add(new Label("Collide with energy station:"));
		helpBox.add(new Label("E"));
		helpBox.add(new Label("Collide with drone:"));
		helpBox.add(new Label("G"));
		helpBox.add(new Label("Tick the clock:"));
		helpBox.add(new Label("T"));
		
		Command confirm = Dialog.show("", helpBox, confirmCommand);
		if (confirm == confirmCommand) {
			return;
		}
	}
}
