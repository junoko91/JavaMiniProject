package core.character;

import java.util.Vector;

/**
 * Created by rdp on 2016-06-10.
 */
public class ObjectManager {
    static Vector<GraphicObject> objectList = new Vector<GraphicObject>();

    synchronized public static void add(GraphicObject object) {
        if (objectList.contains(object))
            return;

        objectList.add(object);
    }

    synchronized public static void remove(GraphicObject object) {
        if (!objectList.contains(object))
            return;

        objectList.remove(object);
    }

    synchronized public static Vector<GraphicObject> getObjectListist(){
        Vector<GraphicObject> ret = new Vector<GraphicObject>();
        ret.addAll(objectList);
        return ret;
    }
}
