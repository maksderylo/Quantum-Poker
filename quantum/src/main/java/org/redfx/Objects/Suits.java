package org.redfx.Objects;
 
import javax.swing.ImageIcon;

public class Suits {
    public static final ImageIcon HEARTS = new ImageIcon("src\\main\\resources\\heart.png");
    public static final ImageIcon DIAMONDS = new ImageIcon("src\\main\\resources\\diamond.png");
    public static final ImageIcon CLUBS = new ImageIcon("src\\main\\resources\\clubs.png");
    public static final ImageIcon SPADES = new ImageIcon("src\\main\\resources\\spades.png");

    public static ImageIcon getSuitSymbol(String suit) {
        switch (suit) {
            case "Hearts":
                return HEARTS;
            case "Diamonds":
                return DIAMONDS;
            case "Clubs":
                return CLUBS;
            case "Spades":
                return SPADES;
            default:
                return null;
        }
    }
}
/*
import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    private String suit;

    public CardPanel(String suit) {
        this.suit = suit;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the card content

        // Draw the suit symbol in the corner
        ImageIcon suitIcon = Suits.getSuitSymbol(suit);
        if (suitIcon != null) {
            int iconWidth = suitIcon.getIconWidth();
            int iconHeight = suitIcon.getIconHeight();
            int cornerX = getWidth() - iconWidth - 5; // Adjust the position
            int cornerY = 5; // Adjust the position
            suitIcon.paintIcon(this, g, cornerX, cornerY);
        }
    }
}

public class CardGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Card Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            JPanel panel = new JPanel(new GridLayout(2, 2));

            // Create card panels with different suits
            CardPanel heartsCard = new CardPanel("Hearts");
            CardPanel diamondsCard = new CardPanel("Diamonds");
            CardPanel clubsCard = new CardPanel("Clubs");
            CardPanel spadesCard = new CardPanel("Spades");

            panel.add(heartsCard);
            panel.add(diamondsCard);
            panel.add(clubsCard);
            panel.add(spadesCard);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
*/




