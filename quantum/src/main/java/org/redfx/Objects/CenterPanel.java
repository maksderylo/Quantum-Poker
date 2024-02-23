package org.redfx.Objects;

import java.awt.Component;
import javax.swing.JPanel;

/** Panel that centers the variables.*/
public class CenterPanel extends JPanel{
    /** Setting the alignment and the background. */
    public CenterPanel(){
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setOpaque(false);
    }
}
