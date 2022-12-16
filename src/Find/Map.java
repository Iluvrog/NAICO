package Find;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Map {

    private static final int VOISINS = 2;
    private static final int SIGMA = 1;
    private static final int DISTANCE_MAX_LOCAL = 3;

    //private final Masque masque;
    private final double[][] map;

    public Map(Masque masque){
        int hauteur = masque.getHauteur();
        int largeur = masque.getLargeur();

        map = new double[hauteur][largeur];
        for (int i = 0; i < hauteur; i++){
            for (int j = 0; j < largeur; j++){
                map[i][j] = getContrasteVoisin(i, j, masque);
            }
        }
    }

    public Map(String line){
        int hauteur = 0;
        int largeur = 0;

        for (int i = 0; i < 4; i++){
            hauteur = hauteur << 8;
            hauteur += line.charAt(0);
            line = line.substring(1);
        }

        for (int i = 0; i < 4; i++){
            largeur = largeur << 8;
            largeur += line.charAt(0);
            line = line.substring(1);
        }

        String bloc;

        map = new double[hauteur][largeur];
        for (int i = 0; i < hauteur; i++){
            for (int j = 0; j < largeur; j++){
                bloc = line.substring(0, 8);
                line = line.substring(8);
                map[i][j] = stringToDouble(bloc);
            }
        }
    }

    private double getContrasteVoisin(int i, int j, Masque masque){
        double contrasteVoisin = 0;
        for (int i2 = i- VOISINS; i2 <= i+ VOISINS; i2++){
            for (int j2 = j- VOISINS; j2 <= j+ VOISINS; j2++){
                contrasteVoisin += getEcart(i, j, i2, j2, masque) * Math.exp(-Math.sqrt((i-i2)*(i-i2)+(j-j2)*(j-j2))/ SIGMA);
            }
        }
        return contrasteVoisin;
    }

    private double getEcart(int i1, int j1, int i2, int j2, Masque masque){
        if (i1<0 || j1<0 || i2<0 || j2<0 || i1>= masque.getHauteur() || i2>= masque.getHauteur() || j1>= masque.getLargeur() || j2>= masque.getLargeur()) return 0;
        return Math.abs(masque.getValue(i1, j1) - masque.getValue(i2, j2));
    }

    public double[][] getMap(){
        return map;
    }

    public static int getVOISINS() {
        return VOISINS;
    }

    public static int getSIGMA() {
        return SIGMA;
    }

    public static int getDISTANCE_MAX_LOCAL() {
        return DISTANCE_MAX_LOCAL;
    }

    public ArrayList<int[]> getPointsInterets(){
        ArrayList<int[]> points = new ArrayList<>();
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if (isPointInteret(i, j, map)) points.add(new int[]{i, j});
            }
        }
        return points;
    }

    private boolean isPointInteret(int i, int j, double[][] map){
        boolean pointInteret = true;
        for (int i2 = i-DISTANCE_MAX_LOCAL; i2 <= i+DISTANCE_MAX_LOCAL; i2++){
            for (int j2 = j-DISTANCE_MAX_LOCAL; j2 <= j+DISTANCE_MAX_LOCAL; j2++){
                if (i2 != i || j2 != j) pointInteret = pointInteret && isSup(i, j, i2, j2, map);
            }
        }
        return pointInteret;
    }

    private boolean isSup(int i1, int j1, int i2, int j2, double[][] map){
        if (i2<0 || j2<0 || i2 >= map.length || j2 >= map[0].length) return true;
        return map[i1][j1]>map[i2][j2];
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                string.append(map[i][j]).append(" ");
            }
            string.append("\n");
        }

        return string.toString();
    }

    public BufferedImage toBufferedImage(){
        int hauteur = map.length;
        int largeur = map[0].length;

        BufferedImage image = new BufferedImage(hauteur, largeur, BufferedImage.TYPE_INT_ARGB);

        double max = 0;
        for (int i = 0; i < hauteur; i++){
            for (int j = 0; j < largeur; j++){
                if (map[i][j] > max) max = map[i][j];
            }
        }

        for (int i = 0; i < hauteur; i++){
            for (int j = 0; j < largeur; j++){
                image.setRGB(i, j, new Color((int) (255*(map[i][j]/max)), 0, (int) (255*(1. - map[i][j]/max))).getRGB());
            }
        }

        return image;
    }

    public BufferedImage toBufferedImagePointsInteret(){
        BufferedImage image = toBufferedImage();

        int hauteur = map.length;
        int largeur = map[0].length;

        for (int i = 0; i < hauteur; i++){
            for (int j = 0; j < largeur; j++){
                if (isPointInteret(i, j)) image.setRGB(i, j, Color.GREEN.getRGB());
            }
        }

        return image;
    }

    public boolean isPointInteret(int x, int y){
        ArrayList<int[]> points = getPointsInterets();
        for (int[] point : points){
            if (point[0] == x && point[1] == y) return true;
        }
        return false;
    }

    public double compare(Map m){
        double comp = 0;

        double[][] mapComp = m.getMap();

        int hauteur = map.length, largeur = map[0].length;

        if (hauteur != mapComp.length || largeur != mapComp[0].length) return -1;

        for (int i = 0; i < hauteur; i++){
            for (int j = 0; j < largeur; j++){
                comp+= Math.abs(map[i][j] - mapComp[i][j]);
            }
        }

        return 1/(comp/hauteur/largeur);
    }

    public String toLine(){
        StringBuilder res = new StringBuilder();

        int hauteur = map.length;
        int largeur = map[0].length;

        for (int i = 0; i < 4; i++){
            res.append((char) (hauteur >> ((3 - i) * 8) & 0xff));
        }
        for (int i = 0; i < 4; i++){
            res.append((char) (largeur >> ((3 - i) * 8) & 0xff));
        }

        for (int i = 0; i < hauteur; i++){
            for (int j = 0; j < largeur; j++){
                res.append(doubleToString(map[i][j]));
            }
        }

        return res.toString();
    }

    public static String doubleToString(double d){
        StringBuilder res = new StringBuilder();

        long lng = Double.doubleToLongBits(d);
        for(int i = 0; i < 8; i++) res.append((char) ((lng >> ((7 - i) * 8)) & 0xff));

        return res.toString();
    }

    public static double stringToDouble(String s){
        char c;
        long lng = 0;

        for (int i = 0 ; i < 8; i++){
            c = s.charAt(i);
            lng = lng << 8;
            lng += c;
        }

        return Double.longBitsToDouble(lng);
    }
}
