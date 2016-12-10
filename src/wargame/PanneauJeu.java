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
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
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
		buttonsPannel.setPreferredSize(new Dimension(200, 60));
		buttonsPannel.setBackground(Color.GRAY);
		sauv = new JButton("Sauvegarde");
		rest = new JButton("Restaurer");
		
		label = new JLabel();
		setLayout(new BorderLayout());
		add(buttonsPannel, BorderLayout.NORTH);
		finTour.setPreferredSize(new Dimension(180, 50));
		//add(finTour,BorderLayout.NORTH);
		buttonsPannel.add(finTour, BorderLayout.NORTH);
		
		sauv.setPreferredSize(new Dimension(180, 50));
		rest.setPreferredSize(new Dimension(180, 50));
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
		
		Position depart = new Position(IConfig.p);//le decalage sur la zone de dessin
		int px, py;
		

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

			});
			
			//A l'appui sur le bouton de la souris sur la case d'un Soldat 
			addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					int x = e.getX();
					int y = e.getY();
					Position p= new Position(0,0);
					p= p.pxtoHex(x,y);
					px=p.getX();
					py=p.getY();
				}
			});
			
			//Au glisser-deposer dans une case adjascente
			addMouseMotionListener(new MouseMotionAdapter(){
				public void mouseDragged(MouseEvent e){
					int x = e.getX();
					int y = e.getY();
					Position p= new Position(0,0);
					p= p.pxtoHex(x,y);
					if(crt.tabElements[px][py] instanceof Heros){
						
						crt.deplaceSoldat(p, (Soldat) crt.getElement(new Position(px,py)));
						/*if(crt.tabElements[p.getX()][p.getY()] == null){
							crt.deplaceSoldat(p, (Soldat) crt.getElement(new Position(px,py)));
						}
						
						if(crt.tabElements[p.getX()][p.getY()] instanceof Soldat){
							//System.out.println("un combat est lancé");
							Soldat soldat1 = (Soldat) crt.getElement(new Position(px,py));
							Soldat soldat2 = (Soldat)crt.getElement(p);
							soldat1.combat(soldat2);
						}*/
					}
					repaint();
				}
				
				//Au survol d'une case de la carte on affiche les infos sur 
				//l'element qui s'y trouve
				public void mouseMoved(MouseEvent e) {
					int x = e.getX();
					int y = e.getY();
					Position p= new Position(0,0);
					p= p.pxtoHex(x,y);
					try {
						if (crt.getElement(p) != null) {
							if (crt.getElement(p) instanceof Soldat) {
								statuBar.setMessage(((Soldat) crt.getElement(p)).toString());
								System.out.println(((Soldat) crt.getElement(p)).toString());
							} else
								statuBar.setMessage(crt.getElement(p).toString());
						} else
							statuBar.setMessage(" case vide " + p.toString());
						repaint();
					}catch (Exception e1){
						return;
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