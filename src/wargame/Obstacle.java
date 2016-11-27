package wargame;
import java.awt.Color;
public class Obstacle extends Element {

	public enum TypeObstacle {
		ROCHER (IConfig.COULEUR_ROCHER), FORET (IConfig.COULEUR_FORET), EAU (IConfig.COULEUR_EAU);
		private final Color COULEUR;
		TypeObstacle(Color couleur) { COULEUR = couleur; }
		public static TypeObstacle getObstacleAlea() {
			return values()[(int)(Math.random()*values().length)];
		}
		public  Color getCouleur() {return COULEUR;}
	}
	private TypeObstacle TYPE;
	//private Position pos;
	Obstacle(TypeObstacle type, Position pos) { TYPE = type; this.pos = pos; }
	Obstacle(Position pos){
		this.pos=pos;
		this.TYPE=TypeObstacle.getObstacleAlea();
		coul=TYPE.getCouleur();
	}
	public String toString() { return ""+TYPE; }
}