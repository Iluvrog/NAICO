package fc.Comparateur;

import fc.Map;
import fc.Masque;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CelluleTest {

    @Test
    void getName() {
        char name = 't';

        Cellule cellule = new Cellule(name);

        assertEquals(name, cellule.getName());
    }

    @Test
    void getMapsAndAddSize() {
        Cellule cellule = new Cellule('t');

        int[][] test0 = new int[][]{{0}};
        Map map0 = new Map(new Masque(test0));
        cellule.add(map0);

        int[][] test1 = new int[][]{{0, 1}, {1, 0}};
        Map map1 = new Map(new Masque(test1));
        cellule.add(map1);

        int[][] test2 = new int[][]{{0, 1, 0}};
        Map map2 = new Map(new Masque(test2));
        cellule.add(map2);

        ArrayList<Map> maps = cellule.getMaps();
        assertEquals(3, maps.size());
    }

    @Test
    void getMapsAndAddElement() {
        Cellule cellule = new Cellule('t');

        int[][] test0 = new int[][]{{0}};
        Map map0 = new Map(new Masque(test0));
        cellule.add(map0);

        int[][] test1 = new int[][]{{0, 1}, {1, 0}};
        Map map1 = new Map(new Masque(test1));
        cellule.add(map1);

        int[][] test2 = new int[][]{{0, 1, 0}};
        Map map2 = new Map(new Masque(test2));
        cellule.add(map2);

        ArrayList<Map> maps = cellule.getMaps();

        assertEquals(map0, maps.get(0));
        assertEquals(map1, maps.get(1));
        assertEquals(map2, maps.get(2));
    }

    @Test
    void saveFormeSize(){
        Cellule cellule = new Cellule('t');

        int[][] test0 = new int[][]{{0}};
        Map map0 = new Map(new Masque(test0));
        cellule.add(map0);

        int[][] test1 = new int[][]{{0, 1}, {1, 0}};
        Map map1 = new Map(new Masque(test1));
        cellule.add(map1);

        int[][] test2 = new int[][]{{0, 1, 0}};
        Map map2 = new Map(new Masque(test2));
        cellule.add(map2);

        Cellule copie = new Cellule(cellule.saveForm());

        assertEquals(cellule.getMaps().size(), copie.getMaps().size());
    }

    @Test
    void saveFormeElement(){
        Cellule cellule = new Cellule('t');

        int[][] test0 = new int[][]{{0}};
        Map map0 = new Map(new Masque(test0));
        cellule.add(map0);

        int[][] test1 = new int[][]{{0, 1}, {1, 0}};
        Map map1 = new Map(new Masque(test1));
        cellule.add(map1);

        int[][] test2 = new int[][]{{0, 1, 0}};
        Map map2 = new Map(new Masque(test2));
        cellule.add(map2);

        Cellule copie = new Cellule(cellule.saveForm());

        assertEquals(Double.POSITIVE_INFINITY, cellule.getMaps().get(0).compare(copie.getMaps().get(0)));
        assertEquals(Double.POSITIVE_INFINITY, cellule.getMaps().get(1).compare(copie.getMaps().get(1)));
        assertEquals(Double.POSITIVE_INFINITY, cellule.getMaps().get(2).compare(copie.getMaps().get(2)));
    }
}