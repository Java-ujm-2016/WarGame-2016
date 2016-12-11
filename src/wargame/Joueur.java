package wargame;

public class Joueur {
	protected Soldat elem;
	protected boolean etat;
	public Joueur(Soldat e, boolean b){
		elem = e;
		etat = b;
	}
	
	public Soldat getSoldat(){
		return elem;
	}
	
	public boolean getEtat(){
		return etat;
	}
	
	public void setSoldat(Soldat sol){
		elem = sol;
	}
	
	public void setEtat(boolean bool){
		etat = bool;
	}
}
