package wargame;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PanneauJeu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanneauJeu(){
		super();
		setOpaque(true);
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(IConfig.WIDTH, IConfig.HIGHT));
	}

}
