package org.redfx;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class StartScreen extends JLabel{
    final ImageIcon icon = new ImageIcon("src\\main\\resources\\background.png");
    JButton playBtn = new JButton("Play", null);
    JButton rulesBtn = new JButton("Rules", null);
    JButton quitBtn = new JButton("Quit", null);

    
    
    private JFrame rulesFrame = null;
    
    public StartScreen(StateManager stateManager, JFrame frame){
        // Background
        setIcon(icon);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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

        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());  // Add some space at the top
        add(buttonPanel);
        add(Box.createVerticalGlue());

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
                rulesFrame.dispose();
            }
        });
    }

    public static void main(String[] args) {
    }

}
