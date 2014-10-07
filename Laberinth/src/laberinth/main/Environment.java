package laberinth.main;

public class Environment {
	
	public  int START = 2;
	public static  int END = 3;
	int score = 10000;

	int p1=0; // 1 choca
	int p2=0; // 1 meta
	boolean shutdown = false;
	
	int X = -1, Y = 0; //A donde está volteando
	int i=0, j=0; // En donde se encuentra el agente
	int action = -1 ; //Define la acción que realizó el agente
	int laberinth[][] = {
			{1,1,1,1,1},
			{1,0,0,2,1},
			{1,1,1,0,1},
			{1,0,0,0,1},
			{1,0,1,1,1},
			{1,0,0,0,1},
			{1,1,1,0,1},
			{1,0,0,0,1},
			{1,0,1,1,1},
			{1,0,0,3,1},
			{1,1,1,1,1}
	};
	
	Agent agent;
	
	public Environment(){
		Agent agent = new Agent(p1,p2,shutdown);
		this.agent = agent;
		defineStartPosition();
		while (p2!=1 && !shutdown)
		{
			//System.out.println("p1 = "+p1+" p2_= "+ p2+" shutdown "+ shutdown);
		this.action=agent.action(this.p1,this.p2,this.shutdown);
		score -=1;
		System.out.println("  -- "+score+" -- ");
		//System.out.println(this.action);
		switch (this.action)
		{
		case 0: //MoveForward
			moveForward();
			break;
		case 1: //TurnLeft
			turnLeft();
			p1=0;
			break;
		case 2: //TurnRight
			turnRight();
			p1=0;
			break;
		case 3: //Shutdown
			shutdown();
			break;
		default:
			
		}

		}
	}
	

	
	public void defineStartPosition(){
		
		for(int i = 0; i < this.laberinth.length; i++){
			for(int j = 0; j < this.laberinth[i].length; j++){
				if(this.laberinth[i][j] == this.START){
					this.i = i;
					this.j = j;
					break;
				}
			}
		}
		
		
		System.out.println(this.i + ", " + this.j);
	}
	
	

	
	public boolean isLeft(){
		if(X==-1 && Y == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isRight(){
		if(X==1 && Y == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isUp(){
		if(X==0 && Y == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isDown(){
		if(X==0 && Y == -1){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String... arg){
		Environment environment = new Environment();
	}
	
	public void moveForward(){
		/*
		 * 
		 */
		if(isLeft()){
			if(laberinth[i][j-1] == 1){
				System.out.println("Crash");
				p1 = 1;
			}else{
				p1 = 0;
				j -=1;
			}
		}else{
			if(isRight()){
				if(laberinth[i][j+1] == 1){
					System.out.println("Crash");
					p1 = 1;
				}else{
					p1 = 0;
					j +=1;
				}
			}else{
				if(isUp()){
					if(laberinth[i-1][j] == 1){
						System.out.println("Crash");
						p1 = 1;
					}else{
						p1 = 0;
						i -=2;
					}
				}else{
					if(isDown()){
						if(laberinth[i+1][j] == 1){
							System.out.println("Crash");
							p1 = 1;
						}else{
							p1 = 0;
							i +=2;
						}
					}
				}
			}
		}
		
		if(laberinth[i][j] == Environment.END){
			score += 100000;
			p2 = 1;
			//shutdown();
		}
		
		System.out.println(this.i + ", " + this.j);
		
		if(score == 0){
			p2 = 0;
			shutdown();
		}
	}
	
	public void turnLeft(){
		if(isLeft()){
			X = 0;
			Y = -1;
		}else{
			if(isRight()){
				Y = 1;
				X = 0;
			}else{
				if(isUp()){
					X = -1;
					Y = 0;
				}else{
					if(isDown()){
						X = 1;
						Y = 0;
					}
				}
			}
		}
	}
	
	public void turnRight(){
		if(isLeft()){
			X = 0;
			Y = 1;
		}else{
			if(isRight()){
				Y = -1;
				X = 0;
			}else{
				if(isUp()){
					X = 1;
					Y = 0;
				}else{
					if(isDown()){
						X = -1;
						Y = 0;
					}
				}
			}
		}
	}
	
	public void shutdown(){
		this.shutdown=true;
		System.out.println("Turned off. Score: " + score);
	}
}
