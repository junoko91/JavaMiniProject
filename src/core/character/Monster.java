package core.character;

import java.util.Vector;

/**
 * Created by JUNO_XPS on 2016-06-09.
 */
public class Monster extends GraphicObject {
    UserCharacter user;
    int attackCycle=0;

    Monster(int coordiX, int coordiY,String name, int monsterType, UserCharacter user) {
        super(coordiX, coordiY, monsterType);
        this.life = 1;
        this.user = user;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void isImpact(){
        Vector<GraphicObject> list = ObjectManager.getObjectListist();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).check(this.circles)) {
                attack();
                return;
            }
        }
    }

    public void attack(){
        if(attackCycle++%300==0){
            user.attacked();
            attackCycle=0;
        }
    }

    public void run() {
        while (life > 0) {
            int incX, incY;

            if (this.point.getX() > user.getPoint().getX()) {
                incX = 1;
            } else if (this.point.getX() < user.getPoint().getX()) {
                incX = -1;
            } else {
                incX = 0;
            }

            if (this.point.getY() > user.getPoint().getY()) {
                incY = 1;
            } else if (this.point.getY() < user.getPoint().getY()) {
                incY = -1;
            } else {
                incY = 0;
            }

            if (Math.random() < 0.2) {
                incX = (int) ((Math.random() - 0.5) * 2);
                incY = (int) ((Math.random() - 0.5) * 2);
            }

            this.move(incX, incY);
        }
    }
}
