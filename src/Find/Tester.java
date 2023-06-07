package Find;

import Find.Comparateur.Comparateur;
import Find.Modeles.Comparatifs.Map;
import Find.Modeles.Comparatifs.Masque;
import Find.Verbose.CelluleVerbose;
import Find.Verbose.Verbose;


public class Tester {

    //Idem
    private void test3(){
        Masque masque = new Masque("./data/picture/A.png");
        Comparateur c = Comparateur.getInstance();
        c.fill();
        System.out.println(c.compare(masque));
    }

    //met une Map en forme de texte et compare avec sa version recréée
    private void test4(){
        Map map = new Map(new Masque("./data/picture/A.png"));
        System.out.println(map.compare(new Map(map.toLine())));
    }

    //Teste les verboses
    private void test5(){
        Comparateur comparateur = Comparateur.getInstance();
        comparateur.fill();
        Verbose verbose = comparateur.compare_Verbose(new Masque("./data/picture/char0_0.png"));
        for (CelluleVerbose c : verbose.getCelluleVerboses()){
            System.out.println(c.getVerboseString());
        }
    }

    public static void main(String[] args){
        Tester tester = new Tester();

        tester.test5();
    }
}
