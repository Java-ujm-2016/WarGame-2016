package wargame;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class PanneauJeu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* Variable **/
	JLabel label;
	Carte crt;
	JPanel zoneDessin;
	JButton finTour;
	JPanel buttonsPannel;

	public PanneauJeu(){
		super();
		buttonsPannel=new JPanel();
		finTour=new JButton("Fin Tour");
		buttonsPannel.setPreferredSize(new Dimension(200,90));
		buttonsPannel.setBackground(Color.GRAY);

		label=new JLabel();
       	setLayout(new BorderLayout());
		add(buttonsPannel,BorderLayout.NORTH);
       	finTour.setPreferredSize(new Dimension(180,80));
		//add(finTour,BorderLayout.NORTH);
		buttonsPannel.add(finTour,BorderLayout.NORTH);



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
			crt=new Carte();




			//add(crt);
			/*			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == MouseEvent.BUTTON1 ){

						System.out.println("clicked");
					}
				}

			});*/


		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//g.drawRect(100,100, IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE);
			//crt.toutDessiner(g);


			crt.toutDissenerPolygone(g);

		}
	}
}
