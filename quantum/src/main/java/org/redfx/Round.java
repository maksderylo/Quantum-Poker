package org.redfx;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch; 
import org.redfx.Objects.*;
import javax.swing.SwingWorker;


class customPair{
    int potsize;
    ArrayList<Integer> playersEligible;
} 

public class Round {

    //TODO are we using infinite bets or a set one? we should change currentBets starting value accordingly
    int currentBet;
    int checkIndex;
    Player[] nextPlayers;
    int startingIndex;
    int currentIndex = 0;
    int potAmount = 0;
    public int pool;
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
    public ArrayList<String> tableCards;//cards on the table
    Round round = this;
    public SwingWorker<Void, Void> worker;
    private ArrayList<customPair> pools = new ArrayList<customPair>();
    private customPair helpPool;
    Game game;
    ArrayList<Integer>thisPotPlayers = new ArrayList<>();


    public Round(/*int index, Player[] players,*/Game game, StateManager manager){
        //so what I changed here is to rather ask for just the game class and use the game.Players to be able to 
        //overwrite players and other info there, when the round is going to finish rather than having to create and call a method inside game that does that
        this.stateManager = manager;
        this.game = game;

        //assigning all the start variables for this round
        Players = game.Players;
        deck = new Deck(); //this rounds deck
        roundNumber = game.roundNumber;
        amountOfPlayers = game.amountOfPlayers;
        smallBlindIndex = game.smallBlindIndex;
        bigBlindIndex = game.bigBlindIndex;
        bigBlindAmount = game.bigBlindAmount;
        smallBlindAmount = game.smallBlindAmount;
        tableCards = new ArrayList<>();
        potAmount=0;
        pool =smallBlindAmount+bigBlindAmount;
        
        
        //automatic bets for big and small blind indexes
        Players[bigBlindIndex].currentBet = bigBlindAmount;
        Players[bigBlindIndex].balance -=bigBlindAmount;
        Players[smallBlindIndex].currentBet = smallBlindAmount;
        Players[smallBlindIndex].balance-=smallBlindAmount;

        System.out.println("Pool " + pool + "smallBlind: " + smallBlindIndex+ " bigBlind" + bigBlindIndex);
        for(Player player : Players){
            System.out.println(player.name +" money: " + player.balance + " folded: " + player.folded);
        }

        //calling to display the start screen
        stateManager.switchToRoundStartScreen(round, "Preflop");
    }

