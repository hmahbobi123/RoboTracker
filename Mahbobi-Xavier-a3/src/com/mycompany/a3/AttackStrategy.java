package com.mycompany.a3;
import com.codename1.util.MathUtil;
import java.lang.Math;

public class AttackStrategy implements IStrategy {
	private PlayerRobot pr;
	private NonPlayerRobot npr;	
	
	
	public AttackStrategy(GameObjectCollection gameObjects, NonPlayerRobot npr) {
		IIterator currentObject = gameObjects.getIterator();
		while (currentObject.hasNext()) {
			GameObject object = (GameObject) currentObject.getNext();
			if (object instanceof PlayerRobot) {
				pr = ((PlayerRobot)object);
			}
		}
		this.npr = npr;
	}
	
	
	public void invokeStrat() {
		double playerX, playerY, nprX, nprY;
		playerX = pr.getXLocation();
		playerY = pr.getYLocation();
		nprX = npr.getXLocation();
		nprY = npr.getYLocation();
		double newX = playerX - nprX;
		double newY = playerY - nprY;
		double newAngle = (90 -  Math.toDegrees(MathUtil.atan2(newX,newY)));
		int angle = 90 - (int)newAngle;
		npr.setHeading(npr.getSteeringDirection()+angle);
	}
}