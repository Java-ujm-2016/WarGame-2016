package wargame;

import java.awt.Color;
import java.awt.Graphics;

public class Element {
	protected Color coul;
	protected Position pos;
	
	//seDessiner chaque element sur la carte
	public void seDessiner(Graphics g){
		int[] xPoints ={pos.getX()*20,(pos.getX()+1)*20,(pos.getX()+1)*20,pos.getX()*20 };
		int[] yPoints ={pos.getY()*20,pos.getY()*20,(pos.getY()+1)*20,(pos.getY()+1)*20};
		g.setColor(coul);
		g.fillPolygon(xPoints, yPoints, 4);
	}
	
}
