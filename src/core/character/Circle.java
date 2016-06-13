package core.character;

import java.awt.*;

/**
 * Created by JUNO_XPS on 2016-06-09.
 */
public class Circle {
    Point refCoordinate;
    Point realCoordinate;
    int radius;

    public Circle(int x,int y, int radius) {
        refCoordinate = new Point(x,y);
        this.radius = radius;
    }

    public Circle(Point point, int radius) {
        refCoordinate = new Point(point);
        this.radius = radius;
    }

    void setCircle(Point objectPoint) {
        int x = (int) (refCoordinate.getX() + objectPoint.getX());
        int y = (int) (refCoordinate.getY() + objectPoint.getY());
        realCoordinate = new Point(x, y);
    }

    public Point getRefCoordinate() {
        return refCoordinate;
    }

    public Point getRealCoordinate() {
        return realCoordinate;
    }

    public int getRadius() {
        return radius;
    }

    public boolean check(Circle circle) {
        double distance = Math.sqrt(Math.pow(realCoordinate.getX() - circle.getRealCoordinate().getX(), 2) + Math.pow(realCoordinate.getY() - circle.getRealCoordinate().getY(), 2));
        double ref = Math.abs(this.radius + circle.getRadius());

        boolean ret = distance > ref ? false : true;
        return ret;
    }
}
