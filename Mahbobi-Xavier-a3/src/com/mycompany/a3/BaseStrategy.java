package com.mycompany.a3;

import com.codename1.util.MathUtil;

public class BaseStrategy implements IStrategy {
	private NonPlayerRobot npr;
	private Base base;
	
	public BaseStrategy(GameObjectCollection gameObjects, NonPlayerRobot npr) {
		this.npr = npr;
		IIterator currentObject = gameObjects.getIterator();
		while (currentObject.hasNext()) {
			GameObject object = (GameObject) currentObject.getNext();
			if (object instanceof Base) {
				if(((Base)object).getBaseNumber() == (npr.getLastBaseReached()+1)) {
					base = ((Base)object);
				}
			}
		}
	}
	
	public void invokeStrat() {
		double baseX, baseY, nprX, nprY;
		baseX = base.getXLocation();
		baseY = base.getYLocation();
		nprX = npr.getXLocation();
		nprY = npr.getYLocation();
		double newX = baseX - nprX;
		double newY = baseY - nprY;
		double newAngle = (90 -  Math.toDegrees(MathUtil.atan2(newX,newY)));
		int angle = 90 - (int)newAngle;
		npr.setHeading(npr.getSteeringDirection()+angle);
	}
	
	
}
