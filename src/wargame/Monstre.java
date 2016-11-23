package wargame;

public class Monstre extends Soldat {
	

	public Monstre (TypesH monstre, int numeroh, Position position){
		super(monstre.getPoints(),monstre.getPortee(),monstre.getPuissance(),monstre.getTir(),numeroh, position);
		
		coul=COULEUR_MONSTRES;
	}
	

	
	public String toString(){
    	return  " numéro :"+(numsoldat+1) + ", points de vie : "+ ptvi + " / points de vie max : " + POINTS_DE_VIE_MAX + ", portée : " + portee + "puissance:"+puissance+"\n";

    }
	
	
}
