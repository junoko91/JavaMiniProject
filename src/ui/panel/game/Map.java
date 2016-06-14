package ui.panel.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by junseokchoi on 2016. 5. 17..
 */
public class Map extends JPanel {

    @Override
    protected void paintComponent(Graphics g)
    {
        BufferedImage image = null;
        /*try {
            image = ImageIO.read(new File("./src/resource/background.png"));
        } catch (IOException e) {
            System.err.print("이미지를 열지 못했습니다.");
        }*/

        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    Map() {
        setLayout(null);
        setBounds(0, 40, 1024, 597);
    }
}
