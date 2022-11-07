package fc;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        int n = 5;
        int[][] test = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test[i][j] = 1;
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.print(test[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println();

        double[][] map = PointInteret.getMap(test);
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println();

        ArrayList<int[]> points = PointInteret.getPointsInterets(map);
        for (int[] point : points){
            System.out.println(point[0] + " " + point[1]);
        }
    }
}
