import org.redfx.strange.algorithm.Classic;
import java.awt.*;
import javax.swing.*;

public class Main {

    void demo() {
        JFrame frame = new JFrame("MF works!", null);
        JButton button = new JButton("ok", null);

        frame.add(button, BorderLayout.SOUTH);

        JPanel newPanel = new JPanel();
        frame.add(newPanel);
        int bit = Classic.randomBit();
        String text = "" + bit;
        JLabel label = new JLabel(text, null, 0);
        frame.add(label, BorderLayout.NORTH);
        Color panelColor = new Color(50, 100, 5);

        newPanel.setBackground(panelColor);

        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main().demo();
    }
}