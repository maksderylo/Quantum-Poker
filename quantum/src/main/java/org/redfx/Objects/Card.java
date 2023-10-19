package org.redfx.Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Card extends JPanel {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        setOpaque(false);

        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 150); // Set your desired width and height
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int cardWidth = 100; // Set your desired width
        int cardHeight = 150; // Set your desired height
        int x = (getWidth() - cardWidth) / 2;
        int y = (getHeight() - cardHeight) / 2;

        RoundRectangle2D roundedRect = new RoundRectangle2D.Double(x, y, cardWidth, cardHeight, 10, 20);

    
        g2d.setColor(Color.WHITE);
        g2d.fill(roundedRect);

        g2d.setColor(Color.BLACK);
        g2d.draw(roundedRect);
    /*
        g.setColor(Color.BLACK);
        g.drawRect(x, y, cardWidth - 1, cardHeight -1);
    
        // Draw border
        g.setColor(Color.BLUE);
        g.drawRect(x - 1, y - 1, cardWidth + 1, cardHeight + 1);
    */
        g.setColor(Color.BLACK);
        g.setFont(new Font("Lucida Console", Font.BOLD, 7));
        String cardText = rank + " of " + suit;
        int textWidth = g.getFontMetrics().stringWidth(cardText);
        int textX = x + (cardWidth - textWidth) / 2;
        int textY = y + cardHeight / 5;
        g.drawString(cardText, textX, textY);
    }
}
