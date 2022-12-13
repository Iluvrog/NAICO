package Find.fc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MapTest {

    //Dépend trop de variable local
    /*@Test
    @Timeout(1)
    @DisplayName("toString()")
    void testToString(){
        int n = 5;
        int[][] test = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test[i][j] = 1;
            }
        }

        Map map = new Map(new Masque(test));

        String attendu = "0.5159783323169419 1.0191930567249972 1.203204044597255 1.0191930567249972 0.5159783323169419 \n" +
                "1.0191930567249972 1.678864936966299 1.670506672278552 1.678864936966299 1.019193056724997 \n" +
                "1.203204044597255 1.6705066722785515 1.632787524477362 1.6705066722785518 1.203204044597255 \n" +
                "1.019193056724997 1.678864936966299 1.6705066722785518 1.678864936966299 1.019193056724997 \n" +
                "0.5159783323169419 1.019193056724997 1.203204044597255 1.019193056724997 0.5159783323169419 \n";

        assertEquals(attendu, map.toString());
    }*/

    @Test
    void compareEgaux(){
        int n = 5;
        int[][] test = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test[i][j] = 1;
            }
        }
        Map map1 = new Map(new Masque(test));
        Map map2 = new Map(new Masque(test));

        assertEquals(Double.POSITIVE_INFINITY, map1.compare(map2));
    }

    @Test
    void compareDifferents(){
        int n = 5;
        int[][] test1 = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test1[i][j] = 1;
            }
        }

        int[][] test2 = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                if(i == j) test2[i][j] = 1;
            }
        }

        Map map1 = new Map(new Masque(test1));
        Map map2 = new Map(new Masque(test2));

        assertTrue(Double.isFinite(map1.compare(map2)));
    }

    @Test
    void compareTaillesDifferentes(){
        int n = 5;
        int[][] test1 = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test1[i][j] = 1;
            }
        }

        int m = 6;
        int[][] test2 = new int[m][m];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                if(i == j) test2[i][j] = 1;
            }
        }

        Map map1 = new Map(new Masque(test1));
        Map map2 = new Map(new Masque(test2));

        assertEquals(-1, map1.compare(map2));
    }

    @Test
    @Timeout(1)
    void creationFromLine(){
        int n = 5;
        int[][] test = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test[i][j] = 1;
            }
        }

        Map map = new Map(new Masque(test));

        Map copie = new Map(map.toLine());

        assertEquals(Double.POSITIVE_INFINITY, map.compare(copie));
    }
}