package core.character;

import java.util.Vector;

/**
 * Created by JUNO_XPS on 2016-06-09.
 */
public class UserCharacter extends GraphicObject{
    private static final int USER = 0;
    private static final int MONSTER = 1;

    public UserCharacter(int coordiX,int coordiY,int life,int monsterType){
        super(coordiX,coordiY,monsterType);
        this.life = life;
        this.name = "user";
    }

    public void attack(String name){
        Vector<GraphicObject> list =ObjectManager.getObjectListist();
        for(int i=0;i<list.size();i++){
            if(list.get(i).name.equals(name)){
                list.get(i).attacked();
                return;
            }
        }
    }

    public void run(){
        while(life>0){
        }
    }
}
