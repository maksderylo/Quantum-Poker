package org.redfx.Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import javax.swing.JLabel;

/**Custom JLabel with set font and size for consistency throughout the program.*/
public class Title extends JLabel {


    /**Constructing with a set font and size.
     * 
     * @param text is the text for the title
     */
    public Title(String text) {
        setText(text);

        //Adding a custom font
        Font customFont;
        Font resizedFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, getClass()
                .getResourceAsStream("/org/redfx/resources/fontOne.otf"));
            if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                resizedFont = customFont.deriveFont((float) 16);
            } else {
                resizedFont = customFont.deriveFont((float) 30);
            }

        } catch (IOException | FontFormatException e) {
            if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                customFont = new Font("Arial", Font.PLAIN, 16);
            } else {
                customFont = new Font("Arial", Font.PLAIN, 30);
            }
        }
        setFont(resizedFont);
        setForeground(Color.WHITE);
    }
    
    /**A different constructor for when the player is playing quantum poker and the background
     * is a different color.
     * 
     * @param text is the text for the title
     * @param color is the color of the text
     */
    public Title(String text, Color color) {
        super(text);
        setForeground(color);
    }
}
