package org.redfx.Screens;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.text.*;

import org.redfx.Objects.*;
import org.redfx.*;

public class RoundStartScreen extends JPanel{

    Title title = new Title("");
    Title poolTitle = new Title("");
    GridBagConstraints constraints = new GridBagConstraints();
    CoolButton playBtn = new CoolButton("Next");
    CenterPanel poolPanel = new CenterPanel();
    CenterPanel tableCardsPanel = new CenterPanel();



    public RoundStartScreen(Round round, String phase){
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        setBorder(new EmptyBorder(0,50,0,50));


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
        constraints.gridy=1;
        constraints.gridwidth = 10;
        add(poolPanel,constraints);
        poolPanel.add(poolTitle);


        //showing the cards
        String card, suit, rank;
        int spaceIndex;

        if(round.tableCards.size()>0){
        constraints.gridy =2;
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
        
        constraints.gridwidth=1;
        JLabel helpLabel = new JLabel(""); 

        helpLabel = new JLabel("Name");
        helpLabel.setForeground(Color.WHITE);
        constraints.gridy = 3;
        constraints.gridx = 0;
        add(helpLabel, constraints);
        helpLabel = new JLabel("Balance"); 
        helpLabel.setForeground(Color.WHITE);
        constraints.gridx = 1;
        add(helpLabel,constraints);
        helpLabel = new JLabel("Role"); 
        helpLabel.setForeground(Color.WHITE);
        constraints.gridx = 2;
        add(helpLabel,constraints);

        
        for(int i = 0; i < round.amountOfPlayers; i++){
            helpLabel = new JLabel(round.Players[i].name + ":");
            helpLabel.setForeground(Color.WHITE);
            constraints.gridy = i+4;
            constraints.gridx = 0;
            add(helpLabel, constraints);
            helpLabel = new JLabel(round.Players[i].balance + "$"); 
            helpLabel.setForeground(Color.WHITE);
            constraints.gridx = 1;
            add(helpLabel,constraints);

            if(round.Players[i].outOfTheGame == true){
                helpLabel = new JLabel("Out of the game"); 
                helpLabel.setForeground(Color.WHITE);
                constraints.gridx = 2;
                add(helpLabel,constraints);
            
            } else if(round.Players[i].allIn == true){
                helpLabel = new JLabel("All In!!"); 
                helpLabel.setForeground(Color.WHITE);
                constraints.gridx = 2;
                add(helpLabel,constraints);
            
            }else if(round.Players[i].folded == true){
                helpLabel = new JLabel("folded"); 
                helpLabel.setForeground(Color.WHITE);
                constraints.gridx = 2;
                add(helpLabel,constraints);
            
            } else if(round.Players[i].role == 2){
                helpLabel = new JLabel("Big Blind"); 
                helpLabel.setForeground(Color.WHITE);
                constraints.gridx = 2;
                add(helpLabel,constraints);
            } else if(round.Players[i].role == 1){
                helpLabel = new JLabel("Small Blind"); 
                helpLabel.setForeground(Color.WHITE);
                constraints.gridx = 2;
                add(helpLabel,constraints);
            } else{
                helpLabel = new JLabel("Playing"); 
                helpLabel.setForeground(Color.WHITE);
                constraints.gridx = 2;
                add(helpLabel,constraints);
            }

        }

        JPanel buttonPanel = new JPanel(); // Change to JPanel for better button alignment
        constraints.gridy = 15;
        constraints.gridx = 0;
        constraints.gridwidth=10;
        add(buttonPanel, constraints);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setOpaque(false);
        buttonPanel.add(playBtn);

        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(phase.equals("Preflop")){
                round.FirstBets();
                }   else if(phase.equals("The flop")){
                    round.secondBettingRound();
                }   else if(phase.equals("The turn")){
                    round.thirdBettingRound();
                }   else if(phase.equals("The river")){
                    round.forthBettingRound();
                }   

            }
        });
        

        //has to show all the info in the beginning of the round

        
    }
    
}
