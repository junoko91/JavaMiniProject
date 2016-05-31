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
    private boolean isLoaded = false;
    private final String path = "fureword.txt";
    private final String seperator = "\r\n";
    private FileChannel channel;
    private FileInputStream inputStream;
    private WordManager wordManager;

    public FileManager() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(81920);
        buffer.clear();

        this.wordManager = new WordManager();


        try {
            this.inputStream = new FileInputStream(path);
            this.channel = this.inputStream.getChannel();
        } catch (IOException IOE) {
            Debug.println("FileManager - file open error");
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

                last = stringArr[stringArr.length - 1];

                for (int i = 0; i < stringArr.length - 1; i++) {

                    // System.out.println(stringArr[i]);
                    this.wordManager.pushWord(stringArr[i]);

                }
            }

        } catch (IOException IOE) {
            Debug.println("FileManager - reading error");
        }
    }

    public boolean isLoaded(){
        return this.isLoaded;
    }

    public WordManager getWordManager() {
        return this.wordManager;
    }


   /* public static void main(String[] args) {
        FileManager rd = new FileManager();

        System.out.println(rd.getWordManager().getAllWords());
    }*/
}
