package core.character;

import debug.Debug;

import java.util.Vector;

/**
 * Created by JUNO_XPS on 2016-06-09.
 */
public class UserCharacter extends GraphicObject{
    private static final int step = 5;
    private static final int USER = 0;
    private static final int MONSTER = 1;

    public UserCharacter(int coordiX,int coordiY,int life,int monsterType){
        super(coordiX,coordiY,monsterType);
        this.life = life;
        this.name = "user";
        Thread thread = new Thread(this);
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
            if(list.get(i).name.equals(name)){
                list.get(i).attacked();
                return;
            }
        }
    }

    public void run() {
        int incX=0, incY=0;
        while (life > 0) {

            if (Math.random() < 0.2) {
                incX = (int) ((Math.random() - 0.3) * 2);
                incY = (int) ((Math.random() - 0.3) * 2);
            }

            //this.move(incX, incY);

          /*  if(Math.random()<0.005){
                this.attacked();
            }*/
            //gamescore 올리는 시퀀스
        }
        Debug.println("game over");
    }
}
