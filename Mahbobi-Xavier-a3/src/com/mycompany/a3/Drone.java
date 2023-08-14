package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.Random;
public class Drone extends MoveableGameObject {
	private Random random = new Random();
	private int size;
	public Drone(double xLocation, double yLocation, int size) {
		super(xLocation, yLocation, size);
		super.setColor(ColorUtil.YELLOW);
		this.size = size;
		this.setHeading(randomStartHeading());
		this.setSpeed(randomStartSpeed());
		
	}
	public void setColor(int color) {}
	
	//functions to provide random values for fields
	public int randomStartHeading() {
		return random.nextInt(359);
	}
	
	public int randomStartSpeed() {
		return random.nextInt(10 - 5) + 5;
	}
	
	public void randomDroneUpdate() {
		int newDroneHeading;
		int randomNumber = random.nextInt(20 + 15) - 20;
		randomNumber += getHeading(); 
		this.setHeading(randomNumber);
		
	}
	//function to update heading to be between 0-360 without going under or over
	public void updateHeading() {
		int updateHeading = getHeading();
		if (updateHeading < 0) {
			this.setHeading(updateHeading + 360);
		}
		else if (updateHeading > 360) {
			this.setHeading(updateHeading - 360);
		}
		else {
			this.setHeading(updateHeading);
		}
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xValue = (int)this.getXLocation() + (int)pCmpRelPrnt.getX();
		int yValue = (int)this.getYLocation() + (int)pCmpRelPrnt.getY();
		g.setColor(this.getColor());
		//need to use the location and size attributes of the object to determine where to draw the object so its center
		//with its location 
		Point LeftCorner = new Point(xValue - size, yValue - size);
		Point TopCorner = new Point(xValue, size + yValue);
		Point RightCorner = new Point(xValue + size, yValue - size);
		g.drawLine((int)TopCorner.getX(), (int)TopCorner.getY(), (int)LeftCorner.getX(), (int)LeftCorner.getY());
		g.drawLine((int)TopCorner.getX(), (int)TopCorner.getY(), (int)RightCorner.getX(), (int)RightCorner.getY());
		g.drawLine((int)LeftCorner.getX(), (int)LeftCorner.getY(), (int)RightCorner.getX(), (int)RightCorner.getY());
	}
	
	
	public String toString() {
		String parentDesc = super.toString();
		return "Drone: " + parentDesc;
	}
}
