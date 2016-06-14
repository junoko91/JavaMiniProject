package wordmodule;

import debug.Debug;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by JUNO_XPS on 2016-05-13.
 */
public class FileManager extends IOException {
    private final String wordPath = "fureword.txt";
    private final String rankPath = "ranking.txt";
    private final String seperator = "\r\n";
    private FileChannel channel;
    private FileInputStream inputStream;
    private WordManager wordManager;
    private RankManager rankManager;
    private boolean isWordLoaded = false;
    private boolean isRankLoaded = false;


    public FileManager() {
        loadWords();
        loadRanking();
    }

    public void loadRanking() {
        if (isRankLoaded) {
            return;
        }
        ByteBuffer buffer = ByteBuffer.allocateDirect(81920);
        buffer.clear();

        rankManager = new RankManager();

        try {
            this.inputStream = new FileInputStream(rankPath);
            this.channel = this.inputStream.getChannel();
        } catch (IOException IOE) {
            Debug.println("FileManager - file open error");
        }

        try {
            String last = "";
            String name = "";
            int score;

            while (this.channel.size() != this.channel.position()) {
                buffer.clear();
                this.channel.read(buffer);
                buffer.flip(); //buffer에서 offset pointer를 0으로 돌려놓음

                Charset charset = Charset.forName("UTF8"); //읽어들이는 문자열의 인코딩타입
                CharBuffer charBuffer = charset.decode(buffer); //buffer에 있는 내용을 intellij가 쓰는 타입으로 디코딩
                String[] stringArr = charBuffer.toString().split(seperator);

                if (!last.equals("")) {
                    stringArr[0] = last + stringArr[0];
                }

                last = stringArr[stringArr.length - 1];
                String[] temps;
                for (int i = 0; i < stringArr.length; i++) {
                    temps = stringArr[i].split("\n");
                    for (String temp: temps) {
                        name = temp.split(" ")[0];
                        score = Integer.parseInt(temp.split(" ")[1]);
                        rankManager.pushData(name, score);
                    }
                }
            }
        } catch (IOException IOE) {
            Debug.println("FileManager - reading error");
        }

        isRankLoaded = true;
    }

    public void loadWords() {
        if (isWordLoaded) {
            return;
        }

        ByteBuffer buffer = ByteBuffer.allocateDirect(81920);
        buffer.clear();

        this.wordManager = new WordManager();


        try {
            this.inputStream = new FileInputStream(wordPath);

            this.channel = this.inputStream.getChannel();
        } catch (IOException IOE) {
            Debug.println("FileManager - file open error");
            new Converter("word.txt", "\n");
        }


        try {
            String last = "";

            //file을 channel에 담겨있다고 보면되고 offset이 size랑 같으면 끝까지 다 읽은거임
            while (this.channel.size() != this.channel.position()) {
                buffer.clear();
                this.channel.read(buffer);
                buffer.flip(); //buffer에서 offset pointer를 0으로 돌려놓음

                Charset charset = Charset.forName("MS949"); //읽어들이는 문자열의 인코딩타입
                CharBuffer charBuffer = charset.decode(buffer); //buffer에 있는 내용을 intellij가 쓰는 타입으로 디코딩
                String[] stringArr = charBuffer.toString().split(seperator);

                if (!last.equals("")) {
                    stringArr[0] = last + stringArr[0];
                }

                last = stringArr[stringArr.length-1];

                for (int i = 0; i < stringArr.length; i++) {
                    this.wordManager.pushWord(stringArr[i]);
                }
            }

        } catch (IOException IOE) {
            Debug.println("FileManager - reading error");
        }

        isWordLoaded = true;
    }

    public void unloadWords() {
        wordManager.init();
        wordManager = null;
    }

    public WordManager getWordManager() {
        return this.wordManager;
    }

    public RankManager getRankManager() {
        return rankManager;
    }

    public static void main(String[] args) {
        FileManager rd = new FileManager();

        System.out.println(rd.getWordManager().getAllWords());

        for(int i=0;i<rd.getRankManager().getAllData().size();i++){
            System.out.println(rd.getRankManager().getAllData().get(i).nickName+" " + rd.getRankManager().getAllData().get(i).score);
        }
    }
}
