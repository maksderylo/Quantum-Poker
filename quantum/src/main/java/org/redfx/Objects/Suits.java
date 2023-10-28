package org.redfx.Objects;
 
import java.awt.Image;
import javax.swing.ImageIcon;



/**A class to compactly store the images for each suit. */
public class Suits {
    public static final ImageIcon HEARTS = new ImageIcon("C:\\Users\\20231479\\Documents\\GitHub\\Quantum-Poker\\quantum\\src\\main\\resources\\org\\redfx\\resources\\hearts.png");
    public static final ImageIcon DIAMONDS = new ImageIcon("C:\\Users\\20231479\\Documents\\GitHub\\Quantum-Poker\\quantum\\src\\main\\resources\\org\\redfx\\resources\\diamonds.png");
    public static final ImageIcon CLUBS = new ImageIcon("C:\\Users\\20231479\\Documents\\GitHub\\Quantum-Poker\\quantum\\src\\main\\resources\\org\\redfx\\resources\\clubs.png");
    public static final ImageIcon SPADES = new ImageIcon("C:\\Users\\20231479\\Documents\\GitHub\\Quantum-Poker\\quantum\\src\\main\\resources\\org\\redfx\\resources\\spades.png");

    /**A method to return the correct image according to the input.
     * 
     * @param suit is the suit for which the correct image is meant to return
     * @return an ImageIcon based on the suit input.
     */
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




