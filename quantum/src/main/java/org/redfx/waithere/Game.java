package org.redfx.waithere;

public class Game {

    Player[] Players;
    int startingBalance;

    public Game(int p, int s){

        int numberOfRounds = 0;
        int amountFolded = 0;
        //TODO: have to Implement from infoscreen.
        int amountOfPlayers = p; 
        startingBalance = s;
        
        //Array of players in order to call each one.
        Players = new Player[amountOfPlayers];
        
        
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
    //the actionlistener for the text field will create an array with names, then when next is clicked game.assign(arrayOfNames)\
    //is called and each player in Players should be initialized with their name and the starting balance.
    void assign(String[] n){
        for (int i = 0; i < n.length; i++){
            Players[i] = new Player(n[i], this.startingBalance);
        }
    }

}