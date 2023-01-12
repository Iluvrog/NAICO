package Cut;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Cutter {

    private static Cutter instance;

    /*
     * 0 = Horizontally
     * 1 = Vertically
     */
    private int type = 0;

    private Cutter(){

    }

    public static Cutter getInstance(){
        if (instance == null) instance = new Cutter();
        return instance;
    }

    public ArrayList<BufferedImage> cutHorizontally(BufferedImage text){
        type = 0;

        ArrayList<BufferedImage> cuts = new ArrayList<>();

        ArrayList<Integer> cutPoint =  cut(text);

        int startCut = 0;
        for (int i : cutPoint){
            cuts.add(text.getSubimage(0, startCut+1, text.getWidth(), i-startCut-1));
            startCut = i;
        }
        //cuts.add(text.getSubimage(0, startCut, text.getWidth(), text.getHeight()-startCut));

        return cuts;
    }

    public ArrayList<BufferedImage> cutVertically(BufferedImage text){
        type = 1;

        ArrayList<BufferedImage> cuts = new ArrayList<>();

        ArrayList<Integer> cutPoint = cut(text);

        int startCut = 0;
        for (int i : cutPoint){
            cuts.add(text.getSubimage(startCut+1, 0, i-startCut-1, text.getHeight()));
            startCut = i;
        }
        //cuts.add(text.getSubimage(startCut, 0, text.getWidth()-startCut, text.getHeight()));

        return cuts;
    }

    private ArrayList<Integer> cut(BufferedImage text){
        int[][] scan = scanner(type, text);
        return findCut(scan);
    }

    private int[][] scanner(int type, BufferedImage img){
        type = type & 1;

        int length, other_length;
        if (type == 0) {length = img.getHeight(); other_length = img.getWidth();}
        else {length = img.getWidth(); other_length = img.getHeight();}

        int[][] res = new int[4][length];

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

            res[0][i] = r/other_length;
            res[1][i] = g/other_length;
            res[2][i] = b/other_length;
            res[3][i] = (int) ((0.2126*r + 0.7152*g + 0.0722*b) / other_length);
        }

        return res;
    }

    private ArrayList<Integer> findCut(int[][] scan){
        ArrayList<Integer> cuts = new ArrayList<>();

        int permCut, space_max;
        if (type == 0) {
            permCut = 253;
            space_max = 15;
        }
        else {
            permCut = 245;
            space_max = 3;
        }

        /*for (int i = 1; i < scan[0].length - 1; i++){
            if (isCut(scan[3][i-1], scan[3][i], scan[3][i+1], permCut)){
                cuts.add(i);
            }
        }*/

        boolean[] lum = new boolean[scan[0].length];
        for (int i = 0; i < scan[0].length; i++){
            lum[i] = scan[3][i] > permCut;
        }

        int nb_space = 0;
        for (int i = 1; i < lum.length - 1; i++){
            if (lum[i]){
                if (!lum[i-1]) cuts.add(i);
                else nb_space++;
            } else {
                if (lum[i-1] && nb_space > space_max) cuts.add(i-1);
                nb_space = 0;
            }
        }

        return cuts;
    }

    private boolean isCut(int lB, int lP, int lA, int permCut){
        boolean res = lP > permCut;

        res = res && ((lB < lP + 50 && lP >= lA) || (lP - 50 < lA));

        return res;
        //return lB < lP && lP >= lA && lP > permCut;
    }
}
