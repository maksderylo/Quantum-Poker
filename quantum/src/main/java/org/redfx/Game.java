package org.redfx;

import org.redfx.Objects.Player;

/**A class to itialize the game with the initial conditions. */
public class Game {

    public Player[] players;
    int startingBalance;
    public int roundNumber = 0;
    public int amountFolded = 0;
    public int amountOfPlayers;
    public int smallBlindIndex; 
    //this variable is useful for round to imidiately see where the small blind is
    public int bigBlindIndex; 
    //this variable is useful for round to imidiately see where the big blind is
    StateManager stateManager;
    public int smallBlindAmount; 
    public int bigBlindAmount;


    /**Constructing the game and then calling the first round.
     * 
     * @param playerAmount is the amount of players.
     * @param startingBalance is the amount of money the players will have
     * @param playerNames is an array with the names of the players
     * @param stateManager
     */
    public Game(int playerAmount, int startingBalance, String[] playerNames, 
        StateManager stateManager) {
        amountOfPlayers = playerAmount; 
        this.startingBalance = startingBalance;
        this.stateManager = stateManager;
        
        //Array of players in order to call each one.
        players = new Player[amountOfPlayers];
        
        
        for (int i = 0; i < amountOfPlayers; i++) {
            //loop through each person and have them type in their name on the info screen
            players[i] = new Player(playerNames[i], startingBalance);
        }

        //initial small and big blind
        players[0].role = 1;
        players[1].role = 2;
        smallBlindIndex = 0;
        bigBlindIndex = 1;
        smallBlindAmount = (int) (startingBalance * 0.01);
        bigBlindAmount = (int) (startingBalance * 0.02);

        for (int i = 2; i < amountOfPlayers; i++) {
            players[i].role = 0;
        }


        
        newRound(); //calling a method that starts the first round

    }

    /**this will be called from inside the round when it ends after checking 
    if there are enough players with money.*/
    public void newRound() { 
        roundNumber++;
        new Round(this, stateManager);
    }


}