package org.redfx.Objects;

import java.util.ArrayList;
import java.util.Arrays;

/**A class for the deck containing a sorted deck and a deal method.*/
public class GatesDeck extends ArrayList<String> {

    /**Creating a new List that we will copy the default deck to and then shuffle it and remove
     * cards from when drawn.
     * C = clubs, D = diamonds, S = spades, H = Hearts.
    A = ace, K = king, Q = queen, J = jack.
    */
    ArrayList<String> defaultDeck = new ArrayList<>(Arrays.asList(
                                    "Hadamard", "Not", "ControlledNOT", "Hadamard", "Not",
                                     "ControlledNOT",
                                    "Hadamard", "Not", "ControlledNOT", "Hadamard", "Not",
                                     "ControlledNOT",
                                    "Hadamard", "Not", "ControlledNOT", "Hadamard", "Not",
                                     "ControlledNOT",
                                    "Hadamard", "Not", "ControlledNOT", "Hadamard", "Not",
                                     "ControlledNOT",
                                    "Hadamard", "Not", "ControlledNOT", "Hadamard", "Not",
                                     "ControlledNOT",
                                    "Hadamard", "Not", "ControlledNOT", "Hadamard", "Not",
                                     "ControlledNOT",
                                    "Hadamard", "Not", "ControlledNOT", "Hadamard", "Not",
                                     "ControlledNOT",
                                    "Hadamard", "Not", "ControlledNOT", "Hadamard", "Not",
                                     "ControlledNOT",
                                    "Hadamard", "Not", "ControlledNOT", "Hadamard", "Not",
                                     "ControlledNOT"));

        
    /**Constructing a new deck which is a shuffled copy of the full sorted deck. */
    public GatesDeck() {
        this.addAll(defaultDeck);
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