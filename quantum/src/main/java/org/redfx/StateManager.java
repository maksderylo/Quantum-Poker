package org.redfx;

import java.awt.CardLayout;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StateManager {
    
    private JPanel container; // A panel to hold different screens
    private CardLayout cardLayout;
    private JPanel currentPanel;
    private JPanel nextPanel;
    private float alpha = 0.0f;
    private Timer timer;
    private boolean isFading = false;



    public StateManager(JFrame frame) {
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
