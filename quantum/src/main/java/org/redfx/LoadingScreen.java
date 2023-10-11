package org.redfx;


import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


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

public class LoadingScreen {

    final ImageIcon icon = new ImageIcon("src\\main\\java\\org\\redfx\\background.png");


    public LoadingScreen(StateManager stateManager, JFrame frame) {

        JLabel startPanel = new JLabel(icon);
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        

        frame.add(startPanel);
        frame.setResizable(false);
        frame.pack();
        frame.setSize(540, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
