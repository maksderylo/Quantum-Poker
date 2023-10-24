package org.redfx.Screens;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.text.*;

import org.redfx.Objects.*;
import org.redfx.*;

public class RoundEndScreen extends JPanel{
    Title title = new Title("");
    Title poolTitle = new Title("");
    GridBagConstraints constraints = new GridBagConstraints();
    CoolButton playBtn = new CoolButton("Proceed");

    public RoundEndScreen(Round round){
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        setBorder(new EmptyBorder(0,50,0,50));

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        //adding the title
        constraints.gridy = 0;
        constraints.gridwidth = 10;
        constraints.gridx = 0;
        title.setText("Round " + round.roundNumber + " has concluded!");
        add(title, constraints);
        constraints.gridwidth = 1;


        constraints.gridwidth=1;
        JLabel helpLabel = new JLabel(""); 

        helpLabel = new JLabel("Name");
        helpLabel.setForeground(Color.WHITE);
        constraints.gridy = 3;
        constraints.gridx = 0;
        add(helpLabel, constraints);
        helpLabel = new JLabel("Money won/lost"); 
        helpLabel.setForeground(Color.WHITE);
        constraints.gridx = 1;
        add(helpLabel,constraints);
        helpLabel = new JLabel("Balance"); 
        helpLabel.setForeground(Color.WHITE);
        constraints.gridx = 2;
        add(helpLabel,constraints);


        for(int i = 0; i < round.amountOfPlayers; i++){
            helpLabel = new JLabel(round.Players[i].name + ":");
            helpLabel.setForeground(Color.WHITE);
            constraints.gridy = i+4;
            constraints.gridx = 0;
            add(helpLabel, constraints);
            int helpInt = round.Players[i].balance-round.Players[i].roundStartBalance;
            helpLabel = new JLabel(helpInt + "$"); 
            helpLabel.setForeground(Color.WHITE);
            constraints.gridx = 1;
            add(helpLabel,constraints);

            
            helpLabel = new JLabel(round.Players[i].balance + "$"); 
            helpLabel.setForeground(Color.WHITE);
            constraints.gridx = 2;
            add(helpLabel,constraints);
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
                round.endRound();

            }
        });

    }
}
