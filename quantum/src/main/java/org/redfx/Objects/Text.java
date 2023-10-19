package org.redfx.Objects;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Text extends JLabel{
    public Text(String text){
        setText(text);
        setForeground(Color.WHITE);
    }
}
