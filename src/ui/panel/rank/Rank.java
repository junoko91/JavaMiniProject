package ui.panel.rank;

import ui.panel.main.Main;
import ui.swingmodule.Custom;
import wordmodule.FileManager;
import wordmodule.RankData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by junseokchoi on 2016. 6. 14..
 */
public class Rank extends JPanel {
    private FileManager rd;
    private JPanel parentPanel;
    private JPanel scrollPanel = new JPanel();

    public void setParentPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public void readFile() {
        Vector<RankData> rankData;
        try {
            this.rd = Main.main.getRd();
            rankData = this.rd.getRankManager().getAllData();
            for (int i = 0; i < rankData.size(); i++) {
                JLabel lb1 = Custom.label(0, i * 20, 220, 20, Integer.toString(i + 1) + " " +
                        rankData.get(i).getNickName() + " " + Integer.toString(rankData.get(i).getScore()), 15);
                lb1.setHorizontalAlignment(JLabel.LEFT);
                scrollPanel.add(lb1);
            }
            scrollPanel.setBounds(0, 0, 220, 20 * rankData.size());
            scrollPanel.setPreferredSize(new Dimension(scrollPanel.getWidth(), scrollPanel.getHeight()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Rank() {
        setLayout(null);
        setBounds(400, 150, 224, 385);
        setBackground(new Color(21, 21, 23, 1));

        JLabel subject = Custom.label(0, 0, 224, 40, "Rank", 40);
        subject.setForeground(Color.white);

        scrollPanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(scrollPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
        scrollPane.setBounds(2, 60, 220, 224);
        JButton back = Custom.button(0, 345, 224, 40, "뒤로가기", 20);
        back.addActionListener(new BeforeListener());

        add(subject);
        add(scrollPane);
        add(back);
    }

    private class BeforeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            parentPanel.setVisible(true);
            setVisible(false);
        }
    }
}