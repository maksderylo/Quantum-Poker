package org.redfx.Screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.redfx.*;
import org.redfx.Objects.*;

public class BettingScreen extends JPanel{
    public Thread thread;

    Title title = new Title("");
    GridBagConstraints constraints = new GridBagConstraints();
    CoolButton checkBtn = new CoolButton("Check");
    CoolButton callBtn= new CoolButton("Call");
    CoolButton foldBtn= new CoolButton("Fold");
    CoolButton raiseBtn = new CoolButton("Raise");
    CoolButton allInBtn = new CoolButton("ALL IN MF!");
    Text raiseLabel ;
    Slider raiseSlider;
    int raiseAmount;
    CenterPanel buttonPanel = new CenterPanel(); // Change to JPanel for better button alignment

    Text playerCardsText = new Text("These are your cards:");
    CenterPanel playerCardsPanel = new CenterPanel();

    Text tableCardsText = new Text("These are the cards on the table:");
    CenterPanel tableCardsPanel = new CenterPanel();
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
        //TODO do the cards
        
        

        constraints.gridwidth=10;

        constraints.gridy=3;
        constraints.gridx=0;

        

        //System.out.println(bettingPlayer.hand.get(0));
        String card, suit, rank;
        int spaceIndex;

        if(round.tableCards.size()>0){
            add(tableCardsText, constraints);
        constraints.gridy =4;
        add(tableCardsPanel, constraints);

        

        //showing the table cards
        for(int i =0;i<round.tableCards.size();i++){
            card = round.tableCards.get(i);
            suit = "";
            rank = "";
            //finding the space
            spaceIndex = 0;
            while(true){
                if(card.charAt(spaceIndex) == ' '){
                    break;
                }
                spaceIndex++;
            }

            rank = card.substring(0, spaceIndex);
            suit = card.substring(spaceIndex + 1, card.length());

            tableCardsPanel.add(new Card(rank, suit));



        }
    }

        for(int i =0;i<2;i++){
            card = bettingPlayer.hand.get(i);
            suit = "";
            rank = "";
            //finding the space
            spaceIndex = 0;
            while(true){
                if(card.charAt(spaceIndex) == ' '){
                    break;
                }
                spaceIndex++;
            }

            rank = card.substring(0, spaceIndex);
            suit = card.substring(spaceIndex + 1, card.length());

        playerCardsPanel.add(new Card(rank, suit));



        }

        constraints.gridy=5;
        add(playerCardsText, constraints);

        constraints.gridy=6;
        add(playerCardsPanel,constraints);

        if(round.largestbet == 0){
            buttonPanel.add(checkBtn);
        }


        

        //Raise slider
        if(bettingPlayer.balance > (round.largestbet - bettingPlayer.currentBet)){
            CenterPanel raisePanelLabel = new CenterPanel();
            CenterPanel raisePanelSlider = new CenterPanel();

            raiseSlider = new Slider(1, bettingPlayer.balance - round.largestbet + bettingPlayer.currentBet - 2,1);
            raiseSlider.setMajorTickSpacing(bettingPlayer.balance - round.largestbet + bettingPlayer.currentBet - 3);
            raiseSlider.setMinorTickSpacing((bettingPlayer.balance - round.largestbet + bettingPlayer.currentBet - 3)/10);
            raiseAmount = 1; // starting slider position
            raiseLabel = new Text("How much would you like to raise? (" + raiseAmount + ")");


            raisePanelLabel.add(raiseLabel);
            raisePanelSlider.add(raiseSlider);
            constraints.gridy=7;
            constraints.gridx=0;
            constraints.gridwidth = 10;
            add(raisePanelLabel, constraints);
            constraints.gridy = 8;
            add(raisePanelSlider,constraints);


            raiseSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e){
                    raiseAmount = ((JSlider)e.getSource()).getValue();
                    raiseLabel.setText("How much would you like to raise? (" + raiseAmount + ")");
                }
            });
        }

        //
        constraints.gridy = 10;
        constraints.gridx = 0;
        constraints.gridwidth = 10;
        add(buttonPanel, constraints);

        buttonPanel.add(foldBtn);//the player can always fold

        if(bettingPlayer.balance <= round.largestbet){ //if they dont have enough money they can only go all in
            buttonPanel.add(allInBtn);
        }   else {
            buttonPanel.add(callBtn);
            buttonPanel.add(raiseBtn);
        }


        //TODO Action events for FOLD BUTTON

        //TODO CREATE ALL IN BUTTON


        checkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bettingPlayer.madeDecision = true;
                round.Players[round.nowBettingPlayerIndex] = bettingPlayer;
                synchronized(round.worker) {
                    round.worker.notify(); // notify the worker when the button is pressed
                }       
            }
        });

        callBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bettingPlayer.balance -= (round.largestbet-bettingPlayer.currentBet);
                round.pool +=(round.largestbet-bettingPlayer.currentBet);
                bettingPlayer.currentBet = round.largestbet;
                round.Players[round.nowBettingPlayerIndex] = bettingPlayer;
                synchronized(round.worker) {
                    round.worker.notify(); // notify the worker when the button is pressed
                }       
            }
        });

        raiseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bettingPlayer.balance-=raiseAmount+round.largestbet-bettingPlayer.currentBet;
                round.pool += raiseAmount+round.largestbet-bettingPlayer.currentBet;
                bettingPlayer.currentBet = round.largestbet + raiseAmount;
                round.largestbet = bettingPlayer.currentBet;
                round.Players[round.nowBettingPlayerIndex] = bettingPlayer;

                synchronized(round.worker) {
                    round.worker.notify(); // notify the worker when the button is pressed
                } 

            }
        });
        foldBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                bettingPlayer.folded=true;
                round.Players[round.nowBettingPlayerIndex]=bettingPlayer;
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
