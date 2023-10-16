package org.redfx;

import org.redfx.Objects.*;

public class Game {

    public Player[] Players;
    int startingBalance;
    public int numberOfRounds;
    public int amountFolded = 0;
    public int amountOfPlayers;
    StateManager stateManager;

    public Game(int playerAmount, int startingBalance, String[] playerNames, StateManager stateManager){ //initialises the game upon being called
        this.stateManager = stateManager;
        
        //TODO: have to Implement from infoscreen.
        amountOfPlayers = playerAmount; 
        this.startingBalance = startingBalance;
        
        //Array of players in order to call each one.
        Players = new Player[amountOfPlayers];
        
        
        for (int i = 0; i < amountOfPlayers; i++){
            //loop through each person and have them type in their name on the info screen
            Players[i] = new Player(playerNames[i], startingBalance);
        }

        numberOfRounds = 1; 
        stateManager.switchToRoundStartScreen(this);

       
    }

    private void PokerGame() {

        

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

}