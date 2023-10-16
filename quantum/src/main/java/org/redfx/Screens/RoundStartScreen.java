package org.redfx.Screens;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.text.*;

import org.redfx.Game;
import org.redfx.StateManager;
import org.redfx.Objects.*;
import javax.swing.JLabel;
import org.redfx.*;

public class RoundStartScreen extends JPanel{

    Title title = new Title("");
    GridBagConstraints constraints = new GridBagConstraints();
    CoolButton playBtn = new CoolButton("Next");



    public RoundStartScreen(Game game){
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
        constraints.gridwidth = 4;
        constraints.gridx = 1;
        title.setText("Round " + game.numberOfRounds);
        add(title, constraints);
        constraints.gridwidth = 1;


        JLabel helpLabel = new JLabel(""); 
        helpLabel.setForeground(Color.WHITE);
        
        for(int i = 0; i < game.amountOfPlayers; i++){
            constraints.gridy = i+1;
            helpLabel.setText(game.Players[i].name + ":");
            constraints.gridx = 0;
            add(helpLabel, constraints);
            helpLabel.setText(game.Players[i].balance + "");
            constraints.gridx = 1;
            add(helpLabel,constraints);

        }

        JPanel buttonPanel = new JPanel(); // Change to JPanel for better button alignment
        constraints.gridy = 10;
        constraints.gridx = 0;
        constraints.gridwidth=10;
        add(buttonPanel, constraints);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setOpaque(false);
        buttonPanel.add(playBtn);

        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        

        //has to show all the info in the beginning of the round

        
    }
    
}
