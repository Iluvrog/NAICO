package fc.Comparateur;

import fc.Map;

import java.util.ArrayList;

public class Cellule {
    private char name;
    private ArrayList<Map> maps;

    Cellule(char name){
        this.name = name;
        maps = new ArrayList<>();
    }

    public char getName() {
        return name;
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
}
