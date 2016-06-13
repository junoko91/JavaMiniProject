package core.character;

import core.GameMain;
import debug.Debug;

/**
 * Created by JUNO_XPS on 2016-06-09.
 */
public class Monster extends GraphicObject {
    UserCharacter user = GameMain.getUser();
    Thread thread;
    private long lastAttackTime = 0;

    public Monster(int coordiX, int coordiY, String name, int monsterType) {
        super(coordiX, coordiY, monsterType);
        this.life = 1;
        this.name = name;
        thread = new Thread(this);
        thread.start();
    }

    public String getName() {
        return name;
    }

    public boolean isImpact() {
        if (user.check(circles)) {
            attack();
            return true;
        }
        return false;
    }

    synchronized public void attack() {
        if (System.currentTimeMillis() - this.lastAttackTime > 700) {
            user.attacked();
            System.out.println(user.getLife());
            Debug.println("attack");
            this.lastAttackTime = System.currentTimeMillis();
        }
    }

    public void run() {
        int incX, incY;
        while (life > 0) {

            if (isImpact()) {
                return;
            }

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

            if(this.move(incX, incY)==false){
                incX = (int)((Math.random()-0.5)*100);
                incY = (int)((Math.random()-0.5)*100);
                this.move(incX, incY);
            }

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {

            }
        }

    }
}
