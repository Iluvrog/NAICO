package fc;

public class Masque {
    private final int[][] masque;

    Masque(int[][] masque){
        this.masque = masque;
    }

    public int getHauteur(){
        return masque.length;
    }

    public int getLargeur(){
        return masque[0].length;
    }

    public int getValue(int i, int j){
        return masque[i][j];
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < masque.length; i++){
            for (int j = 0; j < masque[0].length; j++){
                string.append(masque[i][j]).append(" ");
            }
            string.append("\n");
        }

        return string.toString();
    }
}
