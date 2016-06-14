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
    private Main main;

    public JTextField getNickname() {
        return this.nickname;
    }

    public Nickname(Main main) {
        setLayout(null);
        setBounds(400, 180, 224, 200);
        setBackground(new Color(21, 21, 23, 1));

        this.main = main;

        JLabel label = Custom.label(0, 0, 224, 60, "�г���", 30);
        label.setForeground(Color.white);
        this.nickname = Custom.textField(2, 80, 220, 50, 20);
        JButton button = Custom.button(0, 150, 224, 50, "Start", 15);

        button.addActionListener(new GameStartListener());

        add(label);
        add(this.nickname);
        add(button);
    }

    private class GameStartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            main.getGamePanel().readFile();
            //main.getGamePanel().setMonster(main.getRd().getWordManager().popWord());

            main.getGamePanel().setNickname(getNickname().getText());
            main.getGamePanel().setVisible(true);
            main.getNicknamePanel().setVisible(false);

            main.getGamePanel().resetStopwatch();
            main.getGamePanel().startStopwatch();

            GameMain.gameStart();
        }
    }
}
