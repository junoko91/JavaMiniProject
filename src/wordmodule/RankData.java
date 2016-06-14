package wordmodule;

/**
 * Created by JUNO_XPS on 2016-06-14.
 */

public class RankData {
    String nickName = "";
    int score;

    RankData(String nickName, int score) {
        this.nickName = nickName;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getNickName() {
        return nickName;
    }
}