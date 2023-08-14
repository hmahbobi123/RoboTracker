package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class GameObject implements IDrawable, ICollider {
	//All game objects have the following properties
	//All game objects can be represented by a 2D position (x,y)
	private double xLocation = 0;
	private double yLocation = 0;
	private int size;
	private int color;
	GameWorld gw;
	Game g;
	
	public GameObject(double xLocation, double yLocation, int size) {
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.size = size;
	}
	
	//Get and set methods for (x,y) location, size, and color 
	public void setXLocation(double xLocation) {
		this.xLocation = xLocation;
	}
	
	public double getXLocation() {
		return xLocation;
		
	}
	
	public void setYLocation(double yLocation) {
		this.yLocation = yLocation;
	}
	
	public double getYLocation() {
		return yLocation;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
	/*GameObject class should implement this interface and each
	concrete game object class should then provide code for drawing that particular object using
	the received Graphics object g (which belong to MapView) and Point object
	pCmpRelPrnt, which is the component location (MapViews origin location which is located at
	its the upper left corner) relative to its parent containers origin (parent of MapView is the
	content pane of the Game form and origin of the parent is also located at its upper left corner).
	Remember that calling getX() and getY() methods on MapView would return the MapView
	components location relative to its parent containers origin.
	*/
	public void draw(Graphics g, Point pCmpRelPrnt) {}
	
	//As indicated in A#1, each type of game object has a different shape which can be bounded by a square. *from assignment*
	//Since each shape is bounded by a square, we will check if the top/bottom or left/right overlap
	//AKA Box box collision 
	public boolean collidesWith(GameObject otherObject) {
		double top = (this.getYLocation()) - (this.getSize() / 2);
		double bot = (this.getYLocation()) + (this.getSize() / 2);
		double left = (this.getXLocation()) - (this.getSize() / 2);
		double right = (this.getXLocation()) + (this.getSize() / 2);
		
		double otherObjectTop = (otherObject.getYLocation()) - (otherObject.getSize() / 2);
		double otherObjectBot = (otherObject.getYLocation()) + (otherObject.getSize() / 2);
		double otherObjectLeft = (otherObject.getXLocation()) - (otherObject.getSize() / 2);
		double otherObjectRight = (otherObject.getXLocation()) + (otherObject.getSize() / 2);
		
		
		if (right > otherObjectLeft && left < otherObjectRight && bot > otherObjectTop && top < otherObjectBot) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void handleCollision(GameObject otherObject, GameWorld gw) {
		if (this instanceof PlayerRobot) {
			if (otherObject instanceof Base) {
				//we need to get the base number since our base collision function requires it to progress 
				gw.baseCollision(((Base) otherObject).getBaseNumber());
			}
			
			if (otherObject instanceof Drone) {
				gw.CollisionDrone();
			}
			
			if (otherObject instanceof EnergyStation) {
				gw.energyStationCollision(otherObject);	
			}
			
			if (otherObject instanceof NonPlayerRobot) {
				gw.CollisionRobot();
			}
		}
		/*
		 * couldnt get npr collisions to work
		if (this instanceof NonPlayerRobot) {
			if (otherObject instanceof Base) {
				gw.baseCollision(((Base) otherObject).getBaseNumber());
				gw.setStratNPR();
			}
			
		}
		*/
	}
	
	
	
	//All game objects have Location, Color, and Size
	//Color is (x,x,x) RGB
	public String toString() {
		String myDesc = " Location= (" + Math.round(xLocation * 10.0) / 10.0 + "," + Math.round(yLocation * 10.0)/ 10.0 +
		") Color= (" + ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) + ")"  +
		" Size= " + this.getSize();
		return myDesc;
	}
	
}
