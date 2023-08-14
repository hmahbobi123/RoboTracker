package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Base extends FixedGameObject  {
	private int sequenceNumber = 1; //base numbering starts at 4, "sequenceNumber" as per spec
	private int size;
	public Base(double xLocation, double yLocation, int size, int sequenceNumber) {
		super(xLocation, yLocation, size);
		super.setColor(ColorUtil.BLUE); //blue as suggested in specs 
		this.sequenceNumber = sequenceNumber;
		this.size = size;
	}
	//getter and setter methods for Bases 
	public void setBaseNumber(int baseNumber) {
		this.sequenceNumber = baseNumber;
	}
	
	public int getBaseNumber() {
		return sequenceNumber;
	}
	
	//color is fixed to blue 
	public void setColor(int color) {} 
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		//used slide 7 / documentation
		//much harder to draw triangle than a circle or square
		int[] xCord = new int[3];
		int[] yCord = new int[3];
		int width = size / 2;
		int xValue = (int)this.getXLocation() + (int)pCmpRelPrnt.getX();
		int yValue = (int)this.getYLocation() + (int)pCmpRelPrnt.getY();
		
		//top corner x 
		xCord[0] = xValue;
		//top corner y
		yCord[0] = yValue;
		//left corner x
		xCord[1] = xValue - width;
		//left corner y
		yCord[1] = yValue + size;
		//right corner x 
		xCord[2] = xValue + width;
		//right corner y
		yCord[2] = yValue + size;
		String seqNum = Integer.toString(sequenceNumber);
		g.setColor(this.getColor());
		if (isSelected() == false) {
			g.fillPolygon(xCord, yCord, 3);
		}
		else {
			g.drawPolygon(xCord, yCord, 3);
		}
		g.setColor(0);
		g.drawString(seqNum, xValue, yValue);
	}
	
	
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " Base Number= " + sequenceNumber;
		String totalDesc = parentDesc + myDesc;
		return "Base: " + totalDesc;
		
	}


}
