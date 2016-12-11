package wargame;

import java.awt.Graphics;
import java.io.Serializable;

/**
 * @author Akpo Latifa
 */
public class Soldat extends Element implements ISoldat,Serializable{
	

	protected int djoue; /*pour savoir si un joueur a deja jouer*/
	//protected Position Soldatpos; /*pour connaitre la position d'un soldat*/
	protected int ptvi; /*recup du point de vie d'un soldat*/
	protected int puissance; /*puissance de frappe*/
	protected int tir; /*puissance de tir*/
	protected int numsoldat;/*recup numéro d'un soldat*/
	protected int portee; /*pour récupérer la portée*/
	
	protected int POINTS_DE_VIE_MAX; /*pt de vie max de dépatt d"un soldat*/
	 
	
	
	/**
	 * le constructeur soldat avec les différents parametres
	 * @author AKPO Latifa
	 * 
	 * 
	 * @param pointv qui retourne les points de vi d'un soldat donnée
	 * @param porte retourne la portée d'un soldat c'est à dire sa distance par rapport à un autre soldat
	 * @param puissancef retourne la puissance de frappe d'un soladat pour  un corps à corps
	 * @param puissancet retourne la puissance de tir d'un soldat pour un combat a distance
	 * @param numerosoldat permet de récupérer le numéro associé à un soldat
	 * @param posSoldat renvoie la position d'un soladat qui est récupéré dans la classe Element
	 * 
	 */
	
	
		public Soldat(int pointv, int porte, int puissancef, int puissancet, int numerosoldat, Position posSoldat){
		super();
		//super(posSoldat);
		this.POINTS_DE_VIE_MAX=pointv;
		this.ptvi=pointv;
		this.portee = porte;
		this.puissance=puissancef;
		this.tir=puissancet;
		numeroSoldat = numerosoldat;
		pos= new Position(posSoldat);
		
	}
/*
 * les différents accesseurs des variables de la classe soldat
 * @return ptvi
 */

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return this.ptvi;
	}

