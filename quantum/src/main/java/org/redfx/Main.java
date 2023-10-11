package org.redfx;


import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        //Phase 1

        //initialise the frame with settings and let the StartScreen do its work
        JFrame frame = new JFrame("Poker 1.0");
        frame.setSize(540, 800);
        StateManager manager = new StateManager(frame);
        manager.switchToStartScreen();
        

    }
}