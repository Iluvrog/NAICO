package Find.Modeles.Comparatifs;

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

    private Masque(){}

    public static Masque loadMasque(String line){
        Masque m = new Masque();

        int hauteur = 0;
        int largeur = 0;

        for (int i = 0; i < 4; i++){
            hauteur = hauteur << 8;
            hauteur += line.charAt(0);
            line = line.substring(1);
        }

        for (int i = 0; i < 4; i++){
            largeur = largeur << 8;
            largeur += line.charAt(0);
            line = line.substring(1);
        }

        m.masque = new int[hauteur][largeur];

        byte[] mots = line.getBytes();
        byte mot;
        int mi = 0, mj = 0;
        for (int i = 0; i < mots.length; i++){
            mot = mots[i];
            for (int j = 0; j < 8; j++){
                m.masque[mi][mj] = mot & (1 << 7-j);
                mj++;
                if (mj == largeur){
                    mj = 0;
                    mi++;
                }
                if (mi == hauteur) return m;
            }
        }

        return m;
    }

    private void traiteImage(BufferedImage image){
        int minW = Integer.MAX_VALUE, minH = Integer.MAX_VALUE;
        int maxW = 0, maxH = 0;

        int hauteur = image.getHeight();
        int largeur = image.getWidth();

        masque = new int[tailleResize][tailleResize];

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
        int width = maxW-minW+1;
        int height = maxH-minH+1;
        BufferedImage sub = image.getSubimage(minW, minH, width, height);

        int occupation = 9*tailleResize/10;

        if (width > height){
            height = height * occupation / width;
            width = occupation;
        } else {
            width = width * occupation / height;
            height = occupation;
        }

        BufferedImage resize = new BufferedImage(tailleResize, tailleResize, BufferedImage.TYPE_INT_ARGB);
        Graphics g = resize.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, tailleResize, tailleResize);
        g.drawImage(sub, (tailleResize - width)/2, (tailleResize - height)/2, width, height, null);
        //g.drawImage(sub, tailleResize/20, tailleResize/20, occupation, occupation, null);

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

    public double compare(Masque m){
        double comp = 0;

        int hauteur = getHauteur(), largeur = getLargeur();

        //if (hauteur != mapComp.length || largeur != mapComp[0].length) return -1;

        for (int i = 0; i < hauteur; i++){
            for (int j = 0; j < largeur; j++){
                comp+= Math.abs(getValue(i, j) - m.getValue(i, j));
            }
        }

        return 1/(comp/hauteur/largeur);
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

    public String toLine(){
        StringBuilder res = new StringBuilder();

        int hauteur = getHauteur();
        int largeur = getLargeur();

        for (int i = 0; i < 4; i++){
            res.append((char) (hauteur >> ((3 - i) * 8) & 0xff));
        }
        for (int i = 0; i < 4; i++){
            res.append((char) (largeur >> ((3 - i) * 8) & 0xff));
        }

        byte mot = 0;
        int compteur = 0;
        for (int i = 0; i < hauteur; i++){
            for (int j = 0; j < largeur; j++){
                if (compteur == 0) mot = 0;
                mot += (getValue(i, j) << 7-compteur);
                if (compteur == 7) res.append(mot);
                compteur = (compteur + 1)%8;
            }
        }

        if (compteur != 0){
            res.append(mot);
        }

        return res.toString();
    }
}
