package org.redfx.Objects;
 
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;




/**A class to compactly store the images for each suit. */
public class Suits {
    private Map<String, ImageIcon> suitIcons;
    
    public final ImageIcon hearts = new ImageIcon(getClass().getResource(
        "/org/redfx/resources/hearts.png"));
    public final ImageIcon diamonds = new ImageIcon(getClass().getResource(
        "/org/redfx/resources/diamonds.png"));
    public final ImageIcon clubs = new ImageIcon(getClass().getResource(
        "/org/redfx/resources/clubs.png"));
    public final ImageIcon spades = new ImageIcon(getClass().getResource(
        "/org/redfx/resources/spades.png"));
    

    public Suits() {
        suitIcons = new HashMap<>();
        suitIcons.put("Hearts", hearts);
        suitIcons.put("Diamonds", diamonds);
        suitIcons.put("Clubs", clubs);
        suitIcons.put("Spades", spades);
    }

    
    /**A method to return the correct image according to the input.
     * 
     * @param suit is the suit for which the correct image is meant to return
     * @return an ImageIcon based on the suit input.
     */
    public ImageIcon getSuitSymbol(String suit) {
        return suitIcons.get(suit);
        /* 
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
        }*/
        
    }
}
