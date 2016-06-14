package ui.panel.rank;

import ui.swingmodule.Custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by junseokchoi on 2016. 6. 14..
 */
public class Rank extends JPanel {
    private JLabel rank;
    private JPanel parentPanel;

    public void setParentPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public Rank() {
        setLayout(null);
        setBounds(400, 150, 224, 385);
        setBackground(new Color(21, 21, 23, 1));

        JLabel subject = Custom.label(0, 0, 224, 40, "Rank", 40);
        subject.setForeground(Color.white);

        JPanel scroll = new JPanel();
        // 여기서 받아온 애를 scroll에다가 넣어주면 됨.

        JScrollPane scrollPane = new JScrollPane(scroll, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum());
        scrollPane.setBounds(2, 60, 220, 224);
        JButton back = Custom.button(0, 345, 224, 40, "�ڷΰ���", 20);
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
