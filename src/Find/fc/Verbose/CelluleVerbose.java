package Find.fc.Verbose;

import java.util.Objects;

public class CelluleVerbose {

    private String verboseString;

    CelluleVerbose(){
        verboseString = "";
    }

    public String getVerboseString() {
        return verboseString;
    }

    public void add(String add){
        if (!Objects.equals(verboseString, "")) verboseString += "\t";
        verboseString += add;
    }
}
