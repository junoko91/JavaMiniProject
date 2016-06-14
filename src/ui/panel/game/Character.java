package ui.panel.game;

import core.GameMain;
import core.character.GraphicObject;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by rdp on 2016-06-11.
 */
public class Character extends JLabel implements Runnable {
    private static Vector<Character> repaintList = new Vector<Character>();
    private GraphicObject object;
    private static boolean flag = false;
    private static Character character;


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

    synchronized public static void painting(){

        flag = true;

        while(GameMain.isPlaying){
            for(int i=0;i<repaintList.size();i++){
                character  = repaintList.get(i);
                try {
                    character.setLocation(character.getObject().getPoint());
                }catch (NullPointerException e){
                    character.setVisible(false);
                    repaintList.remove(character);
                }
            }
        }
    }


    public void run() {
        if (true) {
            return;
        }

        

        status = true;


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        while (GameMain.isPlaying) {
            for (int i = 0; i < repaintList.size(); i++) {
                character = repaintList.get(i);
                if (character.getObject().) {
                    character.setLocation(character.getObject().getPoint());
                } else {
                   repaintList.get(i).terminate();
                    break;
                }
            }
        }
        status = false;
        this.terminate();
    }

}
