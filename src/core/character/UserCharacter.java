package core.character;

import debug.Debug;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by JUNO_XPS on 2016-05-13.
 */
public class UserCharacter extends GraphicObject implements Runnable{
    private static final int numOfHeart = 10;
    private int heart;

    public UserCharacter(int coordiX,int coordiY){
        super(coordiX, coordiY,10,10);
        heart = numOfHeart;
        Debug.println("create UserCharacter");
    }

    public void attacked(){
        this.heart--;
        if(this.heart == 0){
            //게임 종료 시퀀스
        }
    }

    public void run(){

    }
}
