package org.redfx;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.redfx.Objects.Player;
import org.redfx.Screens.BettingScreen;
import org.redfx.Screens.ChangeToPlayerScreen;
import org.redfx.Screens.GameEndScreen;
import org.redfx.Screens.LoadingScreen;
import org.redfx.Screens.PlayersNamesScreen;
import org.redfx.Screens.RoundEndScreen;
import org.redfx.Screens.RoundStartScreen;
import org.redfx.Screens.StartScreen;


public class StateManager {
    
    private JPanel container; // A panel to hold different screens
    private CardLayout cardLayout;
    
    LoadingScreen loadingScreen = new LoadingScreen(this);
    GameEndScreen gameEndScreen;
    PlayersNamesScreen playersNamesScreen;
    RoundStartScreen roundStartScreen;
    RoundEndScreen roundEndScreen;
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
        cardLayout.show(container, "StartScreen");
    }

    public void switchToLoadScreen() {
        cardLayout.show(container, "LoadingScreen");
    }

    /**Creates a new PlayersNamesScreen with the given input.
     * 
     * @param playersAmount is the amount of players
     * @param moneyPerPlayer is the money each player starts with
     */
    public void switchToPlayersNamesScreen(int playersAmount, int moneyPerPlayer) {
        playersNamesScreen = new PlayersNamesScreen(this, playersAmount, moneyPerPlayer);
        container.add(playersNamesScreen, "PlayersNamesScreen");
        cardLayout.show(container, "PlayersNamesScreen");
    }

    /**Creates a new RoundStartScreen with the given parameters.
     * 
     * @param round is the current round
     * @param phase is the current phase
     */
    public void switchToRoundStartScreen(Round round, String phase) {
        roundStartScreen = new RoundStartScreen(round, phase);
        container.add(roundStartScreen, "RoundStartScreen");
        cardLayout.show(container, "RoundStartScreen");
    }

    /**Creates a new BettingScreen with the given round.
     * 
     * @param round is the current round
     */
    public void switchToBettingScreen(Round round) {
        bettingScreen = new BettingScreen(round);
        container.add(bettingScreen, "BettingScreen");
        cardLayout.show(container, "BettingScreen");
    }

    /**Creates a ChangeToPlayerScreen with the given round.
     * 
     * @param round is the current round
     */
    public void switchToChangeToPlayerScreen(Round round) {

        changeToPlayerScreen = new ChangeToPlayerScreen(round);
        container.add(changeToPlayerScreen, "ChangingScreen");
        cardLayout.show(container, "ChangingScreen");
    }

    /**Creates a RoundEndScreen with the given round.
     * 
     * @param round is the current round
     */
    public void switchToRoundEndScreen(Round round) {
        roundEndScreen = new RoundEndScreen(round);
        container.add(roundEndScreen, "RoundEndScreen");
        cardLayout.show(container, "RoundEndScreen");
    }

    /**Creates a GameEndScreen with the given parameters.
     * 
     * @param winner is the player that has won the game
     * @param stateManager
     */
    public void switchToGameEndScreen(Player winner, StateManager stateManager) {
        gameEndScreen = new GameEndScreen(winner, stateManager);
        container.add(gameEndScreen, "GameEndScreen");
        cardLayout.show(container, "GameEndScreen");
    }
    
}
