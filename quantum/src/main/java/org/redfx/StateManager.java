package org.redfx;

import javax.swing.JFrame;

public class StateManager {
    

    
    private JFrame frame;

    public StateManager(JFrame frame) {
        this.frame = frame;
        frame.setSize(540, 800);
    }

    public void switchToStartScreen() {
        System.out.println("To Start");

        frame.getContentPane().removeAll();        
        new StartScreen(this, frame);

    }
    public void switchToLoadScreen() {
        System.out.println("To Load");

        frame.getContentPane().removeAll();
        new LoadingScreen(this, frame);

    }

    
}
