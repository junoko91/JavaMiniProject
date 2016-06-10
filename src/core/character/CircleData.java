package core.character;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

/**
 * Created by JUNO_XPS on 2016-06-09.
 */
public class CircleData {
    static HashMap<Integer,Vector> vectorData = new HashMap<Integer,Vector>();
    sta
    static Vector<Circle> monter1 = new Vector<Circle>();
    static Vector<Circle> monter2 = new Vector<Circle>();
    static Vector<Circle> monter3 = new Vector<Circle>();
    static Vector<Circle> monter4 = new Vector<Circle>();
    static Vector<Circle> monter5 = new Vector<Circle>();
    static Vector<Circle> monter6 = new Vector<Circle>();

    static void load(){
        CircleData.vectorData.put(1,monter1);
        CircleData.vectorData.put(2,monter2);
        CircleData.vectorData.put(3,monter3);
        CircleData.vectorData.put(4,monter4);
        CircleData.vectorData.put(5,monter5);
        CircleData.vectorData.put(6,monter6);

        //각각의 충돌 써클을 하드코딩으로 넣어논다.
    }

    getVector(int objectType){

    }

}
