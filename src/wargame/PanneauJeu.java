package wargame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanneauJeu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* Variable **/

	public PanneauJeu(){
		 
		setOpaque(true);
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(IConfig.WIDTH, IConfig.HIGHT));
	}

	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
	}
}
