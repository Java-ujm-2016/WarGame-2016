package wargame;
import java.awt.Color;
public interface IConfig {
	int LARGEUR_CARTE = 25; int HAUTEUR_CARTE = 15; // en nombre de cases
	int NB_PIX_CASE = 20;
<<<<<<< HEAD
	int POSITION_X = 100; int POSITION_Y = 50; // Position de la fenétre
=======
	int POSITION_X = 100; int POSITION_Y = 50; // Position de la fen�tre
>>>>>>> 8e852ab6b68c9dc21ebc6109ed8d9b0c2a287f3b
	int NB_HEROS = 6; int NB_MONSTRES = 15; int NB_OBSTACLES = 20;
	Color COULEUR_VIDE = Color.white, COULEUR_INCONNU = Color.lightGray;
	Color COULEUR_TEXTE = Color.black, COULEUR_MONSTRES = Color.black;
	Color COULEUR_HEROS = Color.red, COULEUR_HEROS_DEJA_JOUE = Color.pink;
	Color COULEUR_EAU = Color.blue, COULEUR_FORET = Color.green, COULEUR_ROCHER = Color.gray;
}