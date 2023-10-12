package org.redfx;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class LoadingScreen extends JLabel{

    final ImageIcon icon = new ImageIcon("src\\main\\resources\\background.png");
    JButton backBtn = new JButton("Play", null);

    public LoadingScreen(StateManager stateManager, JFrame frame) {

        setIcon(icon);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(backBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("To Load from start");
                stateManager.switchToStartScreen();
            }
        });

    }

}
