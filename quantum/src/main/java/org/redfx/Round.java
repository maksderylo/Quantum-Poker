package org.redfx;
import java.util.concurrent.CountDownLatch; 
import org.redfx.Objects.*;
import javax.swing.SwingWorker;


public class Round {

    //TODO are we using infinite bets or a set one? we should change currentBets starting value accordingly
    int currentBet;
    int checkIndex;
    Player[] nextPlayers;
    int startingIndex;
    int currentIndex = 0;

    int pool;
    public Player[] Players;
    public int amountOfPlayers;
    int smallBlindIndex;
    public int bigBlindIndex;
    int smallBlindAmount;
    int bigBlindAmount;
    Deck deck;
    public int roundNumber;
    public StateManager stateManager;
    public int nowBettingPlayerIndex;
    public int largestbet;


    public SwingWorker<Void, Void> worker;


    public Round(/*int index, Player[] players,*/Game game, StateManager manager){
        //so what I changed here is to rather ask for just the game class and use the game.Players to be able to 
        //overwrite players and other info there, when the round is going to finish rather than having to create and call a method inside game that does that
        this.stateManager = manager;


        //assigning all the start variables for this round
        Players = game.Players;
        deck = new Deck(); //this rounds deck
        roundNumber = game.roundNumber;
        amountOfPlayers = game.amountOfPlayers;
        smallBlindIndex = game.smallBlindIndex;
        bigBlindIndex = game.bigBlindIndex;
        bigBlindAmount = game.bigBlindAmount;
        smallBlindAmount = game.smallBlindAmount;

        pool = 0;
        //calling to display the start screen
        stateManager.switchToRoundStartScreen(this);
    }

    public void FirstBets(){ //called from roundStartScreen
        for (Player player : Players){ //deal two cards to each person and update their hand with them.
            if(!player.folded){ // only if player hasn't folded - we can make it that at the end of the round if the player has enough money the player gets unfolded
            //and when creating the players they are initially unfolded meaning folded = false;
            player.updateHand(deck.deal());
            player.updateHand(deck.deal());
            player.currentBet = 0;
            
            }
        }
        largestbet = bigBlindAmount; //variable used to check if all the players have placed the same bet

        //doing the bets for small and big blinds
        pool += smallBlindAmount + bigBlindAmount;

        
        nowBettingPlayerIndex = findNextAbleToBetPlayer(bigBlindIndex);
        
        for(int i = 0;i<2;i++){

        
        worker = new SwingWorker<Void, Void>() {
            private volatile boolean notEveryoneEqual = true;


            @Override
            protected Void doInBackground() throws Exception {
                while(notEveryoneEqual){
                stateManager.switchToChangeToPlayerScreen(Round.this);
                synchronized(this) {
                    wait(); // wait here until notified
                }

                notEveryoneEqual = false;
                for (Player player : Players){
                    if(!player.folded || player.currentBet !=largestbet){
                        notEveryoneEqual = true;
                        System.out.println("yea");

                    } 
                }
                System.out.println("yea");
                nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);

            }
                
                return null;
            }

            
        };
        worker.execute();
        
    }
        
            


            // //checking if every player has put an equal bet or folded - if so break out of the infinite loop
            // boolean sameBets = true;
            // for (Player player : Players){
            //     if(!player.folded || player.currentBet !=largestbet){
            //         sameBets = false;
            //         break;
            //     } 
            // }
            // if(sameBets){
            //     break;
            // }

            // nowBettingPlayerIndex = findNextAbleToBetPlayer(bigBlindIndex);


        }



        

        int findNextAbleToBetPlayer(int nowBettingPlayerIndex){
            nowBettingPlayerIndex++;
            while(true){
                if(nowBettingPlayerIndex == amountOfPlayers){
                    nowBettingPlayerIndex = 0;
                }

                if(!Players[nowBettingPlayerIndex].folded){
                    break;
                }
                nowBettingPlayerIndex++;
            }

            return nowBettingPlayerIndex;
        }
    

        /* 
        

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
    }*/
}

