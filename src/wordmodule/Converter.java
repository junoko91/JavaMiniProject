package wordmodule;

import debug.Debug;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * Created by JUNO_XPS on 2016-05-23.
 */
public class Converter extends IOException {
    private final String targetPath = "fureword.txt";
    FileChannel rdChannel;
    FileChannel wrChannel;
    FileInputStream inputStream;
    FileOutputStream outputStream;
    HashMap<String, String> checkList;

    public Converter(String path, String seperator) {
        int bufferSize = 1048676; //1메가바이트
        ByteBuffer buffer1 = ByteBuffer.allocateDirect(bufferSize);
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(bufferSize);
        buffer1.clear();
        buffer2.clear();
        checkList = new HashMap<String, String>();

        try {
            inputStream = new FileInputStream(path);
            rdChannel = inputStream.getChannel();
            outputStream = new FileOutputStream(targetPath);
            wrChannel = outputStream.getChannel();
        } catch (IOException IOE) {
            Debug.println("FileReader - file open error");
        }

        try {

            String last = "";

            //file을 channel에 담겨있다고 보면되고 offset이 size랑 같으면 끝까지 다 읽은거임
            while (rdChannel.size() != rdChannel.position()) {
                buffer1.clear();
                rdChannel.read(buffer1);
                buffer1.flip(); //buffer에서 offset pointer를 0으로 돌려놓음

                Charset charset = Charset.forName("MS949"); //읽어들이는 문자열의 인코딩타입
                CharBuffer charBuffer = charset.decode(buffer1); //buffer에 있는 내용을 intellij가 쓰는 타입으로 디코딩
                String[] stringArr = charBuffer.toString().split(seperator);

                if (last.equals("") == false) {
                    stringArr[0] = last + stringArr[0];
                }

                last = stringArr[stringArr.length - 1];

                for (int i = 0; i < stringArr.length - 1; i++) {
                    if (checkList.containsKey(stringArr[i]) == false) {
                        checkList.put(stringArr[i], null);
                        buffer2.put((stringArr[i] + "\r\n").getBytes("MS949"));
                        if (buffer2.limit()-buffer2.position()< 100) {
                            buffer2.flip();
                            wrChannel.write(buffer2);
                            buffer2.clear();
                        }
                    }
                }

                buffer2.flip();
                wrChannel.write(buffer2);
                buffer2.clear();
            }
        } catch (IOException IOE) {
            Debug.println("FileReader - reading error");
        }

        buffer1.clear();
        buffer2.clear();
    }

  /* public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new Converter("word.txt", "\n");
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }*/
}
