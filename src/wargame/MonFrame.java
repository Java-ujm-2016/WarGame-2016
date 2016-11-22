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
		StatusBar statuBar= new StatusBar();
		JPanel panneau= new PanneauJeu();
		frame.getContentPane().add(panneau, BorderLayout.CENTER);
		frame.getContentPane().add(statuBar,BorderLayout.SOUTH);
		
		frame.setLocation(250, 100);
		frame.pack();
		frame.setVisible(true);	
	
	}

}
