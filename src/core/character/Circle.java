package core.character;

import java.awt.*;

/**
 * Created by JUNO_XPS on 2016-06-09.
 */
public class Circle {
    Point refCoordinate;
    Point coordinate;
    int radius;

    Circle(int x, int y, int radius) {
        refCoordinate.setLocation(x, y);
        this.radius = radius;
    }

    void setCircle(Point point) {
        int x = (int) (refCoordinate.getX() + point.getX());
        int y = (int) (refCoordinate.getY() + point.getY());
        coordinate.setLocation(x, y);
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public int getRadius() {
        return radius;
    }

    public boolean check(Circle circle) {
        double distance = Math.sqrt(Math.pow(coordinate.getX() - circle.getCoordinate().getX(), 2) + Math.pow(coordinate.getY() - circle.getCoordinate().getY(), 2));
        double ref = Math.abs(this.radius - circle.getRadius());

        boolean ret = distance > ref ? false : true;
        return ret;
    }
}
