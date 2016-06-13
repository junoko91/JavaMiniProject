package core.character;

import java.util.Vector;

/**
 * Created by JUNO_XPS on 2016-06-09.
 */
public class CircleData {
    static boolean isLoaded = false;
    static Vector<Vector> vectors = new Vector<Vector>();
    static Vector<Circle> character0 = new Vector<Circle>();
    static Vector<Circle> character1 = new Vector<Circle>();
    static Vector<Circle> character2 = new Vector<Circle>();
    static Vector<Circle> character3 = new Vector<Circle>();
    static Vector<Circle> character4 = new Vector<Circle>();
    static Vector<Circle> character5 = new Vector<Circle>();

    static void load(){
        if(isLoaded){
            return;
        }
        vectors.add(character0);
        vectors.add(character1);


        character0.add(new Circle(35,35,30));
        character1.add(new Circle(35,35,32));

        //각각의 충돌 써클을 하드코딩으로 넣어논다.
        isLoaded = true;
    }

    synchronized static Vector getVetor(int objectType){
        load();
        Vector<Circle> ret = new Vector<Circle>();
        ret.addAll(vectors.get(objectType));
        return ret;
    }
}
