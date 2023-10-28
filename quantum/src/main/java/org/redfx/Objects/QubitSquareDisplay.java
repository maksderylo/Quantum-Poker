package org.redfx.Objects;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.JPanel;
import org.redfx.Screens.QuantumScreen;
 
public class QubitSquareDisplay extends JPanel{
    char letter;

    public QubitSquareDisplay(char letter) {
        this.letter = letter;
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setOpaque(false);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(60, 60);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        //draws a square dependent on the string given

        g.setColor(Color.YELLOW);
        g.drawRect(10, 10, 40, 40);
        g.setColor(Color.BLACK);

        // Create a font for the letter
        Font font = new Font("SansSerif", Font.BOLD, 16);
        g.setFont(font);

        // Get the size of the letter "H"
        
        FontMetrics metrics = g.getFontMetrics(font);
        int letterWidth = metrics.stringWidth(Character.toString(letter));
        int letterHeight = metrics.getHeight();

        // Calculate the position to center the letter "H" in the square
        int x = (60 - letterWidth) / 2;
        int y = (60 - letterHeight) / 2 + metrics.getAscent();

        // Draw the letter "H" in the center of the square
        g.drawString(Character.toString(letter), x, y);

    }
}
