package wumpus;

import java.util.ArrayList;

public class Enviorment {
	
	int X = 0, Y = 0;

	/**
	 * 0 - Vacio
	 * 1 - Brisa
	 * 2 - Peste
	 * 3 - Pozo
	 * 4 - Wumpus
	 * 5 - Oro
	 * 9 - Inicio
	 */
	public final static int START = 9;
	public final static int GOLD = 5;
	public final static int WUMPUS = 4;
	public final static int HOLE = 3;
	public final static int BREEZE = 1;
	public final static int PESTILENCE = 2;
	public final static int EMPTY = 0;
	
	/**
	 * ---- Flags ----
	 * 11 - Wumpus killed
	 */
	public final static int MOAN = 11;
	
	public Box map[][] = {
			{new Box().addElement(PESTILENCE),	new Box().addElement(EMPTY),	new Box().addElement(BREEZE),	new Box().addElement(HOLE)},
			{new Box().addElement(WUMPUS),		new Box().addElement(BREEZE).addElement(PESTILENCE).addElement(GOLD),new Box().addElement(HOLE),new Box().addElement(BREEZE)},
			{new Box().addElement(PESTILENCE),	new Box().addElement(EMPTY),	new Box().addElement(BREEZE),	new Box().addElement(EMPTY)},
			{new Box().addElement(START),		new Box().addElement(BREEZE),	new Box().addElement(HOLE), 	new Box().addElement(BREEZE)}
	};
	
	public int action(int action){
		switch(action){
		case Agent.FORWARD:
			break;
		case Agent.THROW:
			break;
		}
		
		return -1;
	}
	
}

class Box{
	
	public ArrayList<Integer> elements = new ArrayList<Integer>();
	
	public Box(){
		
	}
	
	public Box addElement(int element){
		elements.add(element);
		return this;
	}
	
	public boolean isElement(int element){
		for(int i = 0; i < elements.size(); i++){
			if(elements.get(i) == element){
				return true;
			}
		}
		return false;
	}
}
