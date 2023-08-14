package com.mycompany.a3;

import java.util.Observer;
import java.util.Observable;
import java.util.*;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Container;
import com.codename1.ui.geom.Point;

public class MapView extends Container implements Observer  {
	private GameWorld gw;
	
	public MapView(Observable GameWorld){
		gw = (GameWorld) GameWorld;
		GameWorld.addObserver(this);
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255,0,0)));
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		/*
		Recall that current location of the object is defined relative to the origin of the
		game world (which corresponds to the origin of the MapView in A#2 and A#3). Hence, do not
		forget to add MapViews origin location (relative to its parent containers origin) to the current
		location while drawing your game objects since draw...() methods
		*/
		Point pCmpRelPrnt = new Point(getX(), getY());
		IIterator currentObject = gw.gameObj.getIterator();
		//loop through game objects and draw them
		while (currentObject.hasNext()) {
			GameObject object = ((GameObject) currentObject.getNext());
			//Each objects draw() method draws the object in its current color and size, at its current location.
			object.draw(g, pCmpRelPrnt);
		}
	}
	
	public void pointerPressed(int x, int y) {
		System.out.println(x+"  "+y);
		
		//used your slide to get started with this function
		
		//this gives us our new location from where our cursor is current located on mapview
		//i tried using the x & y below, but it was really buggy, made this instead 
		int newXLocation = x - this.getAbsoluteX();
		int newYLocation = y - this.getAbsoluteY();
		
		//from slides
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		//from slides
		Point pPtrRelPrnt = new Point(x,y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		IIterator currentObject = gw.gameObj.getIterator();
		if (gw.getGamePause() == true) {
		while (currentObject.hasNext()) {
			
			GameObject object = ((GameObject) currentObject.getNext());
			//if object is a FixedGameObject (base/energystation) then continue
			//using object instanceof FixedGameObject as our condition since fixedObject implements ISelectable
			//makes it easier for me 
			if (object instanceof FixedGameObject) {
				FixedGameObject fixedObject = (FixedGameObject)object;
				//the object needs to be selected and the position button needs to have been clicked
				//if the object is selected and the position button has been click then
				//set the x and y location to the the mouse cursor
				//setSelected back to false since it was just move, draw the object 
				if (fixedObject.isSelected() == true && gw.getPosition() == true) {
				    System.out.println("Object ready to be moved");
				    fixedObject.setXLocation(newXLocation);
				    fixedObject.setYLocation(newYLocation);
				    fixedObject.setSelected(false);
				    this.repaint();
				    gw.map();
				    gw.setIsPosition(false);
				} 
				
				else {
					
					if (fixedObject.contains(pPtrRelPrnt, pCmpRelPrnt)) {
				        System.out.println("Need to click the position button for object");
				        fixedObject.setSelected(true);
				    } 
				    
				    else {
				        fixedObject.setSelected(false);
				    }
					
				    this.repaint();
				    gw.map();
				}
			
			}	
		}
		
		}
		
	}
	


	
	@Override
	public void update(Observable observable, Object data) {
		//When the MapView update() is invoked, it should now also should call
		//repaint() on itself. As described in the course notes, MapView should override paint(),
		//which will be invoked as a result of calling repaint(). 
		this.repaint();
		gw.map();
	}
	
	
}
