package fc.Verbose;

import java.util.ArrayList;

public class Verbose {

    private char winner;
    private double score;

    private final ArrayList<CelluleVerbose> celluleVerboses;

    Verbose(){
        winner = 0;
        score = -1;
        celluleVerboses = new ArrayList<>();
    }

    public void add(char candidat, double newscore, String type){
        if (newscore > score){
            winner = candidat;
            score = newscore;
        }

        boolean haveCel = false;
        for (CelluleVerbose cv : celluleVerboses){
            if (cv.getVerboseString().startsWith(type)){
                cv.add(candidat + " : " + newscore);
                haveCel = true;
            }
        }

        if (!haveCel){
            CelluleVerbose newCellule = new CelluleVerbose();
            newCellule.add(type + " :");
            newCellule.add(candidat + " : " + newscore);
            celluleVerboses.add(newCellule);
        }
    }

    public char getWinner() {
        return winner;
    }

    public double getScore() {
        return score;
    }

    public ArrayList<CelluleVerbose> getCelluleVerboses() {
        return celluleVerboses;
    }
}
