package core.character;

import core.GameMain;
import core.gamedata.GameData;
import debug.Debug;

import java.awt.*;
import java.util.Vector;

/**
 * Created by JUNO_XPS on 2016-05-13.
 */

public abstract class GraphicObject implements Runnable {
    protected String name;
    private int objectType;
    protected Dimension dimension = new Dimension(0, 0);
    private Dimension limitLine = new Dimension(0, 0);
    protected Point point = new Point(0, 0);
    public Vector<Circle> circles = new Vector<Circle>(5);
    protected Thread thread;
    int life = 1;


    GraphicObject(int coordiX, int coordiY, int objectType) {
        this.dimension.setSize(70, 70);
        this.point.setLocation(coordiX, coordiY);
        this.objectType = objectType;
        this.circles = CircleData.getVetor(objectType);

        for (int i = 0; i < circles.size(); i++) {
            circles.get(i).setCircle(this.point);
        }

        limitLine.setSize(GameData.limitLine);
        Debug.println("create GraphicObject");
    }

    public Dimension getDimension() {
        return dimension;
    }

    synchronized public Point getPoint() {
        Point ret = new Point(this.point);
        return ret;
    }

    synchronized public void setPoint(Point point) {
        this.point = point;
    }

    synchronized public void setCircles(Vector<Circle> circles) {
        this.circles = circles;
    }

    public int getLife() {
        return life;
    }

    public Thread getThread() {
        return thread;
    }

    public void setLocation(Point point) {
        this.point.setLocation(point);
        for (int i = 0; i < circles.size(); i++) {
            circles.get(i).setCircle(this.point);
        }
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Debug.println("자다가 인터럽트");
        }
    }


    public boolean check(Vector<Circle> circles) {
        for (int i = 0; i < this.circles.size(); i++) {
            for (int j = 0; j < circles.size(); j++) {
                if (this.circles.elementAt(i).check(circles.elementAt(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean move(int incX, int incY) {
        Point tmpPoint = new Point();
        tmpPoint.setLocation(this.point.getX() + incX, this.point.getY() + incY);

        if ((limitLine.getWidth() >= tmpPoint.getX() + this.dimension.getWidth()
                && limitLine.getHeight() >= tmpPoint.getY() + this.dimension.getHeight()
                && tmpPoint.getX() >= 0 && tmpPoint.getY() >= 0) == false) {
            return false;
        }

        Vector<Circle> tmpCircles = new Vector<Circle>(5);

        for (int i = 0; i < circles.size(); i++) {
            tmpCircles.add(new Circle(circles.get(i).getRefCoordinate(), circles.get(i).getRadius()));
            tmpCircles.get(i).setCircle(tmpPoint);
        }

        Vector<GraphicObject> list = ObjectManager.getObjectList();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(this)) {
                continue;
            } else if (list.get(i).check(tmpCircles)) {
                if (list.get(i).name.equals("user")) {
                    return true;
                }
                return false;
            }
        }

        this.setPoint(tmpPoint);
        this.setCircles(tmpCircles);
        return true;
    }

    synchronized public void attacked() {
        this.life--;
    }

    public abstract void run();

    public void die() {
        ObjectManager.remove(this);
        GameMain.setScore(100);
    }

    public void wakeUp() {
        synchronized (thread){
            thread.interrupt();
        }
    }

    public void blocking() {
        if (GameMain.getPause()) {
            try {
                synchronized (this.thread) {
                    thread.wait();
                }
            } catch (InterruptedException e) {
                Debug.println("wakeup " + this.name);
                return;
            }
        }
    }
}

