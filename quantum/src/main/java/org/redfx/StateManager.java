package org.redfx;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StateManager {
    
    private JPanel container; // A panel to hold different screens
    private StartScreen startScreenLabel;
    private LoadingScreen loadingScreenLabel;
    private CardLayout cardLayout;
    private JFrame frame;
    

    public StateManager(JFrame frame) {
        this.frame = frame;
        frame.setSize(540, 800);
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        frame.getContentPane().add(container);


        container.add(new StartScreen(this, frame), "StartScreen");
        container.add(new LoadingScreen(this, frame), "LoadingScreen");

        frame.setResizable(false);
        frame.pack();
        frame.setSize(540, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public void switchToStartScreen() {
        System.out.println("To Start");
        cardLayout.show(container, "StartScreen");
    }

    public void switchToLoadScreen() {
        System.out.println("To Load");
        cardLayout.show(container, "LoadingScreen");
    }
    
}
