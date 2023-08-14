package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;
import java.lang.String; 
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.BoxLayout;

public class ScoreView extends Container implements Observer{
	private GameWorld model;
	
	private Label timeLabel;
	private Label timeValue;
	
	private Label livesLabel;
	private Label livesValue;
	
	private Label lastBaseLabel;
	private Label lastBaseValue;
	
	private Label playerEnergyLabel;
	private Label playerEnergyValue;
	
	private Label playerDamageLabel;
	private Label playerDamageValue;
	
	private Label soundLabel;
	private Label soundValue;
	
	public ScoreView(Observable myModel) {
		
		model = (GameWorld) myModel;
		myModel.addObserver(this);
		
		this.setLayout(new FlowLayout(Component.CENTER));
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GRAY));
		
		this.initLabels();
		this.add(timeLabel);
		this.add(timeValue);
		this.add(livesLabel);
		this.add(livesValue);
		this.add(lastBaseLabel);
		this.add(lastBaseValue);
		this.add(playerEnergyLabel);
		this.add(playerEnergyValue);
		this.add(playerDamageLabel);
		this.add(playerDamageValue);
		this.add(soundLabel);
		this.add(soundValue);
	}
	
	public void initLabels() {
		timeLabel = new Label("Time: ");
		timeValue = new Label("" + model.getClock());
		timeLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		livesLabel = new Label("Lives Left: ");
		livesValue = new Label("" + model.getLives());
		livesLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		lastBaseLabel = new Label ("Player Last Base Reached: ");
		lastBaseValue = new Label ("" + model.getLastBaseReached());
		lastBaseLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		lastBaseValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		playerEnergyLabel = new Label ("Player Energy Level: ");
		playerEnergyValue = new Label ("" + model.getEnergyLevel());
		playerEnergyLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		playerEnergyValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		playerDamageLabel = new Label ("Player Damage Level: ");
		playerDamageValue = new Label ("" + model.getDamageLevel());
		playerDamageLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		playerDamageValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		soundLabel = new Label ("Sound: ");
		soundValue = new Label ("" + model.isSoundOnOff());
		soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		
	}
	
	public void updateScoreBoard() {
		timeValue.setText("" + model.getClock());
		timeValue.getParent().revalidate();
		
		livesValue.setText("" + model.getLives());
		livesValue.getParent().revalidate();
		
		lastBaseValue.setText("" + model.getLastBaseReached());
		lastBaseValue.getParent().revalidate();
		
		playerEnergyValue.setText("" + model.getEnergyLevel());
		playerEnergyValue.getParent().revalidate();
		
		playerDamageValue.setText("" + model.getDamageLevel());
		playerDamageValue.getParent().revalidate();
		
		soundValue.setText("" + model.isSoundOnOff());
		soundValue.getParent().revalidate();
		
	}
	
	public void update(Observable observable, Object data) {
		this.updateScoreBoard();
		
	}
}
