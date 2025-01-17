package Main.ui;

import Find.Comparateur.Comparateur;
import Find.Modeles.Comparatifs.Map;
import Find.Modeles.Comparatifs.Masque;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main extends JFrame {

    Main(){
        super();

        test3();

        //Les détails de création de ma fenêtre
        setLocation(1000, 250);
        setSize(400, 250);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Teste les versions graphiques de Masque et Map sur un exemple
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

    //Compare une map et celle recréer en passant en forme de ligne
    private void test2(){
        setLayout(new GridLayout(1, 2));

        Masque masque = new Masque("./data/picture/A.png");

        Map map = new Map(masque);

        add(new Image(map.toBufferedImage()));
        Map map2 = new Map(map.toLine());
        add(new Image(map2.toBufferedImage()));
    }

    private void test3(){
        setLayout(new GridLayout(1, 2));

        Masque masque = new Masque("./data/picture/char_extract_1_1.png");

        add(new Image(masque.toBufferedImage()));

        Comparateur.getInstance().fill();

        add(new Image(Comparateur.getInstance().getMasque('J').toBufferedImage()));
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(Main::new);
    }
}
