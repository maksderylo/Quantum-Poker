package org.redfx.waithere;


import java.util.ArrayList;
public class Player {
    String name;
    int balance;
    ArrayList<String> hand; 
    int type;
    boolean folded = false;  
    //type keeps track of if the player is the dealer(1)/small blind(2)/big blind(3)/none(0).
    public Player(String n, int b){
        name = n;
        balance = b;
    }

    public void updateHand(String s){
        hand.add(s);
    }

    public ArrayList<String> getHand(){
        return hand;
    }

    //the @param amount will change based on if its a bet or raise when the person decides
    public int action(int amount){
        this.balance -= amount;
        return amount;
    }
    

    public int getType(){
        return this.type;
    }

    public void setType(int t){
        this.type = t;
    }

  
    
}