package org.redfx.Objects;

import java.util.ArrayList;
import java.util.Arrays;

/**A class for the deck containing a sorted deck and a deal method.*/
public class Deck extends ArrayList<String> {

    /**Creating a new List that we will copy the default deck to and then shuffle it and remove
     * cards from when drawn.
     * C = clubs, D = diamonds, S = spades, H = Hearts.
    A = ace, K = king, Q = queen, J = jack.
    */
    ArrayList<String> defaultDeck = new ArrayList<>(Arrays.asList(
                                    "Ace Clubs", "2 Clubs", "3 Clubs", "4 Clubs", "5 Clubs", 
                                    "6 Clubs", "7 Clubs", "8 Clubs", "9 Clubs", "10 Clubs", 
                                    "Jack Clubs", "Queen Clubs", "King Clubs",
                                    "Ace Diamonds", "2 Diamonds", "3 Diamonds", "4 Diamonds",
                                    "5 Diamonds", "6 Diamonds", "7 Diamonds", "8 Diamonds", 
                                    "9 Diamonds", "10 Diamonds", "Jack Diamonds", "Queen Diamonds", 
                                    "King Diamonds", "Ace Spades", "2 Spades", "3 Spades", 
                                    "4 Spades", "5 Spades", "6 Spades", "7 Spades", "8 Spades", 
                                    "9 Spades", "10 Spades", "Jack Spades", "Queen Spades", 
                                    "King Spades", "Ace Hearts", "2 Hearts", "3 Hearts", 
                                    "4 Hearts", "5 Hearts", "6 Hearts", "7 Hearts", "8 Hearts", 
                                    "9 Hearts", "10 Hearts", "Jack Hearts", "Queen Hearts", 
                                    "King Hearts"));
    ArrayList<String> quantumDeck = new ArrayList<>(Arrays.asList(
                                    "0", "0", "0", "0", "0", 
                                    "1", "1", "1", "+", "+", 
                                    "+", "+", "+",
                                    "+", "+", "-", "-",
                                    "-", "-", "-"));
        
    /**Constructing a new deck which is a shuffled copy of the full sorted deck. */
    public Deck(boolean quantum) {
        if (!quantum) {
            this.addAll(defaultDeck);
        } else {
            this.addAll(quantumDeck);
        }
        long seed = System.nanoTime();
        java.util.Collections.shuffle(this, new java.util.Random(seed));
    }

    /**Returns the top card and removes it from the deck. */
    public String deal() { 
        String current = this.get(0);
        this.remove(0);
        return current;
    }
}