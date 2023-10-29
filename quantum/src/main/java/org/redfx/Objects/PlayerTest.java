package org.redfx.Objects;

import java.util.ArrayList;
import java.util.Arrays;

/**A class to test various methods and fix errors. */
public class PlayerTest {

    ArrayList<String> tableCards = new ArrayList<>(Arrays.asList(
        "Ace Spades", "King Diamonds", "5 Spades", "2 Clubs", "7 Clubs"));
    ArrayList<String> playerHand1 = new ArrayList<>(Arrays.asList("9 Clubs", "2 Diamonds"));
    ArrayList<String> playerHand2 = new ArrayList<>(Arrays.asList("7 Diamonds", "Queen Hearts"));
    Player player1 = new Player(null, 0);
    Player player2 = new Player(null, 0);

    void run() {
        player1.hand = playerHand1;
        player2.hand = playerHand2;

    }
    
    public static void main(String[] args) {
        new PlayerTest().run();

    }


}