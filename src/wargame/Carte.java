package wargame;

import java.awt.Graphics;

public class Carte implements ICarte {
	
	
	
	public Element getElement(Position pos){
		
	}
	
	public Position trouvePositionVide(){
		// Trouve aléatoirement une position vide sur la carte
	}
	
	public Position trouvePositionVide(Position pos){
		// Trouve une position vide choisie
	}
								// aléatoirement parmi les 8 positions adjacentes de pos
	public Heros trouveHeros(){
		// Trouve aléatoirement un héros sur la carte
	}
	
	public Heros trouveHeros(Position pos){
		// Trouve un héros choisi aléatoirement
	}
									 // parmi les 8 positions adjacentes de pos
	
	public boolean deplaceSoldat(Position pos, Soldat soldat){
	
	}
	
	public void mort(Soldat perso){
		
	}
	
	public boolean actionHeros(Position pos, Position pos2){
	
	}
	
	public void jouerSoldats(PanneauJeu pj){
		
	}
	
	public void toutDessiner(Graphics g){
		
	}
}
