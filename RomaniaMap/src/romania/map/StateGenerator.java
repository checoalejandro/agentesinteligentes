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
		String[] label = initialNode.label.split(":");
		
		for(int i = 0; i < operators.size(); i++){
			String operator = operators.get(i);
			int indexX = getCityIndex(operator);
			int indexY = getCityIndex(label[0]);
			int cost = matrix[indexY][indexX];
			
			if(cost > 0){
				states.add(new SearchNode(operator + ":" + cost, initialNode, cost, operator, initialNode.depth+1));
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
	
	 public SearchNode bestFirstSearch(SearchNode initialNode, Object goalState){
		 
	     Vector<SearchNode> queue = initialNode.links;//new Vector(); 
	     queue.addElement(initialNode) ;
	     initialNode.setTested(true) ;  // only test each node once

	     while (queue.size()> 0) {
	       SearchNode testNode = (SearchNode)queue.firstElement() ;
	       queue.removeElementAt(0) ;
//	       testNode.trace() ;
	       String[] label = testNode.label.split(":");
	       int cost = Integer.parseInt(label[1]);
	       String from = label[0];
	       if (from.equals(goalState)){
	    	   System.out.println("Found! " + testNode.label);
	    	   return testNode ;
	       }

	      // now, heuristically add nodes to queue
	      // insert the child nodes according to cost
//	       if (!testNode.expanded) {
//	           testNode.expand(queue,SearchNode.INSERT) ;
//	       }
	     }
	     return null ;
	 }
}