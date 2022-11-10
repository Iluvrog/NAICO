package fc;

import java.awt.*;
import java.awt.image.BufferedImage;
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
        BufferedImage chr;
        Graphics g;
        double res;
        double min = 999999;
        char minC = 0;

        for (char i = 33; i < 127; i++){
            chr = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            g = chr.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 100, 100);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(i), 25, 25);
            res = map.compare(new Map(new Masque(chr)));
            if (res < min) {
                min = res;
                minC = i;
            }
            //System.out.println(i + " : " + res);
        }
        System.out.println(minC + " : " + min);
    }

    public static void main(String[] args){
        Main main = new Main();

        main.test3();
    }
}
