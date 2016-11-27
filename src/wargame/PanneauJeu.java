package wargame;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class PanneauJeu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* Variable **/
	JLabel label;
	Carte crt;
	JPanel zoneDessin;

	public PanneauJeu(){
		super();
		crt=new Carte();
		label=new JLabel();
        setLayout(new BorderLayout());

        JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 40, 0, 300);
        JScrollBar vbar = new JScrollBar(  JScrollBar.VERTICAL, 30, 40, 0, 300);

        //hbar.setUnitIncrement(10);
        //hbar.setBlockIncrement(1);

        //hbar.addAdjustmentListener(new MyAdjustmentListener());
        //vbar.addAdjustmentListener(new MyAdjustmentListener());

        //add(hbar, BorderLayout.SOUTH);
        //add(vbar, BorderLayout.EAST);
        //add(label, BorderLayout.CENTER);
		zoneDessin=new PanneauDessin();
		add(zoneDessin,BorderLayout.CENTER);
		setOpaque(true);
		setBackground(Color.GRAY);

		StatusBar statuBar= new StatusBar();
		add(statuBar,BorderLayout.SOUTH);
		setPreferredSize(new Dimension(IConfig.WIDTH, IConfig.HIGHT));

	}

	

	class MyAdjustmentListener implements AdjustmentListener {
        public void adjustmentValueChanged(AdjustmentEvent e) {
            //label.setText("    New Value is " + e.getValue() + "      ");
            repaint();
        }
    }


	public class PanneauDessin extends JPanel {
		public PanneauDessin(){
			super();
			setBackground(new Color(166,166,166));
			setPreferredSize(new Dimension(IConfig.WIDTH, IConfig.HIGHT));


		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//g.drawRect(100,100, IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE);
			crt.toutDessiner(g);

		}
	}
}
