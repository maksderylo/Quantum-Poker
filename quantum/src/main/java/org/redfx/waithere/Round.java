package org.redfx.waithere;
public class Round {

    //TODO are we using infinite bets or a set one? we should change currentBets starting value accordingly
    int currentBet;
    int pool;
    int checkIndex;
    Player[] Players;
    Player[] nextPlayers;
    int startingIndex;
    int currentIndex = 0;

    public Round(int index, Player[] p){
        Players = p;
        Cards deckOfCards = new Cards();
        deckOfCards.shuffle();

        for (Player player : p){ //deal two cards to each person and update their hand with them.
            player.updateHand(deckOfCards.deal());
            player.updateHand(deckOfCards.deal());
        }

        int b = 0;
        int j = 0;
        while(b < 3){ //checking to see if all of the values have been assigned
            while(index + b < Players.length){ //checking to avoid throwing outOfBounds
                Players[index + b].setType(1 + b);
                if (b == 1){ //the index of the small blind so the game knows where to start
                    startingIndex = index + b;
                }
                b++;
            }
            if (b == 1){ //the index of the small blind so the game knows where to start
                    startingIndex = index + b;
            }
            Players[j].setType(1 + b); 
            j++; //new variable because otherwise the nested while loop will run again
            b++;
        }

        //TODO: LOOP THROUGH PLAYERS AND GIVE THEM A CHOICE
        nextPlayers = new Player[Players.length];
        for (int i = 0; i < Players.length; i++) {
            if (Players[i].folded) {
                nextPlayers[i] = Players[i];
                Players[i] = null; //STILL DONT KNOW HOW TO IMPLMENT THAT BALANCE CARRIES OVER BUT PLAYER DOESNT TO NEXT ROUND
            }
        }
        if (Players.length == 1) {//Check if everyone but one person flopped
            Players[0].balance += pool;
            pool = 0;
        }
        else{   //TODO: GUI show flop
            for (int c = startingIndex ; c<Players.length; c++){
                
            }   
        }

    }

        
    
    
    Player[] getPlayers(){
        return Players;
    }

        //TODO should check stuff somewhere else and only call these when the buttons are pressed.
    void smallBlind(int sb){
        currentIndex = startingIndex;
        pool += sb;
        Players[currentIndex].action(sb);
        currentIndex = (currentIndex + 1) % Players.length; //to ensure it doesnt throw out of bounds
    }  

    void bigBlind(int bb) {
        pool += bb;
        Players[currentIndex].action(bb);
        this.currentBet = bb;
        currentIndex = (currentIndex + 1) % Players.length;
    }

    void call() {
        Players[currentIndex].action(this.currentBet);
        currentIndex = (currentIndex + 1) % Players.length;
        pool += currentBet;
    }

    void raise(int bet) {
        currentBet = bet;
        pool += currentBet;
        Players[currentIndex].action(currentBet);
        currentIndex = (currentIndex + 1) % Players.length;
    }

    void fold() {
        Players[currentIndex].folded = true;
        currentIndex = (currentIndex + 1) % Players.length;
    }

    void check() { 
        this.checkIndex = currentIndex;

        currentIndex = (currentIndex + 1) % Players.length;
    }
}

