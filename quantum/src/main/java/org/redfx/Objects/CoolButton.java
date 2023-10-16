package org.redfx.Objects;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class CoolButton extends JButton{
    public CoolButton(String text){
        setText(text);
        setFont(new Font("Lucida Console",Font.PLAIN,20));
        setBackground(new Color(0,17,51));//import java.awt.Color;
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(true);
    }
}
