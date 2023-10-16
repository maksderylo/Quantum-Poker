package org.redfx.Screens;

/* THIS IS A DUMMY EMPTY WINDOW YEAAAAAAA

 * public class LoadingScreen {
    private StateManager stateManager;
    final ImageIcon icon = new ImageIcon("src\\main\\java\\org\\redfx\\background.png");


    public LoadingScreen(StateManager stateManager, JFrame frame) {
        
        frame.setResizable(false);
        frame.pack();
        frame.setSize(540, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

 */
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.redfx.StateManager;
import org.redfx.Objects.CoolButton;
import org.redfx.Objects.Title;



public class LoadingScreen extends JPanel {

    final ImageIcon icon = new ImageIcon("src\\main\\resources\\background.png");
    CoolButton backBtn = new CoolButton("Back");
    CoolButton playBtn = new CoolButton("Next");
    Title title = new Title("Set up your game");
    JLabel playersTitle = new JLabel("How many players are going to play?");
    JSlider playersSlider = new JSlider(2, 6, 2);
    JPanel playersAmountPanel = new JPanel();
    JLabel moneyAmountTitle = new JLabel("Players money? ");
    JLabel moneyAmountAmount = new JLabel("MONEY");
    JSlider moneyAmountSlider = new JSlider(100, 100000, 100);
    JPanel moneyAmountPanel = new JPanel();
    private int playersAmount = 2;
    private int moneyPerPlayer = 100;



    public LoadingScreen(StateManager stateManager) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        setBorder(new EmptyBorder(0,50,0,50));
        GridBagConstraints constraints = new GridBagConstraints();

        // Weight and fill settings (no need to change these)
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Title
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.insets = new Insets(10, 0, 10, 0); // Add spacing
        add(title, constraints);

        // Players title and slider
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 0, 0, 0); // Reset insets
        add(playersAmountPanel, constraints);
        playersAmountPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playersAmountPanel.setOpaque(false);
        playersAmountPanel.add(playersTitle);
        playersAmountPanel.add(playersSlider);
        playersTitle.setForeground(Color.WHITE);
        playersSlider.setBackground(Color.BLACK);
        playersSlider.setMajorTickSpacing(1);
        playersSlider.setMinorTickSpacing(1);
        playersSlider.setPaintTrack(true);
        playersSlider.setPaintTicks(true);
        playersSlider.setPaintLabels(true);
        playersSlider.setForeground(Color.WHITE);
        playersSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                playersAmount = ((JSlider)e.getSource()).getValue();
            }
        });


        //money
        constraints.gridy = 3;
        moneyAmountPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        moneyAmountPanel.setOpaque(false);
        add(moneyAmountPanel, constraints);
        moneyAmountPanel.add(moneyAmountTitle);
        moneyAmountPanel.add(moneyAmountAmount);
        moneyAmountPanel.add(moneyAmountSlider);
        moneyAmountTitle.setForeground(Color.WHITE);
        moneyAmountAmount.setForeground(Color.WHITE);
        moneyAmountSlider.setBackground(Color.BLACK);
        moneyAmountSlider.setMajorTickSpacing(99900);
        moneyAmountSlider.setPaintTrack(true);
        moneyAmountSlider.setPaintTicks(true);
        moneyAmountSlider.setPaintLabels(true);
        moneyAmountSlider.setForeground(Color.WHITE);   
        moneyAmountSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                moneyAmountAmount.setText("(" + ((JSlider)e.getSource()).getValue() + ")");
                moneyPerPlayer = ((JSlider)e.getSource()).getValue();
            }
        });


        // Next and back buttons
        JPanel buttonPanel = new JPanel(); // Change to JPanel for better button alignment
        constraints.gridy = 10;
        add(buttonPanel, constraints);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setOpaque(false);
        buttonPanel.add(backBtn);
        buttonPanel.add(playBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateManager.switchToStartScreen();
            }
        });
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateManager.switchToPlayersNamesScreen(playersAmount, moneyPerPlayer);
            }
        });
    }
}

