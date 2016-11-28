package wargame;

import java.awt.*;

public  class Element implements IConfig {
	protected Color coul = IConfig.COULEUR_VIDE;
	protected Position pos;
	protected int numeroSoldat=0;

	public Position getElementPosition() {
		return this.pos;
	}

	public void dessinerCarree(int x, int y, Graphics g) {
		g.setColor(Color.yellow);
        if(numeroSoldat != 0)
		    g.drawString(numeroSoldat+"",x + IConfig.NB_PIX_CASE, (y+IConfig.NB_PIX_CASE));
	    g.setColor(IConfig.COULEUR_TEXTE);
		//g.drawString("");
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g.drawRect(x + IConfig.NB_PIX_CASE, y + IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE);
		g.setColor(coul);
		g.fillRect(x + IConfig.NB_PIX_CASE, y + IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE);

	}

	/*
	* Polygone Regulier
	* @param Centre Position de point centre de Polygone
	* */
	public void seDessinerPolygone(Position centre,Graphics g) {
		Position[] tab = new Position[6];
		Position p;
		centre = new Position(centre.getX() + IConfig.NB_PIX_CASE, centre.getY() + IConfig.NB_PIX_CASE);
		p = new Position(centre.getX() + IConfig.NB_PIX_CASE, centre.getY());
		double dx = p.getX() - centre.getX();
		double dy = p.getY() - centre.getY();
		double a = Math.atan2(dy, dx);
		double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		tab[0] = new Position(p);
		for (int i = 1; i < tab.length; i++) {
			tab[i] = new Position((int) (centre.getX() + (r * Math.cos(a + (i * 2 * Math.PI / 6)))), (int) (centre.getY() + (r * Math.sin(a + (i * 2 * Math.PI / 6)))));

		}
		int[] ensPointX = new int[6];
		int[] ensPointY = new int[6];
		g.setColor(IConfig.COULEUR_TEXTE);
		for (int j = 0; j < tab.length; j++) {
			ensPointX[j] = tab[j].getX();
			ensPointY[j] = tab[j].getY();

		}
		//g.drawString(toString(), ensPointX[6 - 2], ensPointY[6 - 2]);
		g.setColor(IConfig.COULEUR_TEXTE);
		Graphics2D g2 = (Graphics2D) g;


		if (this != null) {
			g2.setStroke(new BasicStroke(2));
			g.drawPolygon(ensPointX, ensPointY, 6);
			g.setColor(coul);
			g.fillPolygon(ensPointX, ensPointY, 6);
			if(numeroSoldat!=0) {
				Font font = g.getFont().deriveFont(22.0f);
				g.setFont(font);
				if (this instanceof Monstre) {
					g.setColor(IConfig.COULEUR_VIDE);
					g.drawString(numeroSoldat + "", centre.getX() - 11, centre.getY() + 6);
				} else {
					g.setColor(IConfig.COULEUR_VIDE);
					g.drawString(((char) (numeroSoldat + 96)) + "", centre.getX() - 11, centre.getY() + 6);

				}
			}
		} else {
			g.setColor(IConfig.COULEUR_INCONNU);
			g.fillPolygon(ensPointX, ensPointY, 6);

		}
	}
}