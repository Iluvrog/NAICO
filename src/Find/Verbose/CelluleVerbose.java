package Find.Verbose;

import java.util.ArrayList;
import java.util.Arrays;

public class CelluleVerbose {

    private final String type;
    ArrayList<Double> scores;
    ArrayList<Character> names;

    CelluleVerbose(String name){
        type = name;
        scores = new ArrayList<>();
        names = new ArrayList<>();
    }

    public String getVerboseString() {
        sort();

        String verboseString = type + "\n";

        for (int i = 0; i < scores.size(); i++){
            verboseString += names.get(i) + " : " + scores.get(i) + "\n";
        }

        return verboseString;
    }

    private void sort(){
        Double[] s = scores.toArray(new Double[0]);
        Character[] n = names.toArray(new Character[0]);

        //Pas opti mais osef
        Character tmpN;
        Double tmpS;
        for (int i = 0; i < s.length; i++){
            for (int j = i; j < s.length; j++){
                if (s[i] < s[j]){
                    tmpS = s[i];
                    s[i] = s[j];
                    s[j] = tmpS;
                    tmpN = n[i];
                    n[i] = n[j];
                    n[j] = tmpN;
                }
            }
        }

        for (int i = 0; i < s.length; i++){
            scores.set(i, s[i]);
            names.set(i, n[i]);
        }
    }

    public void add(double score, char name){
        scores.add(score);
        names.add(name);
    }

    public String getType(){
        return type;
    }
}
