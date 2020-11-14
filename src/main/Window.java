package main;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Charlie
 */
public class Window {
    public JFrame frame;
    public Window(String name, int width, int height, Main main){
        frame = new JFrame(name);
        Dimension d = new Dimension(width,height);
        frame.setPreferredSize(d);
        frame.setSize(d);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        frame.add(main);
        main.start();
    }
}
