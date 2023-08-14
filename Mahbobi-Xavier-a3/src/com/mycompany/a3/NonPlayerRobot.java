package com.mycompany.a3;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;


public class NonPlayerRobot extends Robot {
	Random random = new Random();
	private int size;
	private IStrategy currentStrategy;
	public NonPlayerRobot(double xLocation, double yLocation, int size){
		super(xLocation, yLocation, size);
		this.setColor(ColorUtil.YELLOW);
		this.setLastBaseReached(1);
		this.size = size;
	}
	
	public void setStrategy(IStrategy s) {
		currentStrategy = s;
	}
	
	public String getStrategy(){
		if (currentStrategy instanceof AttackStrategy) {
			return "Attack Strategy";
		}
		else
			return "Base Strategy";
	}
	
	public int addNumber(int x) {
		return x + 1;
	}
	
	
	public void invokeStrategy() {
		currentStrategy.invokeStrat();
	}
	
	public String toString() {
		String parentDesc = super.toString();
		return "NonPlayerRobot: "  + parentDesc + " Strategy: " + getStrategy(); 
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xValue = (int)this.getXLocation() + (int)pCmpRelPrnt.getX();
		int yValue = (int)this.getYLocation() + (int)pCmpRelPrnt.getY();
		g.setColor(this.getColor());
		g.drawRect(xValue, yValue, size, size);
	}
	
	
}
