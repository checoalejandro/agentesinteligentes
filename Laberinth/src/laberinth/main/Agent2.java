package laberinth.main;

public class Agent2 {
	
	Environment environment;
	int score = 10000;
	/*
	 * 0 - no choca
	 * 1 - choca
	 */
	int p1=0;
	
	/*
	 * 0 - no meta
	 * 1 - meta
	 */
	int p2=0;
	
	int[][] laberinth;
	
	boolean shutdown = true;
	
	int X = -1, Y = 0;
	
	public Agent2(Environment environment){
		this.environment = environment;
		this.laberinth = environment.getLaberinth();
		defineStartPosition();
		shutdown = false;
		start();
	}
	
	public void start(){
		while(!(p2==1) || (shutdown == false)){
			moveForward();
			if(p1 == 1){
				turnRight();
				moveForward();
				if(p1 == 1){
					turnLeft();
					turnLeft();
					moveForward();
					if(p1 == 1){
						turnLeft();
						moveForward();
					}
				}
			}else{
				turnLeft();
				moveForward();
			}
		}
	}
	
	/*
	 * Position
	 */
	int i=0, j=0;
	
	public void defineStartPosition(){
		
		int[][] laberinth = environment.getLaberinth();
		
		for(int i = 0; i < laberinth.length; i++){
			for(int j = 0; j < laberinth[i].length; j++){
				if(laberinth[i][j] == Environment.START){
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
		
		if(laberinth[this.i][this.j] == Environment.END){
			score += 100000;
			p2 = 1;
			shutdown();
		}
		
		score -=1;
		
		System.out.println(this.i + ", " + this.j);
		
		if(score <= 0){
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
		shutdown = true;
		System.out.println("Turned off. Score: " + score);
	}
}