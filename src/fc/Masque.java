package fc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Masque {
    private int[][] masque;

    private static final int seuil = 15;

    public Masque(int[][] masque){
        this.masque = masque;
    }

    public Masque(BufferedImage image){
        int hauteur = image.getHeight();
        int largeur = image.getWidth();
        masque = new int[largeur][hauteur];

        Color color;

        for (int i = 0; i < largeur; i++){
            for (int j = 0; j < hauteur; j++){
                color = new Color(image.getRGB(i,j));
                if (color.getBlue() <= seuil && color.getRed() <= seuil && color.getGreen() <= seuil) masque[i][j] = 1;
            }
        }
    }

    public Masque(String path){
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
        int hauteur = image.getHeight();
        int largeur = image.getWidth();
        masque = new int[largeur][hauteur];

        Color color;

        for (int i = 0; i < largeur; i++){
            for (int j = 0; j < hauteur; j++){
                color = new Color(image.getRGB(i,j));
                if (color.getBlue() <= seuil && color.getRed() <= seuil && color.getGreen() <= seuil) masque[i][j] = 1;
            }
        }

    }

    public int getHauteur(){
        return masque.length;
    }

    public int getLargeur(){
        return masque[0].length;
    }

    public int getValue(int i, int j){
        return masque[i][j];
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < masque.length; i++){
            for (int j = 0; j < masque[0].length; j++){
                string.append(masque[i][j]).append(" ");
            }
            string.append("\n");
        }

        return string.toString();
    }

    public BufferedImage toBufferedImage(){
        int hauteur = masque.length;
        int largeur = masque[0].length;

        BufferedImage image = new BufferedImage(hauteur, largeur, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < hauteur; i++){
            for (int j = 0;  j < largeur; j++){
                if (masque[i][j] == 0){
                    image.setRGB(i, j, Color.WHITE.getRGB());
                }
                else {
                    image.setRGB(i, j, Color.BLACK.getRGB());
                }
            }
        }
        return image;
    }
}
