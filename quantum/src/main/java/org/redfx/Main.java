package org.redfx;

import java.awt.Image;
import javax.swing.*;


/**Creates a frame for the game and switches to startScreen. */
public class Main {


    public static void main(String[] args) {
        //initialise the frame with settings and let the StartScreen do its work
        JFrame frame = new JFrame("Poker 2.0");
        //TODO: this works for my computer but probably not on other
        Image image = new ImageIcon("C:\\Users\\20231479\\Documents\\GitHub\\Quantum-Poker\\quantum\\src\\main\\resources\\org\\redfx\\resources\\pokerChip.png").getImage();
        frame.setIconImage(image);
        StateManager manager = new StateManager(frame);
        manager.switchToStartScreen();
    }
}