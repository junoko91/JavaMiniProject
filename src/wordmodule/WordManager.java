package wordmodule;

import debug.Debug;

import java.util.Vector;

/**
 * Created by JUNO_XPS on 2016-05-13.
 */
public class WordManager {
    private static final int maxWordLength = 50;
    private Vector<String> wordListArray[];
    private Vector<Vector> instanceVector;

    public WordManager() {

        this.wordListArray = new Vector[maxWordLength];
        for (int i = 0; i < wordListArray.length; i++) {
            wordListArray[i] = null;
        }
        this.instanceVector = new Vector<Vector>(5);
    }

    public boolean pushWord(String word) {
        if (word.equals("")) {
            Debug.println(word + "단어를 추가할 수 없습니다.");
            return false;
        }

        if (this.wordListArray[word.length()] == null) {
            this.wordListArray[word.length()] = new Vector<String>(20);
            this.instanceVector.add(this.wordListArray[word.length()]);
        }


        this.wordListArray[word.length()].add(word);

        return true;
    }

    public String popWord() {
        if (this.instanceVector.size() <= 0) {
            Debug.println("해당레벨에 해당되는 단어가 엄슴");
            return null;
        }

        Vector<String> wordVector = instanceVector.elementAt((int) (Math.random() * 203729323) % instanceVector.size());

        String ret = wordVector.elementAt((int) (Math.random() * 203729323) % wordVector.size());
        return ret;
    }

    public String popWord(int level) {
        if (level > this.instanceVector.size()) {
            Debug.println("해당레벨에 해당되는 단어가 엄슴");
            return null;
        }

        Vector<String> wordVector = instanceVector.elementAt(level);

        String ret = wordVector.elementAt((int) (Math.random() * 203729323) % wordVector.size());
        return ret;
    }

    public String getAllWords() {
        StringBuffer allWords = new StringBuffer();

        for (int i = 0; i < instanceVector.size(); i++) {
            for (int j = 0; j < instanceVector.elementAt(i).size(); j++) {
                allWords.append(instanceVector.elementAt(i).elementAt(j) + "\n");
            }
        }

        return allWords.toString();
    }

    public void init(){
        instanceVector.clear();
    }


}
