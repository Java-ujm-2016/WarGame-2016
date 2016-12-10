package wargame;

/**
 *@author AYADA Ahmad
 *
 * */


import java.awt.*;
import java.io.Serializable;

public class Carte implements ICarte, Serializable{
	
	protected Element[][] tabElements = null;
	protected Obstacle[] tabObstacle;
    protected Heros[] tabHeros;
    protected Monstre[] tabMonstre;
    
    protected Joueur[] ArmeeHeros;
    protected Joueur[] ArmeeMonstre;

    //Definition de la classe interne Joueur
	public class Joueur{
		Element elem;
		boolean bool;
		public Joueur(Element e, boolean b){
			elem = e;
			bool = b;
		}
	}
	
    
	public Carte(){
		tabElements=new Element[LARGEUR_CARTE][HAUTEUR_CARTE];
		viderTabElement();
        createObstacle();
        createMonstre();
        createHeros();
        System.out.println("\n\n");

        System.out.println("**************************** Fin Creation *************************************");

	}

	/**
	 *
	 * @author AYADA Ahmad
	 * Methode "getElement" cherche un element dans un tableau de Dimension 2
	 * @param pos est la Position de point
	 * @return tabElement[p.getX()][p.getY()] l'element se trouve dans la Position entré
	 **/
	public Element getElement(Position pos){
		if( pos.getX()<0 || pos.getX()>=LARGEUR_CARTE || pos.getY()<0 || pos.getY()>=HAUTEUR_CARTE )
			throw new IndexOutOfBoundsException();
		else
		 return tabElements[pos.getX()][pos.getY()];
	}

	/**
	 * Methode Trouve Position Vide
	 * @return P point vide dans la metrice Element
	 */
	public Position trouvePositionVide(){
		// Trouve aléatoirement une position vide sur la carte
		int x= (int) (Math.random() * LARGEUR_CARTE);
		int y= (int) (Math.random() * HAUTEUR_CARTE);
		int count=1;
		while (tabElements[x][y]!=null && count<= (HAUTEUR_CARTE*LARGEUR_CARTE)){
			x= (int) (Math.random() * LARGEUR_CARTE);
			y= (int) (Math.random() * HAUTEUR_CARTE);
			count++;
		}
		if(count<= (HAUTEUR_CARTE*LARGEUR_CARTE))
			return (new Position(x, y));
		else return null;
	}

	/**
	 * Methode Trouve Position Vide
	 * @param pos
	 * @return point
	 */
	public Position trouvePositionVide(Position pos){
		// Trouve une position vide choisie
		if(tabElements[pos.getX()][pos.getY()]!=null)
			return (new Position(pos));
		else return null;
	}

	/**
	 * Trouve Heros ALéatoirment (sans parmétre)
	 * @return hero de tabHeros
	 */
	// aléatoirement parmi les 8 positions adjacentes de pos Trouve aléatoirement un héros sur la carte
	public Heros trouveHeros(){
		int num=(int)Math.random() * tabHeros.length;
		if(tabHeros[num] != null)
			return(tabHeros[num]);
		else
			return null;
	}

	/**
	 * Trouve Heros ALéatoirment (sans parmétre)
	 * @param pos
	 * @return
	 */
	//Trouve un héros choisi aléatoirement parmi les 8 positions adjacentes de pos

	public Heros trouveHeros(Position pos){
		if (tabElements[pos.getX()][pos.getY()] instanceof  Heros)
			return((Heros) tabElements[pos.getX()][pos.getY()]);
		else return null;
	}

	/**
	 * Methode qui fait deplace soldat dans La matrice d'elements[][]
	 * @param pos
	 * @param soldat
	 * @return true/false si réussi ou pas à le deplacer
	 */
	public boolean deplaceSoldat(Position pos, Soldat soldat){
		Position p = soldat.getElementPosition();
		if(tabElements[pos.getX()][pos.getY()] == null){
			tabElements[p.getX()][p.getY()] = null;
			soldat.seDeplace(pos);
			tabElements[pos.getX()][pos.getY()] = soldat;
			return true;
		}
		//si la case indexée par pos n'est pas null et contient un soldat
		else if(tabElements[pos.getX()][pos.getY()] instanceof Soldat){
			
			Soldat soldat2 = (Soldat)tabElements[pos.getX()][pos.getY()];
			
			//si les deux soldat sont de type different (Heros vs Monstre || Monstre vs Heros)
			if((( soldat instanceof Heros) && (soldat2 instanceof Monstre)) 
				|| ((soldat instanceof Monstre) && (soldat2 instanceof Heros))){
				
				if(soldat.combat(soldat2)){
					mort(soldat2);
					tabElements[p.getX()][p.getY()] = null;
					soldat.seDeplace(pos);
					tabElements[pos.getX()][pos.getY()] = soldat;
				}
				else{
					mort(soldat);
				}
				
				return true;
			}
			//les 2 soldats sont du meme type pas de combat
			else
				return false;
		}
		//si la case indexée par pos n'est pas null et  contient autre chose qu'un soldat
		//pas de deplacement
		else
			return false;
	}

