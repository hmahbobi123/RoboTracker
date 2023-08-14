package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

public  class Robot extends MoveableGameObject implements ISteerable {
	//as listed in specs, following attributes Robots have
	private int steeringDirection = 5;
	private int maximumSpeed = 30;
	private int energyLevel = 175;
	private int maxEnergyLevel = 200;
	private int energyConsumptionRate = 1;
	private int damageLevel = 0;
	private int maximumDamageLevel = 100;
	private int lastBaseReached = 1;
	protected int red = 255;
	
	
	public Robot(double xLocation, double yLocation, int size) {
		super(xLocation, yLocation, size);
		super.setColor(ColorUtil.YELLOW);
		super.setSpeed(5);
		super.setHeading(0);
	}
	
	public int getMaximumDamageLevel() {
		return maximumDamageLevel;
	}
	
	
	//handles robot steering and 
	public void Steer(char x) {
        if (x == 'l') {
            if(this.getSteeringDirection() > (-40)) {
                steeringDirection -= 5;
            }
        }
            
        if (x == 'r') {
            if(this.getSteeringDirection() < (40)) {
                steeringDirection += 5;    
            }
        }
    }
	public void updateHeading() {
        if (this.getHeading() < 0) {
            this.setHeading(this.getHeading() + 360);
        }
        else if (this.getHeading() > 360) {
            this.setHeading(this.getHeading() - 360);
        }
    }
	//getter for steeringDirection
	public int getSteeringDirection() {
		return steeringDirection;
	}
	
	
	
	//setting speed but not allowing a speed greater than 30	
	public void setSpeed(int speed) {
		if(speed > 30) {
			System.out.println("At max speed");
		}
		else if(speed < 0) {
			System.out.println("Ship is stopped");
		}
		else {
			super.setSpeed(speed);
		}
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setMaximumSpeed(int speed) {
		this.maximumSpeed = 30;
	}
	
	public int getMaximumSpeed() {
		return maximumSpeed;
	}

	//sets Robot energyLevel
	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel; 
	}
	
	//get energy level
	public int getEnergyLevel() {
		return energyLevel;
	}
	
	public void setMaxEnergyLevel(int maxEnergyLevel) {
		this.maxEnergyLevel = maxEnergyLevel; 
	}
	
	//get energy level
	public int getMaxEnergyLevel() {
		return maxEnergyLevel;
	}
	
	
	//energy level decreased when game ticks
	public void tickEnergy() {
		int eLevel = getEnergyLevel() - energyConsumptionRate;
		energyLevel = eLevel;
	}
	
	//Each  time  the  robot  reaches  the  next 
	//higher-numbered  base,  the  robot  is  deemed  
	//to  have  successfully  moved  that  far  along  the 
	//track  and  its  lastBaseReached  field  is  updated. 
	public void baseCollision(int sequenceNumber){
		if ((sequenceNumber - getLastBaseReached() == 1)){
			lastBaseReached = sequenceNumber; 
			System.out.println("Correct base number, continue to next base");
		}
		else {
			System.out.println("Are you sure you're at the right base?");
		}
	 
	}
	//getter for lastBaseReached
	public int getLastBaseReached() {
		return lastBaseReached;
	}
	
	public void setLastBaseReached(int lastBaseReached) {
		this.lastBaseReached = lastBaseReached;
	}
	
	public void setDamageLevel(int damageLevel) {
		
		this.damageLevel += damageLevel;
	}
	
	//getter for damageLevel
	public int getDamageLevel() {
		return damageLevel;
	}
	
	//R command as per spec
	public void collisionRobot() {
			damageLevel += 15;
			setColor(ColorUtil.rgb((red-=5), 0, 0));
			updateRobotsSpeed();
		
	}
	
	//D command as per spec
	public void collisionDrone(){
			damageLevel += 10;
			setColor(ColorUtil.rgb((red-=10), 0, 0));
			updateRobotsSpeed();
	}
	
	
	public void updateRobotsSpeed() {
		maximumSpeed = ((int) (30 - (30*(damageLevel/100.00) )));
		if (getSpeed() > maximumSpeed) {
			setSpeed(maximumSpeed);
		}
	}
	//When player dies, reset to base values
	public void reset() {
		setEnergyLevel(175);
		setXLocation(500);
		setYLocation(500);
		setSpeed(5);
		setMaximumSpeed(30);
		damageLevel = 0;
		this.setColor(ColorUtil.MAGENTA);
	}
	
	public String toString() // Display method
	{
		String parentDesc = super.toString();
		String myDesc = " maxSpeed=" + maximumSpeed + " steeringDirection=" + getSteeringDirection() + 
						" energyLevel=" + getEnergyLevel()  + " damageLevel=" + getDamageLevel();
		return parentDesc + myDesc;
		
	}

	
	
	
	
	
	
	


}
