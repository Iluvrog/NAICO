package ui;

import fc.Map;
import fc.Masque;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main extends JFrame {

    Main(){
        super();

        test1();

        //Les détails de création de ma fenêtre
        setLocation(1000, 250);
        setSize(400, 250);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void test1(){
        setLayout(new GridLayout(2, 2));

        Masque masque = new Masque("./data/picture/A.png");

        Map map = new Map(masque);

        try {
            add(new Image(ImageIO.read(new File("./data/picture/A.png"))));
        } catch (Exception e){
            e.printStackTrace();
        }
        add(new Image(masque.toBufferedImage()));
        add(new Image(map.toBufferedImage()));
        add(new Image( map.toBufferedImagePointsInteret()));
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(Main::new);
    }
}
