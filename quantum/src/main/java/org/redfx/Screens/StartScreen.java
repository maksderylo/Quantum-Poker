package org.redfx.Screens;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import org.redfx.Objects.CoolButton;
import org.redfx.StateManager;


public class StartScreen extends JLabel {
    final ImageIcon icon = new ImageIcon(getClass().getResource(
        "/org/redfx/resources/background.png"));
    CoolButton playBtn = new CoolButton("Play");
    CoolButton rulesBtn = new CoolButton("Rules");
    CoolButton quitBtn = new CoolButton("Quit");

    
    
    private JFrame rulesFrame = null;
    
    public StartScreen(StateManager stateManager, JFrame frame) {
        // Background
        System.out.println("this is the startscreen");

        JPanel buttonPanel = new JPanel(); // Change to JPanel for better button alignment
        setIcon(icon);
        setLayout(new GridBagLayout());
        
        
        // Center the buttons horizontally
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setOpaque(false);

        GridBagConstraints constraints = new GridBagConstraints();
    
        // Weight and fill settings (no need to change these)
        constraints.weightx = 1;
        constraints.weighty = 1;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
    
        // Position the backBtn in the desired location
        constraints.gridx = 1; // 2nd column
        constraints.gridy = 1; // 2nd row
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(Box.createRigidArea(new Dimension(10, 10)), constraints);

        constraints.gridy = 2;
        add(buttonPanel, constraints);
        buttonPanel.add(playBtn);
        buttonPanel.add(rulesBtn);
        buttonPanel.add(quitBtn);

        //Button actions names are self explanatory
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("To Load from start");
                stateManager.switchToLoadScreen();
            }
        });
        rulesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(rulesFrame);
                if (rulesFrame == null) {
                    rulesFrame = new JFrame("Rules", null);
                    JTextArea rulesTextArea = new JTextArea("Put your rules here.");
                    rulesTextArea.setEditable(false);
                    rulesFrame.add(new JScrollPane(rulesTextArea));
                    
                    // Set the location of rulesFrame relative to the main frame
                    rulesFrame.setLocationRelativeTo(frame);

                    rulesFrame.pack();
                    rulesFrame.setSize(540, 800);
                    rulesFrame.setVisible(true);
                    rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    rulesFrame.setResizable(false);
                    

                    rulesFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            rulesFrame = null; // Set rulesFrame to null when it's disposed
                        }
                    });
                }
            }
        });

        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the JFrame
                rulesFrame.dispose();
            }
        });


        
    }


    public static void main(String[] args) {
    }

}
