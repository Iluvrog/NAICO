package Find;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Masque {
    private int[][] masque;

    private static final int seuil = 15;

    private static final int tailleResize = 30;

    public Masque(int[][] masque){
        this.masque = masque;
    }

    public Masque(BufferedImage image){
        traiteImage(image);
    }

    public Masque(String path){
        try {
            traiteImage(ImageIO.read(new File(path)));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    private void traiteImage(BufferedImage image){
        int minW = Integer.MAX_VALUE, minH = Integer.MAX_VALUE;
        int maxW = 0, maxH = 0;

        int hauteur = image.getHeight();
        int largeur = image.getWidth();

        Color color;
        boolean isVoid = true;

        for (int i = 0; i < largeur; i++){
            for (int j = 0; j < hauteur; j++){
                color = new Color(image.getRGB(i,j));
                if (isUnderSeuil(color)) {
                    if (j < minH) minH = j;
                    if (j > maxH) maxH = j;
                    if (i < minW) minW = i;
                    if (i > maxW) maxW = i;
                    isVoid = false;
                }
            }
        }

        if (isVoid) return;

        //System.out.println(minW + " " + minH + " " + maxW + " " +maxH);
        BufferedImage sub = image.getSubimage(minW, minH, maxW-minW+1, maxH-minH+1);

        BufferedImage resize = new BufferedImage(tailleResize, tailleResize, BufferedImage.TYPE_INT_ARGB);
        Graphics g = resize.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, tailleResize, tailleResize);
        g.drawImage(sub, tailleResize/20, tailleResize/20, 9*tailleResize/10, 9*tailleResize/10, null);

        masque = new int[tailleResize][tailleResize];

        for (int i = 0; i < tailleResize; i++){
            for (int j = 0; j < tailleResize; j++){
                color = new Color(resize.getRGB(i,j));
                if (isUnderSeuil(color)) masque[i][j] = 1;
            }
        }
    }

    private boolean isUnderSeuil(Color color){
        return color.getBlue() <= seuil && color.getRed() <= seuil && color.getGreen() <= seuil;
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

    public static int getTailleResize(){
        return tailleResize;
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
