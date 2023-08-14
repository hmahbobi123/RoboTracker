package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

import java.lang.String;
//Must import runnable to access timer
public class Game extends Form implements Runnable{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private char exitKey;
	private int time = 20;
	//added all of these to be declared here so I can access them throughout the different functions
	private Button pauseButton, accelerateButton, leftButton, strategyButton, brakeButton, rightButton, positionButton;
	private Command aboutCommand, accelerateCommand, brakeCommand, exitCommand, leftTurnCommand, rightTurnCommand, soundCommand, helpCommand, strategyCommand, pauseCommand, positionCommand;
	private CheckBox soundCheck;
	private Toolbar toolbar;
	
	
	
	//using exit key for user to press Y or N to exit game or resume
	
	//got rid of a bunch commands that aren't used anymore
	public Game() {
		gw = new GameWorld();
		sv = new ScoreView(gw);
		mv = new MapView(gw);
		gw.addObserver(mv); 
		gw.addObserver(sv);
		UITimer timer = new UITimer(this);
		toolbar = new Toolbar();
		this.setToolbar(toolbar);
		toolbar.setTitle("Robo-track Game");
		
		//Creating West/South/East Containers
		Container westContainer = new Container();
		westContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GRAY));
		westContainer.setLayout(new BoxLayout(2));
		
		Container southContainer = new Container();
		southContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GRAY));
		southContainer.setLayout(new FlowLayout(Component.CENTER));
		
		Container eastContainer = new Container();
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GRAY));
		eastContainer.setLayout(new BoxLayout(2));
		
		//commands & keylisteners 
		aboutCommand = new CommandAbout(gw);
		accelerateCommand = new CommandAccelerate(gw);
		brakeCommand  = new CommandBrake(gw);
	//	Command collideWithBaseCommand = new CommandCollideWithBase(gw);
	//	Command collideWithDroneCommand  = new CommandCollideWithDrone(gw);
	//	Command collideWithEnergyStationCommand  = new CommandCollideWithEnergyStation(gw);
	//	Command collideWithNprCommand  = new CommandCollideWithNpr(gw);
		exitCommand = new CommandExit(gw);
		leftTurnCommand = new CommandLeftTurn(gw);
		rightTurnCommand = new CommandRightTurn(gw);
	//	Command tickCommand = new CommandTick(gw);
		soundCommand = new CommandSound(gw);
		helpCommand = new CommandHelp(gw);
		strategyCommand = new CommandStrategy(gw);
		pauseCommand = new CommandPause(gw);
		positionCommand = new CommandPosition(gw);
		
		//checkbox for sound
		soundCheck = new CheckBox();
		
		addKeyListener('a', accelerateCommand);
		addKeyListener('b', brakeCommand);
		addKeyListener('l', leftTurnCommand);
		addKeyListener('r', rightTurnCommand);
		//addKeyListener('e', collideWithEnergyStationCommand);
		//addKeyListener('g', collideWithDroneCommand);
		//addKeyListener('t', tickCommand);
		
		//toolbar 
		toolbar.addCommandToSideMenu(accelerateCommand);
		toolbar.addCommandToSideMenu(aboutCommand);
		toolbar.addCommandToSideMenu(exitCommand);
		//sound toolbar 
		toolbar.addComponentToSideMenu(soundCheck);
		soundCheck.setCommand(soundCommand);
		
		//help
		toolbar.addCommandToRightBar(helpCommand);
		
		//West Container Buttons
		accelerateButton = new Button("Accelerate"); 
		accelerateButton.setCommand(accelerateCommand);
		westContainer.addComponent(accelerateButton);	
		accelerateButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		accelerateButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		accelerateButton.getAllStyles().setBgTransparency(255);	
		accelerateButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		leftButton = new Button("Left"); 
		leftButton.setCommand(leftTurnCommand);
		westContainer.addComponent(leftButton);	
		leftButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		leftButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		leftButton.getAllStyles().setBgTransparency(255);	
		leftButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		strategyButton = new Button("Change Strategies"); 
		strategyButton.setCommand(strategyCommand);
		westContainer.addComponent(strategyButton);	
		strategyButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		strategyButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		strategyButton.getAllStyles().setBgTransparency(255);	
		strategyButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		//South Container Buttons 
		positionButton = new Button("Position"); 
		positionButton.setCommand(positionCommand);
		southContainer.addComponent(positionButton);	
		positionButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		positionButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		positionButton.getAllStyles().setBgTransparency(255);	
		positionButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		pauseButton = new Button("Pause"); 
		pauseButton.setCommand(pauseCommand);
		southContainer.addComponent(pauseButton);	
		pauseButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		pauseButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		pauseButton.getAllStyles().setBgTransparency(255);	
		pauseButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
	
		
		//east container buttons 
		brakeButton = new Button("Brake");
		brakeButton.setCommand(brakeCommand);
		eastContainer.addComponent(brakeButton);
		brakeButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		brakeButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		brakeButton.getAllStyles().setBgTransparency(255);	
		brakeButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		rightButton = new Button("Right");
		rightButton.setCommand(rightTurnCommand);
		eastContainer.addComponent(rightButton);
		rightButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		rightButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		rightButton.getAllStyles().setBgTransparency(255);	
		rightButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.WEST, westContainer);
		this.add(BorderLayout.SOUTH, southContainer);
		this.add(BorderLayout.EAST, eastContainer);
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER, mv);
		this.show();
		gw.setGameHeight(mv.getHeight());
		gw.setGameWidth(mv.getWidth());
		gw.init();
		gw.createSounds();
		this.revalidate();
		timer.schedule(70, true, this);
	}
	
	public char getExitKey() {
		return exitKey;
	}
	
	public void setExitKey(char Key) {
		this.exitKey = Key;
	}
	
	public void run() {
		if(gw.getGamePause() == false) {
			if (gw.getSoundValue() == true) {
				gw.getBgSound().play();
			}
			gamePlayButton();
			gw.tick(time);
		}
		else {
			gamePauseButton();
			pauseButton.setText("Pause");
			gw.getBgSound().pause();
			accelerateCommand.setEnabled(false);
		}
	}
	//how the buttons need to be when game is not paused
	public void gamePlayButton() {
		
		pauseButton.setText("Pause");
		
		positionCommand.setEnabled(false);
		positionButton.setEnabled(false);
		positionButton.setCommand(positionCommand);	
		positionButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		positionButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		positionButton.getAllStyles().setBgTransparency(255);	
		positionButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		pauseCommand.setEnabled(true);
		pauseButton.setEnabled(true);
		pauseButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		pauseButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		pauseButton.getAllStyles().setBgTransparency(255);	
		pauseButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		accelerateCommand.setEnabled(true);
		accelerateButton.setEnabled(true);
		accelerateButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		accelerateButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		accelerateButton.getAllStyles().setBgTransparency(255);	
		accelerateButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		leftTurnCommand.setEnabled(true);
		leftButton.setEnabled(true);
		leftButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		leftButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		leftButton.getAllStyles().setBgTransparency(255);	
		leftButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		strategyCommand.setEnabled(true);
		strategyButton.setEnabled(true);
		strategyButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		strategyButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		strategyButton.getAllStyles().setBgTransparency(255);	
		strategyButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		brakeCommand.setEnabled(true);
		brakeButton.setEnabled(true);
		brakeButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		brakeButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		brakeButton.getAllStyles().setBgTransparency(255);	
		brakeButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		rightTurnCommand.setEnabled(true);
		rightButton.setEnabled(true);
		rightButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		rightButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		rightButton.getAllStyles().setBgTransparency(255);	
		rightButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
				
		
		//turn key listners on
		addKeyListener('a', accelerateCommand);
		addKeyListener('b', brakeCommand);
		addKeyListener('l', leftTurnCommand);
		addKeyListener('r', rightTurnCommand);
		revalidate();
	}
	
	//how the buttons need to be when the game is paused
	public void gamePauseButton() {
		pauseButton.setText("Pause");
		
		
		positionCommand.setEnabled(true);
		positionButton.setEnabled(true);
		positionButton.setCommand(positionCommand);	
		positionButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		positionButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		positionButton.getAllStyles().setBgTransparency(255);	
		positionButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		
		pauseCommand.setEnabled(true);
		pauseButton.setEnabled(true);
		pauseButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		pauseButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		pauseButton.getAllStyles().setBgTransparency(255);	
		pauseButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		accelerateCommand.setEnabled(false);
		accelerateButton.setEnabled(false);
		accelerateButton.getAllStyles().setFgColor(ColorUtil.BLACK);
		accelerateButton.getAllStyles().setBgColor(ColorUtil.BLACK);
		accelerateButton.getAllStyles().setBgTransparency(255);	
		accelerateButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		leftTurnCommand.setEnabled(false);
		leftButton.setEnabled(false);
		leftButton.getAllStyles().setFgColor(ColorUtil.BLACK);
		leftButton.getAllStyles().setBgColor(ColorUtil.BLACK);
		leftButton.getAllStyles().setBgTransparency(255);	
		leftButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		strategyCommand.setEnabled(false);
		strategyButton.setEnabled(false);
		strategyButton.getAllStyles().setFgColor(ColorUtil.BLACK);
		strategyButton.getAllStyles().setBgColor(ColorUtil.BLACK);
		strategyButton.getAllStyles().setBgTransparency(255);	
		strategyButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		brakeCommand.setEnabled(false);
		brakeButton.setEnabled(false);
		brakeButton.getAllStyles().setFgColor(ColorUtil.BLACK);
		brakeButton.getAllStyles().setBgColor(ColorUtil.BLACK);
		brakeButton.getAllStyles().setBgTransparency(255);	
		brakeButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		rightTurnCommand.setEnabled(false);
		rightButton.setEnabled(false);
		rightButton.getAllStyles().setFgColor(ColorUtil.BLACK);
		rightButton.getAllStyles().setBgColor(ColorUtil.BLACK);
		rightButton.getAllStyles().setBgTransparency(255);	
		rightButton.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		//turn key listners on
		removeKeyListener('a', accelerateCommand);
		removeKeyListener('b', brakeCommand);
		removeKeyListener('l', leftTurnCommand);
		removeKeyListener('r', rightTurnCommand);
		revalidate();
	}
	

		
	



	/*private void play() {
		Label myLabel = new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();

		myTextField.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt) {
				
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if(sCommand.length() != 0)
					switch (sCommand.charAt(0)) {
					
					case 'a': 
						gw.accelerate();
						break;
						
					case 'b':
						gw.brake();
						break;
						
					case 'l':
						gw.steering('l');
						break;
						
					case 'r':
						gw.steering('r');
						break;
					
					case 'c':
						gw.CollisionRobot();
						break;
					
					case '1': 
						gw.baseCollision(1);
						break;
					
					case '2':
						gw.baseCollision(2);
						break;
						
					case '3':
						gw.baseCollision(3);
						break;
						
					case '4':
						gw.baseCollision(4);
						break;
						
					case '5':
						gw.baseCollision(5);
						break;
						
					case '6':
						gw.baseCollision(6);
						break;
					
					case '7':
						gw.baseCollision(7);
						break;
						
					case '8':
						gw.baseCollision(8);
						break;
					
					case '9':
						gw.baseCollision(9);
						break;
					
					case 'e':
						gw.energyStationCollision();
						break;
						
					case 'g':
						gw.CollisionDrone();
						break;
						
					case 't':
						gw.tick();
						break;
						
					case 'd':
						gw.display();
						break;
						
					case 'm':
						gw.map();
						break;
						
					case 'x':
						System.out.println("Are you sure you want to exit? Y or N?");
						setExitKey(sCommand.charAt(0));
						break;
					
					case 'y':
					case 'Y':
						if (getExitKey() == 'x') {
							System.exit(0);
						}
						
						break;
					
					case 'n':
					case 'N':
						if(getExitKey() == 'x') {
							System.out.println("Game resumed");
						}
						break;
						
					default: 
						System.out.println("Invalid input. Insert valid command");
						break;
					}
			}
			
			}
		);
		}
	*/
				
}
