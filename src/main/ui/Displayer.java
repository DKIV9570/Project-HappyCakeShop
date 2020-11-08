package ui;

import javax.swing.*;
import java.awt.*;

public class Displayer extends JScrollPane {
    private JTextArea textArea;

    public Displayer() {
        textArea = new JTextArea();
        setBorder(BorderFactory.createLoweredBevelBorder());

        //setSize(500,300);
        textArea.setText("Welcome to the Happy Cake Shop Game!\n" + "welcome!!!");
        textArea.setFont(new Font("Unispace",0,20));
        setViewportView(textArea);

    }

}
