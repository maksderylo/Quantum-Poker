package org.redfx.waithere;
import java.util.ArrayList;
import java.util.Arrays;
public class Cards{


    ArrayList<String> deck;
    //C = clubs, D = diamonds, S = spades, H = Hearts.
    //A = ace, K = king, Q = queen, J = jack.
    ArrayList<String> defaultDeck = new ArrayList<>(Arrays.asList(
                                     "CA","C2","C3","C4","C5","C6","C7","C8","C9","C10","CJ","CQ","CK",
                                     "DA","D2","D3","D4","D5","D6","D7","D8","D9","D10","DJ","DQ","DK",
                                     "SA","S2","S3","S4","S5","S6","S7","S8","S9","S10","SJ","SQ","SK",
                                     "HA","H2","H3","H4","H5","H6","H7","H8","H9","H10","HJ","HQ","HK"));
        
    //creating a new List that we will copy the default deck to and then shuffle it and remove
    // cards from when drawn.
    public Cards(){
        this.deck = defaultDeck;
    }
     ArrayList<String> currentShuffledDeck;  
    
    public void shuffle(){
        long seed = System.nanoTime();
        //currentShuffledDeck = deck;
        java.util.Collections.shuffle(this.deck, new java.util.Random(seed));
    }

    public String deal(){ //return the top card and remove it from the deck
        String current = currentShuffledDeck.get(0);
        currentShuffledDeck.remove(0);
        return current;
    }
}