package fc;

import fc.Comparateur.Comparateur;

public class Saver {

    public static void main(String[] args){
        Comparateur comparateur = Comparateur.getInstance();
        comparateur.fill();
        comparateur.save();
    }
}
