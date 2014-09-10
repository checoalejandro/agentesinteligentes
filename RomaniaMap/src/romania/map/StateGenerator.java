package romania.map;

import java.util.ArrayList;
import java.util.Vector;

import busqueda.SearchNode;

public class StateGenerator {
	
	ArrayList<String> cities = new ArrayList<String>();
	int[][] matrix;
	
	public StateGenerator(ArrayList<String> cities, int[][] matrix){
		this.cities = cities;
		this.matrix = matrix;
	}

	public ArrayList<SearchNode> stateGenerator(SearchNode initialNode, Vector<String> operators){
		ArrayList<SearchNode> states = new ArrayList<SearchNode>();
		
		
		for(int i = 0; i < operators.size(); i++){
			String operator = operators.get(i);
			int indexX = getCityIndex(operator);
			int indexY = getCityIndex(initialNode.label);
			int cost = matrix[indexY][indexX];
			
			if(cost > 0){
				states.add(new SearchNode(operator, initialNode, cost, operator, initialNode.depth+1));
			}
		}
		
		return states;
	}
	
	private int getCityIndex(String city){
		int indexT = -1;
		for(int i = 0; i < cities.size(); i ++){
			if(cities.get(i).equals(city)){
				indexT = i;
				break;
			}
		}
		
		return indexT;
	}
}
