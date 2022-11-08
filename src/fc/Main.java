package fc;

import java.util.ArrayList;

public class Main {

    //Teste les versions texte de Masque et Map sur un exemple
    private void test1(){
        int n = 5;
        int[][] test = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test[i][j] = 1;
            }
        }

        Masque masque = new Masque(test);
        System.out.println(masque);

        Map map = new Map(masque);
        System.out.println(map);

        ArrayList<int[]> points = map.getPointsInterets();
        for (int[] point : points){
            System.out.println(point[0] + " " + point[1]);
        }
    }

    public static void main(String[] args){
        Main main = new Main();

        main.test1();
    }
}
