package fc.Comparateur;

import fc.Map;
import fc.Masque;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Comparateur {
    private static Comparateur instance;

    private final ArrayList<Cellule> cellules;

    private static final String default_file_name = "precalc.data";

    private static final int size_img = 50;

    private Comparateur(){
        cellules = new ArrayList<>();
    }

    public static Comparateur getInstance(){
        if (instance == null) instance = new Comparateur();
        return instance;
    }

    public char compare(Map map){
        char res = 0;
        double actcomp = 0;
        double comp;
        for (Cellule c : cellules){
            comp = c.compare(map);
            if (comp > actcomp){
                actcomp = comp;
                res = c.getName();
            }
        }
        return res;
    }

    public void fill(){
        fillAscii();
    }

    public void fillAscii(){
        Cellule cellule;
        BufferedImage img;
        Graphics g;

        for (char i = 33; i < 127; i++){
            cellule = new Cellule(i);

            img = new BufferedImage(size_img, size_img, BufferedImage.TYPE_INT_ARGB);
            g = img.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, size_img, size_img);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(i), size_img/4, size_img/4);

            cellule.add(new Map(new Masque(img)));

            cellules.add(cellule);
        }
    }

    public void save(){
        save(default_file_name);
    }

    public void save(String name){
        StringBuilder saveForm = new StringBuilder();

        String celluleSave;
        int size;
        for (Cellule c : cellules){
            celluleSave = c.saveForm();
            size = celluleSave.length();
            for (int i = 0; i < 4; i++){
                saveForm.append((char) (size >> ((3 - i) * 8) & 0xff));
            }
            saveForm.append(celluleSave);
        }

        try {
            FileWriter file = new FileWriter(name);
            file.write(saveForm.toString());
            file.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        File f = new File(name);
        System.out.println("Size of " + name + " : " + f.length()/1024./1024 + " Mo");
    }

    public void load(){
        load(default_file_name);
    }

    public void load(String name){
        File f = new File(name);
        if (!f.exists()) return;

        cellules.clear();

        try {
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null)
            {
                sb.append(line);
                sb.append("\n");
            }
            fileReader.close();

            if (sb.length() == 0) return;

            String saveForm = sb.substring(0, sb.length()-1);

            int size;

            while (saveForm.length() != 0){
                size = 0;
                for (int i = 0; i < 4; i++){
                    size = size << 8;
                    size += saveForm.charAt(0);
                    saveForm = saveForm.substring(1);
                }

                cellules.add(new Cellule(saveForm.substring(0, size)));
                saveForm = saveForm.substring(size);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
