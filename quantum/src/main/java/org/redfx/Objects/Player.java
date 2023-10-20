package org.redfx.Objects;


import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class Player {
    public String name;
    public int balance;
    public ArrayList<String> hand; 
    public int role;
    public boolean folded = false;
    public int currentBet;
    public boolean madeDecision;
    public boolean outOfTheGame = false;
    public boolean allIn = false;
    //role keeps track of if the player is the small blind(1)/big blind(2)/none(0).
    
    public Player(String name, int balance){
        this.name = name;
        this.balance = balance;
        hand = new ArrayList<String>();
    }

    public void updateHand(String card){
        hand.add(card);
    }

    public ArrayList<String> getHand(){
        return hand;
    }

    // note that we convert all the letter into numbers so 2=2 but Jack = 11 and Ace = 14
    public int rankToNumber(String s){
        char suit = s.charAt(0);
        if(suit == 'J') {
            return 11;
        }
        if(suit == 'Q') {
            return 12;
        }
        if(suit == 'K') {
            return 13;
        }
        if(suit == 'A') {
            return 14;
        }
        return (int)suit;
    }

    
    public int highestCard(ArrayList<String> bestHand){
        int highestCard = 0;
        int temp;
        for (String card : bestHand){
            temp = rankToNumber(card);
            if (temp > highestCard) {
                highestCard = temp;
            }
        }
        return highestCard;
    }

/* Point system used to compare hands:
1. Royal Flush (23) DONE
The combination of ten, jack, queen, king, ace, all of the same suit
2. Straight Flush (22) DONE
Five cards of the same suit in sequential order
3. Four of a kind (21)
Any four numerically matching cards
4. Full house (20)
Combination of three of a kind and a pair in the same hand
5. Flush (19) DONE
Five cards of the same suit, in any order
6. Straight (18)
Five cards of any suit, in sequential order
7. Three of a kind (17)
Any three numerically matching cards
8. Two pair (16)
Two different pairs in the same hand
9. One pair (15)
Any two numerically matching cards
10. High card (2-14)
*/
    public int bestHand(ArrayList<String> river){
        int currentBestHand = 0;
        //yes i know this looks bad but its just one time and i wanted it on single lines
        String CardOneRank = this.hand.get(0).substring(0, this.hand.get(0).indexOf(" "));
        int numberCardOne = rankToNumber(CardOneRank);
        String CardOneSuit = this.hand.get(0).substring(this.hand.get(0).indexOf(" "));
        String CardTwoRank = this.hand.get(1).substring(0, this.hand.get(0).indexOf(" "));
        int numberCardTwo = rankToNumber(CardTwoRank);
        String CardTwoSuit = this.hand.get(1).substring(this.hand.get(0).indexOf(" "));

        boolean[] sameSuit = new boolean[5];

        ArrayList<String> suits = new ArrayList<>();
        ArrayList<String> ranks = new ArrayList<>();
        ArrayList<Integer> numberedRanks = new ArrayList<>();


        //Separating them into suits and ranks
        //the indexes will match for each card
        for (String card : river){
            int spaceIndex = card.indexOf(" ");
            ranks.add(card.substring(0,spaceIndex));
            numberedRanks.add(rankToNumber(card.substring(0,spaceIndex)));
            suits.add(card.substring(spaceIndex + 1));
        }

        //starting by checking for the best case and side cases with this
        if (CardOneSuit.equals(CardTwoSuit)) {
            //checking for flushes, if more than two of the river have the same suit a flush is possible
            if (Collections.frequency(suits, CardTwoSuit) > 2) {
                currentBestHand = 19; // for the regular flush
                //finding which cards could make the flush
                for (int i = 0; i < suits.size(); i++) {
                    if (suits.get(i).equals(CardOneSuit)) {
                        sameSuit[i] = true;
                    }
                }
                //checking if a Straight Flush is possible by checking if the distance between them is max 4
                int distance = Math.abs(numberCardOne-numberCardTwo) - 1;
                int tillFive = 3; //since the cards in the players hand already contribute to it
                int smaller;
                int bigger;
                int biggest; //for royal flush (if straight flush and biggest is an ace then its a royal flush)
                ArrayList<Integer> indexes = new ArrayList<>();
                boolean possible = true;
                if (distance < 4) {

                    if (numberCardOne < numberCardTwo){ // two cases since they cant be equal if they are same suit
                        smaller = numberCardOne;
                        bigger = numberCardTwo;
                        biggest = numberCardTwo;
                    }   else {
                        smaller = numberCardTwo;
                        bigger = numberCardOne;
                        biggest = numberCardOne;
                    }


                        for (int i = smaller + 1; i < bigger; i++) { //checking if the cards in between them are in the river

                            if (numberedRanks.contains(i) && sameSuit[i]){
                                tillFive--;
                            }
                            else{
                                possible = false;// if it doesnt contain it we can break and move on to things other than a flush
                                break;
                            }
                        }
                        if (possible) { //possible (if the middle was filled up)
                            int temp = smaller - 1;
                            while (temp > 1 && !(tillFive == 0)) { //tillfive to check if we have the straight

                                //in order to avoid the error of returning only the first index when there are more than one (need a two of Spades,
                                //first card is two of Diamonds so returns false but second two is of spades)
                                for (int i = 0; i < numberedRanks.size(); i++) {
                                    if (numberedRanks.get(i) == temp) {
                                        indexes.add(i);
                                    }

                                }
                                if (!indexes.isEmpty()) { //meaning there is some with the same rank
                                    for (int id : indexes) {
                                        if (sameSuit[id]) { //it also has the correct suit. No need to break since there will never be two of the same rank and suit
                                            temp--;
                                            tillFive--;
                                        
                                        }
                                    }
                                    indexes = new ArrayList<>(); //setting it back to a new arraylist to make sure isEmpty() returns true
                                    
                                }
                            }
                            temp = bigger + 1;
                            while (temp < 15 && !(tillFive == 0)){



                                //in order to avoid the error of returning only the first index when there are more than one (need a two of Spades,
                                //first card is two of Diamonds so returns false but second two is of spades)
                                for (int i = 0; i < numberedRanks.size(); i++) {
                                    if (numberedRanks.get(i) == temp) {
                                        indexes.add(i);
                                    }

                                }
                                if (!indexes.isEmpty()) { //meaning there is some with the same rank
                                    for (int id : indexes) {
                                        if (sameSuit[id]) { //it also has the correct suit. No need to break since there will never be two of the same rank and suit
                                            biggest = numberedRanks.get(id);
                                            temp++;
                                            tillFive--;
                                        
                                        }
                                    }
                                    indexes = new ArrayList<>(); //setting it back to a new arraylist to make sure isEmpty() returns true
                                }    
                            }
                            if (tillFive == 0) { 
                                currentBestHand = 22;
                                if (biggest == 14){
                                    currentBestHand = 23;
                                }
                            }
                            

                        }
                    
                    

                    //TODO: implement second if for when its >
                }

            }
        }

        //Checking for pairs
        //TODO: make sure to check if the value is not already more than 15/16 before assigning it
        int pairs = 0;







        return currentBestHand;
    }



  
    
}