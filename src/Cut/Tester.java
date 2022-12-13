package Cut;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Tester {

    private void test1(){
        BufferedImage img;
        try {
            img = ImageIO.read(new File("./data/picture/test_text.png"));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
        //*
        int i = 0;
        for (BufferedImage line : Cutter.getInstance().cutVertically(img)){
            try {
                ImageIO.write(line, "png", new File("./data/picture/line" + i + ".png"));
            } catch (Exception e){}
            i++;
        }
         //*/
    }

    public static void main(String[] args){
        Tester tester = new Tester();

        tester.test1();
    }
}
