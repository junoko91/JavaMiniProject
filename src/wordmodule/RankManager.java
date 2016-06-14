package wordmodule;

import java.util.HashMap;

/**
 * Created by rdp on 2016-06-14.
 */
public class RankManager {
    private HashMap<String, Integer> rankingList = new HashMap<String, Integer>();

    public RankManager(){

    }

    public void initData() {
        rankingList.clear();
    }

    public void pushData(String nickName, int score) {
        rankingList.put(nickName, score);
    }


}
