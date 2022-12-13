package Main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Image extends JComponent {
    BufferedImage image;

    Image(BufferedImage image){
        this.image = image;
    }

    @Override
    public void paint(Graphics g){
        Rectangle recG = g.getClipBounds();
        g.drawImage(image, 0, 0, recG.width, recG.height, null);
    }
}
