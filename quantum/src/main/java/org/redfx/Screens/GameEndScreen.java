package org.redfx.Screens;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.text.*;

import org.redfx.Objects.*;
import org.redfx.*;

public class GameEndScreen extends JPanel{
    Title title = new Title("");
    Title playerTitle = new Title("");
    GridBagConstraints constraints = new GridBagConstraints();
    CoolButton playBtn = new CoolButton("Menu");

    public GameEndScreen(Player winner, StateManager stateManager){
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        setBorder(new EmptyBorder(0,50,0,50));

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
        titlePanel.add(title);
        add(titlePanel, constraints);
        

        //adding the title
        CenterPanel playerPanel = new CenterPanel();
        constraints.gridy = 1;
        constraints.gridx = 0;
        playerTitle.setText("Congratulation " + winner.name);
        playerPanel.add(title);
        add(playerPanel, constraints);


        CenterPanel buttonPanel = new CenterPanel();
        constraints.gridy = 2;
        constraints.gridx = 0;
        buttonPanel.add(playBtn);
        add(playBtn, constraints);

        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                stateManager.switchToStartScreen();
            }
        });

    }
    
}
