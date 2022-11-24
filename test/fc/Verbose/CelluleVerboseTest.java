package fc.Verbose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CelluleVerboseTest {

    @Test
    public void add_message(){
        CelluleVerbose celluleVerbose = new CelluleVerbose();
        celluleVerbose.add("test");
        assertEquals("test", celluleVerbose.getVerboseString());
    }

    @Test
    public void add_2message(){
        CelluleVerbose celluleVerbose = new CelluleVerbose();
        celluleVerbose.add("test1");
        celluleVerbose.add("test2");
        assertEquals("test1\ttest2", celluleVerbose.getVerboseString());
    }

}