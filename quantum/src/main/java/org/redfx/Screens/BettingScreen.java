package org.redfx.Screens;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.redfx.Objects.Card;
import org.redfx.Objects.CenterPanel;
import org.redfx.Objects.CoolButton;
import org.redfx.Objects.Player;
import org.redfx.Objects.QuantumSquareDisplay;
import org.redfx.Objects.QubitSquareDisplay;
import org.redfx.Objects.Slider;
import org.redfx.Objects.Text;
import org.redfx.Objects.Title;
import org.redfx.Round;


/**A class for the screen on which the player decides their action. This could be to fold, call,
 * raise, go all in, or even check on some rounds. It also displays your balance, the main pool,
 * the highest bet, the cards on the table, and the players cards.
 */
public class BettingScreen extends JPanel {
    public Thread thread;

    Title title = new Title(""); //done
    GridBagConstraints constraints = new GridBagConstraints();
    CoolButton checkBtn = new CoolButton("Check");
    CoolButton callBtn = new CoolButton("Call");
    CoolButton foldBtn = new CoolButton("Fold");
    CoolButton raiseBtn = new CoolButton("Raise");
    CoolButton allInBtn = new CoolButton("ALL IN"); //done
    Text raiseLabel;
    Slider raiseSlider;
    int raiseAmount;
    CenterPanel buttonPanel = new CenterPanel(); // Change to JPanel for better button alignment
    Text titleBalance = new Text("Your balance");
    Text titleCurrentBet = new Text("Current bet");
    Text titlePot = new Text("Main pool");
    Text titleBalancePlayer = new Text("");
    Text titleCurrentBetPlayer = new Text("");
    Text titlePotTable = new Text("");
    Text titleMaxbet = new Text("Highest bet");
    Text titleMaxbetHere = new Text("");

    Text playerCardsText = new Text("These are your cards:");
    CenterPanel playerCardsPanel = new CenterPanel();

    Text tableCardsText = new Text("These are the cards on the table:");
    CenterPanel tableCardsPanel = new CenterPanel();
    Player bettingPlayer;

    boolean quantum;
    Color backColor;
    Color foreColor;
    Color quantumColor = new Color(165, 165, 165);

    /**Constructing the screen with all the buttons and actions the player can preform.
     * 
     * @param round is the current round
     */
    public BettingScreen(Round round) {

        this.quantum = round.quantum;
        if (!quantum) {
            backColor = Color.BLACK;
            foreColor = Color.WHITE;
            

        } else {
            backColor = Color.LIGHT_GRAY;
            foreColor = Color.BLACK;
            checkBtn = new CoolButton("Check", quantumColor);
            callBtn = new CoolButton("Call", quantumColor);
            foldBtn = new CoolButton("Fold", quantumColor);
            raiseBtn = new CoolButton("Raise", quantumColor);
            allInBtn = new CoolButton("ALL IN", quantumColor);
            titleBalance.setForeground(foreColor);
            titleCurrentBet.setForeground(foreColor);
            titlePot.setForeground(foreColor);
            titleBalancePlayer.setForeground(foreColor);
            titleCurrentBetPlayer.setForeground(foreColor);
            titlePotTable.setForeground(foreColor);
            titleMaxbet.setForeground(foreColor);
            titleMaxbetHere.setForeground(foreColor);
            playerCardsText.setForeground(foreColor);
            tableCardsText.setForeground(foreColor);
            raiseLabel.setForeground(foreColor);
        }
        setLayout(new GridBagLayout());
        setBackground(backColor);
        title.setForeground(foreColor);
        setBorder(new EmptyBorder(0, 50, 0, 50));

        bettingPlayer = round.players[round.nowBettingPlayerIndex];
        bettingPlayer.madeDecision = true;
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
        title.setText(bettingPlayer.name);
        add(title, constraints);
        constraints.gridwidth = 1;
        
        
        //info

        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.gridx = 0;
        add(titleBalance, constraints);
        constraints.gridx = 1;
        add(titleCurrentBet, constraints);
        constraints.gridx = 2;
        add(titlePot, constraints);
        constraints.gridx = 3;
        add(titleMaxbet, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        titleBalancePlayer.setText("" + bettingPlayer.balance);
        add(titleBalancePlayer, constraints);
        constraints.gridx = 1;
        titleCurrentBetPlayer.setText("" + bettingPlayer.currentBet);
        add(titleCurrentBetPlayer, constraints);
        constraints.gridx = 2;
        titlePotTable.setText("" + round.pool);
        add(titlePotTable, constraints);
        constraints.gridx = 3;
        titleMaxbetHere.setText("" + round.largestbet);
        add(titleMaxbetHere, constraints);


        //cards
        constraints.gridwidth = 10;
        constraints.gridy = 3;
        constraints.gridx = 0;

        

        //System.out.println(bettingPlayer.hand.get(0));
        String card;
        String suit;
        String rank;
        int spaceIndex;

        if (round.tableCards.size() > 0) {
            add(tableCardsText, constraints);
            constraints.gridy = 4;
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

        for (int i = 0; i < bettingPlayer.hand.size(); i++) {
            if (!round.quantum) {
                card = bettingPlayer.hand.get(i);
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

                playerCardsPanel.add(new Card(rank, suit));
            } else {
                playerCardsPanel.add(new QuantumSquareDisplay(bettingPlayer.hand.get(i).charAt(0)));
            }


        }

        constraints.gridy = 5;
        add(playerCardsText, constraints);

        constraints.gridy = 6;
        add(playerCardsPanel, constraints);



        

        //Raise slider
        if (bettingPlayer.balance > (round.largestbet - bettingPlayer.currentBet)) {
            CenterPanel raisePanelLabel = new CenterPanel();
            CenterPanel raisePanelSlider = new CenterPanel();

            if (!quantum) {
                raiseSlider = new Slider(1, bettingPlayer.balance - round.largestbet 
                + bettingPlayer.currentBet - 1, 1);
            } else {
                raiseSlider = new Slider(1, bettingPlayer.balance - round.largestbet 
                + bettingPlayer.currentBet - 1, 1, backColor);
            }

            raiseSlider.setMajorTickSpacing(bettingPlayer.balance - round.largestbet 
                + bettingPlayer.currentBet - 2);
            raiseSlider.setMinorTickSpacing((bettingPlayer.balance - round.largestbet 
                + bettingPlayer.currentBet - 2) / 10);
            raiseSlider.setSnapToTicks(false);
            raiseAmount = 1; // starting slider position
            raiseLabel = new Text("How much would you like to raise? (" + raiseAmount + ")");


            raisePanelLabel.add(raiseLabel);
            raisePanelSlider.add(raiseSlider);
            constraints.gridy = 7;
            constraints.gridx = 0;
            constraints.gridwidth = 10;
            add(raisePanelLabel, constraints);
            constraints.gridy = 8;
            add(raisePanelSlider, constraints);


            raiseSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    raiseAmount = ((JSlider) e.getSource()).getValue();
                    raiseLabel.setText("How much would you like to raise? (" + raiseAmount + ")");
                }
            });
        }

