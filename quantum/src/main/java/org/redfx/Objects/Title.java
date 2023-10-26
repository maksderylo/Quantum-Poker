package org.redfx.Objects;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**Custom JLabel with set font and size for consistency throughout the program.*/
public class Title extends JLabel {
    /**Constructing with a set font and size.
     * 
     * @param text is the text for the title
     */
    public Title(String text) {
        setText(text);
        setFont(new Font("Serif", Font.BOLD, 40));
        setForeground(Color.WHITE);
    }
}
