package wargame;

import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JButton;

public class Carte implements ICarte {
	protected Element[][] tabElements;
	public Carte(){
		tabElements=new Element[IConfig.HAUTEUR_CARTE][IConfig.LARGEUR_CARTE];
	}
	
	
	public Element getElement(Position pos){
		return tabElements[pos.getX()][pos.getY()];
	}
	
	public Position trouvePositionVide(){
		// Trouve aléatoirement une position vide sur la carte
		int x= (int) (Math.random()*IConfig.HAUTEUR_CARTE);
		int y= (int) (Math.random() * IConfig.LARGEUR_CARTE);
		int count=1;
		while (tabElements[x][y]!=null && count<= (IConfig.HAUTEUR_CARTE*IConfig.LARGEUR_CARTE)){
			x= (int) (Math.random() * IConfig.HAUTEUR_CARTE);
			y= (int) (Math.random() * IConfig.LARGEUR_CARTE);
			count++;
		}
		if(count<= (IConfig.HAUTEUR_CARTE*IConfig.LARGEUR_CARTE))
			return (new Position(x, y));
		else return null;
	}
	
	public Position trouvePositionVide(Position pos){
		// Trouve une position vide choisie
		if(tabElements[pos.getX()][pos.getY()]!=null)
			return (new Position(pos));
		else return null;
	}
	// aléatoirement parmi les 8 positions adjacentes de pos
	public Heros trouveHeros(){
		// Trouve aléatoirement un héros sur la carte
		return null;
	}
	
	public Heros trouveHeros(Position pos){
		// Trouve un héros choisi aléatoirement
		return null;
	}
									 // parmi les 8 positions adjacentes de pos
	
	public boolean deplaceSoldat(Position pos, Soldat soldat){
		return false;
	}
	
	public void mort(Soldat perso){
		
	}
	
	public boolean actionHeros(Position pos, Position pos2){
		return false;
	}
	
	public void jouerSoldats(PanneauJeu pj){
		
	}
	
	public void toutDessiner(Graphics g){
		
	}
	public void viderTabElement(){
		for(int i=0;i<IConfig.HAUTEUR_CARTE;i++)
			for (int j=0;j<IConfig.LARGEUR_CARTE;j++)
				this.tabElements[i][j]=null;
	}
}
