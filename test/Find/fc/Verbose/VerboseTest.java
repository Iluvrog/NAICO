package Find.fc.Verbose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerboseTest {

    @Test
    public void getWinnerStart(){
        Verbose v = new Verbose();
        assertEquals(0, v.getWinner());
    }

    @Test
    public void getScoreStart(){
        Verbose v = new Verbose();
        assertEquals(-1, v.getScore());
    }

    @Test
    public void getCelluleVerboseStart(){
        Verbose v = new Verbose();
        assertTrue(v.getCelluleVerboses().isEmpty());
    }

    @Test
    public void getWinner(){
        Verbose v = new Verbose();
        v.add('a', 1, "T");
        assertEquals('a', v.getWinner());
    }

    @Test
    public void getScore(){
        Verbose v = new Verbose();
        v.add('a', 1, "T");
        assertEquals(1, v.getScore());
    }

    @Test
    public void getCelluleVerbose(){
        Verbose v = new Verbose();
        v.add('a', 1, "T");
        assertEquals(1, v.getCelluleVerboses().size());
    }

    @Test
    public void getCelluleVerboseMessage(){
        Verbose v = new Verbose();
        v.add('a', 1, "T");
        assertEquals("T :\ta : 1.0", v.getCelluleVerboses().get(0).getVerboseString());
    }

    @Test
    public void getWinner2(){
        Verbose v = new Verbose();
        v.add('a', 1, "T");
        v.add('b', 2.01, "T2");
        assertEquals('b', v.getWinner());
    }

    @Test
    public void getScore2(){
        Verbose v = new Verbose();
        v.add('a', 1, "T");
        v.add('b', 2.01, "T2");
        assertEquals(2.01, v.getScore());
    }

    @Test
    public void getCelluleVerbose2(){
        Verbose v = new Verbose();
        v.add('a', 1, "T");
        v.add('b', 2.01, "T2");
        assertEquals(2, v.getCelluleVerboses().size());
    }

    @Test
    public void getCelluleVerboseMessage2(){
        Verbose v = new Verbose();
        v.add('a', 1, "T");
        v.add('b', 2.01, "T2");
        assertEquals("T :\ta : 1.0", v.getCelluleVerboses().get(0).getVerboseString());
        assertEquals("T2 :\tb : 2.01", v.getCelluleVerboses().get(1).getVerboseString());
    }

    @Test
    public void getWinner3(){
        Verbose v = new Verbose();
        v.add('a', 1, "T");
        v.add('b', 2.01, "T");
        assertEquals('b', v.getWinner());
    }

    @Test
    public void getScore3(){
        Verbose v = new Verbose();
        v.add('a', 1, "T");
        v.add('b', 2.01, "T");
        assertEquals(2.01, v.getScore());
    }

    @Test
    public void getCelluleVerbose3(){
        Verbose v = new Verbose();
        v.add('a', 1, "T");
        v.add('b', 2.01, "T");
        assertEquals(1, v.getCelluleVerboses().size());
    }

    @Test
    public void getCelluleVerboseMessage3(){
        Verbose v = new Verbose();
        v.add('a', 1, "T");
        v.add('b', 2.01, "T");
        assertEquals("T :\ta : 1.0\tb : 2.01", v.getCelluleVerboses().get(0).getVerboseString());
    }

}