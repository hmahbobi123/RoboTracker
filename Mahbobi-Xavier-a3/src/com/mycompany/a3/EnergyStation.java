package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class EnergyStation extends FixedGameObject {
	private int capacity;
	
	public EnergyStation(double xLocation, double yLocation, int size) {
		super(xLocation, yLocation, size);
		super.setColor(ColorUtil.GREEN);
		capacity = size;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getCapacity() {
		return capacity;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xValue = (int)this.getXLocation() + (int)pCmpRelPrnt.getX();
		int yValue = (int)this.getYLocation() + (int)pCmpRelPrnt.getY();
		int CenterX = (int) (xValue + (this.getSize()/2)); 
		int CenterY = (int) (yValue + (this.getSize()/2)); 
		String myString = String.valueOf(getCapacity());
		g.setColor(this.getColor());
		if (this.isSelected() == true) {
			g.drawArc(xValue, yValue, getSize(), getSize(), 0, 360);
		}
		else {
			g.fillArc(xValue, yValue, getSize(), getSize(), 0, 360);
		}
		
		//g.drawArc(xValue, yValue, getSize(), getSize(), 0, 360);
		//g.fillArc(xValue, yValue, getSize(), getSize(), 0, 360);
		g.setColor(0);
		g.drawString(myString, CenterX, CenterY);
	}
	
	
	
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " Capcity=" + capacity;
		String totalDesc = parentDesc + myDesc;
		return "Energy Station: " + totalDesc;
		
	}

}
