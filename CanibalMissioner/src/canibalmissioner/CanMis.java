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

	public int nodes = 0;
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
		
		String initialState = "3,3,1";
		totalStates.add(initialState);
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
				System.out.println(this.nodes);
				System.exit(0);
			}
		}
	}
	
	ArrayList<String> totalStates = new ArrayList<String>();
	
	public Vector<SearchNode> NodeMachine(SearchNode initialNode){
		
		Vector<SearchNode> nodes = new Vector<SearchNode>();
		
		String[] values = initialNode.label.split(",");
		int mis = Integer.parseInt(values[0]);
		int can = Integer.parseInt(values[1]);
		int state = Integer.parseInt(values[2]);
		
		depth++;
		
		String newState = "";
		
		switch(state){
		case 1:
			// 1.
			if(mis - 2 >= 0 && (mis -2 >= can || (3-can)<=(3-(mis-2)))){
				newState = (mis - 2) + "," + can + "," + (state == 1 ? 0 : 1);
				if(!totalStates.contains(newState)){
					nodes.add(new SearchNode(newState, initialNode, 0, operators.get(0), depth));
					totalStates.add(newState);
				}
			}
			// 2.
			if(can - 2 >= 0 && (mis >= can -2 || (3-(can-2))<=(3-mis))){
				newState = mis + "," + (can - 2) + "," + (state == 1 ? 0 : 1);
				if(!totalStates.contains(newState)){
					nodes.add(new SearchNode(newState, initialNode, 0, operators.get(1), depth));
					totalStates.add(newState);
				}
			}
			// 3.
			if(mis - 1 >= 0 && can - 1 >= 0 && mis -1 >= can-1){
				newState = (mis - 1) + "," + (can - 1) + "," + (state == 1 ? 0 : 1);
				if(!totalStates.contains(newState)){
					nodes.add(new SearchNode(newState, initialNode, 0, operators.get(2), depth));
					totalStates.add(newState);
				}
			}
			// 4. 
			if(mis - 1 >= 0 && mis-1 >= can){
				newState = (mis - 1) + "," + can + "," + (state == 1 ? 0 : 1);
				if(!totalStates.contains(newState)){
					nodes.add(new SearchNode(newState, initialNode, 0, operators.get(3), depth));
					totalStates.add(newState);
				}
			}
			// 5.
			if(can - 1 >= 0 && mis >= can-1){
				newState = mis + "," + (can - 1) + "," + (state == 1 ? 0 : 1);
				if(!totalStates.contains(newState)){
					nodes.add(new SearchNode(newState, initialNode, 0, operators.get(4), depth));
					totalStates.add(newState);
				}
			}
			break;
		case 0:
			if(mis + 2 <= 3){
				newState = (mis + 2) + "," + can + "," + (state == 1 ? 0 : 1);
				if(!totalStates.contains(newState)){
					nodes.add(new SearchNode(newState, initialNode, 0, operators.get(0), depth));
					totalStates.add(newState);
				}
			}
			// 2.
			if(can + 2 <= 3){
				newState = mis + "," + (can + 2) + "," + (state == 1 ? 0 : 1);
				if(!totalStates.contains(newState)){
					nodes.add(new SearchNode(newState, initialNode, 0, operators.get(1), depth));
					totalStates.add(newState);
				}
			}
			// 3.
			if(mis + 1 <= 3 && can + 1 <= 3){
				newState = (mis + 1) + "," + (can + 1) + "," + (state == 1 ? 0 : 1);
				if(!totalStates.contains(newState)){
					nodes.add(new SearchNode(newState, initialNode, 0, operators.get(2), depth));
					totalStates.add(newState);
				}
			}
			// 4. 
			if(mis + 1 <= 3){
				newState = (mis + 1) + "," + can + "," + (state == 1 ? 0 : 1);
				if(!totalStates.contains(newState)){
					nodes.add(new SearchNode(newState, initialNode, 0, operators.get(3), depth));
					totalStates.add(newState);
				}
			}
			// 5.
			if(can + 1 <= 3){
				newState = mis + "," + (can + 1) + "," + (state == 1 ? 0 : 1);
				if(!totalStates.contains(newState)){
					nodes.add(new SearchNode(newState, initialNode, 0, operators.get(4), depth));
					totalStates.add(newState);
				}
			}
			break;
		}
		
		for(int i = 0; i < nodes.size(); i++){
			SearchNode node = nodes.get(i);
			values = node.label.split(",");
			mis = Integer.parseInt(values[0]);
			can = Integer.parseInt(values[1]);
			state = Integer.parseInt(values[2]);
			
			if(can>mis && mis!=0){
				nodes.remove(i);
				if ( (3-can) > (3-mis) && (3-mis!=0))
					nodes.remove(i);
			}
			else if ( (3-can) > (3-mis) && (3-mis !=0) )
				nodes.remove(i);
		}
		
		if(nodes.size() > 0){
			this.nodes += nodes.size();
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
