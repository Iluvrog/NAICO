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

    CelluleMasque(String saveF){
        masques = new ArrayList<>();
        name = saveF.charAt(0);

        saveF = saveF.substring(1);

        int size;
        String masqueString;

        while (saveF.length() != 0){
            size = 0;
            for (int i = 0; i < 4; i++){
                size = size << 8;
                size += saveF.charAt(0);
                saveF = saveF.substring(1);
            }
            masqueString = saveF.substring(0, size);
            add(Masque.loadMasque(masqueString));
            saveF = saveF.substring(size);
        }
    }

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

    public String saveForm(){
        StringBuilder res = new StringBuilder();

        res.append(name);

        String masqueString;
        int size;
        for (Masque m : masques){
            masqueString = m.toLine();
            size = masqueString.length();
            for (int i = 0; i < 4; i++){
                res.append((char) (size >> ((3 - i) * 8) & 0xff));
            }
            res.append(masqueString);
        }

        return res.toString();
    }
}
