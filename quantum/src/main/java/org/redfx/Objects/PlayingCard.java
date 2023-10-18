package org.redfx.Objects;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PlayingCard extends JFrame {
    

    public PlayingCard(String rank, String suit){
        super("Playing Card");
        setSize(200, 250);
        setResizable(false);

        Card cardPanel = new Card(rank, suit);
        add(cardPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {new Card("ace", "Hearts");});
        new PlayingCard("Ace", "Hearts");
    }

    

    
}

class Card extends JPanel {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.fillRect(300,600, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.drawRect(300, 600, getWidth() - 1, getHeight() -1);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Lucida Console", Font.BOLD, 10));
        String cardText = rank + " of " + suit;
        int textWidth = g.getFontMetrics().stringWidth(cardText);
        int x = (getWidth() - textWidth) / 2;
        int y = getHeight() / 2;
        g.drawString(cardText, x, y);
    }
/* 
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Draw the rectangle
        Rectangle2D.Double rectangle = new Rectangle2D.Double(0, 0, width, height);
        g2d.setColor(Color.WHITE); // Set the rectangle's color
        g2d.fill(rectangle);

        // Draw the top left text
        g2d.setColor(Color.BLACK); // Set the text color
        g2d.drawString(topLeftText, 5, 15); // Adjust the coordinates as needed

        // Draw the bottom right text
        g2d.drawString(bottomRightText, width - g2d.getFontMetrics().stringWidth(bottomRightText) - 5, height - 5); // Adjust the coordinates as needed
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rectangular Object with Text");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Card panel = new Card("Top Left", "Bottom Right");
            frame.add(panel);
            frame.setSize(100, 40); // Set the desired size (width x height)
            frame.setVisible(true);
        });
    }
    */
}