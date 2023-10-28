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
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.redfx.Objects.CenterPanel;
import org.redfx.Objects.CoolButton;
import org.redfx.Objects.Slider;
import org.redfx.Objects.Text;
import org.redfx.Objects.Title;
import org.redfx.StateManager;



/**A class for the loading-screen of the game. The loading-screen lets the player(s) choose
 * with how many players they're playing and what their starting balance is.
  */
public class LoadingScreen extends JPanel {

    final ImageIcon icon = new ImageIcon("src\\main\\resources\\background.png");
    Color quantumColor = new Color(165, 165, 165);
    CoolButton backBtn;
    CoolButton playBtn;
    Title title = new Title("Set up your game");
    Text playersTitle = new Text("How many players are going to play?");
    Slider playersSlider;
    JPanel playersAmountPanel = new JPanel();
    Text moneyAmountTitle = new Text("Players money? ");
    Text moneyAmountAmount = new Text("MONEY");
    Slider moneyAmountSlider;
    JPanel moneyAmountPanel = new JPanel();
    private int playersAmount = 2;
    private int moneyPerPlayer = 100;
    boolean quantum;

    /**Constructing the loading-screen by adding two sliders for the amount of players
     * and the amount of money, in addition to buttons to show the rules, move on or exit.
     */
    public LoadingScreen(StateManager stateManager, boolean quantum) {
        this.quantum = quantum;
        setLayout(new GridBagLayout());
        if (!quantum) {
            setBackground(Color.BLACK);
            backBtn = new CoolButton("Back");
            playBtn = new CoolButton("Next");
            playersSlider = new Slider(2, 6, 2);
            moneyAmountSlider = new Slider(100, 100000, 100);
        } else {
            setBackground(Color.LIGHT_GRAY);
            backBtn = new CoolButton("Back", quantumColor);
            playBtn = new CoolButton("Next", quantumColor);
            playersSlider = new Slider(2, 6, 2, Color.LIGHT_GRAY);
            moneyAmountSlider = new Slider(100, 100000, 100, Color.LIGHT_GRAY);
            title.setForeground(Color.BLACK);
            moneyAmountTitle.setForeground(Color.BLACK);
            moneyAmountAmount.setForeground(Color.BLACK);
            playersTitle.setForeground(Color.BLACK);
        }
        
        setBorder(new EmptyBorder(0, 50, 0, 50));
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
        
        playersSlider.setMajorTickSpacing(1);
        playersSlider.setMinorTickSpacing(1);
        playersSlider.setPaintTrack(true);
        playersSlider.setPaintTicks(true);
        

        playersSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                playersAmount = ((JSlider) e.getSource()).getValue();
            }
        });


        //money
        constraints.gridy = 3;
        moneyAmountPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        moneyAmountPanel.setOpaque(false);
        add(moneyAmountPanel, constraints);
        moneyAmountPanel.add(moneyAmountTitle);
        moneyAmountAmount.setText("(" + moneyPerPlayer + ")");

        moneyAmountPanel.add(moneyAmountAmount);
        moneyAmountPanel.add(moneyAmountSlider);
        moneyAmountSlider.setMajorTickSpacing(99900);
        moneyAmountSlider.setMinorTickSpacing(100);
        moneyAmountSlider.setPaintTicks(false);
        moneyAmountSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                moneyPerPlayer = ((JSlider) e.getSource()).getValue();
                moneyAmountAmount.setText("(" + moneyPerPlayer + ")");
                
            }
        });


        // Next and back buttons
        CenterPanel buttonPanel = new CenterPanel(); // Change to JPanel for better button alignment
        constraints.gridy = 10;
        add(buttonPanel, constraints);

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
                stateManager.switchToPlayersNamesScreen(playersAmount, moneyPerPlayer, quantum);
            }
        });
    }
}

