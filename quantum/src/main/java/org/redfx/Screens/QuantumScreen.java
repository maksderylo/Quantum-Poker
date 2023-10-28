package org.redfx.Screens;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.redfx.Objects.CenterPanel;
import org.redfx.Objects.EmptySquare;
import org.redfx.Objects.Player;import org.redfx.Objects.QuantumSquareApplied;
import org.redfx.Objects.QuantumSquareNotApplied;
import org.redfx.Objects.Title;
import org.redfx.strange.Program;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.Step;
import org.redfx.strange.gate.Cnot;
import org.redfx.strange.gate.Hadamard;
import org.redfx.strange.gate.X;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;


public class QuantumScreen extends JPanel {
    Title title = new Title("Apply your gates");
    String[][] appliedGates = new String[5][4];
    ArrayList<Character> table;
    GridBagConstraints constraints = new GridBagConstraints();
    public int lastNotFilledColumn = 0;
    EmptySquare[][] emptySquareArray = new EmptySquare[5][4];
    Player player;

    public QuantumScreen(ArrayList<Character> table, Player player) {
        this.player = player;
        this.table = table;
        System.out.println("wasup");
        setLayout(new GridBagLayout());
        setBackground(Color.LIGHT_GRAY);
        setBorder(new EmptyBorder(0, 50, 0, 50));
        GridBagConstraints constraints = new GridBagConstraints();

        // Weight and fill settings (no need to change these)
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Title
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        constraints.insets = new Insets(10, 0, 10, 0); // Add spacing
        add(title, constraints);

        //Setting the applied gates to all empty
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                emptySquareArray[i][j] = new EmptySquare();
                appliedGates[i][j] = "EmptySquare";
            }
        }

        paintAppliedGates();


        CenterPanel centerPanel = new CenterPanel();
        constraints.gridy = 8;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        add(centerPanel, constraints);

        QuantumSquareNotApplied[] playerGates = new QuantumSquareNotApplied[4];
        for (int i = 0; i < 4; i++) {
            playerGates[i] = new QuantumSquareNotApplied(this, player.hand.get(i).charAt(0));
            centerPanel.add(playerGates[i]);
        }


        //add buttons
        CenterPanel buttonPanel = new CenterPanel();
        constraints.gridy = 9;
        add(buttonPanel, constraints);
        JButton probabilities = new JButton("What are the odds?");
        //TODO change to cool button
        buttonPanel.add(probabilities);
        JButton proceed = new JButton("Proceed!");
        //TODO change to cool button
        buttonPanel.add(proceed);

        probabilities.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //haha
                    System.out.println("Probabilities");
                    QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
                    Program program = new Program(5);
                    Step step = new Step();

                    for (int i = 0; i < 5; i++) {
                        if (table.get(i) == '1') {
                            step = new Step();
                            step.addGate(new X(i));
                            program.addStep(step);
                        } else if (table.get(i) == '+') {
                            step = new Step();
                            step.addGate(new Hadamard(i));
                            program.addStep(step);
                        } else if (table.get(i) == '-'){
                            step = new Step();
                            step.addGate(new X(i));
                            program.addStep(step);
                            step = new Step();
                            step.addGate(new Hadamard(i));
                            program.addStep(step);
                        }

                        


                    }
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (appliedGates[i][j].equals("Hardamand")) {
                                step = new Step();
                                step.addGate(new Hadamard(i));
                                program.addStep(step);
                            } else if (appliedGates[i][j].equals("Not")) {
                                step = new Step();
                                step.addGate(new X(i));
                                program.addStep(step);
                            } else if (appliedGates[i][j].equals("Control")) {
                                int target = 0;
                                while (true) {
                                    if (appliedGates[target][j].equals("Target")) {
                                        break;
                                    }
                                    target++;
                                }
                                step = new Step();
                                step.addGate(new Cnot(i, target));
                                program.addStep(step);
                            }
                        } 
                    }

                    simulator.runProgram(program);
                    Renderer.showProbabilities(program, 1000);

                }

        });
        proceed.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //haha
                    System.out.println("clicked");
                    System.out.println("Proceed");
                    QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
                    Program program = new Program(5);
                    Step step = new Step();

                    for (int i = 0; i < 5; i++) {
                        if (table.get(i) == '1') {
                            step = new Step();
                            step.addGate(new X(i));
                            program.addStep(step);
                        } else if (table.get(i) == '+') {
                            step = new Step();
                            step.addGate(new Hadamard(i));
                            program.addStep(step);
                        } else if (table.get(i) == '-'){
                            step = new Step();
                            step.addGate(new X(i));
                            program.addStep(step);
                            step = new Step();
                            step.addGate(new Hadamard(i));
                            program.addStep(step);
                        }

                        


                    }
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (appliedGates[i][j].equals("Hardamand")) {
                                step = new Step();
                                step.addGate(new Hadamard(i));
                                program.addStep(step);
                            } else if (appliedGates[i][j].equals("Not")) {
                                step = new Step();
                                step.addGate(new X(i));
                                program.addStep(step);
                            } else if (appliedGates[i][j].equals("Control")) {
                                int target = 0;
                                while (true) {
                                    if (appliedGates[target][j].equals("Target")) {
                                        break;
                                    }
                                    target++;
                                }
                                step = new Step();
                                step.addGate(new Cnot(i, target));
                                program.addStep(step);
                            }
                        } 
                    }

                    simulator.runProgram(program);
                    
                }

        });
        
    }

    private void paintAppliedGates() {
        constraints.gridwidth = 1;
        constraints.gridheight = 1;

        for (int i = 0; i < 5; i++) {
            JLabel qubit = new JLabel("|" + table.get(i) + ">");
            constraints.gridy = i + 1;

            constraints.gridx = 0;
            add(qubit, constraints);
            for (int j = 1; j < 5; j++) {
                constraints.gridx = j;

                if (appliedGates[i][j - 1].equals("EmptySquare")) {
                    remove(emptySquareArray[i][j - 1]);
                    add(emptySquareArray[i][j - 1], constraints);
                } else {
                    remove(emptySquareArray[i][j - 1]);
                    add(new QuantumSquareApplied(this, appliedGates[i][j - 1].charAt(0)),
                        constraints);
                }

            }
        }

    }

    public void applyGate(int row, char gate) {
        String gateName = "";
        if (gate == 'H') {
            gateName = "Hardamand";
        } else if (gate == 'N') {
            gateName = "Not";
        } else if (gate == 'C') {
            gateName = "Control";
        } else if (gate == 'T') {
            gateName = "Target";
        }


        appliedGates[row - 1][lastNotFilledColumn] = gateName;
        lastNotFilledColumn++;
        paintAppliedGates();
    }

}

