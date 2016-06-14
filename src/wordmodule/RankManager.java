package wordmodule;

import java.util.*;

/**
 * Created by rdp on 2016-06-14.
 */



class RankDataComparator implements Comparator<RankData> {
    public int compare(RankData o1, RankData o2) {
        int score1 =  o1.score;
        int score2 = o2.score;
        return score1 > score2 ? -1 : (score1 == score2 ? 0 : 1); // descending Á¤·Ä.....
    }
}


public class RankManager {
    private Vector<RankData> rankingList = new Vector<RankData>();

    public RankManager(){

    }

    public void initData() {
        rankingList.clear();
    }

    public void pushData(String nickName, int score) {
        rankingList.add(new RankData(nickName,score));
    }

    public int getData(String nickName) {
       return 0;
    }

    public Vector<RankData> getAllData(){
        Collections.sort(rankingList,new RankDataComparator());
        return rankingList;
    }
}


