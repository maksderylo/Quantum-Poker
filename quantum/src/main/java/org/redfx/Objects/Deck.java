package org.redfx.Objects;
import java.util.ArrayList;
import java.util.Arrays;



public class Deck extends ArrayList<String>{

    //C = clubs, D = diamonds, S = spades, H = Hearts.
    //A = ace, K = king, Q = queen, J = jack.
    
    ArrayList<String> defaultDeck = new ArrayList<>(Arrays.asList(
                                     "Ace Clubs","Two Clubs","Three Clubs","Four Clubs","Five Clubs","Six Clubs","Seven Clubs","Eight Clubs","Nine Clubs","Ten Clubs","Jack Clubs","Queen Clubs","King Clubs",
                                     "Ace Diamonds","Two Diamonds","Three Diamonds","Four Diamonds","Five Diamonds","Six Diamonds","Seven Diamonds","Eight Diamonds","Nine Diamonds","Ten Diamonds","Jack Diamonds","Queen Diamonds","King Diamonds",
                                     "Ace Spades","Two Spades","Three Spades","Four Spades","Five Spades","Six Spades","Seven Spades","Eight Spades","Nine Spades","Ten Spades","Jack Spades","Queen Spades","King Spades",
                                     "Ace Hearts","Two Hearts","Three Hearts","Four Hearts","Five Hearts","Six Hearts","Seven Hearts","Eigth Hearts","Nine Hearts","Ten Hearts","Jack Hearts","Queen Hearts","King Hearts"));
        
    //creating a new List that we will copy the default deck to and then shuffle it and remove
    // cards from when drawn.
    public Deck(){
        
        this.addAll(defaultDeck);
        long seed = System.nanoTime();
        java.util.Collections.shuffle(this, new java.util.Random(seed));
    }

    public String deal(){ //return the top card and remove it from the deck
        String current = this.get(0);
        this.remove(0);
        return current;
    }
}