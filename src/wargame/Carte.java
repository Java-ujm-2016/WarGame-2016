package wargame;

/*
 *@author AYADA Ahmad
 *
 * */


import java.awt.*;
import java.io.Serializable;

public class Carte implements ICarte, Serializable {
	
	protected Element[][] tabElements = null;
	protected Obstacle[] tabObstacle;
    protected Heros[] tabHeros;
    protected Monstre[] tabMonstre;
    
    protected Joueur[] ArmeeHeros;
    protected Joueur[] ArmeeMonstre;
	
    
	public Carte(){
		tabElements=new Element[IConfig.LARGEUR_CARTE][IConfig.HAUTEUR_CARTE];
		viderTabElement();
        createObstacle();
        createMonstre();
        createHeros();
        System.out.println("\n\n");

        System.out.println("**************************** Fin Creation *************************************");

	}
	
	/*
	* Methode "getElement" cherche un element dans un tableau de Dimension 2
	* @param pos est la Position de point
	* @return tabElement[p.getX()][p.getY()] l'element se trouve dans la Position entré
	* */
	public Element getElement(Position pos){
		if( pos.getX()<0 || pos.getX()>=IConfig.LARGEUR_CARTE || pos.getY()<0 || pos.getY()>=IConfig.HAUTEUR_CARTE )
			throw new IndexOutOfBoundsException();
		else
		 return tabElements[pos.getX()][pos.getY()];
	}

