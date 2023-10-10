import java.awt.*;
import javax.swing.*;
//import java.awt.event.*;


public class Main {

    void demo() {
        JFrame frame = new JFrame("MF works!", null);
        JButton button = new JButton("ok", null);
        JLabel label = new JLabel("wadup", null, 0);

        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        label.setText("Welcome to Javatpoint.");  
                    }  
                });
        frame.add(button, BorderLayout.SOUTH);

        JPanel newPanel = new JPanel();
        frame.add(newPanel);
        frame.add(label, BorderLayout.NORTH);
        Color panelColor = new Color(50, 100, 5);

        newPanel.setBackground(panelColor);

        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main().demo();
    }
}