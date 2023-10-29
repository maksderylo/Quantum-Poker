package org.redfx.Screens;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import org.redfx.Game;
import org.redfx.Objects.CoolButton;
import org.redfx.Objects.Title;
import org.redfx.StateManager;

/**A class for the screen at which the players are able to enter their names. */
public class PlayersNamesScreen extends JPanel {
    //Creating variables most of them are self explanatory
    CoolButton backBtn;
    CoolButton playBtn;
    Title title = new Title("Set up players names");
    Color backColor;
    Color foreColor;
    Color quantumColor = new Color(165, 165, 165);
    GridBagConstraints constraints = new GridBagConstraints();
    Boolean isEmptyName;
    boolean quantum;

    /**A constructor for the screen, letting the players enter their names, and even displaying
     * an error message if a name is empty. It also stores the names and their index for future use.
     * 
     * @param stateManager used to change to other screens. 
     * @param playersAmount the amount of players participating, 
     *      so it knows for how many names it should ask
     * @param moneyPerPlayer the starting balance of the players.
     */
    public PlayersNamesScreen(StateManager stateManager, 
        int playersAmount, int  moneyPerPlayer, boolean quantum) {
        this.quantum = quantum;

        if (!quantum) {
            backColor = Color.BLACK;
            foreColor = Color.WHITE;
            backBtn = new CoolButton("Back");
            playBtn = new CoolButton("Next");
            
        } else {
            backColor = Color.LIGHT_GRAY;
            foreColor = Color.BLACK;
            title.setForeground(foreColor);
            backBtn = new CoolButton("Back", quantumColor);
            playBtn = new CoolButton("Next", quantumColor);
        }

        

        setLayout(new GridBagLayout());
        setBackground(backColor);
        setBorder(new EmptyBorder(0, 50, 0, 50));
        String[] storeNames = new String[playersAmount];
        JLabel[] labels = new JLabel[playersAmount];
        JTextArea[] nameTextAreas = new JTextArea[playersAmount];
        

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
        add(title, constraints);
        constraints.gridwidth = 1;


        for (int i = 0; i < playersAmount; i++) {
            // Create a label with the player's number
            labels[i] = new JLabel("Players " + (i + 1) + " name: ");
            labels[i].setForeground(foreColor);
            //Create a text area for that player
            nameTextAreas[i] = new JTextArea(1, 5);
            nameTextAreas[i].setText("Player " + (i + 1));
            storeNames[i] = "Player " + (i + 1);


            // Create a DocumentFilter to limit the text length
            ((javax.swing.text.AbstractDocument) nameTextAreas[i].getDocument())
                .setDocumentFilter(new DocumentFilter() {
                    @Override
                public void insertString(FilterBypass fb, int offset, 
                        String string, AttributeSet attr)
                        throws BadLocationException {
                        if ((fb.getDocument().getLength() + string.length()) <= 20) {
                            super.insertString(fb, offset, string, attr);
                        } else {
                        //Do nothing
                        }
                    }

                    @Override
                public void replace(FilterBypass fb, int offset, 
                        int length, String text, AttributeSet attrs)
                        throws BadLocationException {
                        if ((fb.getDocument().getLength() + text.length() - length) <= 20) {
                            super.replace(fb, offset, length, text, attrs);
                        } else {
                        // Do nothing
                        }
                    }
                });

            //Storing the labels values on change inside the storeNames array
            final int currentPlayerIndex = i;
            nameTextAreas[i].getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    storeNames[currentPlayerIndex] = nameTextAreas[currentPlayerIndex].getText();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    storeNames[currentPlayerIndex] = nameTextAreas[currentPlayerIndex].getText();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    // This has to be here cause documentListener expects it
                }
            });


            // Set the gridy for the label
            constraints.gridy = i + 1;
            constraints.gridx = 1;
            // Add the label to the panel
            add(labels[i], constraints);
            constraints.gridx = 2;
            constraints.gridwidth = 2;
            add(nameTextAreas[i], constraints);
            constraints.gridwidth = 1;
        }



        JPanel buttonPanel = new JPanel(); // Change to JPanel for better button alignment
        constraints.gridy = 10;
        constraints.gridx = 0;
        constraints.gridwidth = 10;
        add(buttonPanel, constraints);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setOpaque(false);
        buttonPanel.add(backBtn);
        buttonPanel.add(playBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateManager.switchToLoadScreen(quantum);
            }
        });
        
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isEmptyName = false;
                for (int i = 0; i < playersAmount; i++) {
                    if (storeNames[i].equals("")) {
                        isEmptyName = true;
                        break;
                    }
                }
                if (isEmptyName) {
                    JOptionPane.showMessageDialog(PlayersNamesScreen.this,
                         "All names have to be filled!", "WARNING", JOptionPane.WARNING_MESSAGE);
                } else {
                    new Game(playersAmount, moneyPerPlayer, storeNames, stateManager, quantum);
                }
            }
        });

    }



    
}
