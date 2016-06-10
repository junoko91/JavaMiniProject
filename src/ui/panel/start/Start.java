package ui.panel.start;

import ui.panel.main.Main;
import ui.swingmodule.Custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by junseokchoi on 2016. 5. 17..
 */
public class Start extends JPanel {
    public Start(Main main) {
        setLayout(null);
        setBounds(400, 150, 224, 360);
        setBackground(new Color(21, 21, 23, 1));

        JLabel subject = Custom.label(0, 20, 224, 40, "TYPIXEL", 40);
        subject.setForeground(Color.white);
        JButton start = Custom.button(0, 120, 224, 40, "Start", 20);
        JButton word = Custom.button(0, 170, 224, 40, "단어", 20);
        JButton option = Custom.button(0, 220, 224, 40, "Option", 20);
        JButton rank = Custom.button(0, 270, 224, 40, "Ranking", 20);
        JButton exit = Custom.button(0, 320, 224, 40, "나가기", 20);

        StartListener startListener = new StartListener(main);
        WordListener wordListener = new WordListener(main);
        OptionListener optionListener = new OptionListener();
        RankListener rankListener = new RankListener();
        ExitListener exitListener = new ExitListener();

        start.addActionListener(startListener);
        word.addActionListener(wordListener);
        option.addActionListener(optionListener);
        rank.addActionListener(rankListener);
        exit.addActionListener(exitListener);

        add(subject);
        add(start);
        add(word);
        add(option);
        add(rank);
        add(exit);
    }

    private class StartListener implements ActionListener {
        private Main main;

        StartListener(Main main) {
            this.main = main;
        }

        public void actionPerformed(ActionEvent e) {
            this.main.getNicknamePanel().setVisible(true);
            this.main.getMenuPanel().setVisible(false);
            this.main.getStartPanel().setVisible(false);
            this.main.getGamePanel().setVisible(false);
        }
    }

    private class WordListener implements ActionListener {
        private Main main;
        private JTextArea wordArea;

        WordListener(Main main) {
            this.main = main;

        }

        public void actionPerformed(ActionEvent e) {
            this.main.getWordPanel().readFile();
            //this.main.getWordPanel().setAllWords();

            this.main.getWordPanel().setVisible(true);
            this.main.getStartPanel().setVisible(false);
        }
    }

    private class OptionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class RankListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(1);
        }
    }
}
