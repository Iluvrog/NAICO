package fc;

import fc.Comparateur.Comparateur;

public class Main {

    //Idem
    private void test3(){
        Map map = new Map(new Masque("./data/picture/A.png"));
        Comparateur c = Comparateur.getInstance();
        c.fill();
        System.out.println(c.compare(map));
    }

    //met une Map en forme de texte et compare avec sa version recréée
    private void test4(){
        Map map = new Map(new Masque("./data/picture/A.png"));
        System.out.println(map.compare(new Map(map.toLine())));
    }

    public static void main(String[] args){
        Main main = new Main();

        main.test4();
    }
}
