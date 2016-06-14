package ui.panel.main;

import core.GameMain;
import ui.panel.game.Game;
import ui.panel.menu.MyMenu;
import ui.panel.nickname.Nickname;
import ui.panel.rank.Rank;
import ui.panel.start.Start;
import ui.panel.word.Word;
import wordmodule.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {
    public static Main main;
    private MyMenu myMenu;
    private Game gamePanel;
    private Nickname nicknamePanel;
    private Start startPanel;
    private Word wordPanel;
    private Rank rankPanel;

    private FileManager rd;

    public FileManager getRd() {
        return rd;
    }

    public MyMenu getMenuPanel() {
        return this.myMenu;
    }

    public Game getGamePanel() {
        return this.gamePanel;
    }

    public Nickname getNicknamePanel() {
        return this.nicknamePanel;
    }

    public Start getStartPanel() {
        return this.startPanel;
    }

    public Word getWordPanel() {
        return this.wordPanel;
    }

    public Rank getRankPanel() {
        return this.rankPanel;
    }

    public Main() {
        rd = new FileManager();

        setTitle("TYPIXEL");
        setLayout(null);

        Container contentPane = getContentPane();

        this.rankPanel = new Rank();
        this.wordPanel = new Word(this);
        this.myMenu = new MyMenu(this);
        this.gamePanel = new Game();
        this.nicknamePanel = new Nickname(this);
        this.startPanel = new Start(this);

        contentPane.add(rankPanel).setVisible(false);
        contentPane.add(wordPanel).setVisible(false);
        contentPane.add(myMenu).setVisible(false);
        contentPane.add(gamePanel).setVisible(false);
        contentPane.add(nicknamePanel).setVisible(false);
        contentPane.add(startPanel).setVisible(false);

        ImageIcon background = new ImageIcon("src/resource/background.png");
        JLabel imgBox = new JLabel(background);
        imgBox.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        contentPane.add(imgBox);

        startPanel.setVisible(true);

        setJMenuBar(makeMenuBar());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setResizable(false);
        setVisible(true);
    }

    JMenuBar makeMenuBar() {
        JMenuBar mBar = new JMenuBar();
        JMenu menu = new JMenu("menu");
        menu.addMouseListener(new MenuListener());
        mBar.add(menu);
        return mBar;
    }

    private class MenuListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (getRankPanel().isVisible()) {
                getMenuPanel().setNowPanel(getRankPanel());
            } else if (getWordPanel().isVisible()) {
                getMenuPanel().setNowPanel(getWordPanel());
            } else if (getGamePanel().isVisible()) {
                getMenuPanel().setNowPanel(getGamePanel());
                getGamePanel().stopStopwatch();
            } else if (getStartPanel().isVisible()) {
                getMenuPanel().setNowPanel(getStartPanel());
            } else if (getNicknamePanel().isVisible()) {
                getMenuPanel().setNowPanel(getNicknamePanel());
            }

            if (getMenuPanel().isVisible()) {
                GameMain.resume();
                getMenuPanel().getNowPanel().setVisible(true);
                if (getGamePanel().isVisible()) {
                    getGamePanel().startStopwatch();
                }
                getMenuPanel().setVisible(false);
                getMenuPanel().setNowPanel(null);
            } else {
                getMenuPanel().setVisible(true);
                getMenuPanel().getNowPanel().setVisible(false);
                GameMain.pause();
            }
        }
    }
    public static void main(String[] args){
        main = new Main();
    }
}
