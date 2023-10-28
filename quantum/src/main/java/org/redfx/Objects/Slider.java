package org.redfx.Objects;

import java.awt.Color;
import javax.swing.JSlider;

/**A slider class to re-use throughout the GUI/program. */
public class Slider extends JSlider {
    /**Constructing the slider.
     * 
     * @param min is the minimum value
     * @param max is the maximum value
     * @param value is a value that the slider is initially set to 
     */
    public Slider(int min, int max, int value) {
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

    public Slider(int min, int max, int value, Color color) {
        setMinimum(min);
        setMaximum(max);
        setValue(value);
        setBackground(color);
        setSnapToTicks(true);
        setPaintLabels(true);
        setForeground(Color.BLACK);
        
        setPaintTrack(true);
        setPaintTicks(true);
    }
}
