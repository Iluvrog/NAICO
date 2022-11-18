package fc.Comparateur;

import fc.Map;
import fc.Masque;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Comparateur {
    private static Comparateur instance;

    ArrayList<Cellule> cellules;

    private Comparateur(){
        cellules = new ArrayList<>();
    }

    public static Comparateur getInstance(){
        if (instance == null) instance = new Comparateur();
        return instance;
    }

    public char compare(Map map){
        char res = 0;
        double actcomp = 0;
        double comp;
        for (Cellule c : cellules){
            comp = c.compare(map);
            if (comp > actcomp){
                actcomp = comp;
                res = c.getName();
            }
        }
        return res;
    }

    public void fill(){
        cellules.clear();

        Cellule cellule;
        BufferedImage img;
        Graphics g;

        for (char i = 33; i < 127; i++){
            cellule = new Cellule(i);

            img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            g = img.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 100, 100);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(i), 25, 25);

            cellule.add(new Map(new Masque(img)));

            cellules.add(cellule);
        }
    }
}
