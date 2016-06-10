package core.character;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

/**
 * Created by JUNO_XPS on 2016-06-09.
 */
public class CircleData {
    static boolean isLoaded = false;
    static Vector<Vector> vectors = new Vector<Vector>();
    static Vector<Circle> monter1 = new Vector<Circle>();
    static Vector<Circle> monter2 = new Vector<Circle>();
    static Vector<Circle> monter3 = new Vector<Circle>();
    static Vector<Circle> monter4 = new Vector<Circle>();
    static Vector<Circle> monter5 = new Vector<Circle>();
    static Vector<Circle> monter6 = new Vector<Circle>();

    static void load(){
        if(isLoaded){
            return;
        }
        vectors.add(monter1);
        vectors.add(monter2);
        vectors.add(monter3);
        vectors.add(monter4);
        vectors.add(monter5);
        vectors.add(monter6);

        //각각의 충돌 써클을 하드코딩으로 넣어논다.
    }

    static Vector getVetor(int objectType){
        load();
        Vector<Circle> ret = new Vector<Circle>();
        ret.addAll(vectors.get(objectType));
        return ret;
    }
}
