package Main.fc;

import Cut.Cutter;
import Extract.Extractor;
import Find.Comparateur.Comparateur;
import Find.Masque;
import Find.Verbose.CelluleVerbose;
import Find.Verbose.Verbose;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    private static void test1(){
        try {
            Comparateur comparateur = Comparateur.getInstance();
            comparateur.fill();

            Cutter cutter = Cutter.getInstance();

            Extractor extractor = Extractor.getInstance();

            BufferedImage text = ImageIO.read(new File("./data/picture/test_text.png"));
            for (BufferedImage line : cutter.cutHorizontally(text)){
                for (BufferedImage character : cutter.cutVertically(line)){
                    System.out.print(comparateur.compare(new Masque(extractor.extract(character))));
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test2(int i, int j){
        Comparateur comparateur = Comparateur.getInstance();
        comparateur.fill();

        String path = "./data/picture/char_extract_" + i + "_" + j + ".png";
        BufferedImage img;

        try {
            img = ImageIO.read(new File(path));
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
        Verbose v = comparateur.compare_Verbose(new Masque(img));
        for (CelluleVerbose cv : v.getCelluleVerboses()){
            System.out.println(cv.getVerboseString());
        }
    }

    public static void main(String[] args){
        test2(1, 0);
        //test1();
    }
}
