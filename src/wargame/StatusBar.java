package wargame;

import java.awt.*;

import javax.swing.JLabel;

/**
 * Class STATUS BAR
 * @author AYADA Ahmad
 */
public class StatusBar extends JLabel {
    

	private static final long serialVersionUID = 1L;

	/**
	*  Creates a new instance of StatusBar
	* */
    public StatusBar() {
        super();
        setBackground(Color.white);
        //super.setPreferredSize(new Dimension(getWidth(), 26));
        setMessage("Ready");
    }

    /**
     * Ecrire un message dans un Label(status Bar)
     * @param message
     */
    public void setMessage(String message) {
        if(message==null)
            throw new IndexOutOfBoundsException();
        else
         setText(""+message);
    }        
}