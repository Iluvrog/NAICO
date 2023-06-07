package Find.Comparateur;

import Find.Modeles.Comparatifs.Map;
import Find.Modeles.Comparatifs.Masque;
import Find.Verbose.Verbose;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Comparateur {
    private static Comparateur instance;

    private final ArrayList<CelluleMap> celluleMaps;
    private final ArrayList<CelluleMasque> celluleMasques;

    private static final String default_file_name = "precalc.data";

    private static final int size_img = 50;

    private static final double poidMasque = .5, poidMap = 1;

    /* Liste des types
     * 0 : CelluleMap
     * 1 : CelluleMasque
     */

    private Comparateur(){
        celluleMaps = new ArrayList<>();
        celluleMasques = new ArrayList<>();
    }

    public static Comparateur getInstance(){
        if (instance == null) instance = new Comparateur();
        return instance;
    }

    public char compare(Masque masque){
        return compare_Verbose(masque).getWinner();
    }

    public Verbose compare_Verbose(Masque masque){
        Map map = new Map(masque);

        Verbose verbose = new Verbose();

        double comp;

        for (CelluleMap c : celluleMaps){
            comp = c.compare(map);
            verbose.add(c.getName(), comp * poidMap, "CelluleMap");
        }

        for (CelluleMasque c : celluleMasques){
            comp = c.compare(masque);
            verbose.add(c.getName(), comp * poidMasque, "CelluleMasque");
        }

        return verbose;
    }

    public void fill(){
        fillSpace();
        fillAscii();
        fillLatin1();
    }

    public void fillSpace(){
        fillBorn(32, 33);
    }

    public void fillAscii(){
        fillBorn(33, 127);
    }

    public void fillLatin1(){
        fillBorn(0xa1, 0xff);
    }

    private void fillBorn(int start, int end){
        BufferedImage img;
        Graphics g;

        for (int i = start; i < end; i++){

            img = new BufferedImage(size_img, size_img, BufferedImage.TYPE_INT_ARGB);
            g = img.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, size_img, size_img);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf((char)i), 0, size_img/4);

            //add(img, i, 0);
            add(img, (char)i, 1);
        }
    }

    public void add(BufferedImage img, char name, int type){
        if (type == 0) addMap(img, name);
        else if (type == 1) addMasque(img, name);
    }

    private void addMasque(BufferedImage img, char name){
        Masque m = new Masque(img);
        for (CelluleMasque c : celluleMasques){
            if (c.getName() == name) {
                c.add(m);
                return;
            }
        }
        CelluleMasque c = new CelluleMasque(name);
        c.add(m);
        celluleMasques.add(c);
    }

    private void addMap(BufferedImage img, char name){
        Map m = new Map(new Masque(img));
        for (CelluleMap c : celluleMaps){
            if (c.getName() == name) {
                c.add(m);
                return;
            }
        }
        CelluleMap c = new CelluleMap(name);
        c.add(m);
        celluleMaps.add(c);
    }

    public void save(){
        save(default_file_name);
    }

    public void save(String name){
        StringBuilder saveForm = new StringBuilder();

        String celluleSave;
        int size;
        /*for (CelluleMap c : celluleMaps){
            celluleSave = c.saveForm();
            size = celluleSave.length();
            for (int i = 0; i < 4; i++){
                saveForm.append((char) (size >> ((3 - i) * 8) & 0xff));
            }
            saveForm.append((char)0);
            saveForm.append(celluleSave);
        }*/

        for (CelluleMasque c : celluleMasques){
            celluleSave = c.saveForm();
            size = celluleSave.length();
            for (int i = 0; i < 4; i++){
                saveForm.append((char) (size >> ((3 - i) * 8) & 0xff));
            }
            saveForm.append((char)1);
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
        celluleMaps.clear();

        File f = new File(name);
        if (!f.exists()){
            loadError();
            return;
        }

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

            if (sb.length() == 0){
                loadError();
                return;
            }

            String saveForm = sb.substring(0, sb.length()-1);

            int size;
            char type;

            while (saveForm.length() != 0){
                size = 0;
                for (int i = 0; i < 4; i++){
                    size = size << 8;
                    size += saveForm.charAt(0);
                    saveForm = saveForm.substring(1);
                }

                type = saveForm.charAt(0);
                saveForm = saveForm.substring(1);

                if (type == 0) {
                    celluleMaps.add(new CelluleMap(saveForm.substring(0, size)));
                } else if (type == 1){
                    celluleMasques.add(new CelluleMasque(saveForm.substring(0, size)));
                } else {
                    loadError();
                    return;
                }
                saveForm = saveForm.substring(size);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadError(){
        celluleMaps.clear();
    }

    public Map getMap(char i){
        for (CelluleMap c : celluleMaps){
            if (i == c.getName()) return c.getMaps().get(0);
        }
        return null;
    }

    public Masque getMasque(char i){
        for (CelluleMasque c : celluleMasques){
            if (i == c.getName()) return c.getMasques().get(0);
        }
        return null;
    }
}
