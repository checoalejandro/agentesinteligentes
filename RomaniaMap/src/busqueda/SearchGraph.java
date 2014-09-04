package busqueda;
/*
SearchGraph class

Constructing Intelligent Agents with Java
(C) Joseph P. Bigus and Jennifer Bigus 1997


*/


import java.util.* ;
import java.awt.*;

//SearchGraph is a container for a set of SearchNodes
//SearchNodes are stored in a Hashtable so they can be retrieved by name
//Basic search algorithms are defined here also
class SearchGraph extends Hashtable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String name ;

SearchGraph(String Name) {
 name = Name ;
}

// reset each SearhNode in the graph
// clear expanded and tested flags, set depth=0
void reset() {
 Enumeration enume = this.elements() ;
 while (enume.hasMoreElements()) {
     SearchNode nextNode = (SearchNode)enume.nextElement();
     nextNode.reset() ;
 }
}

// add node to Hashtable, using node label as key
void put(SearchNode node) {
  put(node.label, node) ;
}

// do a depth first search on graph
public SearchNode depthFirstSearch(SearchNode initialNode, Object goalState)
 {
     Vector queue = new Vector() ;
     queue.addElement(initialNode) ;
     initialNode.setTested(true) ;  // test each node once

     while (queue.size()> 0) {
       SearchNode testNode = (SearchNode)queue.firstElement() ;
       queue.removeElementAt(0) ;
       testNode.trace() ;           // display trace information
       if (testNode.state.equals(goalState)) return testNode ; // found it

       if (!testNode.expanded) {
         testNode.expand(queue,SearchNode.FRONT);
       }
     }
     return null ;
 }

// do a breadth-first search on graph
public SearchNode breadthFirstSearch(SearchNode initialNode, Object goalState)
{
   Vector queue = new Vector() ;
   queue.addElement(initialNode) ;
   initialNode.setTested(true) ;  // test each node once

   while (queue.size()> 0) {
     SearchNode testNode = (SearchNode)queue.firstElement() ;
     queue.removeElementAt(0) ;
     testNode.trace() ;
     if (testNode.state.equals(goalState)) return testNode ; // found it

     if (!testNode.expanded) {
        testNode.expand(queue,SearchNode.BACK) ;
     }
   }
   return null ;
}


// this is a slightly modified depth-first search algorith
// that stops searching at a pre-defined depth
public SearchNode depthLimitedSearch(SearchNode initialNode,
                                    Object goalState,
                                    int maxDepth)
{
   Vector queue = new Vector() ;
   queue.addElement(initialNode) ;
   initialNode.setTested(true) ;  // only test each node once

   while (queue.size()> 0) {
     SearchNode testNode = (SearchNode)queue.firstElement() ;
     queue.removeElementAt(0) ;
     testNode.trace() ;
     if (testNode.state.equals(goalState)) return testNode ;

     // limit the depth of search to maxDepth
     if (testNode.depth < maxDepth) {
       if (!testNode.expanded) {
           testNode.expand(queue,SearchNode.FRONT) ;
       }
     }
   }
   return null ;
}

 // use depth-first search to find goal
 public SearchNode iterDeepSearch(SearchNode startNode, Object goalState) {
     int maxDepth = 10 ;                 // arbitrary limit
     for (int j=0 ; j < maxDepth ; j++) {
       reset() ;
       SearchNode answer = depthLimitedSearch(startNode, goalState, j);
       if (answer != null) return answer;
     }
     return null ; // failed to find solution in maxDepth
 }


 // use best-first search algorithm to find the goal
 // default implementation based on SearchNode cost
 public SearchNode bestFirstSearch(SearchNode initialNode, Object goalState)
 {
     Vector queue = new Vector() ;
     queue.addElement(initialNode) ;
     initialNode.setTested(true) ;  // only test each node once

     while (queue.size()> 0) {
       SearchNode testNode = (SearchNode)queue.firstElement() ;
       queue.removeElementAt(0) ;
       testNode.trace() ;
       if (testNode.state.equals(goalState)) return testNode ;

      // now, heuristically add nodes to queue
      // insert the child nodes according to cost
       if (!testNode.expanded) {
           testNode.expand(queue,SearchNode.INSERT) ;
       }
     }
     return null ;
 }
}

