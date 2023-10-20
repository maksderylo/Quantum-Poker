package org.redfx;


import java.awt.CardLayout;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import org.redfx.Screens.*;

public class StateManager {
    
    private JPanel container; // A panel to hold different screens
    private CardLayout cardLayout;
    
    LoadingScreen loadingScreen = new LoadingScreen(this);
    PlayersNamesScreen playersNamesScreen;
    RoundStartScreen roundStartScreen;
    BettingScreen bettingScreen;
    ChangeToPlayerScreen changeToPlayerScreen;


    public StateManager(JFrame frame) {
        frame.setSize(540, 800);
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        frame.getContentPane().add(container);
        StartScreen startScreen = new StartScreen(this, frame);

        container.add(startScreen, "StartScreen");
        container.add(loadingScreen, "LoadingScreen");

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

    public void switchToPlayersNamesScreen(int playersAmount, int moneyPerPlayer) {
        System.out.println("To PlayersScreen");
        playersNamesScreen = new PlayersNamesScreen(this, playersAmount, moneyPerPlayer);
        container.add(playersNamesScreen, "PlayersNamesScreen");
        cardLayout.show(container, "PlayersNamesScreen");
    }

    public void switchToRoundStartScreen(Round round,String phase){
        roundStartScreen = new RoundStartScreen(round, phase);
        container.add(roundStartScreen, "RoundStartScreen");
        cardLayout.show(container, "RoundStartScreen");
    }
    public void switchToBettingScreen(Round round){
        bettingScreen= new BettingScreen(round);
        container.add(bettingScreen, "BettingScreen");
        cardLayout.show(container, "BettingScreen");
    }
    public void switchToChangeToPlayerScreen(Round round){
        System.out.println("inside betting");

        changeToPlayerScreen= new ChangeToPlayerScreen(round);
        container.add(changeToPlayerScreen, "ChangingScreen");
        cardLayout.show(container, "ChangingScreen");
    }
    
}
