package Find.Comparateur;

import Find.Map;
import Find.Masque;
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
        fillAscii();
    }

    public void fillAscii(){
        CelluleMap celluleMap;
        CelluleMasque celluleMasque;
        BufferedImage img;
        Graphics g;

        for (char i = 33; i < 127; i++){
            celluleMap = new CelluleMap(i);
            celluleMasque = new CelluleMasque(i);

            img = new BufferedImage(size_img, size_img, BufferedImage.TYPE_INT_ARGB);
            g = img.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, size_img, size_img);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(i), 0, size_img/4);

            celluleMasque.add(new Masque(img));
            celluleMap.add(new Map(new Masque(img)));

            celluleMasques.add(celluleMasque);
            celluleMaps.add(celluleMap);
        }
    }

    public void save(){
        save(default_file_name);
    }

    public void save(String name){
        StringBuilder saveForm = new StringBuilder();

        String celluleSave;
        int size;
        for (CelluleMap c : celluleMaps){
            celluleSave = c.saveForm();
            size = celluleSave.length();
            for (int i = 0; i < 4; i++){
                saveForm.append((char) (size >> ((3 - i) * 8) & 0xff));
            }
            saveForm.append((char)0);
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
}
