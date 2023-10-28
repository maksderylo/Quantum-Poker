package org.redfx;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;



public class QuantumSquareNotApplied extends JPanel{
    public Boolean active = false;
    public Boolean used = false;
    private JSpinner spinner;
    private JLabel label;
    private JLabel title;
    private JLabel titleControlled;
    private JSpinner spinnerControlled;
    private JButton button;
    public char letter;

    public QuantumSquareNotApplied(QuantumScreen quantumScreen, char qubitLetter) {
        this.letter = qubitLetter;
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setOpaque(false);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the mouse click event here
                // You can add your code to respond to the click event
                if (!used) {
                    if (active) {
                        active = false;
                    } else {
                        active = true;
                    }
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        if (letter == 'H') {
            title = new JLabel("Hardamand on");
            this.add(title);
            title.setVisible(false);
            label = new JLabel("Qubit");
            this.add(label);
            label.setVisible(false);
            spinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
            this.add(spinner);
            spinner.setVisible(false); // Initially, the spinner is not visible
            button = new JButton("Apply");
            this.add(button);
            button.setVisible(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //haha
                    System.out.println("clicked");
                    int value = (Integer) spinner.getValue();
                    used = true;
                    active = false;
                    quantumScreen.applyGate(value, letter);
                    repaint();
                }

            });
        } else if (letter == 'N') {
            title = new JLabel("Gate NOT on");
            this.add(title);
            title.setVisible(false);
            label = new JLabel("Qubit");
            this.add(label);
            label.setVisible(false);
            spinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
            this.add(spinner);
            spinner.setVisible(false); // Initially, the spinner is not visible
            button = new JButton("Apply");
            this.add(button);
            button.setVisible(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //haha
                    System.out.println("clicked");
                    int value = (Integer) spinner.getValue();
                    used = true;
                    active = false;
                    quantumScreen.applyGate(value, letter);
                    repaint();
                }

            });
        } else if (letter == 'C') {
            title = new JLabel("Controlled NOT");
            this.add(title);
            title.setVisible(false);
            label = new JLabel("Control: ");
            this.add(label);
            label.setVisible(false);
            spinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
            this.add(spinner);
            spinner.setVisible(false); // Initially, the spinner is not visible
            titleControlled = new JLabel("Target: ");
            this.add(titleControlled);
            titleControlled.setVisible(false);
            spinnerControlled = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
            this.add(spinnerControlled);
            spinnerControlled.setVisible(false);
            button = new JButton("Apply");
            this.add(button);
            button.setVisible(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //haha
                    System.out.println("clicked");
                    int value = (Integer) spinner.getValue();
                    int valueTwo = (Integer) spinnerControlled.getValue();
                    if (value == valueTwo) {
                        JOptionPane.showMessageDialog(null,
                            "You shouldn't entangle a qubit with itself dummy!",
                             "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        used = true;
                        active = false;
                        quantumScreen.applyGate(value, letter);
                        quantumScreen.lastNotFilledColumn--;
                        quantumScreen.applyGate(valueTwo, 'T');
                        repaint();
                    }
                }

            });
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 120); // Set your desired width and height
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (active) {
            drawForm(g);
        } else {
            drawSquareWithLetter(g);
        }
    }

    private void drawSquareWithLetter(Graphics g) {
        // Create a blue square
        if (used) {
            g.setColor(Color.GRAY);
            
        } else {
            if (letter == 'H'){
                g.setColor(Color.BLUE);
            } else if (letter == 'N') {
                g.setColor(Color.RED);
            } else if (letter == 'C') {
                g.setColor(Color.GREEN);
            }
        }
        g.fillRect(30, 30, 40, 40);

        // Set the color to white for drawing the letter
        g.setColor(Color.WHITE);

        // Create a font for the letter
        Font font = new Font("SansSerif", Font.BOLD, 16);
        g.setFont(font);

        // Get the size of the letter "H"
        FontMetrics metrics = g.getFontMetrics(font);
        int letterWidth = metrics.stringWidth(Character.toString(letter));
        int letterHeight = metrics.getHeight();

        // Calculate the position to center the letter "H" in the square
        int x = (100 - letterWidth) / 2;
        int y = (100 - letterHeight) / 2 + metrics.getAscent();

        // Draw the letter "H" in the center of the square
        g.drawString(Character.toString(letter), x, y);

        spinner.setVisible(false); // Initially, the spinner is not visible
        label.setVisible(false);
        button.setVisible(false);
        title.setVisible(false);
        if (letter == 'C') {
            titleControlled.setVisible(false);
            spinnerControlled.setVisible(false);
        }
    }


    private void drawForm(Graphics g) {
        if (letter == 'C') {
            g.setColor(Color.BLACK);
            g.fillRect(5, 0, 90, 110);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(6, 1, 88, 108);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(5, 0, 90, 90);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(6, 1, 88, 88);
        }
        


        
        spinner.setVisible(true); // Initially, the spinner is not visible
        label.setVisible(true);
        button.setVisible(true);
        title.setVisible(true);
        if (letter == 'C') {
            titleControlled.setVisible(true);
            spinnerControlled.setVisible(true);
        }
    }

    
    


    
}
