package org.redfx.Screens;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.redfx.Objects.CenterPanel;
import org.redfx.Objects.CoolButton;
import org.redfx.Objects.Player;
import org.redfx.Objects.Title;
import org.redfx.StateManager;


/**A class for the screen when a player has won.*/
public class GameEndScreen extends JPanel {
    Title title = new Title("");
    Title playerTitle = new Title("");
    GridBagConstraints constraints = new GridBagConstraints();
    CoolButton playBtn;
    boolean quantum;
    Color backColor;
    Color foreColor;
    Color quantumColor = new Color(165, 165, 165);

    /**Constructing the screen for when the game has ended. 
     * It displays the winners name and states that the game has ended.
     * 
     * @param winner the winning player of which the name will be displayed
     * @param stateManager TODO: WTF IS THIS HELP
     * */
    public GameEndScreen(Player winner, StateManager stateManager, boolean quantum) {
        this.quantum = quantum;

        if (!quantum) {
            backColor = Color.BLACK;
            foreColor = Color.WHITE;
            playBtn = new CoolButton("Menu");

        } else {
            backColor = Color.LIGHT_GRAY;
            foreColor = Color.BLACK;
            playBtn = new CoolButton("Menu", quantumColor);
        }
        setLayout(new GridBagLayout());
        setBackground(backColor);
        setBorder(new EmptyBorder(0, 50, 0, 50));

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        //adding the title
        CenterPanel titlePanel = new CenterPanel();
        constraints.gridy = 0;
        constraints.gridwidth = 10;
        constraints.gridx = 0;
        title.setText("The game has ended!");
        title.setForeground(foreColor);
        titlePanel.add(title);
        add(titlePanel, constraints);
        

        //adding the title
        CenterPanel playerPanel = new CenterPanel();
        constraints.gridy = 1;
        constraints.gridx = 0;
        playerTitle.setText("Congratulation " + winner.name);
        playerTitle.setForeground(Color.WHITE);
        playerPanel.add(title);
        add(playerPanel, constraints);


        CenterPanel buttonPanel = new CenterPanel();
        constraints.gridy = 2;
        constraints.gridx = 0;
        buttonPanel.add(playBtn);
        add(playBtn, constraints);

        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateManager.switchToStartScreen();
            }
        });

    }
    
}
