package fc;

import java.util.ArrayList;

public class Map {

    private final int VOISINS = 2;
    private final int SIGMA = 1;

    private final Masque masque;
    private final double[][] map;

    Map(Masque masque){
        this.masque = masque;

        int hauteur = masque.getHauteur();
        int largeur = masque.getLargeur();

        map = new double[hauteur][largeur];
        for (int i = 0; i < hauteur; i++){
            for (int j = 0; j < largeur; j++){
                map[i][j] = getContrasteVoisin(i, j);
            }
        }
    }

    private double getContrasteVoisin(int i, int j){
        double contrasteVoisin = 0;
        for (int i2 = i- VOISINS; i2 <= i+ VOISINS; i2++){
            for (int j2 = j- VOISINS; j2 <= j+ VOISINS; j2++){
                contrasteVoisin += getEcart(i, j, i2, j2) * Math.exp(-Math.sqrt((i-i2)*(i-i2)+(j-j2)*(j-j2))/ SIGMA);
            }
        }
        return contrasteVoisin;
    }

    private double getEcart(int i1, int j1, int i2, int j2){
        if (i1<0 || j1<0 || i2<0 || j2<0 || i1>= masque.getHauteur() || i2>= masque.getHauteur() || j1>= masque.getLargeur() || j2>= masque.getLargeur()) return 0;
        return Math.abs(masque.getValue(i1, j1) - masque.getValue(i2, j2));
    }

    public double[][] getMap(){
        return map;
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
        for (int i2 = i-1; i2 <= i+1; i2++){
            for (int j2 = j-1; j2 <= j+1; j2++){
                pointInteret = pointInteret && isSup(i, j, i2, j2, map);
            }
        }
        return pointInteret;
    }

    private boolean isSup(int i1, int j1, int i2, int j2, double[][] map){
        if (i2<0 || j2<0 || i2 >= map.length || j2 >= map[0].length) return true;
        return map[i1][j1]>=map[i2][j2];
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
}
