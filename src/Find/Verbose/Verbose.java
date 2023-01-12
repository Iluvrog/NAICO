package Find.Verbose;

import java.util.ArrayList;

public class Verbose {

    private char winner;
    private double score;

    private final ArrayList<CelluleVerbose> celluleVerboses;

    public Verbose(){
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
            if (cv.getType().startsWith(type)){
                cv.add(newscore, candidat);
                haveCel = true;
            }
        }

        if (!haveCel){
            CelluleVerbose newCellule = new CelluleVerbose(type + " : ");
            newCellule.add(newscore, candidat);
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
