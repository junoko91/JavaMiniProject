package ui.swingmodule;

import javax.swing.*;
import java.awt.*;

/**
 * Created by junseokchoi on 2016. 5. 17..
 */
public class Custom {
    public static JLabel label(int location1, int location2, int size1, int size2, String label, int fontSize) {
        JLabel customLabel = new JLabel(label);
        customLabel.setBounds(location1, location2, size1, size2);
        customLabel.setHorizontalAlignment(SwingConstants.CENTER);
        customLabel.setFont(new Font("바탕", Font.PLAIN, fontSize));
        return customLabel;
    }

    public static JButton button(int location1, int location2, int size1, int size2, String label, int fontSize) {
        JButton customButton = new JButton(label);
        customButton.setBounds(location1, location2, size1, size2);
        customButton.setFont(new Font("바탕", Font.PLAIN, fontSize));
        return customButton;
    }

    public static JTextField textField(int location1, int location2, int size1, int size2, int fontSize) {
        JTextField textField = new JTextField(10);
        textField.setBounds(location1, location2, size1, size2);
        textField.setFont(new Font("바탕", Font.PLAIN, fontSize));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        return textField;
    }

    public static JTextArea textArea(int location1, int location2, int size1, int size2, int fontSize) {
        JTextArea textArea = new JTextArea(size1, size2);
        textArea.setBounds(location1, location2, size1, size2);
        textArea.setFont(new Font("바탕", Font.PLAIN, fontSize));
        return textArea;
    }

    public static JScrollPane scrollPane(JTextArea wordArea, int location1, int location2,
                                         int size1, int size2) {
        JScrollPane scrollPane = new JScrollPane(wordArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(location1, location2, size1, size2);
        return scrollPane;
    }
}
