package wargame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MonMenu extends JMenuBar{
	
	
	/**
	 * MenuBar
	 */
	private static final long serialVersionUID = 1L;

	public MonMenu(){
		super();
		

		JMenu file =new JMenu("Fichier");
		/* Fichier */
		add(file);
		JMenuItem restart=new JMenuItem("réJeux");
		JMenuItem open=new JMenuItem("ouvrir");
		JMenuItem save=new JMenuItem("sauvgarder");
		JMenuItem exit=new JMenuItem("sortir");
		file.add(restart);
		file.add(open);
		file.add(save);
		file.add(exit);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		/* MODIFIER **/
		JMenu edit =new JMenu("Modifier");
		add(edit);
		/* Aide */
		JMenu help =new JMenu("Aide");
		add(help);
		JMenuItem welcome=new JMenuItem("Bienvenu");
		JMenuItem about=new JMenuItem("?");
		help.add(welcome);
		help.add(about);
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "WarGame version 1.0 est un projet des Étudiant 3éme Année Sciences Informatique\n"
						+ "En construction de Jeux en JAVA Objet Orienté","Information",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
	}
}
