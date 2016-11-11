package wargame;
import java.awt.Graphics;
public interface ICarte {
	Element getElement(Position pos);
<<<<<<< HEAD
	Position trouvePositionVide(); // Trouve alÃ©atoirement une position vide sur la carte
	Position trouvePositionVide(Position pos); // Trouve une position vide choisie
								// alÃ©atoirement parmi les 8 positions adjacentes de pos
	Heros trouveHeros(); // Trouve alÃ©atoirement un hÃ©ros sur la carte
	Heros trouveHeros(Position pos); // Trouve un hÃ©ros choisi alÃ©atoirement
=======
	Position trouvePositionVide(); // Trouve aléatoirement une position vide sur la carte
	Position trouvePositionVide(Position pos); // Trouve une position vide choisie
								// aléatoirement parmi les 8 positions adjacentes de pos
	Heros trouveHeros(); // Trouve aléatoirement un héros sur la carte
	Heros trouveHeros(Position pos); // Trouve un héros choisi aléatoirement
>>>>>>> 8e852ab6b68c9dc21ebc6109ed8d9b0c2a287f3b
									 // parmi les 8 positions adjacentes de pos
	boolean deplaceSoldat(Position pos, Soldat soldat);
	void mort(Soldat perso);
	boolean actionHeros(Position pos, Position pos2);
	void jouerSoldats(PanneauJeu pj);
	void toutDessiner(Graphics g);
}