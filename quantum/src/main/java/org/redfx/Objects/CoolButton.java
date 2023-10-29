package org.redfx.Objects;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

/**A class for a fixed button used throughout the GUI.*/
public class CoolButton extends JButton {
    /**Constructor for the button. 
     * @param text is the text on the button.
    */
    public CoolButton(String text) {
        setText(text);
        setFont(new Font("Arial", Font.PLAIN, 20));
        setBackground(Color.LIGHT_GRAY); //import java.awt.Color;
        //setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(true);
    }

    public CoolButton(String text, Color color) {
        super(text);
        setBackground(color);
        setForeground(Color.BLACK);
    }
}
