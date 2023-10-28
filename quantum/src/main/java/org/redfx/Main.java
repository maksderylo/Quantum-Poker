package org.redfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


/**Creates a frame for the game and switches to startScreen. */
public class Main {
    static BufferedImage pokerChip;

    public static void main(String[] args) {

        //for the icon of the frame
        try {
            pokerChip = ImageIO.read(Main.class.getResource(
                "/org/redfx/resources/pokerChip.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //initialise the frame with settings and let the StartScreen do its work
        JFrame frame = new JFrame("Poker 2.0");
        frame.setIconImage(pokerChip);
        StateManager manager = new StateManager(frame);
        manager.switchToStartScreen();
    }
}