package ui.panel.nickname;

import core.GameMain;
import ui.panel.main.Main;
import ui.swingmodule.Custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by junseokchoi on 2016. 5. 17..
 */
public class Nickname extends JPanel {
    private JTextField nickname;

    public JTextField getNickname() {
        return this.nickname;
    }

    public Nickname(Main main) {
        setLayout(null);
        setBounds(400, 180, 224, 200);
        setBackground(new Color(21, 21, 23, 1));

        JLabel label = Custom.label(0, 0, 224, 60, "´Ð³×ÀÓ", 30);
        label.setForeground(Color.white);
        this.nickname = Custom.textField(2, 80, 220, 50, 20);
        JButton button = Custom.button(0, 150, 224, 50, "Start", 15);

        button.addActionListener(new GameStartListener(main));

        add(label);
        add(this.nickname);
        add(button);
    }

    private class GameStartListener implements ActionListener {
        private Main main;

        GameStartListener(Main main) {
            this.main = main;
        }

        public void actionPerformed(ActionEvent e) {
            this.main.getGamePanel().readFile();
            //this.main.getGamePanel().setMonster(this.main.getRd().getWordManager().popWord());

            this.main.getGamePanel().setNickname(getNickname().getText());
            this.main.getGamePanel().setVisible(true);
            this.main.getNicknamePanel().setVisible(false);

            this.main.getGamePanel().resetStopwatch();
            this.main.getGamePanel().startStopwatch();

            GameMain.gameStart();
        }
    }
}
