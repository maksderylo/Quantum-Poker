package org.redfx.Objects;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**The class for a single card, with its rank, suit, size ect. */
public class Card extends JPanel {
    private String firstLetter;
    private String suit;

    /**Constructor of the actual card and visually painting it.
     * @param rank is the rank(number) of the card.
     * @param suit is the suit of the card.*/
    public Card(String rank, String suit) {
        this.suit = suit;

        char r = rank.charAt(0);
        if (r == '1') { 
            firstLetter = "10"; /*we only have to check for ten since this is the only 
            two lettered value that is displayed, everything else is one letter.*/
        } else {
            firstLetter = "" + r;
        }

        
        
        setOpaque(false);

        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(70, 105);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int cardWidth = 70;
        int cardHeight = 105;
        int x = (getWidth() - cardWidth) / 2;
        int y = (getHeight() - cardHeight) / 2;

        RoundRectangle2D roundedRect = 
            new RoundRectangle2D.Double(x, y, cardWidth, cardHeight, 10, 20);
    
        g2d.setColor(Color.WHITE);
        g2d.fill(roundedRect);

        g2d.setColor(Color.BLACK);
        g2d.draw(roundedRect);

        Suits st = new Suits();
        int width = 28;
        int height = 34;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        BufferedImage bufferedImage = new BufferedImage(2048, 2048, BufferedImage.TYPE_INT_ARGB);
        st.getSuitSymbol(suit).paintIcon(null, bufferedImage.getGraphics(), 0, 0);
        Image resizedSuit = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        if (suit.equals("Spades")) {
            g2d.drawImage(resizedSuit, x + 9, y + 9, width + 10, height + 10, null);
        } else if (suit.equals("Clubs")) {
            g2d.drawImage(resizedSuit, x + 9, y + 11, width + 10, height + 10, null);
        } else if (suit.equals("Hearts")) {
            g2d.drawImage(resizedSuit, x + 9, y + 10, width - 10, height - 10, null);
        } else { //Diamonds
            g2d.drawImage(resizedSuit, x + 9, y + 8, width, height, null);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Lucida Console", Font.BOLD, 16));
        String cardText = firstLetter;
        int textWidth = g.getFontMetrics().stringWidth(cardText);
        int textX = x + (cardWidth - textWidth) / 2;
        int textY = y + cardHeight / 2 - 5;
        g.drawString(cardText, textX, textY);
    }
}
