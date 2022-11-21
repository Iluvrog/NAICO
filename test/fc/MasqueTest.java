package fc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MasqueTest {

    @Test
    @Timeout(1)
    void getHauteur1() {
        int n = 5;
        int[][] test = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test[i][j] = 1;
            }
        }

        Masque masque = new Masque(test);

        assertEquals(n, masque.getHauteur());
    }

    @Test
    @Timeout(1)
    void getHauteur2() {
        int n = 5;
        int[][] test = new int[n+1][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test[i][j] = 1;
            }
        }

        Masque masque = new Masque(test);

        assertEquals(n+1, masque.getHauteur());
    }

    @Test
    @Timeout(1)
    void getLargeur1() {
        int n = 5;
        int[][] test = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test[i][j] = 1;
            }
        }

        Masque masque = new Masque(test);

        assertEquals(n, masque.getLargeur());
    }

    @Test
    @Timeout(1)
    void getLargeur2() {
        int n = 5;
        int[][] test = new int[n][n+1];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test[i][j] = 1;
            }
        }

        Masque masque = new Masque(test);

        assertEquals(n+1, masque.getLargeur());
    }

    @Test
    @Timeout(1)
    void getValue0() {
        int n = 5;
        int[][] test = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test[i][j] = 1;
            }
        }

        Masque masque = new Masque(test);

        assertEquals(0, masque.getValue(0, 0));
    }

    @Test
    @Timeout(1)
    void getValue1() {
        int n = 5;
        int[][] test = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test[i][j] = 1;
            }
        }

        Masque masque = new Masque(test);

        assertEquals(1, masque.getValue(1, 1));
    }

    @Test
    @Timeout(1)
    @DisplayName("toString()")
    void testToString() {
        int n = 5;
        int[][] test = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test[i][j] = 1;
            }
        }

        Masque masque = new Masque(test);

        String attendu = "0 0 0 0 0 \n" +
                "0 1 1 1 0 \n" +
                "0 1 1 1 0 \n" +
                "0 1 1 1 0 \n" +
                "0 0 0 0 0 \n";

        assertEquals(attendu, masque.toString());
    }
}