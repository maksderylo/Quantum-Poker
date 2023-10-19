package org.redfx.Objects;


import javax.swing.*;
import java.awt.*;

public class Card extends JPanel {
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
        repaint();

        g.setColor(Color.BLACK);
        g.drawRect(300, 600, getWidth() - 1, getHeight() -1);
        repaint();

        g.setColor(Color.BLACK);
        g.setFont(new Font("Lucida Console", Font.BOLD, 14));
        String cardText = rank + " of " + suit;
        int textWidth = g.getFontMetrics().stringWidth(cardText);
        int x = (getWidth() - textWidth) / 2;
        int y = getHeight() / 10;
        g.drawString(cardText, x, y);
    }
}
