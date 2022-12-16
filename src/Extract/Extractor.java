package Extract;

import java.awt.image.BufferedImage;

public class Extractor {

    private static Extractor instance;

    private Extractor(){}

    public static Extractor getInstance(){
        if (instance == null) instance = new Extractor();
        return instance;
    }

    public BufferedImage extract(BufferedImage img){
        BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        double l;
        int color;
        for (int i = 0; i < img.getWidth(); i++){
            for (int j = 0; j < img.getHeight(); j++){
                color = img.getRGB(i, j);
                l = 0.2126 * (color & 0xff0000 >> 16)  + 0.7152 * (color & 0xff00 >> 8) + 0.0722 * (color & 0xff);
                if (l < 200) res.setRGB(i, j, 0xff000000);
                else res.setRGB(i, j, 0xffffffff);
            }
        }

        return res;
    }
}
