package org.redfx.Objects;

import java.awt.Color;
import javax.swing.JLabel;

/**A class for a consistent text throughout the GUI/program. */
public class Text extends JLabel {

    /**Constructing the text with the parameter.
     * 
     * @param text the text which will be displayed
     */
    public Text(String text) {
        setText(text);
        setForeground(Color.WHITE);
    }
}
