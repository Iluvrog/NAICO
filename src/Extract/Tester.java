package Extract;

import Cut.Cutter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Tester {

    private void test1(){
        BufferedImage img;
        try {
            img = ImageIO.read(new File("./data/picture/A.png"));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
        //*
        int i = 0;
        try {
            ImageIO.write(Extractor.getInstance().extract(img), "png", new File("./data/picture/A_extract.png"));
        } catch (Exception e){}
         //*/
    }

    private void test2(){
        try {

            Cutter cutter = Cutter.getInstance();

            Extractor extractor = Extractor.getInstance();

            BufferedImage text = ImageIO.read(new File("./data/picture/test_latin1.png"));
            int i,j;
            i=0;
            for (BufferedImage line : cutter.cutHorizontally(text)){
                j =0;
                for (BufferedImage character : cutter.cutVertically(line)){
                    ImageIO.write(Extractor.getInstance().extract(character), "png", new File("./data/picture/char_extract_" + i + "_" + j + ".png"));
                    j++;
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Tester tester = new Tester();

        tester.test2();
    }
}
