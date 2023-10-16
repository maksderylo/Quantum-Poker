package org.redfx.Objects;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Title extends JLabel{
    public Title(String text){
        setText(text);
        setFont(new Font("Serif", Font.BOLD, 40));
        setForeground(Color.WHITE);
    }
}
