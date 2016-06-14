package ui.panel.word;

import debug.Debug;
import ui.panel.main.Main;
import ui.swingmodule.Custom;
import wordmodule.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by junseokchoi on 2016. 5. 22..
 */
public class Word extends JPanel {
    private FileManager rd;
    private JTextArea wordArea;
    private Main main;
    public void setWordArea(String word) {
        this.wordArea.setText(word);
    }

    public void readFile() {
        try {
           rd = new FileManager();
            String instanceWord = rd.getWordManager().getAllWords();

            this.wordArea.setText(instanceWord);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Word(Main main) {
        setLayout(null);
        setBounds(400, 150, 224, 385);
        setBackground(new Color(21, 21, 23, 1));

        this.main = main;

        JLabel subject = Custom.label(0, 0, 224, 40, "Word", 40);
        subject.setForeground(Color.white);
        this.wordArea = new JTextArea("");
        JScrollPane scrollPane = Custom.scrollPane(this.wordArea, 2, 60, 220, 224);

        JButton change = Custom.button(0, 295, 224, 40, "�����ϱ� ", 20);
        change.addActionListener(new ChangeListener());
        JButton back = Custom.button(0, 345, 224, 40, "�ڷΰ���", 20);
        back.addActionListener(new MainListener());

        add(subject);
        add(scrollPane);
        add(change);
        add(back);
    }

    private class MainListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            main.getStartPanel().setVisible(true);
            setVisible(false);
        }
    }

    private class ChangeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setWordArea(wordArea.getText().replace(" ", "\n"));
            try {
                //new FileWriter(wordArea.getText(), getPath());
            } catch (NullPointerException ee) {
                Debug.println("FileWriter - file open error");
            }
        }
    }
}
