package Main.fc;

import Cut.Cutter;
import Extract.Extractor;
import Find.Comparateur.Comparateur;
import Find.Masque;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args){
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
            return;
        }
    }
}