	/**
	 * Methode Mort faire déspariter un soldat où cas il perdre ses poin de vie
	 * @param perso parametre de type soldat (Hero ou Monstre)
	 */
	public void mort(Soldat perso){
		Position p = perso.getElementPosition();
		tabElements[p.getX()][p.getY()] = null;
	}
	
	public boolean actionHeros(Position pos, Position pos2){
		return false;
	}
	
	public void jouerSoldats(PanneauJeu pj){
		
	}

	/**
	 * Methode Tous Dessiner, une matrice
	 * Forme carré a dessiner (version 1)
	 * d'element[x][y] (Heros,soldat,obstacle)
	 * @param g
	 */
	public void toutDessiner(Graphics g){
		for(int i=0;i<LARGEUR_CARTE;i++)
            for(int j=0; j< HAUTEUR_CARTE;j++) {
			if (tabElements[i][j] != null) {
                    //System.out.print(tabElements[i][j].coul + " | ");
				tabElements[i][j].dessinerCarree(i*NB_PIX_CASE,j*NB_PIX_CASE,g);
					//tabElements[i][j].seDessinerPolygone(new Position(i*(NB_PIX_CASE+NB_PIX_CASE/2),j*((int)(NB_PIX_CASE* Math.sqrt(3))/2)),g);
			}else{

				g.setColor(COULEUR_TEXTE);
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(2));
				g.drawRect(i*NB_PIX_CASE+NB_PIX_CASE, j*NB_PIX_CASE+NB_PIX_CASE, NB_PIX_CASE,NB_PIX_CASE);
                g.setColor(COULEUR_INCONNU);
                g.fillRect(i*NB_PIX_CASE+NB_PIX_CASE, j*NB_PIX_CASE+NB_PIX_CASE, NB_PIX_CASE,NB_PIX_CASE);
                	//tabElements[i][j].seDessinerPolygone(new Position(i*(NB_PIX_CASE+NB_PIX_CASE/2),j*((int)(NB_PIX_CASE* Math.sqrt(3))/2)),g);
            }
		}
	}

	/**
	* @author AYADA Ahmad
	* Methode viderTabElement()
	* Initialisation de Table Des Elements
	* mis chaque cellule en null
	*
	* */
    protected void viderTabElement(){
		for(int i=0;i<LARGEUR_CARTE;i++)
			for (int j=0;j<HAUTEUR_CARTE;j++) {
                this.tabElements[i][j] = null;

                //this.tabElements[i][j].coul=COULEUR_VIDE;
            }
		}

	/**
	* @author AYADA Ahmad
    * Methode CreateObstacle()
    * Générate un tableau (1 dimenssion) des Obstacle
    * et stocker leurs positions dans Tableau d'element
    *
    * */
	public void createObstacle(){
		tabObstacle =new Obstacle[NB_OBSTACLES];
		for(int i=0;i<tabObstacle.length;i++)
			tabObstacle[i]= new Obstacle(trouvePositionVide());


		for(int i=0;i<tabObstacle.length;i++) {
            tabElements[tabObstacle[i].getElementPosition().getX()][tabObstacle[i].getElementPosition().getY()] = tabObstacle[i];

        }
	}

    /**
	* @author AYADA Ahmad
    * Methode CreateMontre()
    * Générate un tableau (1 dimenssion) de Monstre
    * et Stocker leur positions dans Tableau d'elements
    * */
	public void createMonstre(){
        tabMonstre =new Monstre[NB_MONSTRES];
        ArmeeMonstre= new Joueur[NB_MONSTRES];
        for(int i=0;i<tabMonstre.length;i++) {
            tabMonstre[i] = new Monstre(ISoldat.TypesM.getTypeMAlea(), i+1 , trouvePositionVide());
            //System.out.println(tabMonstre[i]);
            ArmeeMonstre[i]= new Joueur(tabMonstre[i],false);
        }

        for(int i=0;i<tabMonstre.length;i++)
            tabElements[tabMonstre[i].getElementPosition().getX()][tabMonstre[i].getElementPosition().getY()] = tabMonstre[i];
    }


	/**
	* @author AYADA Ahmad
    * Methode CreateHeros()
    * Générate un tableau (1 dimenssion) des Heros
    * et stocker leurs positions dans Tableau d'element
    *
    * */
	public void createHeros(){
        tabHeros =new Heros[NB_HEROS];
        ArmeeHeros = new Joueur[NB_HEROS];
        for(int i=0;i<tabHeros.length;i++) {
            tabHeros[i] = new Heros(ISoldat.TypesH.getTypeHAlea(), i + 1, trouvePositionVide());
            //System.out.println(tabHeros[i]);
            ArmeeHeros[i] = new Joueur(tabHeros[i], false);
        }

        for(int i=0;i<tabHeros.length;i++){
            tabElements[tabHeros[i].getElementPosition().getX()][tabHeros[i].getElementPosition().getY()] = tabHeros[i];
			//System.out.println(tabHeros[i].getNumeroSoldat());
		}
    }



    /**
	 * Methode Tous Dessiner (version 2)
	 * Dessine Un Matrice de Deux dimension
	 * Forme Hexagone
	 * @author AYADA Ahmad
	 * @param g
     * */
	public void toutDissenerPolygone(Graphics g){
    	Position p=new Position(IConfig.p);
		Element tmps=new Element();
    	for(int i=0;i<LARGEUR_CARTE;i++){
			if(i%2==0) {
				for (int j = 0; j < HAUTEUR_CARTE; j++){
					if (tabElements[i][j] != null) {
						tabElements[i][j].seDessinerPolygone(new Position((int) (p.getX() + (i * 1.5 * NB_PIX_CASE)),
								(int) (p.getY() + (j * (2*NB_PIX_CASE * Math.sqrt(3) / 2)))), g);
					}else{
						tmps.seDessinerPolygone(new Position((int) (p.getX() + (i * 1.5 * NB_PIX_CASE)),
								(int) (p.getY()+ (j * (2*NB_PIX_CASE * Math.sqrt(3) / 2)))), g);
					}
				}
			}else{
				for (int j = 0; j < HAUTEUR_CARTE; j++)
					if (tabElements[i][j] != null) {
						tabElements[i][j].seDessinerPolygone(new Position((int) (p.getX() + (i * 1.5 * NB_PIX_CASE)),
								(int) (p.getY() + (j * (2*NB_PIX_CASE * Math.sqrt(3) / 2))+(NB_PIX_CASE * Math.sqrt(3) / 2))), g);
					}else{
						tmps.seDessinerPolygone(new Position((int) (p.getX() + (i * 1.5 * NB_PIX_CASE)),
								(int) (p.getY() + (j * (2*NB_PIX_CASE  * Math.sqrt(3)/2))+ (NB_PIX_CASE * Math.sqrt(3) / 2))), g);
					}
				}
		}
	}

	/**
	 * Getter de Matrice D'Element dimenssion 2
	 *
	 * @return Matrice d'element Dimenssion 2
	 */
	public Element[][] getTabElements() {
		return tabElements;
	}
	/**
	 * Getter d'un Tableau Des Obstacle dimenssion 1
	 *
	 * @return Matrice d'Obstacles Dimenssion 1
	 */

	public Obstacle[] getTabObstacle() {
		return tabObstacle;
	}
	/**
	 * Getter d'un Tableau Des Heros dimenssion 1
	 *
	 * @return Matrice des Heros Dimenssion 1
	 */

	public Heros[] getTabHeros() {
		return tabHeros;
	}
	/**
	 * Getter d'un Tableau Des Monstre dimenssion 1
	 * recuprer un Tableau
	 * @return Tableu des Monsters Dimenssion 1
	 */
	public Monstre[] getTabMonstre() {
		return tabMonstre;
	}
	/**
	 * Methode getTabElements dimenssion 2
	 * *Recuper Matrice d'elemente
	 *
	 * @return Matric d'elements
	 */
	public void setTabElements(Element[][] tabElements) {
		this.tabElements = tabElements;
	}
	/**
	 * Methode setTabElements dimenssion 2
	 * Modifier les element de cette matrice
	 *
	 * @return Tableau des Heros Dimenssion 1
	 */
	public void setTabObstacle(Obstacle[] tabObstacle) {
		this.tabObstacle = tabObstacle;
	}

	/**
	 *
	 * @param tabHeros
	 */
	public void setTabHeros(Heros[] tabHeros) {
		this.tabHeros = tabHeros;
	}

	/**
	 *
	 * @param tabMonstre
	 */
	public void setTabMonstre(Monstre[] tabMonstre) {
		this.tabMonstre = tabMonstre;
	}
}
