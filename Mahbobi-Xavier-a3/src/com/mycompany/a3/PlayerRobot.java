package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class PlayerRobot extends Robot {
	private int size; 
	public PlayerRobot(double xLocation, double yLocation, int size) {
		super(xLocation, yLocation, size);
		this.setColor(ColorUtil.MAGENTA);
		this.size = size;
		
	}

	public String toString() {
		String parentDesc = super.toString();
		return "Robot:" + parentDesc;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xValue = (int)this.getXLocation() + (int)pCmpRelPrnt.getX();
		int yValue = (int)this.getYLocation() + (int)pCmpRelPrnt.getY();
		g.setColor(this.getColor());
		g.drawRect(xValue, yValue, size, size);
		g.fillRect(xValue, yValue, size, size);
	}
	
	
}
