package com.mycompany.a3;

import com.codename1.ui.geom.Point;

public abstract class FixedGameObject extends GameObject implements ISelectable{
	//Fixed game objects are represented by a 2D point
	//Fixed game objects can't be changed... they are fixed
	
	private boolean isSelected;
	
	public FixedGameObject(double xLocation, double yLocation, int size) {
		super(xLocation, yLocation, size);
	}
	
	//in a3 we can move it with position, this took me hrs to realize this was my error 
	
	//public void setXLocation(double xlocation) {
		//can't be changed
	//}
	//public void setYLocation(double ylocation) {
		//can't be changed
	//}
	
	public boolean isSelected() {
		return this.isSelected;
	}
	
	public void setSelected(boolean b) {
		this.isSelected = b;
	}
	
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
        
        //Get the coordinates of the pointer relative 
        //Get the size of the object
        int pointerLocationX = pPtrRelPrnt.getX();
        int pointerLocationY = pPtrRelPrnt.getY();
        int size = this.getSize();
        
        //Calculate coordinates of the object relative
        int objLeft = (int)(pCmpRelPrnt.getX() + this.getXLocation() - size / 2);
        int objRight = (int)(pCmpRelPrnt.getX() + this.getXLocation() + size / 2);
        int objTop = (int)(pCmpRelPrnt.getY() + this.getYLocation() - size / 2);
        int objBot = (int) (pCmpRelPrnt.getY() + this.getYLocation() + size / 2);
        
        
        if ((pointerLocationX >= objLeft) && (pointerLocationX < objRight) && (pointerLocationY >= objTop) && (pointerLocationY < objBot)){
            return true;
        }
        else {
            return false;
        }
        
    }
	
}
