package fc;

import java.util.ArrayList;

public class PointInteret {

    public static double[][] getMap(int[][] masque){
        int hauteur = masque.length;
        int largeur = masque[0].length;

        double[][] map = new double[hauteur][largeur];
        for (int i = 0; i < hauteur; i++){
            for (int j = 0; j < largeur; j++){
                map[i][j] = getContrasteVoisin(i, j, masque);
            }
        }
        return map;
    }

    private static double getContrasteVoisin(int i, int j, int[][] masque){
        int VOISINS = 2;
        int SIGMA = 1;

        double contrasteVoisin = 0;
        for (int i2 = i- VOISINS; i2 <= i+ VOISINS; i2++){
            for (int j2 = j- VOISINS; j2 <= j+ VOISINS; j2++){
                contrasteVoisin += getEcart(i, j, i2, j2, masque) * Math.exp(-Math.sqrt((i-i2)*(i-i2)+(j-j2)*(j-j2))/ SIGMA);
            }
        }
        return contrasteVoisin;
    }

    private static double getEcart(int i1, int j1, int i2, int j2, int[][] masque){
        if (i1<0 || j1<0 || i2<0 || j2<0 || i1>=masque.length || i2>= masque.length || j1>=masque[0].length || j2>=masque[0].length) return 0;
        return Math.abs(masque[i1][j1] - masque[i2][j2]);
    }

    public static ArrayList<int[]> getPointsInterets(double[][] map){
        ArrayList<int[]> points = new ArrayList<>();
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if (isPointInteret(i, j, map)) points.add(new int[]{i, j});
            }
        }
        return points;
    }

    private static boolean isPointInteret(int i, int j, double[][] map){
        boolean pointInteret = true;
        for (int i2 = i-1; i2 <= i+1; i2++){
            for (int j2 = j-1; j2 <= j+1; j2++){
                pointInteret = pointInteret && isSup(i, j, i2, j2, map);
            }
        }
        return pointInteret;
    }

    private static boolean isSup(int i1, int j1, int i2, int j2, double[][] map){
        if (i2<0 || j2<0 || i2 >= map.length || j2 >= map[0].length) return true;
        return map[i1][j1]>=map[i2][j2];
    }
}
