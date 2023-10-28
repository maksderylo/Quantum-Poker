package org.redfx.Screens;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.redfx.Objects.CenterPanel;
import org.redfx.Objects.CoolButton;
import org.redfx.StateManager;


public class StartScreen extends JLabel{
    final ImageIcon icon = new ImageIcon(getClass().getResource(
        "/org/redfx/resources/background.png"));
    CoolButton playBtn = new CoolButton("Poker");
    CoolButton rulesBtn = new CoolButton("Rules");
    CoolButton quitBtn = new CoolButton("Quit");
    CoolButton quantumBtn = new CoolButton("Quantum Poker");
    CoolButton quantumRulesBtn = new CoolButton("Rules");

    
    private JFrame rulesFrame = null;

    
    public StartScreen(StateManager stateManager, JFrame frame){
        // Background
        

        CenterPanel buttonPanel = new CenterPanel(); // Change to JPanel for better button alignment
        setIcon(icon);
        setLayout(new GridBagLayout());
        


        GridBagConstraints constraints = new GridBagConstraints();
    
        // Weight and fill settings (no need to change these)
        constraints.weightx = 1;
        constraints.weighty = 1;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
    
        constraints.gridx = 1; // 2nd column
        constraints.gridy = 1; // 2nd row
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(Box.createRigidArea(new Dimension(10, 10)), constraints);
        constraints.gridy = 2; // 2nd row
        add(Box.createRigidArea(new Dimension(10, 10)), constraints);
        constraints.gridy = 3; // 3nd row
        add(Box.createRigidArea(new Dimension(10, 10)), constraints);
        constraints.gridy = 4; // 4nd row

        add(Box.createRigidArea(new Dimension(10, 10)), constraints);


        constraints.gridy = 5;
        add(buttonPanel, constraints);
        buttonPanel.add(playBtn);
        buttonPanel.add(rulesBtn);
        


        CenterPanel buttonPanel2 = new CenterPanel(); 
        
        constraints.gridy = 6; // Move to the 5th row
        add(buttonPanel2, constraints);
        buttonPanel2.add(quantumBtn);
        buttonPanel2.add(quantumRulesBtn);
        buttonPanel2.add(quitBtn);
        

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

        quantumRulesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rulesFrame == null) {
                    rulesFrame = new JFrame("Quantum Poker Rules", null);
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


        
    }


    public static void main(String[] args) {
    }

}
