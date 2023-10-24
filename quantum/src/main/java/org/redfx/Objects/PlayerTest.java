package org.redfx.Objects;



import java.util.ArrayList;
import java.util.Arrays;

public class PlayerTest {

    ArrayList<String> tableCards = new ArrayList<>(Arrays.asList("Ace Spades", "King Diamonds", "5 Spades", "2 Clubs", "7 Clubs"));
    ArrayList<String> playerHand1 = new ArrayList<>(Arrays.asList("9 Clubs", "2 Diamonds"));
    ArrayList<String> playerHand2 = new ArrayList<>(Arrays.asList("7 Diamonds", "Queen Hearts"));
    Player player1 = new Player(null, 0);
    Player player2 = new Player(null, 0);

    void run(){
        player1.hand=playerHand1;
        player2.hand=playerHand2;


        System.out.println("Player one score: " + player1.bestHand(tableCards));
        System.out.println("Player two score: " + player2.bestHand(tableCards));
        System.out.println(player2.highestCard(player2.currentHand) + "");
    }
    
    public static void main(String Args[]){
        new PlayerTest().run();

    }


}