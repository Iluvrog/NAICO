package fc;

import fc.Comparateur.Comparateur;

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

    //Je teste la fonction de comparaison des Map
    private void test2(){
        Map map1 = new Map(new Masque("./data/picture/A.png"));
        Map map2 = new Map(new Masque("./data/picture/A.png"));
        System.out.println(map1.compare(map2));
    }

    //Idem
    private void test3(){
        Map map = new Map(new Masque("./data/picture/A.png"));
        Comparateur c = Comparateur.getInstance();
        c.fill();
        System.out.println(c.compare(map));
    }

    //met une Map en forme de texte et compare avec sa version recréée
    private void test4(){
        Map map = new Map(new Masque("./data/picture/A.png"));
        System.out.println(map.compare(new Map(map.toLine())));
    }

    public static void main(String[] args){
        Main main = new Main();

        main.test1();
    }
}
