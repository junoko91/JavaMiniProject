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
    public Vector<Circle> circles = new Vector<Circle>(5);


    GraphicObject(int coordiX, int coordiY,int objectType) {
        this.objectType = objectType;
        this.circles.addAll(CircleData.)
        Debug.println("create GraphicObject");
    }

    public Dimension getDimension() {
        return dimension;
    }

    public boolean check(Vector<Circle> circles){
        boolean ret = false;
        for(int i=0;i<this.circles.size();i++){
            for(int j=0;j<circles.size();j++){
                ret = ret | this.circles.elementAt(i).check(circles.elementAt(j));
            }
        }
        return ret;
    }
}

