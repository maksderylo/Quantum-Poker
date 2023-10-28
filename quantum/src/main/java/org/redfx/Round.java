package org.redfx;

import java.util.ArrayList;
import javax.swing.SwingWorker;
import org.redfx.Objects.Deck;
import org.redfx.Objects.GatesDeck;
import org.redfx.Objects.Player;

class CustomPair {
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
    public Player[] players;
    public int amountOfPlayers;
    int smallBlindIndex;
    public int bigBlindIndex;
    int smallBlindAmount;
    int bigBlindAmount;
    Deck deck;
    GatesDeck gatesDeck;
    public int roundNumber;
    public StateManager stateManager;
    public int nowBettingPlayerIndex;
    public int largestbet;
    public ArrayList<String> tableCards; //cards on the table
    Round round = this;
    public SwingWorker<Void, Void> worker;
    private ArrayList<CustomPair> pools = new ArrayList<CustomPair>();
    private CustomPair helpPool;
    Game game;
    ArrayList<Integer> thisPotPlayers = new ArrayList<>();
    public boolean quantum;
	public ArrayList<Character> table;

    public Round(Game game, StateManager manager, boolean quantum) {
        this.quantum = quantum;
        /*so what I changed here is to rather ask for just the game class and use the game.Players 
        to be able to overwrite players and other info there, when the round is going to finish 
        rather than having to create and call a method inside game that does that */
        this.stateManager = manager;
        this.game = game;

        //assigning all the start variables for this round
        players = game.players;
        deck = new Deck(quantum); //this rounds deck
        if (quantum) {
            gatesDeck = new GatesDeck(); 
        }
        roundNumber = game.roundNumber;
        amountOfPlayers = game.amountOfPlayers;
        smallBlindIndex = game.smallBlindIndex;
        bigBlindIndex = game.bigBlindIndex;
        bigBlindAmount = game.bigBlindAmount;
        smallBlindAmount = game.smallBlindAmount;
        tableCards = new ArrayList<>();
        potAmount = 0;
        pool = smallBlindAmount + bigBlindAmount;

        for (Player player : players) {
            player.roundStartBalance = player.balance;
            player.hand.clear();
        }
        
        
        //automatic bets for big and small blind indexes
        players[bigBlindIndex].currentBet = bigBlindAmount;
        players[bigBlindIndex].balance -= bigBlindAmount;
        players[smallBlindIndex].currentBet = smallBlindAmount;
        players[smallBlindIndex].balance -= smallBlindAmount;

        System.out.println("Pool " + pool + "smallBlind: " 
            + smallBlindIndex + " bigBlind" + bigBlindIndex);
        for (Player player : players) {
            System.out.println(player.name + " money: " 
                + player.balance + " folded: " + player.folded);
        }

        //calling to display the start screen
        stateManager.switchToRoundStartScreen(round, "Preflop", quantum);
    }

