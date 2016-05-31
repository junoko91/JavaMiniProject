package ui.panel.game;

import ui.swingmodule.Custom;
import wordmodule.FileManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * Created by junseokchoi on 2016. 5. 17..
 */
public class Game extends JPanel {
    private Map map;
    private Information information;
    private Item item;
    private JTextField input;
    private Timer stopwatch;
    private StopwatchListener stopwatchListener;
    private FileManager rd;

    private Vector<String> words;
    private JLabel monster1;
    private int count = 0;

    public int getCount() {
        return this.count;
    }

    public void increaseCount() {
        this.count++;
    }

    public void resetCount() {
        this.count = 0;
    }


    /**
     * Get JTextField input
     *
     * @return input
     */
    public JTextField getInput() {
        return this.input;
    }

    /**
     * Set nickname
     *
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.getInformationPanel().getNickname().setText(nickname);
    }

    /**
     * For stopwatch (divide hours, minutes, seconds)
     *
     * @param time
     * @return int[] values;
     */
    private int[] divideTime(int time) {
        int hour = time / 3600;
        int min = time / 60 % 60;
        int sec = time % 60;

        int[] values = {hour, min, sec};
        return values;
    }

    /**
     * For stopwatch (decoration)
     *
     * @param intValue
     * @return String time
     */
    private String parseIntToString(int intValue) {
        if (intValue < 10) {
            return "0" + Integer.toString(intValue);
        } else {
            return Integer.toString(intValue);
        }
    }

    /**
     * Set stopwatch
     *
     * @param time
     */
    public void setStopwatch(int time) {
        int[] values = this.divideTime(time);
        String hour = this.parseIntToString(values[0]);
        String min = this.parseIntToString(values[1]);
        String sec = this.parseIntToString(values[2]);

        this.getInformationPanel().getTime().setText(hour + ":" + min + ":" + sec);
    }

    /**
     * Get information panel
     *
     * @return Information information
     */
    public Information getInformationPanel() {
        return this.information;
    }

    /**
     * Get map panel
     *
     * @return Map map
     */
    public Map getMapPanel() {
        return this.map;
    }

    /**
     * Get item panel
     *
     * @return Item item
     */
    public Item getItemPanel() {
        return this.item;
    }

    /**
     * Start to stopwatch
     */
    public void startStopwatch() {
        this.stopwatch.start();
    }

    /**
     * Stop to stopwatch
     */
    public void stopStopwatch() {
        this.stopwatch.stop();
    }

    /**
     * Reset to stopwatch
     */
    public void resetStopwatch() {
        this.stopwatchListener.resetTimer();
    }

    /**
     * Read Words File
     */
    public void readFile() {
        try {
            rd = new FileManager();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setMonster(String monster) {
        this.monster1.setText(monster);
    }

    public JLabel getMonster() {
        return monster1;
    }

    public Game() {
        setLayout(null);
        setBounds(0, 0, 1024, 720);
        setBackground(new Color(21, 21, 23));

        this.stopwatchListener = new StopwatchListener();
        this.stopwatch = new Timer(1000, stopwatchListener);

        this.map = new Map();
        this.information = new Information();
        this.item = new Item();
        this.input = Custom.textField(332, 637, 362, 40, 18);
        this.input.setBorder(new LineBorder(Color.BLACK));
        this.input.addKeyListener(new EnterListener());

        add(this.map);
        add(this.input);
        add(this.information);
        add(this.item);

        this.monster1 = Custom.label(322, 280, 380, 50, "", 40);
        this.monster1.setForeground(Color.white);
        this.monster1.setBorder(new LineBorder(Color.white));
        this.getMapPanel().add(this.monster1);
    }

    private class StopwatchListener implements ActionListener {
        private int time = 0;

        public void resetTimer() {
            this.time = 0;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setStopwatch(this.time++);
        }
    }

    private class EnterListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (getInput().getText().length() >= 30) {
                getInput().setText(getInput().getText().substring(0, 30));
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (getMonster().getText().equals(getInput().getText()+"\r")) {
                    System.out.println("Correct!");
                    increaseCount();
                    if (getCount() < 20)
                        setMonster(rd.getWordManager().popWord());
                    else
                        System.out.println("Clear!");
                }
                System.out.println(getMonster().getText());
                getInput().setText("");
            } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                setMonster(rd.getWordManager().popWord());
            }
        }
    }
}
