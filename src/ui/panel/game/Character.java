package ui.panel.game;

import core.GameMain;
import core.character.GraphicObject;
import debug.Debug;
import ui.panel.main.Main;
import wordmodule.FileManager;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by rdp on 2016-06-11.
 */
public class Character extends JLabel implements Runnable {
    private static Vector<Character> repaintList = new Vector<Character>();
    private static boolean flag = false;
    private static Character character;
    private GraphicObject object;
    private JLabel name ;


    public Character(GraphicObject object, Map map) {
        FileManager rd = Main.main.getRd();
        ImageIcon icon = new ImageIcon("char.png");

        this.object = object;
        setVisible(true);
        setSize(object.getDimension());
        setLocation(object.getPoint());
        this.setIcon(icon);
        /*this.setBackground(Color.CYAN);
        this.setOpaque(true);*/

        name = new JLabel(object.getName());
        if(name.getText().equals("user")){
            name.setText("");
        }
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setVerticalAlignment(SwingConstants.CENTER);
        name.setFont(new Font("����", Font.PLAIN, 20));
        name.setVisible(true);
        name.setSize(200,30);
        name.setLocation(this.getX()+this.getWidth()/2 - name.getWidth()/2,this.getY()-30);

        map.add(name);
        map.add(this);

        repaintList.add(this);
        Thread thread = new Thread(this);
        thread.start();
        repaint();
    }

    public void setLocations(Point point){
        this.setLocation(point);
        name.setLocation(this.getX()+this.getWidth()/2 - name.getWidth()/2,this.getY()-30);
        repaint();
    }

    public GraphicObject getObject() {
        return object;
    }

    public void terminate() {
        this.setVisible(false);
        synchronized (this) {
            this.getParent().remove(this);
        }
    }

    synchronized public static void painting() {
        if(flag){
            return;
        }

        flag = true;

        while (GameMain.isPlaying) {
            for (int i = 0; i < repaintList.size(); i++) {
                character = repaintList.get(i);
                try {
                    if(character.getObject().getLife()>0) {
                        character.setLocations(character.getObject().getPoint());
                    }
                    else{
                        character.terminate();
                        repaintList.remove(character);
                    }
                } catch (NullPointerException e) {
                    character.terminate();
                    repaintList.remove(character);
                    break;
                }
            }
        }

        for (int i = 0; i < repaintList.size(); i++) {
            character = repaintList.get(i);
            character.terminate();
        }
        repaintList.clear();
        flag = false;
        Debug.println("out");
    }


    public void run() {
        if(flag){
            return;
        }
        painting();
    }
}
