package wargame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 *@author AYADA Ahmad
 *
 * */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.*;

public class PanneauJeu extends JPanel {
	/**
	 *Cette classe contient tous les boutton necessaire à l'utilisation 
	 *du projet ainsi que les évènement souris	
	 */

	private static final long serialVersionUID = 1L;
	/* Variable **/
	JLabel label;
	Carte crt;
	JPanel zoneDessin;
	JButton finTour;
	JPanel buttonsPannel;
	StatusBar statuBar;
	
	JButton sauv;
	JButton rest;
	

	public PanneauJeu() {
		super();
		buttonsPannel = new JPanel();
		finTour = new JButton("Fin Tour");
		buttonsPannel.setPreferredSize(new Dimension(200, 90));
		buttonsPannel.setBackground(Color.GRAY);
		sauv = new JButton("Sauvegarde");
		rest = new JButton("Restaurer");
		
		label = new JLabel();
		setLayout(new BorderLayout());
		add(buttonsPannel, BorderLayout.NORTH);
		finTour.setPreferredSize(new Dimension(180, 80));
		//add(finTour,BorderLayout.NORTH);
		buttonsPannel.add(finTour, BorderLayout.NORTH);
		
		sauv.setPreferredSize(new Dimension(180, 80));
		rest.setPreferredSize(new Dimension(180, 80));
		buttonsPannel.add(sauv,BorderLayout.NORTH);
		buttonsPannel.add(rest,BorderLayout.NORTH);

		zoneDessin = new PanneauDessin();

		add(zoneDessin, BorderLayout.CENTER);
		setOpaque(true);
		setBackground(Color.GRAY);

		statuBar = new StatusBar();
		add(statuBar, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(IConfig.WIDTH, IConfig.HIGHT));

		/*
		 * Sauvegarde des données  ainsi que leurs restauration du jeu qui seront composés
		 * carte, Element et Position
		 */
		/*instanciation de la carte pour son utilisation*/
		
		//crt = new Carte();
		
	/*	sauv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent agr){
				try {
					FileOutputStream fichier = new FileOutputStream("wargame.ser");
					ObjectOutputStream var = new ObjectOutputStream(fichier);
					var.writeObject(crt);
					var.flush();
					var.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}
		});
		
		
		
		/*
		 * Restauration du jeu après une pause
		 */
		
	/*	rest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent agr){
				FileInputStream fichier;
				try {
					fichier = new FileInputStream("wargame.ser");
					ObjectInputStream var = new ObjectInputStream(fichier);
					Object lect = var.readObject();
					crt= (Carte) lect;
					var.close();
					repaint();
				//crt.SeDessiner();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});*/
	

	}
	
	

	class MyAdjustmentListener implements AdjustmentListener {
		public void adjustmentValueChanged(AdjustmentEvent e) {
			//label.setText("    New Value is " + e.getValue() + "      ");
			repaint();
		}
	}


	public class PanneauDessin extends JPanel {
		int panWdth = (2* IConfig.NB_PIX_CASE) * (IConfig.LARGEUR_CARTE + 1) + IConfig.BORDERS * 3;
		int panHight = (2* IConfig.NB_PIX_CASE )* (IConfig.HAUTEUR_CARTE + 1) +IConfig.BORDERS * 3;

		public PanneauDessin() {
			super(true);

			crt = new Carte();
			
/**********************************SAUVEGARDE RESTAURATION************************/
			
			sauv.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent agr){
					try {
						FileOutputStream fichier = new FileOutputStream("wargame.ser");
						ObjectOutputStream var = new ObjectOutputStream(fichier);
						var.writeObject(crt);
						var.flush();
						var.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
				}
			});
			
			
			
			/*
			 * Restauration du jeu après une pause
			 */
			
			rest.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent agr){
					FileInputStream fichier;
					try {
						fichier = new FileInputStream("wargame.ser");
						ObjectInputStream var = new ObjectInputStream(fichier);
						Object lect = var.readObject();
						crt= (Carte) lect;
						var.close();
						repaint();
					//crt.SeDessiner();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int x = e.getX();
					int y = e.getY();
					Position p= new Position(0,0);
					p= p.pxtoHex(x,y);

					if(e.getButton() == MouseEvent.BUTTON1 ){
						if (p.getX() < 0 || p.getY() < 0 || p.getX() >= IConfig.LARGEUR_CARTE || p.getY() >= IConfig.HAUTEUR_CARTE) return;
						try {
							statuBar.setMessage(crt.getElement(p).toString());
						}catch (NullPointerException e1){
							statuBar.setMessage(p.toString()+"");
							System.out.println("NO ELEMENT\n");
							//return;
						}
						System.out.println(crt.getElement(p));
					}
				}

			});


		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//g.drawRect(100,100, IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE);
			//crt.toutDessiner(g);


			crt.toutDissenerPolygone(g);

		}
	}
}