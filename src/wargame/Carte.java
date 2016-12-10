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
		int num=(int)Math.random() * tabHeros.length;
		if(tabHeros[num] != null)
			return(tabHeros[num]);
		else
			return null;
	}
	
	//Trouve un héros choisi aléatoirement parmi les 8 positions adjacentes de pos
	public Heros trouveHeros(Position pos){
		if (tabElements[pos.getX()][pos.getY()] instanceof  Heros)
			return((Heros) tabElements[pos.getX()][pos.getY()]);
		else return null;
	}
	
	//
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
				
				soldat.combat(soldat2);
			}
		}
			return false;
	}
	
	public void mort(Soldat perso){
		
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
        tabMonstre =new Monstre[IConfig.NB_MONSTRES];
        for(int i=0;i<tabMonstre.length;i++) {
            tabMonstre[i] = new Monstre(ISoldat.TypesM.getTypeMAlea(), i+1 , trouvePositionVide());
            //System.out.println(tabMonstre[i]);
        }

        for(int i=0;i<tabMonstre.length;i++)
            tabElements[tabMonstre[i].getElementPosition().getX()][tabMonstre[i].getElementPosition().getY()] = tabMonstre[i];
    }


	/**
    * Methode CreateHeros()
    * Générate un tableau (1 dimenssion) des Heros
    * et stocker leurs positions dans Tableau d'element
    *
    * */
	public void createHeros(){
        tabHeros =new Heros[IConfig.NB_HEROS];
        for(int i=0;i<tabHeros.length;i++) {
            tabHeros[i] = new Heros(ISoldat.TypesH.getTypeHAlea(), i + 1, trouvePositionVide());
            //System.out.println(tabHeros[i]);

        }

        for(int i=0;i<tabHeros.length;i++){
            tabElements[tabHeros[i].getElementPosition().getX()][tabHeros[i].getElementPosition().getY()] = tabHeros[i];
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
