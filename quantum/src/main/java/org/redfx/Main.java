package org.redfx;


import javax.swing.*;


public class Main {

    public void StartGame(int playersAmount, String[] PlayersNames, int moneyPerPlayer, StateManager stateManager) {
        //TODO Create the variables - players, rounds etc..
        
        //TODO store the given data in the appropriate places

        

    }

    public static void main(String[] args) {
        //initialise the frame with settings and let the StartScreen do its work
        JFrame frame = new JFrame("Poker 1.0");
        StateManager manager = new StateManager(frame);
        manager.switchToStartScreen();
    }
}