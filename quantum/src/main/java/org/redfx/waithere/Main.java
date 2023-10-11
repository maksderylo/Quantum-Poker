package org.redfx.waithere;
import java.awt.*;
import javax.swing.*;

public class Main {

    void testGame(){

        int numberOfRounds = 0;
        int amountFolded = 0;
        //TODO: have to Implement from infoscreen.
        int amountOfPlayers = 4; 
        int startingBalance = 100;
        
        //Array of players in order to call each one.
        Player[] Players = new Player[amountOfPlayers];
        
        for (int i = 0; i < amountOfPlayers; i++){
            //loop through each person and have them type in their name on the info screen
            //Players[i] = new Player(scanner.nextString(), startingBalance);
        }

       //TODO: at this point there should be checked if everyone still has money, then a new rounds starts:
        /*  
        do{
        int index = numberOfRounds%amountOfPlayers; //Deciding who is the dealer, who is the small/big blind
        Round roundOne = new Round(index, Players);
        numberOfRounds++;
        
        if (index < amountOfPlayers - 1){
            index++;
        }   else{
            index = 0;
        }
        }
        while(amountFolded < amountOfPlayers - 1);
        **/

}
    public static void main(String[] args) {
        //Phase 1

    }
}