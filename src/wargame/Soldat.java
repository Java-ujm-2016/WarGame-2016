package wargame;

public abstract class Soldat extends Element implements ISoldat {
	
	public int getPoints(){
		return 1;
	}
	
	public int getTour(){
		return 1;
	}
	
	public int getPortee(){
		return 2;
	}
	
	public void joueTour(int tour){
		
	}
	
	public void combat(Soldat soldat){
		
	}
	
	public void seDeplace(Position newPos){
		
	}

}
