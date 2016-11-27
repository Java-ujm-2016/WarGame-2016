package wargame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MonFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public MonFrame(){
		super();
		JFrame frame = new JFrame("WarGame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mMenu=new MonMenu();
		
		frame.setJMenuBar(mMenu);
		JPanel panneau= new PanneauJeu();
		frame.add(panneau);


		frame.setLocation(250, 100);
		frame.pack();
		frame.setVisible(true);	
	
	}

}
