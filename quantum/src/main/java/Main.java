import java.awt.*;
import javax.swing.*;

class BackgroundPanel extends JPanel {
        final ImageIcon icon = new ImageIcon("mistzombie.jpg");
        Image img = icon.getImage();
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, null);
        }
}

public class Main {

    

    void demo() {
        
        JFrame frame = new JFrame("Display an image in the background");
        BackgroundPanel panel = new BackgroundPanel();
        frame.add(panel);




        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main().demo();
    }
}