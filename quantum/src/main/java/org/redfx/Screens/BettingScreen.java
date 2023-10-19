package org.redfx.Screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.redfx.*;
import org.redfx.Objects.*;

public class BettingScreen extends JPanel{
    public Thread thread;

    Title title = new Title("");
    GridBagConstraints constraints = new GridBagConstraints();
    CoolButton callBtn= new CoolButton("Call");
    CoolButton foldBtn= new CoolButton("Fold");
    CoolButton raiseBtn = new CoolButton("Raise");
    CoolButton allInBtn = new CoolButton("ALL IN MF!");
    JLabel raiseLabel = new JLabel("How much would you like to raise?");
    JSlider raiseSlider;


    JPanel buttonPanel;
    Player bettingPlayer;

    public BettingScreen(Round round){
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        setBorder(new EmptyBorder(0,50,0,50));

        bettingPlayer = round.Players[round.nowBettingPlayerIndex];

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
        
        
        //Show the cards
        

        //Raise slider
        if(bettingPlayer.balance > (round.largestbet - bettingPlayer.currentBet)){
            constraints.gridy=5;
            constraints.gridx = 0;
            add(raiseLabel);
            constraints.gridx = 1;
            raiseSlider = new JSlider(1, bettingPlayer.balance - round.largestbet + bettingPlayer.currentBet,1);
            add(raiseSlider);
        }

        //
        JPanel buttonPanel = new JPanel(); // Change to JPanel for better button alignment
        constraints.gridy = 10;
        constraints.gridx = 0;
        constraints.gridwidth = 10;
        add(buttonPanel, constraints);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setOpaque(false);

        buttonPanel.add(foldBtn);//the player can always fold

        if(bettingPlayer.balance <= round.largestbet){ //if they dont have enough money they can only go all in
            buttonPanel.add(allInBtn);
        }   else {
            buttonPanel.add(callBtn);
            buttonPanel.add(raiseBtn);
        }


        //TODO Action events for the buttons

        callBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bettingPlayer.balance -= (round.largestbet-bettingPlayer.currentBet);
                bettingPlayer.currentBet = round.largestbet;
                round.Players[round.nowBettingPlayerIndex] = bettingPlayer;
                synchronized(round.worker) {
                    round.worker.notify(); // notify the worker when the button is pressed
                }       
            }
        });



        // synchronized(round.worker) {
        //     round.worker.notify(); // notify the worker when the button is pressed
        // }

    }
}
