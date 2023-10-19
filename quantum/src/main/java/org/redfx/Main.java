package org.redfx;


import javax.swing.*;


public class Main {


    public static void main(String[] args) {
        //initialise the frame with settings and let the StartScreen do its work
        JFrame frame = new JFrame("Poker 1.2");
        ImageIcon image = new ImageIcon("src\\main\\resources\\pokerChip.png");
        frame.setIconImage(image.getImage());
        StateManager manager = new StateManager(frame);
        manager.switchToStartScreen();
    }
}