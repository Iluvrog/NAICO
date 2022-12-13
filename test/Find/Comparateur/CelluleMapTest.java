package Find.Comparateur;

import Find.Map;
import Find.Masque;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CelluleMapTest {

    @Test
    void getName() {
        char name = 't';

        CelluleMap celluleMap = new CelluleMap(name);

        assertEquals(name, celluleMap.getName());
    }

    @Test
    void getMapsAndAddSize() {
        CelluleMap celluleMap = new CelluleMap('t');

        int[][] test0 = new int[][]{{0}};
        Map map0 = new Map(new Masque(test0));
        celluleMap.add(map0);

        int[][] test1 = new int[][]{{0, 1}, {1, 0}};
        Map map1 = new Map(new Masque(test1));
        celluleMap.add(map1);

        int[][] test2 = new int[][]{{0, 1, 0}};
        Map map2 = new Map(new Masque(test2));
        celluleMap.add(map2);

        ArrayList<Map> maps = celluleMap.getMaps();
        assertEquals(3, maps.size());
    }

    @Test
    void getMapsAndAddElement() {
        CelluleMap celluleMap = new CelluleMap('t');

        int[][] test0 = new int[][]{{0}};
        Map map0 = new Map(new Masque(test0));
        celluleMap.add(map0);

        int[][] test1 = new int[][]{{0, 1}, {1, 0}};
        Map map1 = new Map(new Masque(test1));
        celluleMap.add(map1);

        int[][] test2 = new int[][]{{0, 1, 0}};
        Map map2 = new Map(new Masque(test2));
        celluleMap.add(map2);

        ArrayList<Map> maps = celluleMap.getMaps();

        assertEquals(map0, maps.get(0));
        assertEquals(map1, maps.get(1));
        assertEquals(map2, maps.get(2));
    }

    @Test
    void saveFormeSize(){
        CelluleMap celluleMap = new CelluleMap('t');

        int[][] test0 = new int[][]{{0}};
        Map map0 = new Map(new Masque(test0));
        celluleMap.add(map0);

        int[][] test1 = new int[][]{{0, 1}, {1, 0}};
        Map map1 = new Map(new Masque(test1));
        celluleMap.add(map1);

        int[][] test2 = new int[][]{{0, 1, 0}};
        Map map2 = new Map(new Masque(test2));
        celluleMap.add(map2);

        CelluleMap copie = new CelluleMap(celluleMap.saveForm());

        assertEquals(celluleMap.getMaps().size(), copie.getMaps().size());
    }

    @Test
    void saveFormeElement(){
        CelluleMap celluleMap = new CelluleMap('t');

        int[][] test0 = new int[][]{{0}};
        Map map0 = new Map(new Masque(test0));
        celluleMap.add(map0);

        int[][] test1 = new int[][]{{0, 1}, {1, 0}};
        Map map1 = new Map(new Masque(test1));
        celluleMap.add(map1);

        int[][] test2 = new int[][]{{0, 1, 0}};
        Map map2 = new Map(new Masque(test2));
        celluleMap.add(map2);

        CelluleMap copie = new CelluleMap(celluleMap.saveForm());

        assertEquals(Double.POSITIVE_INFINITY, celluleMap.getMaps().get(0).compare(copie.getMaps().get(0)));
        assertEquals(Double.POSITIVE_INFINITY, celluleMap.getMaps().get(1).compare(copie.getMaps().get(1)));
        assertEquals(Double.POSITIVE_INFINITY, celluleMap.getMaps().get(2).compare(copie.getMaps().get(2)));
    }
}