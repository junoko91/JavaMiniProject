package core.character;

import debug.Debug;

import java.awt.*;
import java.util.Objects;
import java.util.Vector;
import java.util.zip.CheckedInputStream;

/**
 * Created by JUNO_XPS on 2016-05-13.
 */

public class GraphicObject {
    private int objectType;
    protected Dimension dimension = new Dimension();
    protected Point point = new Point();
    public Vector<Circle> circles = new Vector<Circle>(5);


    GraphicObject(int coordiX, int coordiY, int objectType) {
        this.point.setLocation(coordiX, coordiY);
        this.objectType = objectType;
        this.circles = CircleData.getVetor(objectType);
        for (int i = 0; i < circles.size(); i++) {
            circles.get(i).setCircle(this.point);
        }
        Debug.println("create GraphicObject");
    }

    private void setLocation(Point point) {
        this.point.setLocation(point);
        for (int i = 0; i < circles.size(); i++) {
            circles.get(i).setCircle(this.point);
        }
    }

    public boolean check(Vector<Circle> circles) {
        boolean ret = false;
        for (int i = 0; i < this.circles.size(); i++) {
            for (int j = 0; j < circles.size(); j++) {
                ret = ret | this.circles.elementAt(i).check(circles.elementAt(j));
            }
        }
        return ret;
    }

    public void move(int incX, int incY) {
        Point tmpPoint = new Point();
        tmpPoint.setLocation(this.point.getX() + incX, this.point.getY() + incY);
        Vector<Circle> tmpCircles = new Vector<Circle>(5);
        tmpCircles.addAll(this.circles);
        for (int i = 0; i < tmpCircles.size(); i++) {
            tmpCircles.get(i).setCircle(tmpPoint);
        }
        Vector<GraphicObject> list = ObjectManager.getObjectListist();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).check(tmpCircles)) {
                return;
            }
        }

        this.setLocation(tmpPoint);
    }
}

