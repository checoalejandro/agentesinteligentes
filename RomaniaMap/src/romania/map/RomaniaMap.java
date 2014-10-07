package romania.map;

import java.awt.Label;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import busqueda.SearchGraph;
import busqueda.SearchNode;

public class RomaniaMap {
	ArrayList<String> cities = new ArrayList<String>();
	ArrayList<Node> nodes = new ArrayList<Node>();
	public int[][] matrix;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SearchGraph algorithms = new SearchGraph("");
		
		// Adjacency Matrix
		RomaniaMap m1 = new RomaniaMap();
		m1.addCity("Mexico");
		m1.addCity("GDL");
		m1.addCity("MTY");
		m1.addCity("GRO");
		m1.addCity("PV");
		m1.addCity("Tepic");
		m1.addCity("Morelia");
		m1.addCity("Cancún");
		
		m1.generateMatrix();
		
		m1.addNode("Mexico", "GDL", 100);
		m1.addNode("GDL", "MTY", 80);
		m1.addNode("Mexico", "MTY", 10);
		m1.addNode("GRO", "GDL", 23);
		m1.addNode("GRO", "Mexico", 63);
		m1.addNode("Mexico", "GRO", 70);
		m1.addNode("MTY", "Morelia", 60);
		m1.addNode("Morelia", "Cancún", 43);
		m1.addNode("Cancún", "Tepic", 31);
		m1.addNode("Tepic", "PV",94);
		
//		m1.printMatrix();
		
		// Straight line matrix distance
		RomaniaMap m2 = new RomaniaMap();
		m2.addCity("Mexico");
		m2.addCity("GDL");
		m2.addCity("MTY");
		m2.addCity("GRO");
		
		m2.generateMatrix();
		
		m2.addNode("Mexico", "GDL", 100);
		m2.addNode("GDL", "MTY", 80);
		m2.addNode("Mexico", "MTY", 10);
		m2.addNode("GRO", "GDL", 23);
		
//		m2.printMatrix();
		
		Vector<String> operators = new Vector<String>();
		
		operators.add("Mexico");
		operators.add("GDL");
		operators.add("MTY");
		operators.add("GRO");
		
		SearchNode initialNode = new SearchNode("Mexico:0", null, 0, null, 0);
		
//		algorithms.bestFirstSearch(initialNode, goalState)
		
//		StateGenerator generator = new StateGenerator(m1.cities, m1.matrix);
		/*ArrayList<SearchNode> newStates = */
		m1.generateGraph(initialNode, operators);
		StateGenerator generator = new StateGenerator(m1.cities, m1.matrix);
		generator.bestFirstSearch(initialNode, "PV");
		
		
//		System.out.println();
//		System.out.println();
//		
//		for(int i = 0; i< newStates.size(); i++){
//			System.out.println(newStates.get(i).label);
//		}	
	}
	
	ArrayList<String> allNodes = new ArrayList<String>();
	
	public void search(SearchNode initialNode, Object finalstate){
		
	}
	
	private void generateGraph(SearchNode initialNode, Vector<String> operators){
		
		String[] label = initialNode.label.split(":");
		String city = label[0];
		int cost = Integer.parseInt(label[1]);
		
		StateGenerator generator = new StateGenerator(cities, matrix);
		ArrayList<SearchNode> newStates = generator.stateGenerator(initialNode, operators);
		
		for(int i = 0; i < newStates.size(); i++){
			if(!allNodes.contains(city+":"+cost+":"+newStates.get(i).label)){
				allNodes.add(city+":"+cost+":"+newStates.get(i).label);
			}else{
				newStates.remove(i);
			}
		}
		
		initialNode.links.addAll(newStates);
		
		for(int i = 0; i < newStates.size(); i++){
			generateGraph(newStates.get(i), operators);
			System.out.println(newStates.get(i).label);
		}
		
//		generator.bestFirstSearch(initialNode, "PV");
	}
	
	public int getCityIndex(String city){
		int indexT = -1;
		for(int i = 0; i < cities.size(); i ++){
			if(cities.get(i).equals(city)){
				indexT = i;
				break;
			}
		}
		
		return indexT;
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
	
	public void printMatrix(){
		for(int i = 0; i < cities.size();i ++){
			for(int j = 0; j < cities.size(); j ++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void addCity(String city){
		cities.add(city);
	}
	
	public void addNode(String from, String to, int cost){
		int indexF = -1, indexT = -1;
		
		for(int i = 0; i < cities.size(); i ++){
			if(cities.get(i).equals(from)){
				indexF = i;
				break;
			}
		}
		
		for(int i = 0; i < cities.size(); i ++){
			if(cities.get(i).equals(to)){
				indexT = i;
				break;
			}
		}
				
		matrix[indexF][indexT] = cost;
	}
	
	public void generateMatrix(){
		
		matrix = new int[cities.size()][cities.size()];
		for(int i = 0; i < cities.size();i ++){
			for(int j = 0; j < cities.size(); j ++){
				matrix[i][j] = -1;
			}
		}
	}

}

class Node{
	public String from, to;
	public Integer cost;
	
	public Node(String from, String to, Integer cost){
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
}