    public void FirstBets(){ //called from roundStartScreen
        for (Player player : Players){ //deal two cards to each person and update their hand with them.
            if(!player.folded){ // only if player hasn't folded - we can make it that at the end of the round if the player has enough money the player gets unfolded
            //and when creating the players they are initially unfolded meaning folded = false;
            player.updateHand(deck.deal());
            player.updateHand(deck.deal());
            player.madeDecision = false;
            }
        }
        largestbet = bigBlindAmount; //variable used to check if all the players have placed the same bet

        
        //doing the bets for small and big blinds

        
        nowBettingPlayerIndex = findNextAbleToBetPlayer(bigBlindIndex);
        
      

        
        worker = new SwingWorker<Void, Void>() {
            private volatile boolean sameBets = false;

            @Override
            protected Void doInBackground() throws Exception {
                while(!sameBets){
                stateManager.switchToChangeToPlayerScreen(Round.this);
                synchronized(this) {
                    wait(); // wait here until notified
                }

                if(!checkForEnoughPlayers()){
                    break;
                }
                sameBets = true;
                for (Player player : Players){
                    if(!player.folded && (player.currentBet != largestbet || !player.madeDecision) && !player.allIn){
                        sameBets = false;
                    } 

                }
                nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);
            }

            updatePools();
           


            if(checkForEnoughPlayers()){
                for(int i=0;i<3;i++){
                tableCards.add(deck.deal());
                }
                stateManager.switchToRoundStartScreen(round, "The flop");
            } else{
                prematureWinner();
            }
                
                return null;
            }

            
        };
        worker.execute();

        }

        public void secondBettingRound(){
            
            //new table and dealing cards there!
            

            largestbet = 0;

            for (Player player : Players){
                if(!player.folded){
                    player.madeDecision = false;
                    player.currentBet = 0;
                }
            }

            nowBettingPlayerIndex = smallBlindIndex;
            if(Players[nowBettingPlayerIndex].folded){
                nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);
            }
            worker = new SwingWorker<Void, Void>() {
                private volatile boolean ableToProceed = false;

                @Override
                protected Void doInBackground() throws Exception {
                    while(!ableToProceed){
                    if(!checkForEnoughPlayers()){
                        break;
                    }
                    if(Players[nowBettingPlayerIndex].allIn){

                    }
                    else{
                    stateManager.switchToChangeToPlayerScreen(Round.this);
                    synchronized(this) {
                        wait(); // wait here until notified
                    }

                }

                    ableToProceed = true;
                    for (Player player : Players){
                        if(!player.folded && !player.allIn && (player.currentBet != largestbet || !player.madeDecision)){
                            ableToProceed = false;
                        } 

                    }
                    

                    nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);

                }

                updatePools();

                if(checkForEnoughPlayers()){
                    //deal another card
                    tableCards.add(deck.deal());
                    stateManager.switchToRoundStartScreen(round, "The turn");
                } else{ //determine the winner 
                    prematureWinner();
                }
                    
                    return null;
                }

                
            };
            worker.execute();
        }

        public void thirdBettingRound(){
            largestbet = 0;

            for (Player player : Players){
                if(!player.folded){
                    player.madeDecision = false;
                    player.currentBet = 0;
                }
            }

            nowBettingPlayerIndex = smallBlindIndex;
            if(Players[nowBettingPlayerIndex].folded){
                nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);
            }
            worker = new SwingWorker<Void, Void>() {
                private volatile boolean ableToProceed = false;


                @Override
                protected Void doInBackground() throws Exception {
                    
                    while(!ableToProceed){

                        if(!checkForEnoughPlayers()){
                            break;
                        }
                    
                    if(Players[nowBettingPlayerIndex].allIn){
                        
                    }
                    else{
                    stateManager.switchToChangeToPlayerScreen(Round.this);
                    synchronized(this) {
                        wait(); // wait here until notified
                    }
                    }

                    ableToProceed = true;
                    for (Player player : Players){
                        if(!player.folded && !player.allIn && (player.currentBet != largestbet || !player.madeDecision)){
                            ableToProceed = false;
                        } 

                    }
                    nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);

                }

                updatePools();

                if(checkForEnoughPlayers()){
                    //deal another card
                    tableCards.add(deck.deal());
                    stateManager.switchToRoundStartScreen(round, "The river");
                } else{ //determine the winner 
                    prematureWinner();
                }
                    
                    return null;
                }

                
            };
            worker.execute();
        }

        public void forthBettingRound(){
            largestbet = 0;

            for (Player player : Players){
                if(!player.folded){
                    player.madeDecision = false;
                    player.currentBet = 0;
                }
            }

            nowBettingPlayerIndex = smallBlindIndex;
            if(Players[nowBettingPlayerIndex].folded){
                nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);
            }
            worker = new SwingWorker<Void, Void>() {
                private volatile boolean ableToProceed = false;


                @Override
                protected Void doInBackground() throws Exception {
                    while(!ableToProceed){

                    if(!checkForEnoughPlayers()){
                            break;
                        }

                    if(Players[nowBettingPlayerIndex].allIn){
                        
                    }
                    else{
                    stateManager.switchToChangeToPlayerScreen(Round.this);
                    synchronized(this) {
                        wait(); // wait here until notified
                    }
                }
                    ableToProceed = true;
                    for (Player player : Players){
                        if(!player.folded && !player.allIn && (player.currentBet != largestbet || !player.madeDecision)){
                            ableToProceed = false;
                        } 

                    }
                    nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);

                }

                if(checkForEnoughPlayers()){
                    distributePots();
                } else{ //determine the winner by last one standing
                    prematureWinner();
                }
                    
                    return null;
                }

                
            };
            worker.execute();

            updatePools();
            //TODO create the last pool with the remaining players


        }



        private void prematureWinner(){
            //find premature winner
            int winnerIndex =0;

            for (Player player : Players){
                if(!player.folded || player.allIn){
                    break;
                } 
                winnerIndex++;
            }


            //give all the money to the remaining player
            Players[winnerIndex].balance+=potAmount;
            potAmount=0;
            for(int i =0;i <pools.size(); i++){
                Players[winnerIndex].balance+=pools.get(i).potsize;
            }

            endRound(winnerIndex);


        }

        private void distributePots() {

            //create the last pool
            thisPotPlayers = new ArrayList<Integer>();
            for(int i = 0; i < amountOfPlayers; i++){
                if(!Players[i].folded){
                    thisPotPlayers.add(i);//this player is eligible for this pot
                }
            }
            helpPool = new customPair();
            helpPool.playersEligible = thisPotPlayers;
            helpPool.potsize = potAmount;
            pools.add(helpPool);
            potAmount=0;



            //
            System.out.println("Pools amount: " + pools.size());


            //rate the cards of all players   player.bestHand(tableCards);

            int currentHighestScore;
            Integer highestScoreIndex = -1;
            ArrayList<Integer> playersEligible = new ArrayList<Integer>();

            for(int i = 0; i < pools.size(); i++) { //distributing for all created pools
                System.out.println("Inside pool  " + pools.get(i).potsize + " ");

                
                helpPool = pools.get(i); //variable that stores the pool(money and players eligible to win it) that is currently distributed
                currentHighestScore = 0;
                playersEligible = helpPool.playersEligible;
                System.out.println("players eligible size  " + playersEligible.size());

                for(int j = 0; j < playersEligible.size();j++) {           
                    System.out.println(Players[playersEligible.get(j)].name + " has a score of: " +Players[playersEligible.get(j)].bestHand(tableCards));
         
                    if(Players[playersEligible.get(j)].bestHand(tableCards) > currentHighestScore) {
                        System.out.println("?");
                        currentHighestScore = Players[playersEligible.get(j)].bestHand(tableCards);
                        highestScoreIndex = playersEligible.get(j);
                    }
                    if(Players[playersEligible.get(j)].bestHand(tableCards) == currentHighestScore) {
                        
                    }

                }
                System.out.println("winner: "+highestScoreIndex + " with a score of: " + currentHighestScore);
                Players[highestScoreIndex].balance += helpPool.potsize;


                //determine the winner of this pool



            }
            endRound(highestScoreIndex);

        }

    
        private void endRound(int winnerIndex){ // winner index is the one that has won the main pot

            int enoughPlayers = 0;

            for(Player player : Players){
                player.role=0;
                if(player.balance < bigBlindAmount){ //eliminate players
                    player.outOfTheGame = true;
                    player.allIn=false;
                    player.folded=true;
                    player.currentBet=0;
                }
                else{
                    enoughPlayers++;
                    player.outOfTheGame = false;
                    player.allIn=false;
                    player.folded=false;
                    player.currentBet=0;
                }
            }


            //TODO find new small and big blinds

            game.smallBlindIndex=game.bigBlindIndex;
            game.bigBlindIndex=findNextAbleToBetPlayer(smallBlindIndex);
            

            if(enoughPlayers>1){
                game.smallBlindIndex=game.bigBlindIndex;
                game.bigBlindIndex=findNextAbleToBetPlayer(game.smallBlindIndex);
                Players[game.smallBlindIndex].role = 1;
                Players[game.bigBlindIndex].role = 2;
                game.Players = Players;
                game.NewRound();
            }
            else{
                //TODO CREATE END GAME SCREEN
            }

            //TODO reset the variables, check if the game should go on etc.

        }




        private boolean checkForEnoughPlayers(){
            int notFoldedPlayers=0;
            for (Player player : Players){
                if(!player.folded||player.allIn){
                    notFoldedPlayers++;
                } 
            }

            if(notFoldedPlayers>1){
                return true;
            }

            return false;
        }




        

        int findNextAbleToBetPlayer(int nowBettingPlayerIndex){
            nowBettingPlayerIndex++;
            while(true){
                if(nowBettingPlayerIndex == amountOfPlayers){
                    nowBettingPlayerIndex = 0;
                }

                if(!Players[nowBettingPlayerIndex].folded || Players[nowBettingPlayerIndex].allIn){
                    break;
                }
                nowBettingPlayerIndex++;
            }

            return nowBettingPlayerIndex;
        }

        void updatePools(){
            Integer lowestAllInAmount=999999999;
            Boolean allInPlayers = true;
            



            while(allInPlayers) {
                allInPlayers = false;
                for(int i=0;i< amountOfPlayers;i++) {
                    Player player = Players[i];
                    if(player.allIn && !player.folded) {
                        allInPlayers=true;
                        if(player.currentBet<lowestAllInAmount){
                            lowestAllInAmount=player.currentBet;
                        }
                    }
                }

                
                if(allInPlayers){
                    thisPotPlayers = new ArrayList<Integer>();
                    for(int i = 0; i < amountOfPlayers; i++){
                        if(!Players[i].folded){

                            thisPotPlayers.add(i);//this player is eligible for this pot
                            Players[i].currentBet-=lowestAllInAmount;
                            potAmount+=lowestAllInAmount;
                            pool+=lowestAllInAmount;
                            if(Players[i].currentBet==0 && Players[i].allIn){
                                
                                Players[i].folded=true;
                            }
                        }
                    }
                                                

                    helpPool = new customPair();
                    helpPool.playersEligible = thisPotPlayers;
                    helpPool.potsize = potAmount;

                    pools.add(helpPool);

                    potAmount=0;
                } else{ //add all rest to the pot
                    for(int i = 0; i < amountOfPlayers; i++){
                        potAmount+=Players[i].currentBet;
                        Players[i].currentBet = 0;

                    }
                }


            }

        System.out.println("PotAmount: " + potAmount);

        }

    
}

