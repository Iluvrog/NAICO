package Find.Comparateur;

import Find.Map;
import Find.Masque;

import java.util.ArrayList;

public class CelluleMasque {
    private final char name;
    private final ArrayList<Masque> masques;

    CelluleMasque(char name){
        this.name = name;
        masques = new ArrayList<>();
    }

    /*CelluleMap(String saveF){
        maps = new ArrayList<>();
        name = saveF.charAt(0);

        saveF = saveF.substring(1);

        int size;
        String mapString;

        while (saveF.length() != 0){
            size = 0;
            for (int i = 0; i < 4; i++){
                size = size << 8;
                size += saveF.charAt(0);
                saveF = saveF.substring(1);
            }
            mapString = saveF.substring(0, size);
            add(new Map(mapString));
            saveF = saveF.substring(size);
        }
    }*/

    public char getName() {
        return name;
    }

    public ArrayList<Masque> getMasques() {
        return masques;
    }

    public void add(Masque masque){
        masques.add(masque);
    }

    public double compare(Masque masque){
        double res = 0;
        double comp;
        for (Masque m : masques){
            comp = m.compare(masque);
            if (comp > res) res = comp;
        }
        return res;
    }

    /*public String saveForm(){
        StringBuilder res = new StringBuilder();

        res.append(name);

        String mapString;
        int size;
        for (Map m : maps){
            mapString = m.toLine();
            size = mapString.length();
            for (int i = 0; i < 4; i++){
                res.append((char) (size >> ((3 - i) * 8) & 0xff));
            }
            res.append(mapString);
        }

        return res.toString();
    }*/
}
