package canibalmissioner;

import java.util.ArrayList;
import java.util.Vector;

import busqueda.SearchNode;

/**
 * This is the class where the game is defined.<br><br>
 * Initial state: 0,0,0 <br>
 * First is for missioners, second for cannibals, third is for canoa 1 for left 0 to right<br><br>
 * Restrictions: <br>
 * 1. No more cannibals than missioners
 * @author Alex
 *
 */

public class CanMis {

	Vector<String> operators = new Vector<String>();
	String initial = "3,3,0";
	int depth = 0;
	
	public CanMis(){
		
		/*
		 * Set operators
		 */
		// 1.
		operators.add("MM");
		// 2.
		operators.add("CC");
		// 3.
		operators.add("MC");
		// 4.
		operators.add("M");
		// 5.
		operators.add("C");
		
		depth = 0;
		
		SearchNode initialNode = new SearchNode("3,3,1", null, 0, null, 0);
		buildGraph(initialNode);
		
//		for(int i = 0; i < nodes.size(); i++){
//			System.out.println(nodes.get(i).label);
//		}
	}
	
	public void buildGraph(SearchNode initialNode){
		Vector<SearchNode> nodes = NodeMachine(initialNode);
		for(int i = 0; i < nodes.size(); i++){
			buildGraph(nodes.get(i));
			SearchNode finalNode = breadthFirstSearch(nodes.get(i), "0,0,0");
			if(finalNode != null){
				System.exit(0);
			}
		}
	}
	
	public Vector<SearchNode> nodeMachine(SearchNode initialNode){
		Vector<SearchNode> nodes = new Vector<SearchNode>();
		
		String[] values = initialNode.label.split(",");
		int mis = Integer.parseInt(values[0]);
		int can = Integer.parseInt(values[1]);
		int canoa = Integer.parseInt(values[2]);
		
		depth++;
		return nodes;
	}
	
	public Vector<SearchNode> NodeMachine(SearchNode initialNode){
		Vector<SearchNode> nodes = new Vector<SearchNode>();
		
		String[] values = initialNode.label.split(",");
		int mis = Integer.parseInt(values[0]);
		int can = Integer.parseInt(values[1]);
		int canoa = Integer.parseInt(values[2]);
		
		depth++;
		
		// 1.
		if(mis - 2 >= 0 && mis >= can){
			nodes.add(new SearchNode((mis - 2) + "," + can + "," + (canoa == 1 ? 0 : 1), initialNode, 0, operators.get(0), depth));
		}
		// 2.
		if(can - 2 >= 0 && mis >= can){
			nodes.add(new SearchNode(mis + "," + (can - 2) + "," + (canoa == 1 ? 0 : 1), initialNode, 0, operators.get(1), depth));
		}
		// 3.
		if(mis - 1 >= 0 && can - 1 >= 0 && mis >= can){
			nodes.add(new SearchNode((mis - 1) + "," + (can - 1) + "," + (canoa == 1 ? 0 : 1), initialNode, 0, operators.get(2), depth));
		}
		// 4. 
		if(mis - 1 >= 0 && mis >= can){
			nodes.add(new SearchNode((mis - 1) + "," + can + "," + (canoa == 1 ? 0 : 1), initialNode, 0, operators.get(3), depth));
		}
		// 5.
		if(can - 1 >= 0 && mis >= can){
			nodes.add(new SearchNode(mis + "," + (can - 1) + "," + (canoa == 1 ? 0 : 1), initialNode, 0, operators.get(4), depth));
		}
		
		for(int i = 0; i < nodes.size(); i++){
			values = initialNode.label.split(",");
			mis = Integer.parseInt(values[0]);
			can = Integer.parseInt(values[1]);
			canoa = Integer.parseInt(values[2]);
			nodes.add(new SearchNode((mis) -1 + "," + can + "," + (canoa == 1 ? 0 : 1), initialNode, 0, operators.get(3), depth));
			nodes.add(new SearchNode(mis + "," + (can - 1) + "," + (canoa == 1 ? 0 : 1), initialNode, 0, operators.get(3), depth));
		}
		
		if(nodes.size() > 0){
			initialNode.addLink(nodes);
		}
		
		return nodes;
	}
	
	public static void main(String[] args){
		new CanMis();
	}
	
	public SearchNode breadthFirstSearch(SearchNode initialNode, Object goalState)
	{
	   Vector<SearchNode> queue = initialNode.links;//NodeMachine(initialNode);
	   initialNode.setTested(true) ;  // test each node once

	   while (queue.size()> 0) {
	     SearchNode testNode = (SearchNode)queue.firstElement() ;
	     queue.removeElementAt(0) ;
//	     testNode.trace() ;
	     if (testNode.label.equals(goalState)){
	    	 System.out.println("FOUND!: " + testNode.label + testNode.oper);
	    	 SearchNode node = testNode.parent;
//	    	 System.out.println(node.label + testNode.oper);
//	    	 System.out.println(node.parent.label + node.oper);
//	    	 System.out.println(node.parent.parent.label + node.parent.oper);
	    	 while(node.parent != null){
	    		 System.out.println(node.label + node.oper);
	    		 node = node.parent;
	    	 }
	    	 System.out.println("3,3,1");
	    	 return testNode ; // found it
	     }

	     if (!testNode.expanded) {
	        testNode.expand(queue,SearchNode.BACK) ;
	     }
	   }
	   return null ;
	}
	
}
