package Find.Comparateur;

import Find.Map;

import java.util.ArrayList;

public class CelluleMap {
    private final char name;
    private final ArrayList<Map> maps;

    CelluleMap(char name){
        this.name = name;
        maps = new ArrayList<>();
    }

    CelluleMap(String saveF){
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
    }

    public char getName() {
        return name;
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public void add(Map map){
        maps.add(map);
    }

    public double compare(Map map){
        double res = 0;
        double comp;
        for (Map m : maps){
            comp = m.compare(map);
            if (comp > res) res = comp;
        }
        return res;
    }

    public String saveForm(){
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
    }
}
