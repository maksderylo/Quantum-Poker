package org.redfx.Objects;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**Claas of the player containing their name, balance, if they have folded, gone all in, 
 * and other player-specific variables. Also contains a constructor, method to convert
 * card ranks into only numbers, and mainly a method to assign points based on
 * the best possible hand.*/
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
    public int roundStartBalance;
    public int score;
    public ArrayList<String> currentHand = new ArrayList<>();

    /**Constructs a player with their name and balance, giving it a new hand.
     * 
     * @param name is the players name
     * @param balance is the starting balance of the player
     */
    public Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
        hand = new ArrayList<String>();
    }

    
    public void updateHand(String card){
        hand.add(card);
    }


    /**A method to convert the rank of a card to a number.
     * 
     * @param s is the card of which the rank should be converted to a number. 
     *      For example if s = "Queen of Spades", suit would be Q and it would return 12.
     * @return returns the rank as a number
     */
    public int rankToNumber(String s) {
        char rank = s.charAt(0);
        if (rank == '1') { 
            return 10; //cant check for 10 by only 1 since it would return 1.
        } else if (rank == 'J') {
            return 11;
        } else if (rank == 'Q') {
            return 12;
        } else if (rank == 'K') {
            return 13;
        } else if (rank == 'A') {
            return 14;
        }

        return Integer.parseInt(s.substring(0, 1));
    }

    /**Method to pick the highest value card given an arraylist of cards in order to compare 
     * it with other players when there is a tie.
     * 
     * @param bestHand is the ArrayList of cards of which the highest value card is chosen
     * @return the highest value card
     */
    public int highestCard(ArrayList<String> bestHand) {
        int highestCard = 0;
        for (String card : bestHand){
            if (rankToNumber(card.substring(0, card.indexOf(" "))) > highestCard) {
                highestCard = rankToNumber(card.substring(0, card.indexOf(" ")));
            }
        }
        return highestCard;
    }

    /**A complicated method figuring out what combination of cards gives the player the best score.
 * 
 * @param river is the river with 5 cards
 * @return is a value 2 - 23 according to what combination of cards the player has.
    Point system used to compare hands:
    1. Royal Flush (23) DONE
    The combination of ten, jack, queen, king, ace, all of the same suit
    2. Straight Flush (22) DONE
    Five cards of the same suit in sequential order
    3. Four of a kind (21) DONE
    Any four numerically matching cards
    4. Full house (20) DONE
    Combination of three of a kind and a pair in the same hand
    5. Flush (19) DONE
    Five cards of the same suit, in any order
    6. Straight (18) DONE
    Five cards of any suit, in sequential order
    7. Three of a kind (17) DONE
    Any three numerically matching cards
    8. Two pair (16) DONE
    Two different pairs in the same hand
    9. One pair (15) DONE
    Any two numerically matching cards
    10. High card (2-14) DONE
 */
    public int bestHand(ArrayList<String> river) {
    
        System.out.println(river);
        System.out.println(this.hand);

        int currentBestHand = 0;
        //yes i know this looks bad but its just one time and i wanted it on single lines
        String cardOneRank = this.hand.get(0).substring(0, this.hand.get(0).indexOf(" "));
        int numberCardOne = rankToNumber(cardOneRank);
        String cardOneSuit = this.hand.get(0).substring(this.hand.get(0).indexOf(" "));
        String cardTwoRank = this.hand.get(1).substring(0, this.hand.get(1).indexOf(" "));
        int numberCardTwo = rankToNumber(cardTwoRank);
        String cardTwoSuit = this.hand.get(1).substring(this.hand.get(1).indexOf(" "));

        System.out.print("half way assigning");
        boolean[] sameSuit = new boolean[5];

        ArrayList<String> suits = new ArrayList<>();
        ArrayList<String> ranks = new ArrayList<>();
        ArrayList<Integer> numberedRanks = new ArrayList<>();
        ArrayList<String> temporaryHand = new ArrayList<>();
        currentHand = new ArrayList<String>(); 


        System.out.println("After assigning");
        //Separating them into suits and ranks
        //the indexes will match for each card
        for (String card : river) {
            int spaceIndex = card.indexOf(" ");
            ranks.add(card.substring(0, spaceIndex));
            numberedRanks.add(rankToNumber(card.substring(0, spaceIndex)));
            suits.add(card.substring(spaceIndex + 1));
        }
        System.out.println("Before checking if equal");
        //starting by checking for the best case and side cases with this
        if (cardOneSuit.equals(cardTwoSuit)) {
            //this.currentHand.add(this.hand.get(0));
            temporaryHand.add(river.get(0));
            //this.currentHand.add(this.hand.get(1));
            temporaryHand.add(river.get(1));

            /*checking for flushes, if more than two of the river have the same suit it is possible
             * only have to check against cardTwoSuit since it equals cardOneSuit*/
            if (Collections.frequency(suits, cardTwoSuit) > 2) {
                currentBestHand = 19; // for the regular flush
                
                //finding which cards could make the flush
                for (int i = 0; i < suits.size(); i++) {
                    if (suits.get(i).equals(cardOneSuit)) {
                        sameSuit[i] = true;
                        temporaryHand.add(river.get(i));
                    }
                }
                currentHand.removeAll(currentHand);
                currentHand.addAll(temporaryHand);
                //System.out.println(currentHand);

                /*Checking if a Straight Flush is possible by checking 
                if the distance between them is max 4*/
                int distance = Math.abs(numberCardOne - numberCardTwo) - 1;
                int tillFive = 3; //since the cards in the players hand already contribute to it
                int smaller = 0;
                int bigger = 0;
                int biggest = 0; //straight flush and biggest is an ace then its a royal flush
                ArrayList<Integer> indexes = new ArrayList<>();
                boolean possible = true;
                if (distance < 4) {

                    /*two cases, no third since they cant be equal if they are same suit */
                    if (numberCardOne < numberCardTwo) {
                        smaller = numberCardOne;
                        bigger = numberCardTwo;
                        biggest = numberCardTwo;
                    }   else {
                        smaller = numberCardTwo;
                        bigger = numberCardOne;
                        biggest = numberCardOne;
                    }
                    //Checking if the cards in between them are in the river
                    for (int i = smaller + 1; i < bigger; i++) { 

                        if (numberedRanks.contains(i) && sameSuit[i]) {
                            tillFive--;
                            temporaryHand.add(river.get(i));
                            
                        } else {
                            /*if it doesnt contain it we can break 
                            and move on to things other than a flush */    
                            possible = false; 
                            break;
                        }
                    }
                    if (possible) { //possible (if the middle was filled up)
                        int temp = smaller - 1;
                        //tillfive to check if we have the straight
                        while (temp > 1 && !(tillFive == 0)) { 

                            /*in order to avoid the error of returning only the first index 
                            when there are more than one (need a two of Spades,first card is 
                            two of Diamonds so returns false but second two is of spades)*/
                            for (int i = 0; i < numberedRanks.size(); i++) {
                                if (numberedRanks.get(i) == temp) {
                                    indexes.add(i);
                                }

                            }
                            if (!indexes.isEmpty()) { //meaning there is some with the same rank
                                for (int id : indexes) {
                                    /*if it also has the correct suit. No need to break 
                                    since there will never be two of the same rank and suit */
                                    if (sameSuit[id]) { 
                                        temp--;
                                        tillFive--;
                                        temporaryHand.add(river.get(id));
                                    
                                    }
                                }
                                /*setting it back to a new arraylist 
                                to make sure isEmpty() returns true*/
                                indexes = new ArrayList<>(); 
                                
                            } else {
                                break;
                            }
                            
                        }
                        temp = bigger + 1;
                        while (temp < 15 && !(tillFive == 0)) {



                            /*in order to avoid the error of returning only the first index 
                            when there are more than one (need a two of Spades,first card is 
                            two of Diamonds so returns false but second two is of spades)*/
                            for (int i = 0; i < numberedRanks.size(); i++) {
                                if (numberedRanks.get(i) == temp) {
                                    indexes.add(i);
                                }

                            }
                            if (!indexes.isEmpty()) { //meaning there is some with the same rank
                                for (int id : indexes) {
                                    /*it also has the correct suit. No need to break 
                                    since there will never be two of the same rank and suit*/
                                    if (sameSuit[id]) { 
                                        biggest = numberedRanks.get(id);
                                        temp++;
                                        tillFive--;
                                        temporaryHand.add(river.get(id));
                                    
                                    }
                                }
                                /*setting it back to a new arraylist 
                                to make sure isEmpty() returns true */
                                indexes = new ArrayList<>(); 
                            } else {
                                break;
                            }
                            
                        }
                        if (tillFive == 0) { 
                            currentBestHand = 22;
                            this.currentHand.removeAll(currentHand);
                            this.currentHand.addAll(temporaryHand);
                            if (biggest == 14) {
                                currentBestHand = 23;
                            }
                        }
                    }
                }

            }
        }
        if (!temporaryHand.isEmpty()) {
            temporaryHand.removeAll(temporaryHand);
        }
               
        /*sameRank and same are set to 1 since it is the card that is being tested against,
        thus its always the same rank as the card were testing against.*/
        int temporaryBest = 0;
        int sameRank = 1; 
        int same = 1;
        boolean pairInRiver = false;
        boolean pair = false;
        boolean threeOfAKind = false;
        boolean equal = false;
        ArrayList<String> ofAKindOne = new ArrayList<String>();
        ArrayList<String> ofAKindTwo = new ArrayList<String>();
        ArrayList<String> better = new ArrayList<String>();
        ArrayList<String> worse = new ArrayList<String>();

        System.out.println(numberedRanks);
        //checking for two or three of a kind in river
        for (int i = 2; i < 15; i++) {
            System.out.println(Collections.frequency(numberedRanks, i));
            if (Collections.frequency(numberedRanks, i) > 1) {

                for (int n = 0; n < numberedRanks.size(); n++) {
                    System.out.println(n);
                    //to prevent double counting etc.
                    List<Integer> temp = new ArrayList<>(
                        numberedRanks.subList(n, numberedRanks.size()));
                    if (ofAKindOne.isEmpty() || rankToNumber(ofAKindOne.get(0)) == i) {
                        ofAKindOne.add(river.get(temp.indexOf(i)));
                    } else {
                        ofAKindTwo.add(river
                            .get(temp.indexOf(i)));
                    }
                    n = temp.indexOf(i);
                    System.out.println(n);
                    List<Integer> temp2 = new ArrayList<>(
                        numberedRanks.subList(n + 1, numberedRanks.size()));
                    if (temp2.indexOf(i) <= 0) {
                        break;
                    }
                }


            }           
        }
        //TODO: if only a pair in river it should still be counted.
        if (ofAKindOne.size() != 0) {
            if (ofAKindTwo.size() != 0) {
                if (ofAKindOne.size() > ofAKindTwo.size()) {
                    better = ofAKindOne;
                    worse = ofAKindTwo;
                } else if (ofAKindOne.size() == ofAKindTwo.size()) {
                    if (rankToNumber(ofAKindOne.get(0)) > rankToNumber(ofAKindTwo.get(0))) {
                        better = ofAKindOne;
                        worse = ofAKindTwo;
                    } else {
                        better = ofAKindTwo;
                        worse = ofAKindOne;
                    }
                } else {
                    better = ofAKindTwo;
                    worse = ofAKindOne;
                }

            } else {
                better = ofAKindOne;
                worse = ofAKindTwo;
            }
        }
        System.out.println("assinged better/worse");
        for (int n = 0; n < numberedRanks.size(); n++) {
            if (numberCardOne == numberedRanks.get(n)) {
                sameRank++;
                temporaryHand.add(river.get(n));
            }
        }
        if (numberCardOne == numberCardTwo) {
            sameRank += 1;
            equal = true;
            temporaryHand.add(hand.get(1));
        }
        if (sameRank > 1) {
            temporaryHand.add(hand.get(0));
        }
        if (sameRank == 4 && currentBestHand < 21) {
            currentBestHand = 21; //immediately setting currentBestHand to avoid a specific case.
            this.currentHand.removeAll(currentHand);
            this.currentHand.addAll(temporaryHand);
        }
        if (sameRank == 3) {
            threeOfAKind = true;
            if (equal) { //checking for another pair
                if (better.size() > 1) {
                    for (int i = 0; i < 2; i++) {
                        temporaryHand.add(river.get(river.indexOf(better.get(i))));
                    }
                    temporaryBest = 20;
                    this.currentHand.removeAll(currentHand);
                    this.currentHand.addAll(temporaryHand);
                    //there is nothing better to find
                } else if (worse.size() > 1) {
                    for (int i = 0; i < 2; i++) {
                        temporaryHand.add(river.get(river.indexOf(worse.get(i))));
                    }
                    temporaryBest = 20;
                    this.currentHand.removeAll(currentHand);
                    this.currentHand.addAll(temporaryHand);
                }                 
            } else {
                if (better.size() != 0) { 
                    for (int i = 0; i < 2; i++) {
                        temporaryHand.add(river.get(river.indexOf(better.get(i))));
                    }
                    temporaryBest = 20;
                    this.currentHand.removeAll(currentHand);
                    this.currentHand.addAll(temporaryHand);
                } else {
                    //below is for three of a kind
                    temporaryBest = 17;

                    this.currentHand.removeAll(currentHand);
                    this.currentHand.addAll(temporaryHand);
                }
            }
        }
        if (sameRank == 2) {
            pair = true;
            /*if the cards in hand make a pair we still have to check for 
            a separate pair or three of a kind in the river. */
            if (equal) { 
                if (better.size() == 3) {
                    for (int i = 0; i < 3; i++) {
                        temporaryHand.add(river.get(river.indexOf(better.get(i))));
                    }
                    temporaryBest = 20;
                    this.currentHand.removeAll(currentHand);
                    this.currentHand.addAll(temporaryHand);
                } else if (better.size() == 2) {
                    for (int i = 0; i < 2; i++) {
                        temporaryHand.add(river.get(river.indexOf(better.get(i))));
                    }
                    temporaryBest = 16;
                    this.currentHand.removeAll(currentHand);
                    this.currentHand.addAll(temporaryHand);
                }
            } else {
                temporaryBest = 15;
                this.currentHand.removeAll(currentHand);
                this.currentHand.addAll(temporaryHand);
            }
        }
        /*it only has to check the second card whenever the hand cards are not equal 
        since it already exhausted all those possibilities */
        System.out.println("before checking second one");
        if (!equal) { 
            sameRank = 1;
            for (int n = 0; n < numberedRanks.size(); n++) {
                if (numberCardTwo == numberedRanks.get(n)) {
                    sameRank++;
                    temporaryHand.add(river.get(n));
                    
                }
            }
            if (sameRank > 1) {
                temporaryHand.add(this.hand.get(1));
            }
            if (sameRank == 4 && currentBestHand < 21) {
                //immediately setting currentBestHand to avoid a specific case.
                currentBestHand = 21;
                this.currentHand.removeAll(currentHand);
                this.currentHand.addAll(temporaryHand);
            }
            if (sameRank == 3) {
                if (pair || threeOfAKind) {
                    temporaryBest = 20;
                } else {
                    temporaryBest = 17;
                }
            } 
            if (sameRank == 2) {
                //System.out.println("pair from second card");
                if (threeOfAKind) {
                    temporaryBest = 20;
                } else if (pair) { 
                    temporaryBest = 16;
                } else { 
                    if (better.size() == 3) { 
                        for (int i = 0; i < 3; i++) {
                            temporaryHand.add(river.get(river.indexOf(better.get(i))));
                        }
                        temporaryBest = 20;
                        this.currentHand.removeAll(currentHand);
                        this.currentHand.addAll(temporaryHand);
                    } else if (better.size() == 2) {
                        for (int i = 0; i < 2; i++) {
                            temporaryHand.add(river.get(river.indexOf(better.get(i))));
                        }
                        temporaryBest = 16;
                        this.currentHand.removeAll(currentHand);
                        this.currentHand.addAll(temporaryHand);
                    } else {
                        temporaryBest = 15;
                    }
                }


            }
        }
        //actually assinging it
        //System.out.println("right before assinging check");
        if (temporaryBest > currentBestHand) {
            currentBestHand = temporaryBest;
            this.currentHand.removeAll(currentHand);
            this.currentHand.addAll(temporaryHand);
        }
        System.out.println("After assigning it");
        System.out.println(currentBestHand);






        temporaryHand.removeAll(temporaryHand);
        temporaryHand.add(hand.get(0));
        temporaryHand.add(hand.get(1));
        /*Making a separate part for the straight since the earlier 'methods' 
        require the cards to be of the same suits*/
        int distance = Math.abs(numberCardOne - numberCardTwo) - 1;
        int tillFive = 3; //since the cards in the players hand already contribute to it
        int smaller;
        int bigger;
        //ArrayList<Integer> indexes = new ArrayList<>();
        boolean possible = true;
        /*distance cant be -1 because you cant have a straight if the cards have 
        the same rank */
        if (distance < 4 && distance > -1) { 
            //two cases since they cant be equal if they are same suit
            if (numberCardOne < numberCardTwo) {
                smaller = numberCardOne;
                bigger = numberCardTwo;
            }   else {
                smaller = numberCardTwo;
                bigger = numberCardOne;
            }
            System.out.println("before middle");
            //checking if the cards in between them are in the river
            for (int i = smaller + 1; i < bigger; i++) { 

                if (numberedRanks.contains(i)) {
                    tillFive--;
                    temporaryHand.add(river.get(numberedRanks.indexOf(i)));

                } else {
                    //If it doesnt contain it we can break and move on to things other than a flush
                    possible = false;
                    break;
                }
            }
            System.out.println("Before smaller");
            if (possible) { //possible (if the middle was filled up)
                int temp = smaller - 1;
                //while (temp > 1 && !(tillFive == 0)) { //tillfive to check if we have the straight

                for (int i = 0; i < numberedRanks.size(); i++) {
                    if (numberedRanks.get(i) == temp) {
                        temp--;
                        tillFive--;
                        temporaryHand.add(river.get(i));
                        if (temp > 1 && !(tillFive == 0)) {
                            /* We're checking agian with a new temp so i is set to -1 so that it
                            will be equal to 0 again for the next loop*/
                            i = -1; 
                        } else {
                            break;
                        }

                        
                    }

                }   
                //}
                System.out.println("before bigger");
                temp = bigger + 1;
                //while (temp < 15 && !(tillFive == 0)) {
                for (int i = 0; i < numberedRanks.size(); i++) {
                    if (numberedRanks.get(i) == temp) {
                        temp++;
                        tillFive--;
                        temporaryHand.add(river.get(i));
                        if (temp < 15 && !(tillFive == 0)) {
                            /* We're checking agian with a new temp so i is set to -1 so that it
                            will be equal to 0 again for the next loop*/
                            i = -1; 
                        } else {
                            break;
                        }
                        i = -1;
                    }

                }
                //}
            }
            if (tillFive == 0 && currentBestHand < 18) {
                currentBestHand = 18;
                this.currentHand.removeAll(currentHand);
                this.currentHand.addAll(temporaryHand);
            }
        }
        System.out.println("Before high card");
        if (currentBestHand < 15) { //if no special hand can be made we take the highest value card.
        
            for (int i = 0; i < numberedRanks.size(); i++) {
                if (numberedRanks.get(i) > currentBestHand) {
                    currentBestHand = numberedRanks.get(i);
                    this.currentHand.add(river.get(i));
                }
            }
            if (numberCardOne > currentBestHand) {
                currentBestHand = numberCardOne;
                this.currentHand.set(0, hand.get(0));
            }
            if (numberCardTwo > currentBestHand) {
                currentBestHand = numberCardTwo;
                this.currentHand.set(0, hand.get(1));
            }
        }
        temporaryHand.removeAll(temporaryHand);
        System.out.println(currentHand);
        return currentBestHand;
    }
}