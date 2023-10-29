package org.redfx.Screens;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.redfx.Objects.CenterPanel;
import org.redfx.Objects.CoolButton;
import org.redfx.StateManager;


public class StartScreen extends JLabel {
    final ImageIcon icon = new ImageIcon(getClass().getResource(
        "/org/redfx/resources/background.png"));
    CoolButton playBtn = new CoolButton("Standard Poker");
    CoolButton rulesBtn = new CoolButton("Rules");
    CoolButton quitBtn = new CoolButton("Quit");
    CoolButton quantumBtn = new CoolButton("Quantum Poker");
    CoolButton quantumRulesBtn = new CoolButton("Rules");

    
    private JFrame rulesFrame = null;
    private JFrame qRulesFrame = null;

    
    public StartScreen(StateManager stateManager, JFrame frame) {

       
        JTextArea rulesTextArea = new JTextArea();
        StringBuilder text = new StringBuilder();

        JTextArea qRulesTextArea = new JTextArea();
        StringBuilder qText = new StringBuilder();
        
        //reading the standard poker rules from the file:
        try {
            InputStream inputStream = StartScreen.class
                .getResourceAsStream("/org/redfx/resources/standardPokerRules.txt");
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String line;
                
                while ((line = reader.readLine()) != null) {
                    text.append(line).append("\n");
                }
                //rulesTextArea.setText(text.toString());
            } else {
                rulesTextArea.setText("Error loading the text file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            rulesTextArea.setText("An error occurred.");
        }

        //reading the quantum rules from file:
        try {
            InputStream inputStream = StartScreen.class
                .getResourceAsStream("/org/redfx/resources/quantumPokerRules.txt");
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String line;
                
                while ((line = reader.readLine()) != null) {
                    qText.append(line).append("\n");
                }
            } else {
                qRulesTextArea.setText("Error loading the text file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            rulesTextArea.setText("An error occurred.");
        }
        
        // Change to JPanel for better button alignment
        final CenterPanel buttonPanel = new CenterPanel(); 
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
        for (int i = 0; i < 8; i++) {
            constraints.gridy = i; // 2nd row
            add(Box.createRigidArea(new Dimension(10, 10)), constraints);
        }
       
        //Quantum Poker button
        constraints.gridy = 9;
        add(buttonPanel, constraints);
        buttonPanel.add(playBtn);
        buttonPanel.add(rulesBtn);
        
        //standard Poker button
        CenterPanel buttonPanel2 = new CenterPanel(); 
        constraints.gridy = 10; 
        add(buttonPanel2, constraints);
        buttonPanel2.add(quantumBtn);
        buttonPanel2.add(quantumRulesBtn);

        CenterPanel buttonPanel3 = new CenterPanel();
        constraints.gridy = 11;
        add(buttonPanel3, constraints);
        buttonPanel3.add(quitBtn);
        

        //Button actions names are self explanatory
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("To Load from start");
                stateManager.switchToLoadScreen(false);
            }
        });
        rulesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(rulesFrame);
                if (rulesFrame == null) {
                    rulesFrame = new JFrame("Rules", null);
                    JTextArea rulesTextArea = new JTextArea();
                    rulesTextArea.setText(text.toString());
                    rulesTextArea.setEditable(false);
                    rulesFrame.add(new JScrollPane(rulesTextArea));
                    
                    // Set the location of rulesFrame relative to the main frame
                    rulesFrame.setLocationRelativeTo(frame);

                    rulesFrame.pack();
                    rulesFrame.setSize(800, 800);
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

        quantumBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Loading quantum");
                stateManager.switchToLoadScreen(true);
            }
        });

        quantumRulesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (qRulesFrame == null) {
                    
                    qRulesFrame = new JFrame("Quantum Poker Rules", null);
                    qRulesTextArea.setText(qText.toString());
                    qRulesTextArea.setEditable(false);
                    qRulesFrame.add(new JScrollPane(qRulesTextArea));
                    
                    // Set the location of rulesFrame relative to the main frame
                    qRulesFrame.setLocationRelativeTo(frame);

                    qRulesFrame.pack();
                    qRulesFrame.setSize(800, 800);
                    qRulesFrame.setVisible(true);
                    qRulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    qRulesFrame.setResizable(false);
                    

                    qRulesFrame.addWindowListener(new WindowAdapter() {
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