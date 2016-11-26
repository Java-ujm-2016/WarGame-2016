package wargame;

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
	
	
	public PanneauJeu(){
		super(true);
        label=new JLabel();
        setLayout(new BorderLayout());
         
        JScrollBar hbar = new JScrollBar(
           JScrollBar.HORIZONTAL, 30, 20, 0, 300);
        JScrollBar vbar = new JScrollBar(
                JScrollBar.VERTICAL, 30, 40, 0, 300);
         
        hbar.setUnitIncrement(10);
        hbar.setBlockIncrement(1);
         
        hbar.addAdjustmentListener(new MyAdjustmentListener());
        vbar.addAdjustmentListener(new MyAdjustmentListener());
         
        add(hbar, BorderLayout.SOUTH);
        add(vbar, BorderLayout.EAST);
        //add(label, BorderLayout.CENTER);

		setOpaque(true);
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(IConfig.WIDTH, IConfig.HIGHT));
		StatusBar statuBar= new StatusBar();

		add(statuBar,BorderLayout.SOUTH);

	}

	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
	}
	class MyAdjustmentListener implements AdjustmentListener {
        public void adjustmentValueChanged(AdjustmentEvent e) {
            //label.setText("    New Value is " + e.getValue() + "      ");
            repaint();
        }
    }

}
