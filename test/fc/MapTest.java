package fc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MapTest {

    @Test
    @Timeout(1)
    @DisplayName("toString")
    void testToString(){
        int n = 5;
        int[][] test = new int[n][n];
        for (int i = 1; i < n-1; i++){
            for (int j = 1; j < n-1; j++){
                test[i][j] = 1;
            }
        }

        Map map = new Map(new Masque(test));

        String attendue = "0.5159783323169419 1.0191930567249972 1.203204044597255 1.0191930567249972 0.5159783323169419 \n" +
                "1.0191930567249972 1.678864936966299 1.670506672278552 1.678864936966299 1.019193056724997 \n" +
                "1.203204044597255 1.6705066722785515 1.632787524477362 1.6705066722785518 1.203204044597255 \n" +
                "1.019193056724997 1.678864936966299 1.6705066722785518 1.678864936966299 1.019193056724997 \n" +
                "0.5159783323169419 1.019193056724997 1.203204044597255 1.019193056724997 0.5159783323169419 \n";

        assertEquals(map.toString(), attendue);
    }

}