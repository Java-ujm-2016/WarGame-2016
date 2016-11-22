package wargame;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		SwingUtilities.invokeLater( new Runnable() {
			public void	run	() {

				new MonFrame();
		}});
	}

}
