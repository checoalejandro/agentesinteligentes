package busqueda;
/*
SearchNode class  

Constructing Intelligent Agents with Java
(C) Joseph P. Bigus and Jennifer Bigus 1997


*/

import java.util.* ;
import java.awt.*;

public class SearchNode extends Object { 
public String label ;     // symbolic name
Object state ;     // defines the state-space
Object oper;       // operator used to generate this node
Vector links;      // edges or links to other nodes
public int depth ;        // depth in a tree from start node
boolean expanded ; // indicates if node has been expanded
boolean tested ;   // indicates if node was ever tested 
float cost=0 ;     // cost to get to this node  
SearchNode parent; // Node parent

static TextArea textArea1 ; // used for trace output only 
public static final int FRONT = 0 ;
public static final int BACK = 1 ;
public static final int INSERT = 2;  

public SearchNode(String label, SearchNode parent, float cost, Object operator, int depth){
	this.label = label;
	this.parent = parent;
	this.cost = cost;
	this.oper =operator;
	this.depth = depth;
}

public SearchNode(String Label, Object State) {
label = Label ; state = State ; depth = 0 ;
links = new Vector() ; oper = null ;
expanded = false ; tested = false ;
}

public void addLink(SearchNode Node) { 
            links.addElement(Node);
}

public void addLinks(SearchNode n1, SearchNode n2) {
            links.addElement(n1) ; 
            links.addElement(n2) ;
}

public void addLinks(SearchNode n1, SearchNode n2, SearchNode n3) {
            links.addElement(n1) ;
            links.addElement(n2) ; 
            links.addElement(n3) ; 
}

public void addLinks(SearchNode n1, SearchNode n2, 
                   SearchNode n3, SearchNode n4) {
            links.addElement(n1) ; links.addElement(n2) ; 
            links.addElement(n3) ; links.addElement(n4) ; 
}

public void addLinks(Vector Nodes) {
           for (int i=0 ; i < Nodes.size() ; i++) {
              links.addElement(Nodes.elementAt(i)) ; 
           }
}
public boolean leaf() { return (links.size() == 0) ; }
public void setDepth(int Depth) { depth = Depth; }
public void setOperator(Object Oper) { oper = Oper; }
public void setExpanded() { expanded = true; } 
public void setExpanded(boolean state) { expanded = state; } 
public void setTested(boolean state) { tested = state ; } 

static public void setDisplay(TextArea textArea) { textArea1 = textArea; } 

// initialize the node for another search 
public void reset() {
  depth = 0 ;
  expanded = false ;
  tested = false ; 
}

// write a trace statement -- indent to indicate depth  
public void trace() {
   String indent = new String() ; 
   for (int i=0 ; i < depth ; i++) indent += "  " ; 
   textArea1.appendText(indent + "Searching " + depth + ": " + label +
                       " with state = " + state + "\n") ;                        
}

// expand the node and add to queue at specified position
// position 0=front, 1=back, 2=based on node cost
public void expand(Vector queue, int position) {
  setExpanded() ; 
  for (int j = 0; j < links.size(); j++) {
     SearchNode nextNode = (SearchNode)links.elementAt(j) ;
     if (!nextNode.tested) {
       nextNode.setTested(true) ; 
       nextNode.setDepth(depth+1) ; 
       switch (position) {
        case FRONT: queue.insertElementAt(nextNode,0); 
                break ;
        case BACK: queue.addElement(nextNode);   
                break ;
        case INSERT: 
         boolean inserted = false ;
         float nextCost = nextNode.cost ; 
         for (int k=0 ; k < queue.size() ; k++) { 
           // find where to insert this node
           if (nextCost < ((SearchNode)queue.elementAt(k)).cost) {
             queue.insertElementAt(nextNode, k); // insert in middle 
             inserted = true ;
             break ;     // exit the for loop
           } 
         } 
         // couldn't find place to insert, just add to end
         if (!inserted) queue.addElement(nextNode) ;  
         break;                    
       }  
     } 
 }         
}


}

