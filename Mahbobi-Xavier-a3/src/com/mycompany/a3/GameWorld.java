package com.mycompany.a3;
import java.util.Vector;
import java.util.Observable;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.table.TableLayout;

public class GameWorld extends Observable {
	private int lives = 3;
	private int clock = 0;
	private boolean soundOnOff = false;
	private PlayerRobot probot;
	private NonPlayerRobot nprrobots1;
	private NonPlayerRobot nprrobots2;
	private NonPlayerRobot nprrobots3;
	//private Vector<GameObject> gameObj = new Vector<GameObject>();
	private Vector<GameObject> collisionList = new Vector<GameObject>();
	private Vector<GameObject> collisionList2 = new Vector<GameObject>();
	GameObjectCollection gameObj;
	private Random random = new Random();
	private int mapHeight;
	private int mapWidth;
	private int lastBase = 4;
	private Sound energySound, gameDeathSound, robotSound;
	private BGSound bgSound;
	private boolean pause = false; 
	private boolean position = false;
	
	public GameWorld() {
		gameObj = new GameObjectCollection();
		nprrobots1 = new NonPlayerRobot(150, 150, 40);
		nprrobots2 = new NonPlayerRobot(50, 50, 40);
		nprrobots3 = new NonPlayerRobot(1000, 1000, 40);
		probot = new PlayerRobot(700, 28 , 40);
	}
	
	public void init() {
		createSounds();
		gameObj.add(probot);
		gameObj.add(nprrobots1);
		gameObj.add(nprrobots2);
		gameObj.add(nprrobots3);
		addBases();
		addEnergyStations();
		addDrone();
		IIterator currentObject = gameObj.getIterator();
		while (currentObject.hasNext()) {
			GameObject object = ((GameObject) currentObject.getNext());
			if (object instanceof NonPlayerRobot) {
				int startingStrat = random.nextInt(2);
				if(startingStrat == 1) {
					((NonPlayerRobot)object).setStrategy(new AttackStrategy(gameObj,(NonPlayerRobot)object));
				}
				else {
					((NonPlayerRobot)object).setStrategy(new BaseStrategy(gameObj,(NonPlayerRobot)object));
				}
						
			}
		}
		
		this.setChanged();
		this.notifyObservers();
		
	}
	
	public void addBases() {
		gameObj.add(new Base(100, 100, 40, 1));
		gameObj.add(new Base(550.0, 500.0, 40, 2));
		gameObj.add(new Base(900.0, 300.0, 40, 3));
		gameObj.add(new Base(1200.0, 1200.0, 40, 4));
	}
	

	
	public void addEnergyStations() {
		gameObj.add(new EnergyStation(randomX(), randomY(), randomSize()));
		gameObj.add(new EnergyStation(randomX(), randomY(), randomSize()));
		
	}
	
	public void addDrone() {
		gameObj.add(new Drone(randomX(), randomY(), randomSize()));
		gameObj.add(new Drone(randomX(), randomY(), randomSize()));
	}
	
	
	//following methods for top of screen
	//get player current time
	public int getClock() {
		return clock;
	}
	
	//get player current lives
	public int getLives() {
		return lives;
	}
	
	//get player lastBaseReached
	public int getLastBaseReached() {
		return probot.getLastBaseReached();
	}
	
	//get player energy level
	public int getEnergyLevel() {
		return probot.getEnergyLevel();
	}
	
	//get player damage level
	public int getDamageLevel() {
		return probot.getDamageLevel();
	}
	
	//get if sound is on or off
	public String isSoundOnOff() {
		if (this.soundOnOff) {
			
			return " ON";
		}
		else {
			return " OFF";
			
		}
		
	}
	
	public void togglePosition() {
		this.position = !(this.position);
		System.out.println(position);
		this.setChanged();
		this.notifyObservers();
	}
	
	//toggles sound ON and OFF 
	public void toggleSound() {
		this.soundOnOff = !(this.soundOnOff);
		this.setChanged();
		this.notifyObservers();
	}
	
