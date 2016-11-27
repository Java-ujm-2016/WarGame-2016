package wargame;

import java.awt.Color;
import java.awt.Graphics;

public  class Element implements IConfig  {
	protected Color coul=IConfig.COULEUR_VIDE;
	protected Position pos;




	//seDessiner chaque element sur la carte
	/*public void seDessiner(Graphics g){
		int[] xPoints ={pos.getX()*20,(pos.getX()+1)*20,(pos.getX()+2)*20,(pos.getX()+3)*20 };
		int[] yPoints ={pos.getY()*20,(pos.getY()+1)*20,(pos.getY()+2)*20,(pos.getY()+3)*20 };
		g.setColor(coul);
		g.fillPolygon(xPoints, yPoints, 4);
	}*/
	public Position getElementPosition(){return this.pos;}
	public void dessinerCarree(int x,int y,Graphics g){
		g.setColor(coul);
		g.drawRect(x+IConfig.NB_PIX_CASE,y+IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE);
		g.fillRect(x+IConfig.NB_PIX_CASE,y+IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE);
	}


}