    /**A method called from roundStartScreen to give each player two cards, 
     * and collecting the small and big blind bets.
    */
    public void firstBets() { 
        for (Player player : players) { 
            //deal two cards to each person and update their hand with them.
            if (!player.folded) { 
                /* only if player hasn't folded - we can make it that at the end of the round if 
                the player has enough money the player gets unfolded
                and when creating the players they are initially unfolded meaning folded = false;*/
                if (!quantum) {
                    player.updateHand(deck.deal());
                    player.updateHand(deck.deal());
                } else {
                    player.updateHand(gatesDeck.deal());
                    player.updateHand(gatesDeck.deal());
                    player.updateHand(gatesDeck.deal());
                    player.updateHand(gatesDeck.deal());
                }
                player.madeDecision = false;
            }
        }
        largestbet = bigBlindAmount; 
        //variable used to check if all the players have placed the same bet

        //doing the bets for small and big blinds
        nowBettingPlayerIndex = findNextAbleToBetPlayer(bigBlindIndex);
        
        worker = new SwingWorker<Void, Void>() {
            private volatile boolean sameBets = false;

            @Override
            protected Void doInBackground() throws Exception {
                while (!sameBets) {
                    stateManager.switchToChangeToPlayerScreen(Round.this);
                    synchronized (this) {
                        wait(); // wait here until notified
                    }

                    if (!checkForEnoughPlayers()) {
                        break;
                    }
                    sameBets = true;
                    for (Player player : players) {
                        if (!player.folded && (player.currentBet != largestbet 
                            || !player.madeDecision) && !player.allIn) {
                            sameBets = false;
                        } 

                    }
                    nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);
                }

                updatePools();
            


                if (checkForEnoughPlayers()) {
                    for (int i = 0; i < 3; i++) {
                        tableCards.add(deck.deal());
                    }
                    stateManager.switchToRoundStartScreen(round, "The flop", quantum);
                } else {
                    prematureWinner();
                }
                
                return null;
            }

            
        };
        worker.execute();

    }

    /** */
    public void secondBettingRound() {
            
        //new table and dealing cards there!
            

        largestbet = 0;

        for (Player player : players) {
            player.madeDecision = false;
            player.currentBet = 0;
        }

        nowBettingPlayerIndex = smallBlindIndex;
        if (players[nowBettingPlayerIndex].folded) {
            nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);
        }
        worker = new SwingWorker<Void, Void>() {
            private volatile boolean ableToProceed = false;

            @Override
            protected Void doInBackground() throws Exception {
                while (!ableToProceed) {
                    if (!checkForEnoughPlayers()) {
                        break;
                    }
                    if (players[nowBettingPlayerIndex].allIn) {
                        //TODO: ADD SOMETHING HERE
                    } else {
                        stateManager.switchToChangeToPlayerScreen(Round.this);
                        synchronized (this) {
                            wait(); // wait here until notified
                        }

                    }

                    ableToProceed = true;
                    for (Player player : players) {
                        if (!player.folded && !player.allIn && (player.currentBet != largestbet 
                            || !player.madeDecision)) {
                            ableToProceed = false;
                        } 

                    }
                    

                    nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);

                }

                updatePools();

                if (checkForEnoughPlayers()) {
                    //deal another card
                    tableCards.add(deck.deal());
                    stateManager.switchToRoundStartScreen(round, "The turn", quantum);
                } else { //determine the winner 
                    prematureWinner();
                }
                
                return null;
            }

            
        };
        worker.execute();
    }

    /** */
    public void thirdBettingRound() {
        largestbet = 0;

        for (Player player : players) {
            player.madeDecision = false;
            player.currentBet = 0;
            
        }

        nowBettingPlayerIndex = smallBlindIndex;
        if (players[nowBettingPlayerIndex].folded) {
            nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);
        }
        worker = new SwingWorker<Void, Void>() {
            private volatile boolean ableToProceed = false;

                @Override
                protected Void doInBackground() throws Exception {
                    
                    while (!ableToProceed) {

                        if (!checkForEnoughPlayers()) {
                            break;
                        }
                    
                    if (players[nowBettingPlayerIndex].allIn) {
                        //TODO: ADD SOMETHING HERE
                    } else {
                        stateManager.switchToChangeToPlayerScreen(Round.this);
                        synchronized (this) {
                            wait(); // wait here until notified
                    }
                    }

                    ableToProceed = true;
                    for (Player player : players) {
                        if (!player.folded && !player.allIn && (player.currentBet != largestbet 
                            || !player.madeDecision)) {
                            ableToProceed = false;
                        } 

                    }
                    nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);

                }

                updatePools();

                if (checkForEnoughPlayers()) {
                    //deal another card
                    tableCards.add(deck.deal());
                    stateManager.switchToRoundStartScreen(round, "The river", quantum);
                } else { //determine the winner 
                    prematureWinner();
                }
                    
                    return null;
                }

                
            };
        worker.execute();
    }

    /** */
    public void fourthBettingRound() {
        largestbet = 0;

        for (Player player : players) {
            player.madeDecision = false;
            player.currentBet = 0;

        }

        nowBettingPlayerIndex = smallBlindIndex;
        if (players[nowBettingPlayerIndex].folded) {
            nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);
        }
        worker = new SwingWorker<Void, Void>() {
                private volatile boolean ableToProceed = false;


                @Override
                protected Void doInBackground() throws Exception {
                    while (!ableToProceed) {

                    if (!checkForEnoughPlayers()) {
                            break;
                        }

                    if (players[nowBettingPlayerIndex].allIn) {
                        //TODO: ADD SOMETHING HERE
                    } else {
                        stateManager.switchToChangeToPlayerScreen(Round.this);
                        synchronized (this) {
                            wait(); // wait here until notified
                        }
                    }
                    ableToProceed = true;
                    for (Player player : players) {
                        if (!player.folded && !player.allIn && (player.currentBet != largestbet 
                            || !player.madeDecision)) {
                            ableToProceed = false;
                        } 

                    }
                    nowBettingPlayerIndex = findNextAbleToBetPlayer(nowBettingPlayerIndex);

                }

                if (checkForEnoughPlayers()) {
                    distributePots();
                } else { //determine the winner by last one standing
                    prematureWinner();
                }
                    
                    return null;
                }

                
            };
        worker.execute();

        updatePools();
    }

    private void prematureWinner() {
        System.out.println("premature winner");
        //find premature winner
        int winnerIndex  = 0;

        for (Player player : players) {
            if (!player.folded || player.allIn) {
                break;
            } 
            winnerIndex++;
        }


        //give all the money to the remaining player
        players[winnerIndex].balance += potAmount;
        potAmount = 0;
        for (int i = 0; i < pools.size(); i++) {
            players[winnerIndex].balance += pools.get(i).potsize;

        }

        stateManager.switchToRoundEndScreen(this);


    }

    /** */
    private void distributePots() {
        System.out.println("distributing pots");

        if (quantum) {
            for (int i = 0; i < amountOfPlayers; i++) {
                if (!players[i].folded || players[i].allIn) {
                    worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            
                            return null;
                        }

                        
                    };
                    worker.execute();
                }
            }
        }

        //create the last pool
        thisPotPlayers = new ArrayList<Integer>();
        for (int i = 0; i < amountOfPlayers; i++) {
            if (!players[i].folded) {
                thisPotPlayers.add(i); //this player is eligible for this pot
            }
        }
        if (!thisPotPlayers.isEmpty()) {
            helpPool = new CustomPair();
            helpPool.playersEligible = thisPotPlayers;
            helpPool.potsize = potAmount;
            pools.add(helpPool);
            potAmount = 0;
        }
        System.out.println("Pools amount: " + pools.size());

        //rate the cards of all players   player.bestHand(tableCards);

        int currentHighestScore;
        int currentScore;
        Integer highestScoreIndex = -1;
        ArrayList<Integer> playersEligible = new ArrayList<Integer>();
        ArrayList<Integer> winners = new ArrayList<Integer>();
        Player currentPlayer;
        for (int i = 0; i < pools.size(); i++) { //distributing for all created pools
            System.out.println("Inside pool  " + pools.get(i).potsize + " ");

            
            helpPool = pools.get(i); /*variable that stores the pool
            (money and players eligible to win it) that is currently distributed*/
            currentHighestScore = 0;
            playersEligible = helpPool.playersEligible;
            System.out.println("players eligible size  " + playersEligible.size());

            for (int j = 0; j < playersEligible.size(); j++) {
                currentPlayer = players[playersEligible.get(j)];
                System.out.println(currentPlayer.name + ":");
                if (!quantum) {
                    currentScore = currentPlayer.bestHand(tableCards);
                } else {
                    currentScore = currentPlayer.score;
                }
                System.out.println(currentPlayer.name + " has a score of: " + currentScore);
        
                if (currentScore > currentHighestScore) {
                    currentHighestScore = currentScore;
                    winners = new ArrayList<Integer>();
                    winners.add(playersEligible.get(j));
                    highestScoreIndex = playersEligible.get(j);
                } else if (currentScore == currentHighestScore) {
                    if (currentPlayer.highestCard(currentPlayer.currentHand) 
                        > players[highestScoreIndex].highestCard(
                        players[highestScoreIndex].currentHand)) {

                        winners = new ArrayList<Integer>();
                        System.out.println("equal scores");
                        winners.add(playersEligible.get(j));
                    } else if (players[highestScoreIndex].highestCard(players
                        [highestScoreIndex].currentHand) == currentPlayer
                        .highestCard(currentPlayer.currentHand)) {
                        winners.add(playersEligible.get(j));
                    } 
                }
                System.out.println("?");

            }
            for (int j = 0; j < winners.size(); j++) {
                players[winners.get(j)].balance += helpPool.potsize / winners.size();
            }
        }



        System.out.println("call next round");
        stateManager.switchToRoundEndScreen(this);
    }

    /** */
    public void endRound() { // winner index is the one that has won the main pot
        System.out.println("ending round");

        int enoughPlayers = 0;
        for (Player player : players) {
            player.role = 0;
            if (player.balance < bigBlindAmount) { //eliminate players
                player.outOfTheGame = true;
                player.allIn = false;
                player.folded = true;
                player.currentBet = 0;
            } else {
                enoughPlayers++;
                player.outOfTheGame = false;
                player.allIn = false;
                player.folded = false;
                player.currentBet = 0;
            }
        }


            
            

        if (enoughPlayers > 1) {

            //locating new small and big blind indexes
            int index = smallBlindIndex;
            while (true) {
                index++;
                if (index == game.amountOfPlayers) {
                    index = 0;
                }
                if (!players[index].folded) {
                    break;
                }
            }
            game.smallBlindIndex = index;
            while (true) {
                index++;
                if (index == game.amountOfPlayers) {
                    index = 0;
                }
                if (!players[index].folded) {
                    break;
                }
            }
            game.bigBlindIndex = index;

            //reseting the roles
            for (Player player : players) {
                player.role = 0;
            }



            //setting the new roles
            players[game.smallBlindIndex].role = 1;
            players[game.bigBlindIndex].role = 2;
            game.players = players;
            game.newRound();
        } else {
            Player winner = new Player(null, 0);
            for (Player player: players) {
                if (!player.outOfTheGame) {
                    winner = player;
                    break;
                }
            }
            stateManager.switchToGameEndScreen(winner, stateManager);
        }


    }




    private boolean checkForEnoughPlayers() {
        int notFoldedPlayers = 0;
        for (Player player : players) {
            if (!player.folded || player.allIn) {
                notFoldedPlayers++;
            } 
        }

        if (notFoldedPlayers > 1) {
            return true;
        }

        return false;
    }




        
    /**A method checking for the next player that hasn't folded.
     * 
     * @param nowBettingPlayerIndex is the index of the player whose turn it was
     * @return is the index of the next avaliable player
     */
    int findNextAbleToBetPlayer(int nowBettingPlayerIndex) {
        nowBettingPlayerIndex++;
        while (true) {
            if (nowBettingPlayerIndex == amountOfPlayers) {
                nowBettingPlayerIndex = 0;
            }

            if (!players[nowBettingPlayerIndex].folded || players[nowBettingPlayerIndex].allIn) {
                break;
            }
            nowBettingPlayerIndex++;
        }

        return nowBettingPlayerIndex;
    }

    /**A method to update all of the pools with the correct amount of money and participants. */
    void updatePools() {
        Integer lowestAllInAmount = 999999999;
        Boolean allInPlayers = true;
        



        while (allInPlayers) {
            lowestAllInAmount = 999999999;
            allInPlayers = false;
            for (int i = 0; i < amountOfPlayers; i++) {
                Player player = players[i];
                if (player.allIn && !player.folded) {
                    allInPlayers = true;
                    if (player.currentBet < lowestAllInAmount) {
                        lowestAllInAmount = player.currentBet;
                    }
                }
            }

            
            if (allInPlayers) {
                thisPotPlayers = new ArrayList<Integer>();
                for (int i = 0; i < amountOfPlayers; i++) {
                    if (!players[i].folded) {

                        thisPotPlayers.add(i); //this player is eligible for this pot
                        players[i].currentBet -= lowestAllInAmount;
                        potAmount += lowestAllInAmount;
                        if (players[i].currentBet == 0 && players[i].allIn) {
                            
                            players[i].folded = true;
                        }
                    }
                }
                                            

                helpPool = new CustomPair();
                helpPool.playersEligible = thisPotPlayers;
                helpPool.potsize = potAmount;

                pools.add(helpPool);

                potAmount = 0;
            } else { //add all rest to the pot
                for (int i = 0; i < amountOfPlayers; i++) {
                    potAmount += players[i].currentBet;
                    players[i].currentBet = 0;
                }
            }
        }
        System.out.println("PotAmount: " + potAmount);
    }
}