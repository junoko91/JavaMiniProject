package core.character;

import debug.Debug;

import java.awt.*;

/**
 * Created by JUNO_XPS on 2016-05-13.
 */

class Point {
    public int coordinateX,coordinateY;

    public Point(int coordiX, int coordiY){
        this.coordinateX = coordiX;
        this.coordinateY = coordiY;
    }
}

public class GraphicObject {
    protected Point point;
    protected Dimension di;


    GraphicObject(int coordiX, int coordiY,int height,int width) {
        point = new Point(coordiX+width/2,coordiY+height/2);
        /*this.height = height;
        this.width = width;*/

        Debug.println("create GraphicObject");
    }

    protected Point getPoint() {
        return point;
    }
}

