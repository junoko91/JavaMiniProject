package core;

import core.character.Monster;
import core.character.ObjectManager;
import core.character.UserCharacter;
import debug.Debug;
import ui.panel.game.Character;
import ui.panel.game.Map;
import ui.panel.main.Main;

/**
 * Created by JUNO_XPS on 2016-05-19.
 */
public class GameMain{
    private static Main uiMain = Main.main;
    private static Map map;
    private static final int USER = 0;
    private static final int MONSTER = 1;
    private static int level = 5;
    private static int score = 0;
    public static int monsterCount = 0;
    public static boolean isPlaying = false;
    private static UserCharacter user;
    private static Monster monster;
    public static boolean pause = false;
    static long last = 0;
    private static Thread thread;


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

    public static void levelUP(){
        level++;
    }

    synchronized public static void setScore(int score) {
        if(GameMain.score != 0 &&GameMain.score%200==0){
            levelUP();
            Debug.println("LEVELUP");
            System.out.println(level);
        }

        if (System.currentTimeMillis() - last > 0) {
            GameMain.score += score;
            uiMain.getGamePanel().getInformationPanel().setScore(GameMain.score);
            last = System.currentTimeMillis();
        }
    }

    public static void makeMonster(){
        while(monsterCount<level) {
            monster = new Monster((int)(Math.random()*800),(int)(Math.random()*500), MONSTER);
            new Character(monster, map);
            monsterCount++;
        }
    }


    public static void gameOver(){
        pause();
        //점수판 띄우고
        //랭킹 ㄱㄱ
        
    }


    public static void gameStart() {
        initGame();

        isPlaying = true;

        map = uiMain.getGamePanel().getMapPanel();

        user = new UserCharacter(100, 300, 300, USER);
        new Character(user, map);

        for (int i = 0; i < 5; i++) {
            //몬스터 생성
            monster = new Monster(100 * i, 50, MONSTER);
            new Character(monster, map);
            monsterCount++;
        }
    }
}
