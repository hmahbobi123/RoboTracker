package com.mycompany.a3;
import com.codename1.ui.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandCollideWithBase extends Command{
	private GameWorld gw; 
	
	public CommandCollideWithBase(GameWorld gw) {
		super("Collide With Base");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent event) {
		TextField textBox = new TextField();
		Command confirmCommand = new Command("Confirm");
		Command seqCommand = Dialog.show("Enter Base number between 1-9", textBox, confirmCommand);
		
		int sequenceNumber = 1;
		if(seqCommand == confirmCommand ) {
			try {
				sequenceNumber = Integer.parseInt(textBox.getText());
			}
			catch(Exception invalidInput) {
				Dialog.show("Error", "Enter a Number Between 1-9", "Try Again", null);
				return;
			}	
		}
		if (sequenceNumber < 1 || sequenceNumber > 9) {
			Dialog.show("Error", "Enter a Number Between 1-9", "Try Again", null);
			return;
		}
		
		gw.baseCollision(sequenceNumber);
	}
}
