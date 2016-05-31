package ui.panel.game;

import ui.swingmodule.Custom;

import javax.swing.*;

/**
 * Created by junseokchoi on 2016. 5. 17..
 */
public class Information extends JPanel {
    private JLabel time;
    private JLabel nickname;
    private JLabel level;
    private JLabel score;

    public JLabel getTime() {
        return this.time;
    }

    public JLabel getNickname() {
        return this.nickname;
    }

    public JLabel getLevel() {
        return this.level;
    }

    public JLabel getScore() {
        return this.score;
    }

    public  void setScore(int score){
        this.score.setText(Integer.toString(score)+"점");
    }

    Information() {
        setLayout(null);
        setBounds(0, 0, 1024, 40);

        JLabel timeLabel = Custom.label(0, 0, 128, 40, "진행시간", 20);
        this.time = Custom.label(128, 0, 128, 40, "00:00:00", 20);
        JLabel nicknameLabel = Custom.label(256, 0, 128, 40, "닉네임", 20);
        this.nickname = Custom.label(384, 0, 128, 40, "", 20);
        JLabel levelLabel = Custom.label(512, 0, 128, 40, "난이도", 20);
        this.level = Custom.label(640, 0, 128, 40, "1", 20);
        JLabel scoreLabel = Custom.label(768, 0, 128, 40, "점수", 20);
        this.score = Custom.label(896, 0, 128, 40, "0점", 20);

        add(timeLabel);
        add(this.time);
        add(nicknameLabel);
        add(this.nickname);
        add(levelLabel);
        add(this.level);
        add(scoreLabel);
        add(this.score);
    }
}
