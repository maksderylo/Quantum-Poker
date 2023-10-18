package org.redfx;

import org.redfx.Objects.*;

public class Game {

    public Player[] Players;
    int startingBalance;
    public int roundNumber = 0;
    public int amountFolded = 0;
    public int amountOfPlayers;
    public int smallBlindIndex; //this variable is useful for round to imidiately see where the small blind is
    public int bigBlindIndex; //this variable is useful for round to imidiately see where the big blind is
    StateManager stateManager;
    public int smallBlindAmount; //TODO we didn't give the user the choice to pick these amounts by themselves so we need to make a way of calculating them.
    public int bigBlindAmount;



    public Game(int playerAmount, int startingBalance, String[] playerNames, StateManager stateManager){ //initialises the game upon being called
        //TODO we have to assign the small and big blinds amounts here;

        amountOfPlayers = playerAmount; 
        this.startingBalance = startingBalance;
        this.stateManager = stateManager;
        
        //Array of players in order to call each one.
        Players = new Player[amountOfPlayers];
        
        
        for (int i = 0; i < amountOfPlayers; i++){
            //loop through each person and have them type in their name on the info screen
            Players[i] = new Player(playerNames[i], startingBalance);
        }

        //initial small and big blind
        Players[0].role = 1;
        Players[1].role = 2;
        smallBlindIndex = 0;
        bigBlindIndex = 1;
        smallBlindAmount = (int)(startingBalance * 0.01);
        bigBlindAmount = (int)(startingBalance * 0.02);

        for(int i = 2 ; i < amountOfPlayers; i++){
            Players[i].role = 0;
        }


        
        NewRound(); //calling a method that starts the first round


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

    public void NewRound() { //this will be called from inside the round when it ends after checking if there are enough players with money
        roundNumber++;
        new Round(this, stateManager);
    }


}