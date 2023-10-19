package org.redfx.Objects;


import java.util.ArrayList;
public class Player {
    public String name;
    public int balance;
    public ArrayList<String> hand; 
    public int role;
    public boolean folded = false;
    public int currentBet;
    public boolean madeDecision;
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



  
    
}