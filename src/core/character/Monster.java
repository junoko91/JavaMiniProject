package core.character;

import core.GameMain;
import debug.Debug;
import wordmodule.WordManager;

/**
 * Created by JUNO_XPS on 2016-05-13.
 */
public class Monster extends GraphicObject implements Runnable {
    private boolean status = true;
    private UserCharacter userCharacter;
    private String Name = "";


    public Monster(int coordiX, int coordiY, UserCharacter userCharacter, WordManager wordManager) {
        super(coordiX, coordiY,10,10);
        this.userCharacter = userCharacter;
        this.Name = wordManager.popWord(GameMain.getLevel());
        Debug.println("create Monster");
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return this.status;
    }

    public void diying(){
        this.status = false;
    }

    public void attack(UserCharacter userCharacter) {
        userCharacter.attacked();
    }

    protected void move() {
       //움직이는 알고리즘
    }

    public void run() {
        while(status){
           //움직이고 공격
        }
    }
}
