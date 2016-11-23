package wargame;

public class Heros extends Soldat {
	
	
	final int RECUP_POINT=2;
	/*
	 * constructeur hero
	 * définition du type de soldat à savoir heros ici
	 * recupération du numéro du soldat ici num heros
	 * er récup postion dees heros qui sont à gauche sur la carte
	 * hero icipermet de recupérer le type de hero donnée c'est à 
	 * dire humain, nain, elf ou hobbit
	 */
	
	public Heros(TypesH hero, int numeroh, Position position){
		super(hero.getPoints(),hero.getPortee(),hero.getPuissance(),hero.getTir(),numeroh, position);
		djoue=0;
		coul=COULEUR_HEROS;
	}
	
	/*
	 * un soldat se repose après avoir fait un tour de jeu 
	 * il peut récupérer des point de vie si l'ajout des point à son point
	 * de restant  ne dépasse pas le point de vie initial qui est 
	 * POINT_DE_VIE_MAX 
	 */
	public void Sereposer(){
		if(this.ptvi+RECUP_POINT<POINTS_DE_VIE_MAX){
			this.setPoints(this.getPoints()+RECUP_POINT);		
			
		}
		else{
			this.setPoints(POINTS_DE_VIE_MAX);
		}
		this.setJoueTour(1);
	}
	
	public String toString(){
    	return  " numéro :"+(numsoldat+1) + ", points de vie : "+ ptvi + " / points de vie max : " + POINTS_DE_VIE_MAX + ", portée : " + portee + "puissance:"+puissance+"\n";

    }



}
