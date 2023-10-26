package org.redfx.Screens;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.text.*;
import org.redfx.*;
import org.redfx.Objects.CoolButton;
import org.redfx.Objects.Title;

/**A class for the screen inbetween players. This way they cant see each others cards
 * but still play on one screen.
 */
public class ChangeToPlayerScreen extends JPanel {
    CoolButton nextBtn = new CoolButton("Next");
    Title title = new Title("");
    GridBagConstraints constraints = new GridBagConstraints();

    /**Constructing the screen with the current round.
     * 
     * @param round used to get the players information such as name.
     */
    public ChangeToPlayerScreen(Round round) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        setBorder(new EmptyBorder(0, 50, 0, 50));

        System.out.println("hey");
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        

        title.setText(round.Players[round.nowBettingPlayerIndex].name + "'s turn to bet!");

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
