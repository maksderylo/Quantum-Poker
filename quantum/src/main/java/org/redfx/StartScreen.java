package org.redfx;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class StartScreen {
    final ImageIcon icon = new ImageIcon("src\\main\\java\\org\\redfx\\background.png");
    JButton playBtn = new JButton("Play", null);
    JButton rulesBtn = new JButton("Rules", null);
    JButton quitBtn = new JButton("Quit", null);

    
    
    private JFrame rulesFrame = null;
    
    public StartScreen(StateManager stateManager, JFrame frame){
        // Background

        
        
        JLabel startPanel = new JLabel(icon);
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel(); // Change to JPanel for better button alignment

        
        //Buttons look
        playBtn.setFont(new Font("LiSong Pro",Font.PLAIN,20));
        playBtn.setBackground(new Color(0,17,51));//import java.awt.Color;
        playBtn.setForeground(Color.WHITE);
        playBtn.setFocusPainted(false);
        playBtn.setBorderPainted(true);
        
        // Center the buttons horizontally
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setOpaque(false);

        startPanel.add(Box.createVerticalGlue());
        startPanel.add(Box.createVerticalGlue());
        startPanel.add(Box.createVerticalGlue());
        startPanel.add(Box.createVerticalGlue());
        startPanel.add(Box.createVerticalGlue());  // Add some space at the top
        startPanel.add(buttonPanel);
        startPanel.add(Box.createVerticalGlue());

        buttonPanel.add(playBtn);
        buttonPanel.add(rulesBtn);
        buttonPanel.add(quitBtn);

        //Button actions names are self explanatory
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("To Load from start");
                stateManager.switchToLoadScreen();
            }
        });
        rulesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(rulesFrame);
                if(rulesFrame == null) {
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
            }
        });



        frame.add(startPanel);
        frame.setResizable(false);
        frame.pack();
        frame.setSize(540, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
    }

}