	public boolean getSoundValue() {
		return soundOnOff;
	}
	
	//functions to provide command functionality as listed in specs 
	public void CollisionDrone() {
		if (getSoundValue() == true) {
			getRobotSound().play();
		}
		probot.collisionDrone();
		System.out.println("You just hit a drone!");
		death();
		this.setChanged();
		this.notifyObservers();
	}
	public void CollisionRobot() {
		if (getSoundValue() == true) {
			getRobotSound().play();
		}
		probot.collisionRobot();
		System.out.println("You just hit a Robot!");
		death();
		this.setChanged();
		this.notifyObservers();
	}
	
	public void accelerate() {
		int acceleration = 5;
		probot.setSpeed(probot.getSpeed() + acceleration );
		System.out.println("Speed increased");
		this.setChanged();
		this.notifyObservers();
	}
	
	public void brake() {
		int brake = -5;
		probot.setSpeed(probot.getSpeed() + brake );
		System.out.println("Speed decreased");
		this.setChanged();
		this.notifyObservers();
	}
	
	public void steering(char x) {
        if (x == 'l') {
            System.out.println("You have turned left");
            probot.Steer(x);
            probot.setHeading(probot.getHeading() + probot.getSteeringDirection());
            probot.updateHeading();
        }
        
        if (x == 'r') {
            System.out.println("You have turned right");
            probot.Steer(x);
            probot.setHeading(probot.getHeading() + probot.getSteeringDirection());
            probot.updateHeading();
        }
        this.setChanged();
        this.notifyObservers();
    }
	
	public void baseCollision(int x) {
			Command exitCommand = new Command("Exit");
			Dialog winBox = new Dialog("Last Base Reached, You Win!");
			System.out.println("Colliding with base: " + x);
			probot.baseCollision(x);
			if (getLastBaseReached() == lastBase) {
				Command youWinCommand = Dialog.show("", winBox, exitCommand);
			}
			this.setChanged();
			this.notifyObservers();
	}
	/*
	public void energyStationCollision() {
		probot.setEnergyLevelCollision(probot.getEnergyLevel());
		for(GameObject temp: gameObj) {
			if(temp instanceof EnergyStation) {
				if(((EnergyStation) temp).getCapacity()!=0) {
					probot.setEnergyLevel(((EnergyStation) temp).getCapacity());
					((EnergyStation) temp).setCapacity(0);
					temp.setColor(ColorUtil.MAGENTA);
					break;
				}
			}
		}
		gameObj.add(new EnergyStation(randomX(), randomY(), randomSize()));
	}
	*/
	
	public void energyStationCollision(GameObject stationHit) {
		
		if (getSoundValue() == true) {
			getESound().play();
		}
		
		if(((EnergyStation) stationHit).getCapacity() != 0) {
			probot.setEnergyLevel(probot.getEnergyLevel() + ((EnergyStation)stationHit).getCapacity());
			gameObj.add(new EnergyStation(randomX(), randomY(), randomSize()));
				if(probot.getEnergyLevel() > probot.getMaxEnergyLevel()) {
					probot.setEnergyLevel(probot.getMaxEnergyLevel());
				}
			((EnergyStation) stationHit).setCapacity(0);
				if (stationHit instanceof EnergyStation) {
				((EnergyStation) stationHit).setColor(ColorUtil.MAGENTA);
				}
		}
					
		this.setChanged();
		this.notifyObservers();
	}
	
	
	//functions to produce random numbers 
	public double randomX() {
		int randomNumber =  random.nextInt(getGameWidth());
		return randomNumber;
	}
	
