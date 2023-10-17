package org.redfx;


import javax.swing.*;


public class Main {


    public static void main(String[] args) {
        //initialise the frame with settings and let the StartScreen do its work
        JFrame frame = new JFrame("Poker 1.0");
        StateManager manager = new StateManager(frame);
        manager.switchToStartScreen();
    }
}