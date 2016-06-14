package core;

import core.character.GraphicObject;
import core.character.Monster;
import core.character.ObjectManager;
import core.character.UserCharacter;
import ui.panel.game.Character;
import ui.panel.game.Map;
import ui.panel.main.Main;

import java.awt.*;

/**
 * Created by JUNO_XPS on 2016-05-19.
 */
public class GameMain {
    private static Main uiMain = Main.main;
    private static Map map;
    private static final int USER = 0;
    private static final int MONSTER = 1;
    private static int level = 5;
    private static int score = 0;
    private static int monsterCount = level;
    public static boolean isPlaying = false;
    private static UserCharacter user;
    private static Monster monster;
    public static boolean pause = false;
    static long last = 0;


    public static int getLevel() {
        return level;
    }

    public static void initGame(){
        resume();
        ObjectManager.init();
        level = 5;
        monster = null;
        user = null;
        score = 0;
        setScore(0);
    }

    public static boolean getStatus() {
        return pause;
    }

    public static void pause() {
        pause = true;
    }

    synchronized public static void resume() {
        pause = false;

        for (int i = 0; i < ObjectManager.getObjectList().size(); i++) {
            ObjectManager.getObjectList().get(i).wakeUp();
        }
    }

    public static UserCharacter getUser() {
        return user;
    }

    synchronized public static void setScore(int score) {
        if (System.currentTimeMillis() - last > 0) {
            GameMain.score += score;
            uiMain.getGamePanel().getInformationPanel().setScore(GameMain.score);
            last = System.currentTimeMillis();
        }
    }

    public static void gameStart() {
        initGame();

        isPlaying = true;

        map = uiMain.getGamePanel().getMapPanel();

       /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }*/
        user = new UserCharacter(100, 300, 90000, USER);
        new Character(user, map);

        for (int i = 0; i < 5; i++) {
            //몬스터 생성
            monster = new Monster(100 * i, 50, MONSTER);
            new Character(monster, map);
        }
    }
}
