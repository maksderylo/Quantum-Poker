
public class Round {

    //TODO are we using infinite bets or a set one? we should change currentBets starting value accordingly
    int currentBet;
    int pool;
    int checkIndex;
    Player[] Players;

    public Round(int index, Player[] p){
        Player[] Players = p;
        Cards deckOfCards = new Cards();
        deckOfCards.shuffle();

        for (Player player : p){ //deal two cards to each person and update their hand with them.
            player.updateHand(deckOfCards.deal());
            player.updateHand(deckOfCards.deal());
        }

        int b = 0;
        int startingIndex = 0; 
        int j = 0;
        while(b < 3){ //checking to see if all of the values have been assigned
            while(index + b < amountOfPlayers){ //checking to avoid throwing outOfBounds
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
            j++; //new variable so that the nested while loop doesnt run again
            b++;
        }

        //TODO implement this actually and the right way(not with scanner and changing somehting
        // to point to the button press)
        //TODO implement when it goes back to the beginning c = startingIndex - 1. and then after c = amountOfPlayers - 1, c = 0;
        
        Players[startingIndex].action(scanner.nextInt()); //small blind
        currentBet = scanner.nextInt();
        Players[startingIndex + 1].action(currentBet); //big blind
        for (int c = startingIndex + 2; c<amountOfPlayers; c++){
            if  (something.equals("call")){
            Players[c].action(currentBet);
            pool += currentBet;
            }   else if (something.equals("raise")){
                currentBet = scanner.nextInt();
                Players[c].action(currentBet);
                pool += currentBet;
            }   else if (something.equals("fold")){
                Players[c].folded = true;
                amountFolded++;
            }
        }
        currentBet = 0; //in order to allow checks
        if (P.length == 1){
            P[0].balance += pool;
            pool = 0;
        }
        else{   //TODO: GUI show flop
            for (int c = startingIndex ; c<amountOfPlayers; c++){
                if (currentBet == 0 && something.equals("check")){//shouldnt have to have a && here and the button should dissapear if currentBet!=0
                    checkIndex = c;
                }   else if  (something.equals("call")){
                    Players[c].action(currentBet);
                    pool += currentBet;
                }   else if (something.equals("raise")){
                        currentBet = scanner.nextInt();
                        Players[c].action(currentBet);
                        pool += currentBet;
                }   else if (something.equals("fold")){
                        Players[c].folded = true;
                        amountFolded++;
                }
            
            }   
        }
    }
}
