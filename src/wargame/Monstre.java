package wargame;

/**
 * Class Monstre herete Soldat
 * @author Akpo Latifa
 * @author Nour
 */

public class Monstre extends Soldat {
	TypesM tMonstre;

	public Monstre (TypesM monstre, int numeroh, Position position){
		super(monstre.getPoints(),monstre.getPortee(),monstre.getPuissance(),monstre.getTir(),numeroh, position);
		tMonstre=monstre;
		coul=COULEUR_MONSTRES;
	}


	
	public String affiche(){
    	return  "Monstre : "+tMonstre+", numéro :"+ (numsoldat +1) + ", points de vie : "+ ptvi + " / points de vie max : " + POINTS_DE_VIE_MAX + ", portée : " + portee + ", puissance:"+puissance+"\n"+""+pos+"\n";

    }
	/*public String toString() {
		return "Monstre : " + tMonstre + " "+pos;
	}*/
	
}
