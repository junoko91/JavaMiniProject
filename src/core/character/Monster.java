package core.character;

import core.GameMain;
import debug.Debug;
import ui.panel.main.Main;

import java.awt.*;

/**
 * Created by JUNO_XPS on 2016-06-09.
 */
public class Monster extends GraphicObject {
    private int delayTime = 70 / GameMain.getLevel();
    UserCharacter user = GameMain.getUser();
    private long lastAttackTime = 0;

    public Monster(int coordiX, int coordiY, int monsterType) {
        super(coordiX, coordiY, monsterType);
        this.life = 1;
        this.name = Main.main.getRd().getWordManager().popWord(GameMain.getLevel());
        thread = new Thread(this);
        thread.start();
    }

    public String getName() {
        return name;
    }

    public boolean isImpact() {
        if (user.check(circles,2)) {
            attack();
            return true;
        }
        return false;
    }

    synchronized public void attack() {
        if (System.currentTimeMillis() - this.lastAttackTime > 800) {
            user.attacked();
            System.out.println(user.getLife());
            Debug.println("attack");
            this.lastAttackTime = System.currentTimeMillis();
        }
    }

    public void avoid(int count) {
        Point oldPoint = new Point();
        int limit = 0;
        int incX = 0, incY = 0;

        double random = Math.random();

        if (oldPoint.equals(this.point)) {
            if (limit++ > 10) {
                return;
            }
        }

        oldPoint.setLocation(this.point);

        if (random > 0.75) {
            incX = 1;
        } else if (random > 0.5) {
            incX = -1;
        } else if (random > 0.25) {
            incY = 1;
        } else {
            incY = -1;
        }

        for (int i = 0; i < count; i++) {
            if (this.move(incX, incY)) {
                sleep(delayTime);
            }
        }
    }

    public void run() {
        int incX, incY;
        while (GameMain.isPlaying) {

            this.blocking();

            isImpact();

            if (this.point.getX() > user.getPoint().getX()) {
                incX = -1;
            } else if (this.point.getX() < user.getPoint().getX()) {
                incX = 1;
            } else {
                incX = 0;
            }

            if (this.point.getY() > user.getPoint().getY()) {
                incY = -1;
            } else if (this.point.getY() < user.getPoint().getY()) {
                incY = 1;
            } else {
                incY = 0;
            }

            if (this.move(incX, incY) == false) {
                if (Math.random() < 0.7) {
                    this.avoid(20);
                }
            } else {
                if (Math.random() < 0.02) {
                    this.avoid(70);
                }
            }

            sleep(delayTime);
        }
        die();
    }
}