        //
        constraints.gridy = 10;
        constraints.gridx = 0;
        constraints.gridwidth = 10;
        add(buttonPanel, constraints);

        buttonPanel.add(foldBtn); //the player can always fold

        if (bettingPlayer.balance <= round.largestbet) { 
            //if they dont have enough money they can only go all in
            buttonPanel.add(allInBtn);
        }   else {
            if (round.largestbet == bettingPlayer.currentBet) {
                buttonPanel.add(checkBtn);
            } else {
                buttonPanel.add(callBtn);
            }
            buttonPanel.add(raiseBtn);
            buttonPanel.add(allInBtn);
            
        }



        checkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                round.players[round.nowBettingPlayerIndex] = bettingPlayer;
                synchronized (round.worker) {
                    round.worker.notify(); // notify the worker when the button is pressed
                }       
            }
        });

        callBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bettingPlayer.balance -= (round.largestbet - bettingPlayer.currentBet);
                round.pool += (round.largestbet - bettingPlayer.currentBet);
                bettingPlayer.currentBet = round.largestbet;
                round.players[round.nowBettingPlayerIndex] = bettingPlayer;
                synchronized (round.worker) {
                    round.worker.notify(); // notify the worker when the button is pressed
                }       
            }
        });

        raiseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bettingPlayer.balance -= raiseAmount + round.largestbet - bettingPlayer.currentBet;
                round.pool += round.largestbet + raiseAmount - bettingPlayer.currentBet;

                bettingPlayer.currentBet = round.largestbet + raiseAmount;
                round.largestbet = bettingPlayer.currentBet;
                round.players[round.nowBettingPlayerIndex] = bettingPlayer;

                synchronized (round.worker) {
                    round.worker.notify(); // notify the worker when the button is pressed
                } 

            }
        });
        foldBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bettingPlayer.folded = true;
                round.players[round.nowBettingPlayerIndex] = bettingPlayer;
                synchronized (round.worker) {
                    round.worker.notify(); // notify the worker when the button is pressed
                } 
            }
        });
        allInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bettingPlayer.allIn = true; 
                if (round.largestbet - bettingPlayer.currentBet < bettingPlayer.balance) {
                    round.largestbet = bettingPlayer.balance + bettingPlayer.currentBet;
                }
                bettingPlayer.currentBet += bettingPlayer.balance;
                round.pool += bettingPlayer.balance;
                bettingPlayer.balance = 0;

                round.players[round.nowBettingPlayerIndex] = bettingPlayer;
                synchronized (round.worker) {
                    round.worker.notify(); // notify the worker when the button is pressed
                } 
            }
        });
        



        // synchronized(round.worker) {
        //     round.worker.notify(); // notify the worker when the button is pressed
        // }

    }
}
