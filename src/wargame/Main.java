package wargame;
/*
 *@author AYADA Ahmad
 *
 * */

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		SwingUtilities.invokeLater( new Runnable() {
			public void	run	() {

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}
				//setDefaultSize(14);

				new MonFrame();
		}});
	}
	public static void setDefaultSize(int size) {

		Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();

		Object[] keys = keySet.toArray(new Object[keySet.size()]);
	for (Object key : keys) {

			if (key != null && key.toString().toLowerCase().contains("font")) {

		System.out.println(key);
				Font font = UIManager.getDefaults().getFont(key);
				if (font != null) {
					font = font.deriveFont((float) size);
								UIManager.put(key, font);
				}

			}

		}

	}

}
