package org.redfx;


import javax.swing.*;
import java.awt.Image;


public class Main {


    public static void main(String[] args) {
        //initialise the frame with settings and let the StartScreen do its work
        JFrame frame = new JFrame("Poker 2.0");
        Image image = new ImageIcon("src\\main\\resources\\pokerChip.png").getImage();
        frame.setIconImage(image);
        StateManager manager = new StateManager(frame);
        manager.switchToStartScreen();
    }
}