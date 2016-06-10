package ui.panel.menu;

import ui.panel.main.Main;
import ui.swingmodule.Custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by junseokchoi on 2016. 5. 17..
 */
public class MyMenu extends JPanel {
    private JPanel nowPanel = null;

    public void setNowPanel(JPanel nowPanel) {
        this.nowPanel = nowPanel;
    }

    public JPanel getNowPanel() {
        return this.nowPanel;
    }

    public MyMenu(Main main) {
        setLayout(null);
        setBounds(0, 0, 1024, 720);
        setBackground(new Color(0, 0, 0, 200));

        JLabel subject = Custom.label(400, 200, 224, 40, "메 뉴", 40);
        subject.setForeground(Color.white);
        JButton first = Custom.button(400, 300, 224, 40, "처음으로", 20);
        JButton option = Custom.button(400, 350, 224, 40, "Option", 20);
        JButton rank = Custom.button(400, 400, 224, 40, "Ranking", 20);
        JButton exit = Custom.button(400, 450, 224, 40, "나가기", 20);

        FirstListener firstListener = new FirstListener(main);
        OptionListener optionListener = new OptionListener();
        RankListener rankListener = new RankListener();
        ExitListener exitListener = new ExitListener();

        first.addActionListener(firstListener);
        option.addActionListener(optionListener);
        rank.addActionListener(rankListener);
        exit.addActionListener(exitListener);

        add(subject);
        add(first);
        add(option);
        add(rank);
        add(exit);
    }

    private class FirstListener implements ActionListener {
        private Main main;

        FirstListener(Main main) {
            this.main = main;
        }

        public void actionPerformed(ActionEvent e) {
            this.main.getStartPanel().setVisible(true);
            this.main.getNicknamePanel().setVisible(false);
            this.main.getMenuPanel().setVisible(false);
            this.main.getGamePanel().setVisible(false);
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
