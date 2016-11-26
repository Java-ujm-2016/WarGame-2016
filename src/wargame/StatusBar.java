package wargame;

import java.awt.*;

import javax.swing.JLabel;

public class StatusBar extends JLabel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Creates a new instance of StatusBar */
    public StatusBar() {
        super();
        setBackground(Color.white);
        //super.setPreferredSize(new Dimension(getWidth(), 26));
        setMessage("Ready");
    }
     
    public void setMessage(String message) {
        setText(" "+message);        
    }        
}