/**
 *savoir si un joueur a déja joué
 * (non-Javadoc)
 * @see wargame.ISoldat#getTour()
 * @return djoue
 */
	@Override
	public int getTour() {
		// TODO Auto-generated method stub
		return this.djoue;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public int getPortee() {
		// TODO Auto-generated method stub
		return this.portee;
	}
	
	/**
	 * accesseurs puissance de frappe si corps à corps
	 * @return puissance
	 */
	public int getPuissance(){
		return this.puissance;
	}
	
	/**
	 * accesseurs puissance de tir si combat à distance
	 * @return  tir
	 */
	
	public int getTir(){
		return this.tir;
	}

	/**
	 *
	 * @return numsoldat
	 */
	public int getNumeroSoldat(){
		return this.numsoldat;
	}
	/*
	 * récupérer la position d'un soldat sur la carte
	 * @param Soldatpos
	 */
	public Position getSoldatpos(){
		return pos;
	}
	
	
	/***
	 * mutateurs point de vie pour la modification du point de vie d'un joueur pendant une partie
	 * @param pointViNew
	 */
	public void setPoints(int pointViNew){
		this.ptvi=pointViNew;
	}
	
	
	 /**
	  * mutateur permettant de savoir si un joueur a deja joué un tour de jeu ou pas
	  * 
	  * @param tour
	  */
	
	public void setJoueTour(int tour){
		this.djoue=tour;
		
	}


	@Override
	
	/**
	 * (non-Javadoc)
	 * @see wargame.ISoldat#joueTour(int)
	 * cette methode permet de savoir si un joueur a déja joué par le changement de sa couleur et on se base 
	 * sur le changement des héros parce que un soldat hero ne fait que un tour de jeu contrairement aux monstres
	 * la couleur de case de base d'un hero est rouge si ce dernier à deja joué sa case devient rose
	 * si tour =1 alors joueur deja joué sinon pas encore et case prend la couleur rose
	 */
	 /**
	 * (non-Javadoc)
	 * @see wargame.ISoldat#joueTour(int)
	 * casecolor est défini dans la classe Element
	 * @param tour
	 */
	public void joueTour(int tour) {
		// TODO Auto-generated method stub
		this.djoue=tour;
		if(tour==1){
			coul=COULEUR_HEROS_DEJA_JOUE ;
		}
		else if(tour==0){
			coul=COULEUR_HEROS;
		}
		
		
	}
	
	public void repos(){
		ptvi+=5;
		if (ptvi > POINTS_DE_VIE_MAX)
			ptvi = POINTS_DE_VIE_MAX;
	}

/**
 * (non-Javadoc)
 * @see wargame.ISoldat#combat(wargame.Soldat)
 * cette méthode permet d'implémenter le combat entre deux soldats à savoir entre
 * entre un hero et un monstre
 * @param soldat
 */
	//le soldat courant frappe un adversaire avec une puissance donnée
	public void frapper(int puis, Soldat soldat){
		soldat.setPoints(soldat.getPoints() - puis);
	}
	
	@Override
	public boolean combat(Soldat soldat) {
		// TODO Auto-generated method stub
		
		int puisf1; /***@puisf1 puissance de frappe pour un corps à corps**/
		int puisf2;  /***@puisf2 puissance de frappe pour un combat à distance**/
		
		while(this.getPoints() > 0 && soldat.getPoints() > 0){
			puisf1=(int)(Math.random()*this.puissance); /*puissance de frappa de l'objet courant*/
			puisf2=(int)(Math.random()*(soldat.getPuissance())); /* puissance de frappe de soldat*/
			this.frapper(puisf1, soldat);
			
			if (soldat.getPoints() <= 0)
				return true;
			soldat.frapper(puisf2, this);	
		}
		
		if(this.getPoints() <= 0)
			return false;
		
		return false;
	}
			
			/*if(this.pos.estVoisine( soldat.pos) ){
				puisf1=(int)(Math.random()*this.puissance); /*puissance de frappa de l'objet courant*/
				//puisf2=(int)(Math.random()*(soldat.getPuissance())); /* puissance de frappe de soldat*/
				
				/*le premier coup est porté par l'objet courant donc soldat perd
				 * un nombre de point de vie équiv alent à la puissance de frappe de this
				 */
				//soldat.setPoints(soldat.getPoints()-puisf1);
				//if(soldat.getPoints()>0){ 
					/*le premier coup est porté par soldat
					 * d'ou le nombre sup de son nombre de vie
					 * */
					
					//this.setPoints(ptvi-puisf2); /*pt de vie de l'objet courant diminue*/
					//if(this.getPoints()<=0){ /**point de vie de courant 
					//passe à 0 ou inférieur à 0 donc mort de l'objet courant*/
						//this.setPoints(0); /*mise à jour à ptvi=0*/
						//mapJeu.mort(this); /*diparition de la carte du soldat
					/*}
					else{
						soldat.setPoints(0);
						//mapJeu.mort(this); 
					}
				}
			}*/
			
	public void combatDistance(Soldat soldat){ /*combat à distance*/
		
		int puisf1; 
		int puisf2;  
		
		puisf1=(int)(Math.random()*soldat.tir);
		puisf2=(int)(Math.random()*(this.getTir()));
		
		soldat.setPoints(soldat.getPoints()-puisf1);
		if(soldat.getPoints()>0){ 
			/**
			 * le premier coup est porté par soldat
			 * d'ou le nombre sup de son nombre de vie */
			
			this.setPoints(ptvi-puisf2); /*pt de vie de l'objet courant diminue*/
			if(this.getPoints()<=0){ /**point de vie de courant
			passe à 0 ou inférieur à 0 donc mort de l'objet courant*/
				this.setPoints(0); /*mise à jour à ptvi=0*/
				//mapJeu.mort(this); /*diparition de la carte du soldat
			}
			else{
				soldat.setPoints(0);
				//mapJeu.mort(this); 
			}
		}
	}


	@Override
	
	/**
	 * (non-Javadoc)
	 * @see wargame.ISoldat#seDeplace(wargame.Position)
	 * méthode pour implémenter le déplacement d'un soldat sur la carte
	 * @param newPos
	 */
	public void seDeplace(Position newPos) {
		// TODO Auto-generated method stub
		this.pos = newPos;
	}
	
	/**
	 *
	 * Dessin des soldat heros, soldate ne fonction des dimensions
	 * et pixels des cases de la carte pour ne pas avoir de débordement
	 * des soldats sur la carte
	 * @param g
	 * @param largeur
	 * @param hauteur
	 */
	
	public void seDessiner(Graphics g,int largeur,int hauteur){

    	if (this instanceof Heros){
    		if (this.djoue == 1){ /*hero ayant deja joué donc couleur rose*/
    			g.setColor(COULEUR_HEROS_DEJA_JOUE);
    		}
    		else{
    			g.setColor(COULEUR_HEROS); /*couleur de base de hero rouge si pas encore joué*/ 
    		}
    		g.fillRect(largeur, hauteur, NB_PIX_CASE, NB_PIX_CASE); /*remplissage
    		de la case sur la carte pour le dessin de hero*/

    	}
    	
    	/**
    	 * si pas hero alors monstre donc idem de couleur hero en couleurs monstre
    	 * et remplissage de la case
    	 */
    	else {
    		g.setColor(COULEUR_MONSTRES);
        	g.fillRect(largeur, hauteur, NB_PIX_CASE, NB_PIX_CASE);

    	}	
		
	}

	/**
	 *
	 * @return
	 */
public String toString(){
		String ch = pos.toString();
		if (this instanceof Monstre)
			ch +=  numeroSoldat + " (" + ptvi +"PV/"+POINTS_DE_VIE_MAX+")";
		else
			ch +=  (char) (numeroSoldat + 64) + " (" + ptvi +"PV/"+POINTS_DE_VIE_MAX+")";
		return ch;
	}


	

}
