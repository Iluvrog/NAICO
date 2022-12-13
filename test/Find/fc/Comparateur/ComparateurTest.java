package Find.fc.Comparateur;

import Find.fc.Masque;
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

        int taille = Masque.getTailleResize();

        int[][] test0 = new int[taille][taille];
        test0[0][0] = 1;
        Masque masque0 = new Masque(test0);

        int[][] test1 = new int[taille][taille];
        for (int i = 0; i < taille; i++){
            test1[i][i] = 1;
        }
        Masque masque1 = new Masque(test1);

        int[][] test2 = new int[taille][taille];
        for (int i = 1; i < taille-1; i++){
            for (int j = 1; j < taille-1; j++){
                test2[i][j] = 1;
            }
        }
        Masque masque2 = new Masque(test2);

        char[] before = new char[]{comparateur.compare(masque0), comparateur.compare(masque1), comparateur.compare(masque2)};

        comparateur.save(name_file_test);

        comparateur.load(name_file_test);

        char[] after = new char[]{comparateur.compare(masque0), comparateur.compare(masque1), comparateur.compare(masque2)};

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