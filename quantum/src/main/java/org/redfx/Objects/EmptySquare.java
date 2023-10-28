package org.redfx.Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EmptySquare extends JPanel {

    public EmptySquare() {
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setOpaque(false);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(60, 60);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTransparentSquareWithBorder(g);
    }

    private void drawTransparentSquareWithBorder(Graphics g) {
        // Set the border color to black
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, 40, 40); // Draw a black border
        g.setColor(new Color(0, 0, 0, 0)); // Transparent color
        g.fillRect(11, 11, 39, 39); // Leave a 1-pixel border for the black border
    }
}