	public Position trouvePositionVide(){
		// Trouve aléatoirement une position vide sur la carte
		int x= (int) (Math.random() * IConfig.LARGEUR_CARTE);
		int y= (int) (Math.random() * IConfig.HAUTEUR_CARTE);
		int count=1;
		while (tabElements[x][y]!=null && count<= (IConfig.HAUTEUR_CARTE*IConfig.LARGEUR_CARTE)){
			x= (int) (Math.random() * IConfig.LARGEUR_CARTE);
			y= (int) (Math.random() * IConfig.HAUTEUR_CARTE);
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
	
	// aléatoirement parmi les 8 positions adjacentes de pos Trouve aléatoirement un héros sur la carte
	public Heros trouveHeros(){
		int num=(int)Math.random() * ArmeeHeros.length;
		if(ArmeeHeros[num] != null)
			return (Heros) (ArmeeHeros[num].getSoldat());
		else
			return null;
	}
	
	//Trouve un héros choisi aléatoirement parmi les 8 positions adjacentes de pos
	public Heros trouveHeros(Position pos){
		if (tabElements[pos.getX()][pos.getY()] instanceof  Heros)
			return((Heros) tabElements[pos.getX()][pos.getY()]);
		else return null;
	}
	
	//Touver l'indice de la case du Soldat en action dans ArmeeHeros
	public int trouveIndice(Position pos, Joueur[] armee){
		int i=0;
		boolean bool = false;
		while(i < armee.length && bool == false){
			if (armee[i] != null)
				if (pos.getX() == armee[i].getSoldat().getSoldatpos().getX() 
						&& pos.getY() == armee[i].getSoldat().getSoldatpos().getY())
					bool = true;
			i++;
		}
		
		return i-1;
	}
	
	//Trouver toutes les positions adjacentes à la position donnée
	public Position[] trouvePositonAdj(Position pos){
		Position[] tab = new Position[10];
		int k = 0;
		for (int i=0; i < IConfig.LARGEUR_CARTE;i++){
			for (int j=0; j < IConfig.HAUTEUR_CARTE;j++){
				if (pos.estVoisine(new Position(i,j))){
					tab[k] = new Position(i,j);
					System.out.println(tab[k].toString());
					k++;
				}
			}
		}
		return tab;
	}
	
	//Verification et mise en repos Heros n'ayant pas joués
	public void finTour(){
		for(int i = 0; i < ArmeeHeros.length; i++ ){
			Position p = null;
			if(ArmeeHeros[i]!= null){
				if(ArmeeHeros[i].getEtat() == false){
					p = ArmeeHeros[i].getSoldat().getElementPosition();
					((Soldat) tabElements[p.getX()][p.getY()]).repos();
				}
				ArmeeHeros[i].setEtat(false);
			}
		}
	}
	
	//Faire jouer les Monstres
	public void tourMonstre(Monstre monstre){
		
			Position posi = monstre.getElementPosition();
			
			System.out.println("action Monstre N° "+posi.toString());
			
			Position[] tab = new Position[10];
			tab = trouvePositonAdj(posi);
			Heros soldat=null;
			//recherche des heros aux alentours du monstre courant
			for(int j=0; j < tab.length; j++){
				if(tab[j] != null && trouveHeros(tab[j]) != null)
					soldat = trouveHeros(tab[j]);
			}
			//si Heros trouvé : combatre l'heros
			if(soldat != null){
				Position pos = soldat.getElementPosition();
				deplaceSoldat(pos, monstre);
			}
			//deplacement aleatoire dans une des postions adjascentes 
			else{
				Position alea = null;
				while (alea == null){
					alea = tab[(int)Math.random()*tab.length];
				}
				
				if (getElement(alea) == null)
					deplaceSoldat(alea, monstre);
				else
					((Soldat)getElement(posi)).repos();
			}
		System.out.println("blalalalaalaalalalalla");
	}

	//Deplacement et engagement d'eventuel combat
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
					//Suprimmer le soldat mort de son armee
					if (soldat2 instanceof Heros){
						ArmeeHeros[trouveIndice(p,ArmeeHeros)]= null;
					}
					else{
						ArmeeMonstre[trouveIndice(p,ArmeeMonstre)]= null;
					}
					mort(soldat2);
					tabElements[p.getX()][p.getY()] = null;
					soldat.seDeplace(pos);
					tabElements[pos.getX()][pos.getY()] = soldat;
				}
				else{
					//Suprimmer le soldat mort de son armee
					if (soldat instanceof Heros){
						ArmeeHeros[trouveIndice(p,ArmeeHeros)]= null;
					}
					else{
						ArmeeMonstre[trouveIndice(p,ArmeeMonstre)]= null;
					}
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
	 * */
	public void toutDessiner(Graphics g){
		for(int i=0;i<IConfig.LARGEUR_CARTE;i++)
            for(int j=0; j< IConfig.HAUTEUR_CARTE;j++) {
			if (tabElements[i][j] != null) {
                    //System.out.print(tabElements[i][j].coul + " | ");
				tabElements[i][j].dessinerCarree(i*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE,g);
					//tabElements[i][j].seDessinerPolygone(new Position(i*(IConfig.NB_PIX_CASE+IConfig.NB_PIX_CASE/2),j*((int)(IConfig.NB_PIX_CASE* Math.sqrt(3))/2)),g);
			}else{

				g.setColor(IConfig.COULEUR_TEXTE);
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(2));
				g.drawRect(i*IConfig.NB_PIX_CASE+IConfig.NB_PIX_CASE, j*IConfig.NB_PIX_CASE+IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE);
                g.setColor(IConfig.COULEUR_INCONNU);
                g.fillRect(i*IConfig.NB_PIX_CASE+IConfig.NB_PIX_CASE, j*IConfig.NB_PIX_CASE+IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE);
                	//tabElements[i][j].seDessinerPolygone(new Position(i*(IConfig.NB_PIX_CASE+IConfig.NB_PIX_CASE/2),j*((int)(IConfig.NB_PIX_CASE* Math.sqrt(3))/2)),g);
            }
		}
	}

	/**
	* Methode viderTabElement()
	* Initialisation de Table Des Elements
	* mis chaque cellule en null
	*
	* */
	public void viderTabElement(){
		for(int i=0;i<IConfig.LARGEUR_CARTE;i++)
			for (int j=0;j<IConfig.HAUTEUR_CARTE;j++) {
                this.tabElements[i][j] = null;

                //this.tabElements[i][j].coul=IConfig.COULEUR_VIDE;
            }
		}

	/**
    * Methode CreateObstacle()
    * Générate un tableau (1 dimenssion) des Obstacle
    * et stocker leurs positions dans Tableau d'element
    *
    * */
	public void createObstacle(){
		tabObstacle =new Obstacle[IConfig.NB_OBSTACLES];
		for(int i=0;i<tabObstacle.length;i++)
			tabObstacle[i]= new Obstacle(trouvePositionVide());


		for(int i=0;i<tabObstacle.length;i++) {
            tabElements[tabObstacle[i].getElementPosition().getX()][tabObstacle[i].getElementPosition().getY()] = tabObstacle[i];

        }
	}

    /**
    * Methode CreateMontre()
    * Générate un tableau (1 dimenssion) de Monstre
    * et Stocker leur positions dans Tableau d'elements
    * */
	public void createMonstre(){
        ArmeeMonstre= new Joueur[IConfig.NB_MONSTRES];
        for(int i=0;i<ArmeeMonstre.length;i++) {
            //tabMonstre[i] = new Monstre(ISoldat.TypesM.getTypeMAlea(), i+1 , trouvePositionVide());
            //System.out.println(tabMonstre[i]);
            ArmeeMonstre[i]= new Joueur(new Monstre(ISoldat.TypesM.getTypeMAlea(), i+1 , trouvePositionVide()),false);
        }

        for(int i=0;i<ArmeeMonstre.length;i++)
            tabElements
            [ArmeeMonstre[i].getSoldat().getElementPosition().getX()]
            [ArmeeMonstre[i].getSoldat().getElementPosition().getY()] = ArmeeMonstre[i].getSoldat();
    }


	/**
    * Methode CreateHeros()
    * Générate un tableau (1 dimenssion) des Heros
    * et stocker leurs positions dans Tableau d'element
    *
    * */
	public void createHeros(){
        ArmeeHeros = new Joueur[IConfig.NB_HEROS];
        for(int i=0;i<ArmeeHeros.length;i++) {
            //tabHeros[i] = new Heros(ISoldat.TypesH.getTypeHAlea(), i + 1, trouvePositionVide());
            //System.out.println(tabHeros[i]);
            ArmeeHeros[i]= new Joueur(new Heros(ISoldat.TypesH.getTypeHAlea(), i + 1, trouvePositionVide()), false);
        }

        for(int i=0;i<ArmeeHeros.length;i++){
            tabElements
            [ArmeeHeros[i].getSoldat().getElementPosition().getX()]
            [ArmeeHeros[i].getSoldat().getElementPosition().getY()] = ArmeeHeros[i].getSoldat();
			//System.out.println(tabHeros[i].getNumeroSoldat());
		}
    }


    public void toutDissenerPolygone(Graphics g){
    	Position p=new Position(IConfig.p);
		Element tmps=new Element();
    	for(int i=0;i<IConfig.LARGEUR_CARTE;i++){
			if(i%2==0) {
				for (int j = 0; j < IConfig.HAUTEUR_CARTE; j++){
					if (tabElements[i][j] != null) {
						tabElements[i][j].seDessinerPolygone(new Position((int) (p.getX() + (i * 1.5 * IConfig.NB_PIX_CASE)),
								(int) (p.getY() + (j * (2*IConfig.NB_PIX_CASE * Math.sqrt(3) / 2)))), g);
					}else{
						tmps.seDessinerPolygone(new Position((int) (p.getX() + (i * 1.5 * IConfig.NB_PIX_CASE)),
								(int) (p.getY()+ (j * (2*IConfig.NB_PIX_CASE * Math.sqrt(3) / 2)))), g);
					}
				}
			}else{
				for (int j = 0; j < IConfig.HAUTEUR_CARTE; j++)
					if (tabElements[i][j] != null) {
						tabElements[i][j].seDessinerPolygone(new Position((int) (p.getX() + (i * 1.5 * IConfig.NB_PIX_CASE)),
								(int) (p.getY() + (j * (2*IConfig.NB_PIX_CASE * Math.sqrt(3) / 2))+(IConfig.NB_PIX_CASE * Math.sqrt(3) / 2))), g);
					}else{
						tmps.seDessinerPolygone(new Position((int) (p.getX() + (i * 1.5 * IConfig.NB_PIX_CASE)),
								(int) (p.getY() + (j * (2*IConfig.NB_PIX_CASE  * Math.sqrt(3)/2))+ (IConfig.NB_PIX_CASE * Math.sqrt(3) / 2))), g);
					}
				}
		}
	}

	public Element[][] getTabElements() {
		return tabElements;
	}

	public Obstacle[] getTabObstacle() {
		return tabObstacle;
	}

	public Heros[] getTabHeros() {
		return tabHeros;
	}

	public Monstre[] getTabMonstre() {
		return tabMonstre;
	}

	public void setTabElements(Element[][] tabElements) {
		this.tabElements = tabElements;
	}

	public void setTabObstacle(Obstacle[] tabObstacle) {
		this.tabObstacle = tabObstacle;
	}

	public void setTabHeros(Heros[] tabHeros) {
		this.tabHeros = tabHeros;
	}

	public void setTabMonstre(Monstre[] tabMonstre) {
		this.tabMonstre = tabMonstre;
	}
}
