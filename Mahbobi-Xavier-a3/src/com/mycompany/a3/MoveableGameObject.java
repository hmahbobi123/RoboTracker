package com.mycompany.a3;
import java.lang.Math;

import com.codename1.charts.util.ColorUtil;

public abstract class MoveableGameObject extends GameObject {
	//Move-able objects have heading and speed
	//Declared protected so Robot class can access 
	private int heading = 0;
	protected int speed = 0;
	
	public MoveableGameObject(double xLocation, double yLocation, int size) {
		super(xLocation, yLocation, size);
	}
	
	//get and set methods for heading and speed
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	public int getHeading() {
		return heading;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	//1024 x 768
	public void move(int mapHeight, int mapWidth, int time) {
		//Theta = (90 -  heading), use Math.toRadians to convert from degrees to radians 
		double deltaX = (this.getXLocation() + (double)Math.cos(Math.toRadians(90 - this.getHeading())) * this.getSpeed());
		double deltaY = (this.getYLocation() + (double)Math.sin(Math.toRadians(90 - this.getHeading())) * this.getSpeed());
		this.setXLocation(deltaX);
		this.setYLocation(deltaY);
	}
	

	
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " heading=" + heading + " speed=" + speed;
		return parentDesc + myDesc;
	}
		
}
