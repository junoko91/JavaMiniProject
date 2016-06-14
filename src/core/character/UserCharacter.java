package core.character;

import core.GameMain;
import debug.Debug;

import java.util.Vector;

/**
 * Created by JUNO_XPS on 2016-06-09.
 */
public class UserCharacter extends GraphicObject{
    private static final int step = 8;
    private static final int USER = 0;
    private static final int MONSTER = 1;

    public UserCharacter(int coordiX,int coordiY,int life,int monsterType){
        super(coordiX,coordiY,monsterType);
        this.life = life;
        this.name = "user";
        thread = new Thread(this);
        thread.start();
    }

    public void moveUp(){
        //point.setLocation(point.getX(),point.getY()-step);
        this.move(0,-step);
    }
    public void moveDown(){
        //point.setLocation(point.getX(),point.getY()+step);
        this.move(0,step);
    }
    public void moveLeft(){
        //point.setLocation(point.getX()-step,point.getY());
        this.move(-step,0);
    }
    public void moveRight(){
        //point.setLocation(point.getX()+step,point.getY());
        this.move(step,0);
    }

    public void attack(String name){
        Vector<GraphicObject> list =ObjectManager.getObjectList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getName().equals(name + "\r")){
                list.get(i).attacked();
                GameMain.setScore(100);
                GameMain.monsterCount --;
                GameMain.makeMonster();
                return;
            }
        }
    }

    public void run() {
        int incX=0, incY=0;
        while (life > 0) {

            this.blocking();
        }
        die();
        Debug.println("game over");
    }
}
