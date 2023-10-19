package org.redfx.Objects;

import java.awt.Color;

import javax.swing.JSlider;

public class Slider extends JSlider{
    public Slider(int min, int max, int value){
        setMinimum(min);
        setMaximum(max);
        setValue(value);
        setBackground(Color.BLACK);
        setSnapToTicks(true);
        setPaintLabels(true);
        setForeground(Color.WHITE);
        
        setPaintTrack(true);
        setPaintTicks(true);
    }
    
}
