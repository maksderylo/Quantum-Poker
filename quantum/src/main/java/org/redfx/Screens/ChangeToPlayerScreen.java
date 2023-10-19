package org.redfx.Screens;

import org.redfx.*;
import org.redfx.Objects.CoolButton;
import org.redfx.Objects.Title;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.text.*;

public class ChangeToPlayerScreen extends JPanel{
    CoolButton nextBtn = new CoolButton("Next");
    Title title = new Title("");
    GridBagConstraints constraints = new GridBagConstraints();


    public ChangeToPlayerScreen(Round round){
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        setBorder(new EmptyBorder(0,50,0,50));

        System.out.println("hey");
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        

        title.setText(round.Players[round.nowBettingPlayerIndex].name + "'s turn to bet!" );

        //title.setText(" turn to bet!" );
        //adding the title
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.gridx = 1;
        add(title, constraints);
        constraints.gridwidth = 1;

        JPanel buttonPanel = new JPanel(); // Change to JPanel for better button alignment
        constraints.gridy = 10;
        add(buttonPanel, constraints);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setOpaque(false);
        buttonPanel.add(nextBtn);

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                round.stateManager.switchToBettingScreen(round);
            }
        });

    }
}
