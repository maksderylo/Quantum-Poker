package org.redfx.Screens;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.redfx.Objects.Card;
import org.redfx.Objects.CenterPanel;
import org.redfx.Objects.CoolButton;
import org.redfx.Objects.QubitSquareDisplay;
import org.redfx.Objects.Title;
import org.redfx.Round;

/**A class for the screen before a phase of the round (preflop, flop, ect.)*/
public class RoundStartScreen extends JPanel {

    Title title = new Title("");
    Title poolTitle = new Title("");
    GridBagConstraints constraints = new GridBagConstraints();
    CoolButton playBtn;
    CenterPanel poolPanel = new CenterPanel();
    CenterPanel tableCardsPanel = new CenterPanel();
    boolean quantum;
    Color backColor;
    Color foreColor;
    Color quantumColor = new Color(165, 165, 165);


    /**Constructing the screen by displaying each players name with their balance
     * and their role. It also displays what phase the players are in.
     * 
     * @param round is the round of which it gets the information of the players
     * @param phase is the phase that the game is in
     */
    public RoundStartScreen(Round round, String phase, boolean quantum) {
        this.quantum = quantum;

        if (!quantum) {
            backColor = Color.BLACK;
            foreColor = Color.WHITE;
            playBtn = new CoolButton("Next");

        } else {
            backColor = Color.LIGHT_GRAY;
            foreColor = Color.BLACK;
            playBtn = new CoolButton("Next", quantumColor);
        }

        setLayout(new GridBagLayout());
        setBackground(backColor);
        title.setForeground(foreColor);
        poolTitle.setForeground(foreColor);
        setBorder(new EmptyBorder(0, 50, 0, 50));


        // Weight and fill settings (no need to change these)
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
    

        //adding the title
        constraints.gridy = 0;
        constraints.gridwidth = 10;
        constraints.gridx = 0;
        title.setText("Round " + round.roundNumber + " - " + phase);
        add(title, constraints);
        constraints.gridwidth = 1;

        poolTitle.setText("Pool - " + round.pool + "$");
        constraints.gridy = 1;
        constraints.gridwidth = 10;
        add(poolPanel, constraints);
        poolPanel.add(poolTitle);


        //showing the cards
        String card;
        String suit;
        String rank;
        int spaceIndex;

        if (round.tableCards.size() > 0) {
            constraints.gridy = 2;
            add(tableCardsPanel, constraints);

        
            //showing the table cards
            for (int i = 0; i < round.tableCards.size(); i++) {
                if (!round.quantum) {
                    card = round.tableCards.get(i);
                    suit = "";
                    rank = "";
                    //finding the space
                    spaceIndex = 0;
                    while (true) {
                        if (card.charAt(spaceIndex) == ' ') {
                            break;
                        }
                        spaceIndex++;
                    }

                    rank = card.substring(0, spaceIndex);
                    suit = card.substring(spaceIndex + 1, card.length());

                    tableCardsPanel.add(new Card(rank, suit));
                } else {
                    tableCardsPanel.add(new QubitSquareDisplay(round.tableCards.get(i).charAt(0)));
                }
            }
        }
        
        constraints.gridwidth = 1;
        JLabel helpLabel = new JLabel(""); 

        helpLabel = new JLabel("Name");
        helpLabel.setForeground(foreColor);
        constraints.gridy = 3;
        constraints.gridx = 0;
        add(helpLabel, constraints);
        helpLabel = new JLabel("Balance"); 
        helpLabel.setForeground(foreColor);
        constraints.gridx = 1;
        add(helpLabel, constraints);
        helpLabel = new JLabel("Role"); 
        helpLabel.setForeground(foreColor);
        constraints.gridx = 2;
        add(helpLabel, constraints);

        
        for (int i = 0; i < round.amountOfPlayers; i++) {
            helpLabel = new JLabel(round.players[i].name + ":");
            helpLabel.setForeground(foreColor);
            constraints.gridy = i + 4;
            constraints.gridx = 0;
            add(helpLabel, constraints);
            helpLabel = new JLabel(round.players[i].balance + "$"); 
            helpLabel.setForeground(foreColor);
            constraints.gridx = 1;
            add(helpLabel, constraints);

            if (round.players[i].outOfTheGame) {
                helpLabel = new JLabel("Out of the game"); 
                helpLabel.setForeground(foreColor);
                constraints.gridx = 2;
                add(helpLabel, constraints);
            
            } else if (round.players[i].allIn) {
                helpLabel = new JLabel("All In!!"); 
                helpLabel.setForeground(foreColor);
                constraints.gridx = 2;
                add(helpLabel, constraints);
            
            } else if (round.players[i].folded) {
                helpLabel = new JLabel("folded"); 
                helpLabel.setForeground(foreColor);
                constraints.gridx = 2;
                add(helpLabel, constraints);
            
            } else if (round.players[i].role == 2) {
                helpLabel = new JLabel("Big Blind"); 
                helpLabel.setForeground(foreColor);
                constraints.gridx = 2;
                add(helpLabel, constraints);
            } else if (round.players[i].role == 1) {
                helpLabel = new JLabel("Small Blind"); 
                helpLabel.setForeground(foreColor);
                constraints.gridx = 2;
                add(helpLabel, constraints);
            } else {
                helpLabel = new JLabel("Playing"); 
                helpLabel.setForeground(foreColor);
                constraints.gridx = 2;
                add(helpLabel, constraints);
            }

        }

        JPanel buttonPanel = new JPanel(); // Change to JPanel for better button alignment
        constraints.gridy = 15;
        constraints.gridx = 0;
        constraints.gridwidth = 10;
        add(buttonPanel, constraints);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setOpaque(false);
        buttonPanel.add(playBtn);

        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (phase.equals("Preflop")) {
                    round.firstBets();
                }   else if (phase.equals("The flop")) {
                    round.secondBettingRound();
                }   else if (phase.equals("The turn")) {
                    round.thirdBettingRound();
                }   else if (phase.equals("The river")) {
                    round.fourthBettingRound();
                }   

            }
        });
        

        //has to show all the info in the beginning of the round

        
    }
    
}
