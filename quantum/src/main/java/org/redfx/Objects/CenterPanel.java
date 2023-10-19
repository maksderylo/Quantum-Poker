package org.redfx.Objects;

import java.awt.Component;

import javax.swing.JPanel;

public class CenterPanel extends JPanel{
    public CenterPanel(){
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setOpaque(false);
    }
}
