package romania.map;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class RomaniaMap {
	ArrayList<String> cities = new ArrayList<String>();
	ArrayList<Node> nodes = new ArrayList<Node>();
	public int[][] matrix;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

		RomaniaMap map = new RomaniaMap();
		map.addCity("Mexico");
		map.addCity("GDL");
		map.addCity("MTY");
		map.addCity("GRO");
		
		map.generateMatrix();
		
		map.addNode("Mexico", "GDL", 100);
		map.addNode("GDL", "MTY", 80);
		map.addNode("Mexico", "MTY", 10);
		map.addNode("GRO", "GDL", 23);
		
		map.printMatrix();
		
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