	public double randomY() {
		int randomNumber =  random.nextInt(getGameHeight());
		return randomNumber;
	}
	public int randomSize() {
		int randomNumber = random.nextInt(80) + 40;
		return randomNumber;
	}
	
	
	public void tick(int gametick) {
		clock++;
		death();
		IIterator currentObject = gameObj.getIterator();
		
		while (currentObject.hasNext() ) {
			GameObject object = (GameObject) currentObject.getNext();
			if (object instanceof MoveableGameObject) {
				((MoveableGameObject) object).move(getGameHeight(), getGameWidth(), getClock());
			
			}
			
			if (object instanceof NonPlayerRobot) {
				((NonPlayerRobot) object).invokeStrategy();
			}
			
			if(object instanceof Drone) {
				if (((Drone) object).getXLocation() > getGameHeight() || ((Drone) object).getXLocation() < 0 ){
					((Drone) object).setXLocation(650);
					((Drone) object).setYLocation(650);
				}
				if (((Drone) object).getYLocation() > getGameHeight() || ((Drone) object).getYLocation() < 0 ){
					((Drone) object).setXLocation(650);
					((Drone) object).setYLocation(650);
				}
			}
			
			if (object instanceof NonPlayerRobot) {
				if (((NonPlayerRobot) object).getLastBaseReached() == lastBase){
					System.out.println("Game Over, AI robot wins! Total time: " + this.clock);
					System.exit(0);
				}
			}
			
		}
		
		
		//collision check per tick
		
		/*When collidable object obj1 collides with obj2, right after handling the
		collision, you need to add obj2 to collision vector of obj1. If obj2 is also a collidable object,
		you also need to add obj1 to collision vector of obj2. Each time you check for possible
		collisions (in each clock tick, after the moving the objects) you need to update the collision
		vectors. If the obj1 and obj2 are no longer colliding, you need to remove them from each
		others collision vectors. 
		*/
		
		
		IIterator collisionIterator = gameObj.getIterator();
		IIterator collisionIterator2 = gameObj.getIterator();
		while (collisionIterator.hasNext()) {
			GameObject collisionObject = (GameObject) collisionIterator.getNext();
			if (probot.collidesWith(collisionObject)) {
				/*
				You can use contains() method of
				Vector to check if the object is already in the collision vector or not. The contains()
				method is also useful for deciding whether to handle collision or not.
				*/
				if (collisionList.contains((GameObject)collisionObject) == false) {
					collisionList.add((GameObject)collisionObject);
					probot.handleCollision(collisionObject, this);
				}
				//If the obj1 and obj2 are no longer colliding, you need to remove them from each others collision vectors.
			}
			else if (collisionList.contains((GameObject)collisionObject)) {
				collisionList.remove((GameObject)collisionObject);
			}
		}
		
		//npr collision handler, does not work
		while (collisionIterator2.hasNext()) {
			IIterator nprObject = gameObj.getIterator();
			GameObject collisionObject2 = (GameObject) collisionIterator2.getNext();
			if (nprrobots1.collidesWith(collisionObject2) || nprrobots2.collidesWith(collisionObject2) || nprrobots3.collidesWith(collisionObject2)) {
				/*
				You can use contains() method of
				Vector to check if the object is already in the collision vector or not. The contains()
				method is also useful for deciding whether to handle collision or not.
				*/
				if (collisionList2.contains((GameObject)collisionObject2) == false) {
					collisionList2.add((GameObject)collisionObject2);
					nprrobots1.handleCollision(collisionObject2, this);
				}
				//If the obj1 and obj2 are no longer colliding, you need to remove them from each others collision vectors.
			}
			else if (collisionList2.contains((GameObject)collisionObject2)) {
				collisionList2.remove((GameObject)collisionObject2);
			}
		}
		
	
		
		if (probot.getLastBaseReached() == lastBase) {
			System.out.println("You win! Total time: " + this.clock);
			System.exit(0);
		}
		
		probot.tickEnergy();
		System.out.println(getGameWidth());
		System.out.println(getGameHeight());
		//death();
		this.setChanged();
		this.notifyObservers();
		System.out.println("Tick has occured");
	}
	
