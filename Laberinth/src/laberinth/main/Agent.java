package laberinth.main;

public class Agent {


	int p1=0; //choca 1
    int p2=0;// meta 1
	boolean shutdown = false;
	
public Agent (int p1,int p2, boolean shutdown)
{
  this.p1=p1;
  this.p2=p2;
  this.shutdown=shutdown;
}

	int action=0;

public int action(int p1,int p2, boolean shutdown){
	if (shutdown == true){
		action=shutdown();
		//System.out.println("Shutdown");
	}
	else if (p1==0 && p2==0){
		action=moveForward();
		//System.out.println("Camino");
	}
	else if (p1==1 && p2==0){
		action=turnRight();
		//System.out.println("Vuelta a la derecha");
	}
	return action;	
}
	
	public int moveForward(){
       return 0;
	}
	
	public int turnLeft(){
		return 1;
	}
	
	public int turnRight(){
		return 2;
	}
	
	public int shutdown(){
		this.shutdown=true;
		return 3;
	}
	

}
