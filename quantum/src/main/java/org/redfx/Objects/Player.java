package org.redfx.Objects;


import java.util.ArrayList;
public class Player {
    public String name;
    public int balance;
    public ArrayList<String> hand; 
    public int type;
    public boolean folded = false;  
    //type keeps track of if the player is the dealer(1)/small blind(2)/big blind(3)/none(0).
    public Player(String name, int bal){
        this.name = name;
        this.balance = bal;
    }

    public void updateHand(String card){
        hand.add(card);
    }

    public ArrayList<String> getHand(){
        return hand;
    }

    //the @param amount will change based on if its a bet or raise when the person decides
    public void action(int amount){
        this.balance -= amount;
    }
    

    public int getType(){
        return this.type;
    }

    public void setType(int t){
        this.type = t;
    }

  
    
}