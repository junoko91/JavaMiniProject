package ui.panel.game;

import ui.swingmodule.Custom;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by junseokchoi on 2016. 5. 17..
 */
public class Item extends JPanel {
    public Item() {
        setLayout(null);
        setBounds(0, 677, 1024, 40);

        JLabel item1 = Custom.label(0, 0, 60, 40, "item1", 20);
        item1.setBorder(new LineBorder(Color.RED));
        JLabel item2 = Custom.label(60, 0, 60, 40, "item2", 20);
        item2.setBorder(new LineBorder(Color.RED));
        JLabel item3 = Custom.label(120, 0, 60, 40, "item3", 20);
        item3.setBorder(new LineBorder(Color.RED));

        add(item1);
        add(item2);
        add(item3);
    }
}