	//handles diff ways player can die
	public void death() {
		if (lives == 0) {
			System.out.println("GAME OVER, you ran out of lives");
			System.out.println("Total Game Time: " + clock );
			System.exit(0);
		}
		if (probot.getDamageLevel() >= probot.getMaximumDamageLevel()) {
			if (getSoundValue() == true) {
				getDeathSound().play();
			}
			System.out.println("Your robot is broken, you lose a life");
			lives--;
			probot.reset();
			
		}
		if (probot.getEnergyLevel() <= 0) {
			if (getSoundValue() == true) {
				getDeathSound().play();
			}
			System.out.println("You are out of energy, you lose a life");
			lives--;
			probot.reset();
		}
		this.setChanged();
		this.notifyObservers();
	}
	//command d
	public void display() {
		System.out.println("Number of lives left is: " + lives );
		System.out.println("Current time is: " + clock );
		System.out.println("The highest base number reached is: " + probot.getLastBaseReached());
		System.out.println("Current energy level: " + probot.getEnergyLevel());
		System.out.println("Current damage level: " + probot.getDamageLevel());
	}


	//command m
	
	public void map(){
		System.out.println();
		IIterator currentObject = gameObj.getIterator();
		while (currentObject.hasNext()) {
			GameObject object = ((GameObject) currentObject.getNext());
			System.out.println(object.toString());
		}
	}
	
	public void changeStrategy() {
		System.out.println("Changing Strategy");
		IIterator currentObject = gameObj.getIterator();
		while (currentObject.hasNext()) {
			GameObject object = ((GameObject) currentObject.getNext());
			if (object instanceof NonPlayerRobot){
				if (((NonPlayerRobot)object).getStrategy() == "Base Strategy") {
					((NonPlayerRobot)object).setLastBaseReached(((NonPlayerRobot)object).getLastBaseReached() + 1);
					((NonPlayerRobot)object).setStrategy(new AttackStrategy(gameObj,((NonPlayerRobot)object)));
				}
				else if (((NonPlayerRobot)object).getStrategy() == "Attack Strategy") {
					((NonPlayerRobot)object).setStrategy(new BaseStrategy(gameObj,((NonPlayerRobot)object)));
				}
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	/*
	public void setStratNPR() {
		IIterator currentObject = gameObj.getIterator();
		while (currentObject.hasNext()) { 
			GameObject object = ((GameObject) currentObject.getNext());
			if (object instanceof NonPlayerRobot){
				if (object instanceof NonPlayerRobot) {
					if (((NonPlayerRobot) object).getLastBaseReached() == lastBase){
						System.out.println("Game Over, AI robot wins! Total time: " + this.clock);
						System.exit(0);
					}
				}
				if (((NonPlayerRobot)object).getStrategy() == "Base Strategy") {
					((NonPlayerRobot)object).setLastBaseReached(((NonPlayerRobot)object).getLastBaseReached() + 1);
					((NonPlayerRobot)object).setStrategy(new BaseStrategy(gameObj,((NonPlayerRobot)object)));
				}
			}
		}
	}
	*/
	
	//private Sound energySound, gameDeathSound, robotSound, bgSound;
	public void createSounds() {
		energySound = new Sound("spaceSound.wav");
		gameDeathSound = new Sound("deathSound.wav");
		robotSound = new Sound("robotSound.wav");
		bgSound = new BGSound("bgSound.wav");
	}
	
	
	
	public void setGameHeight(int height) {
		this.mapHeight = height;
		System.out.println(height);
	}
	
	public void setGameWidth(int width) {
		this.mapWidth = width;
		System.out.println(width);
	}
	
	public int getGameHeight() {
		return this.mapHeight;
	}
	public int getGameWidth() {
		return this.mapWidth;
	}

	public BGSound getBgSound() {
		return bgSound;
	}

	public void setBgSound(BGSound bgSound) {
		this.bgSound = bgSound;
	}
	
	public Sound getESound() {
		return energySound;
	}
	
	public Sound getRobotSound() {
		return robotSound;
	}
	
	public Sound getDeathSound() {
		return gameDeathSound;
	}

	public boolean getGamePause() {
		return pause;
	}
	
	public void setGamePause(boolean pause) {
		this.pause = pause;
	}
	
	public void setIsPosition(boolean position) {
		this.position = position;
	}
	
	public boolean getPosition() {
		return position;
	}
	
}
