package org.redfx.Objects;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.JPanel;
import org.redfx.Screens.QuantumScreen;
 
public class QuantumSquareApplied extends JPanel{
    char letter;

    public QuantumSquareApplied(QuantumScreen quantumScreen, char letter) {
        this.letter = letter;
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setOpaque(false);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the mouse click event here
                // You can add your code to respond to the click event
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(60, 60);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        //draws a square dependent on the string given

        if (letter == 'H') { //Hardamand square
            g.setColor(Color.BLUE);
            g.drawRect(10, 10, 40, 40);
            g.setColor(Color.WHITE);

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


        } else if (letter == 'N') {
            g.setColor(Color.RED);
            g.drawRect(10, 10, 40, 40);
            g.setColor(Color.WHITE);

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
        } else if (letter == 'C') {
            g.setColor(Color.GREEN);
            g.drawRect(10, 10, 40, 40);
            g.setColor(Color.WHITE);

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
        } else if (letter == 'T') {
            g.setColor(Color.YELLOW);
            g.drawRect(10, 10, 40, 40);
            g.setColor(Color.WHITE);

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
}
