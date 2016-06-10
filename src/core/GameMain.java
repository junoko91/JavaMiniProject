package core;

import core.character.Monster;
import core.character.ObjectManager;
import core.character.UserCharacter;
import ui.panel.game.Character;
import ui.panel.game.Map;
import ui.panel.main.Main;

import java.time.Month;

/**
 * Created by JUNO_XPS on 2016-05-19.
 */
public class GameMain {
    private static Main uiMain;
    private static Map map;
    private static final int USER = 0;
    private static final int MONSTER = 1;
    private static final int LEVEL=5;
    public static boolean isPlaying = false;
    private static UserCharacter user;
    private static Monster monster;

    public GameMain(){

    }

    public static int getLevel() {
        return LEVEL;
    }

    public static UserCharacter getUser() {
        return user;
    }

    public static void gameStart(){
        uiMain = new Main();
        map = uiMain.getGamePanel().getMapPanel();

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){

        }
        user = new UserCharacter(0,0,5,USER);
        ObjectManager.add(user);
        new Character(user,map);

        for(int i=0;i<LEVEL;i++){
            //몬스터 생성
            monster = new Monster(500,500,"monster",1);
            ObjectManager.add(monster);
            new Character(monster,map);
            //딜레이
        }
    }

    public static void main(String[] args){
        new GameMain();
    }
}
