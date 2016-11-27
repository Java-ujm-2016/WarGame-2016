package wargame;


import java.awt.Graphics;


public class Carte implements ICarte {
	protected Element[][] tabElements;
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
        /*for(int i=0;i<IConfig.HAUTEUR_CARTE;i++){
            for(int j=0; j< IConfig.LARGEUR_CARTE;j++) {
                if(tabElements[i][j]!=null)
                System.out.print("Position : "+tabElements[i][j].pos+" "+tabElements[i][j].coul);
            }
            System.out.print("\n");
        }*/
    }
	
	
	public Element getElement(Position pos){
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
	// aléatoirement parmi les 8 positions adjacentes de pos
	public Heros trouveHeros(){
		// Trouve aléatoirement un héros sur la carte
		return null;
	}
	
	public Heros trouveHeros(Position pos){
		// Trouve un héros choisi aléatoirement
		return null;
	}
									 // parmi les 8 positions adjacentes de pos
	
	public boolean deplaceSoldat(Position pos, Soldat soldat){
		return false;
	}
	
	public void mort(Soldat perso){
		
	}
	
	public boolean actionHeros(Position pos, Position pos2){
		return false;
	}
	
	public void jouerSoldats(PanneauJeu pj){
		
	}
	
	public void toutDessiner(Graphics g){

        for(int i=0;i<IConfig.LARGEUR_CARTE;i++)
            for(int j=0; j< IConfig.HAUTEUR_CARTE;j++) {
                if (tabElements[i][j] != null) {
                    //System.out.print(tabElements[i][j].coul + " | ");
                    tabElements[i][j].dessinerCarree(i*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE,g);
                }else{
                    g.setColor(IConfig.COULEUR_INCONNU);
                    g.drawRect(i*IConfig.NB_PIX_CASE+IConfig.NB_PIX_CASE, j*IConfig.NB_PIX_CASE+IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE);
                    g.setColor(IConfig.COULEUR_INCONNU);
                    g.fillRect(i*IConfig.NB_PIX_CASE+IConfig.NB_PIX_CASE, j*IConfig.NB_PIX_CASE+IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE);
                }

            }
	}
	public void viderTabElement(){
		for(int i=0;i<IConfig.LARGEUR_CARTE;i++)
			for (int j=0;j<IConfig.HAUTEUR_CARTE;j++) {
                this.tabElements[i][j] = null;
                //this.tabElements[i][j].coul=IConfig.COULEUR_VIDE;
            }
		}
	public void createObstacle(){
		tabObstacle =new Obstacle[IConfig.NB_OBSTACLES];
		for(int i=0;i<tabObstacle.length;i++)
			tabObstacle[i]= new Obstacle(trouvePositionVide());


		for(int i=0;i<tabObstacle.length;i++) {
            tabElements[tabObstacle[i].getElementPosition().getX()][tabObstacle[i].getElementPosition().getY()] = tabObstacle[i];

        }
	}

    public void createMonstre(){
        tabMonstre =new Monstre[IConfig.NB_MONSTRES];
        for(int i=0;i<tabMonstre.length;i++) {
            tabMonstre[i] = new Monstre(ISoldat.TypesM.getTypeMAlea(), i+1 , trouvePositionVide());
            //System.out.println(tabMonstre[i]);
        }

        for(int i=0;i<tabMonstre.length;i++)
            tabElements[tabMonstre[i].getElementPosition().getX()][tabMonstre[i].getElementPosition().getY()] = tabMonstre[i];
    }



    public void createHeros(){
        tabHeros =new Heros[IConfig.NB_HEROS];
        for(int i=0;i<tabHeros.length;i++) {
            tabHeros[i] = new Heros(ISoldat.TypesH.getTypeHAlea(), i + 1, trouvePositionVide());
            //System.out.println(tabHeros[i]);

        }

        for(int i=0;i<tabHeros.length;i++)
            tabElements[tabHeros[i].getElementPosition().getX()][tabHeros[i].getElementPosition().getY()] = tabHeros[i];
    }
}