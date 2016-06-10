package ui.panel.game;

import core.character.GraphicObject;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rdp on 2016-06-11.
 */
public class Character extends JLabel implements Runnable {
        private GraphicObject object;
        private boolean status=true;


        public Character(GraphicObject object,Map map){
            setVisible(true);
            setSize(object.getDimension());
            setLocation(object.getPoint());
            this.setBackground(Color.BLUE);
            this.setOpaque(true);
            map.add(this);
            Thread thread = new Thread(this);
            thread.run();
        }

        public void run(){
        while (object.getLife()>0) {
            setSize(object.getDimension());
            setLocation(object.getPoint());
        }
        object.die();
    }
}
