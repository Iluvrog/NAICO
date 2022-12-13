package Cut;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Cutter {

    private static Cutter instance;

    private Cutter(){

    }

    public static Cutter getInstance(){
        if (instance == null) instance = new Cutter();
        return instance;
    }

    public ArrayList<BufferedImage> cutHorizontally(BufferedImage text){
        ArrayList<BufferedImage> cuts = new ArrayList<>();

        ArrayList<Integer> cutPoint =  cut(text, 0);

        int startCut = 0;
        for (int i : cutPoint){
            cuts.add(text.getSubimage(0, startCut, text.getWidth(), i-startCut));
            startCut = i;
        }
        //cuts.add(text.getSubimage(0, startCut, text.getWidth(), text.getHeight()-startCut));

        return cuts;
    }

    public ArrayList<BufferedImage> cutVertically(BufferedImage text){
        ArrayList<BufferedImage> cuts = new ArrayList<>();

        ArrayList<Integer> cutPoint = cut(text, 1);

        int startCut = 0;
        for (int i : cutPoint){
            cuts.add(text.getSubimage(startCut, 0, i-startCut, text.getHeight()));
            startCut = i;
        }
        //cuts.add(text.getSubimage(startCut, 0, text.getWidth()-startCut, text.getHeight()));

        return cuts;
    }

    private ArrayList<Integer> cut(BufferedImage text, int type){
        int[][] scan = scanner(type, text);
        //for (int i = 0; i < scan[0].length; i++) System.out.println(scan[0][i]);
        return findCut(scan);
    }

    /*
     * 0 = Horizontally
     * 1 = Vertically
     */
    private int[][] scanner(int type, BufferedImage img){
        type = type & 1;

        int length, other_length;
        if (type == 0) {length = img.getHeight(); other_length = img.getWidth();}
        else {length = img.getWidth(); other_length = img.getHeight();}

        int[][] res = new int[3][length];

        int r,g,b;
        for (int i = 0; i < length; i++){
            r = g = b = 0;

            for (int j = 0; j < other_length; j++){
                if (type == 1){
                    r+= img.getRGB(i, j) & 0xff0000 >> 16;
                    g+= img.getRGB(i, j) & 0xff00 >> 8;
                    b+= img.getRGB(i, j) & 0xff;
                } else {
                    r+= img.getRGB(j, i) & 0xff0000 >> 16;
                    g+= img.getRGB(j, i) & 0xff00 >> 8;
                    b+= img.getRGB(j, i) & 0xff;
                }
            }

            res[0][i] = r;
            res[1][i] = g;
            res[2][i] = b;
        }

        return res;
    }

    private ArrayList<Integer> findCut(int[][] scan){
        ArrayList<Integer> cuts = new ArrayList<>();

        int max = 0;
        for (int i = 0; i < scan.length; i++)
            for (int j = 0; j < scan[0].length; j++)
                if (scan[i][j] > max) max = scan[i][j];

                boolean b;
        for (int i = 1; i < scan[0].length - 1; i++){
            b = scan[0][i] == max && scan[1][i] == max && scan[2][i] == max;
            if (isCut(scan[0][i-1], scan[1][i-1], scan[2][i-1], scan[0][i], scan[1][i], scan[2][i], scan[0][i+1], scan[1][i+1], scan[2][i+1]) && b){
                cuts.add(i);
            }
        }

        return cuts;
    }
    /*
     * r = red
     * g = green
     * b = blue
     *
     * B = Before
     * P = Present
     * A = After
     */
    private boolean isCut(int rB, int gB, int bB, int rP, int gP, int bP, int rA, int gA, int bA){
        boolean res;

        res = rB < rP && gB < gP && bB < bP;
        res = res && rP >= rA && gP >= gA && bP >= bA;
        //res = res && rA == 31110 && gA == 31110 && bA == 31110;

        return res;
    }
}
