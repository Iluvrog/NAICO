package fc.Comparateur;

import fc.Map;
import fc.Masque;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ComparateurTest {

    static String name_file_test = ".TEST_LOAD_SAVE.nv";

    @Test
    void getInstance(){
        Comparateur comparateur = Comparateur.getInstance();
        assertNotEquals(null, comparateur);
    }

    @Test
    void fill(){
        Comparateur comparateur = Comparateur.getInstance();
        comparateur.fill();
    }

    @Test
    void saveAndLoad() {
        Comparateur comparateur = Comparateur.getInstance();
        comparateur.fill();

        int[][] test0 = new int[][]{{0}};
        Map map0 = new Map(new Masque(test0));

        int[][] test1 = new int[][]{{0, 1}, {1, 0}};
        Map map1 = new Map(new Masque(test1));

        int[][] test2 = new int[][]{{0, 1, 0}};
        Map map2 = new Map(new Masque(test2));

        char[] before = new char[]{comparateur.compare(map0), comparateur.compare(map1), comparateur.compare(map2)};

        comparateur.save(name_file_test);

        comparateur.load(name_file_test);

        char[] after = new char[]{comparateur.compare(map0), comparateur.compare(map1), comparateur.compare(map2)};

        for (int i = 0; i < before.length; i++){
            assertEquals(before[i], after[i]);
        }
    }

    @AfterAll
    static void deleteFile(){
        File f = new File(name_file_test);
        if (f.exists()) f.delete();
    }
}