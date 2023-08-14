package com.mycompany.a3;
import java.util.Vector; 
public class GameObjectCollection implements ICollection{
	
	private Vector<GameObject> gameObj2 = new Vector<GameObject>();
	
	public class Iterator implements IIterator{
		 private int currentIndex;
		 public Iterator() {
			 currentIndex = -1;
		 }
	
		public boolean hasNext() {
			if (gameObj2.size () <= 0) {
				return false;
			}
			if (currentIndex == gameObj2.size() - 1) {
				return false;
			}
			return true;
		}

		public Object getNext() {
			currentIndex ++;
			return gameObj2.get(currentIndex);
		}
		 
	}

	
	public void add(Object object) {
		gameObj2.add((GameObject)object);
		
	}

	
	public IIterator getIterator() {
		return new Iterator();
	}

		
		 
	
	
	
	
	

}
