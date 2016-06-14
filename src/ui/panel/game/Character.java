package ui.panel.game;

import core.character.GraphicObject;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by rdp on 2016-06-11.
 */
public class Character extends JLabel implements Runnable {
    private static Vector<Character> repaintList = new Vector<Character>();
    private boolean flag = false;
    private GraphicObject object;
    private boolean status = true;
    private Character character;



    public Character(GraphicObject object, Map map) {
        this.object = object;
        setVisible(true);
        setSize(object.getDimension());
        setLocation(object.getPoint());
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        map.add(this);
        repaintList.add(this);
        Thread thread = new Thread(this);
        thread.start();
        repaint();
    }

    public GraphicObject getObject() {
        return object;
    }

    public void run() {
        if(flag){
            return;
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        while (true) {
            for(int i=0;i<repaintList.size();i++){
                character = repaintList.get(i);
                if(character.getObject().getLife()>0){
                    character.setLocation(character.getObject().getPoint());
                }
                else{
                    character.getObject().die();
                    character.setVisible(false);
                    repaintList.remove(character);
                    break;
                }
            }
        }
    }
}
