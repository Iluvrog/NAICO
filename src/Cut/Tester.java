package Cut;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Tester {

    private void test1(){
        BufferedImage img;
        try {
            img = ImageIO.read(new File("./data/picture/test_latin1.png"));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
        //*
        int i = 0;
        for (BufferedImage line : Cutter.getInstance().cutHorizontally(img)){
            try {
                ImageIO.write(line, "png", new File("./data/picture/line" + i + ".png"));
            } catch (Exception e){}
            i++;
        }
         //*/
    }

    private void test2(){
        int ligne = 0;
        BufferedImage img;
        try {
            img = ImageIO.read(new File("./data/picture/line" + ligne + ".png"));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
        //*
        int i = 0;
        for (BufferedImage line : Cutter.getInstance().cutVertically(img)){
            try {
                ImageIO.write(line, "png", new File("./data/picture/char" + ligne + "_" + i + ".png"));
            } catch (Exception e){}
            i++;
        }
        //*/
    }

    public static void main(String[] args){
        Tester tester = new Tester();

        tester.test1();
        tester.test2();
    }
}
