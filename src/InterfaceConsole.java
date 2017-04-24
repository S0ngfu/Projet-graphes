import java.util.Scanner;

public class InterfaceConsole {

    Graphe g;
    Scanner in;
    String inputs;

    public void run() {
        init();
        boolean quit;
        in = new Scanner(System.in);

        inputs = in.next();
        quit = input(inputs);
        while(!quit) {
            inputs = in.next();
            quit = input(inputs);
        }

    }

    private void init() {
        // INIT GRAPHE!!!
        Graphe gm = new GrapheMatrice("graphe.txt");
        gm.afficherGraphe();
        //System.out.println(gm);

        System.out.println("Voici l'interface console pour gérer un graphe.\n Entrez help pour voir les commandes disponibles");
    }

    private boolean input(String inputs) {

        if("quit".startsWith(inputs.toLowerCase()))
            return true;

        if("help".startsWith(inputs.toLowerCase()))
            showHelp();

        if("open".startsWith(inputs.toLowerCase()))
            //ToDo OpenFileManager .fsaps .matrice .listes
            ;

        if("algos".startsWith(inputs.toLowerCase()))
            showAlgo();

        return false;
    }

    private void showHelp() {
        System.out.println("Quit \n" +
                "Help");
    }

    private void showAlgo() {
        System.out.println("Rentrez un nombre\n" +
                "1 : Distance\n" +
                "2 : Détermination rang\n" +
                "3 : Chemins critiques\n" +
                "4 : Parcours préordre\n" +
                "5 : Parcours postordre\n" +
                "6 : Dijkstra\n" +
                "7 : Bellmanford\n" +
                "8 : Kruskal\n" +
                "9 : Codage prufer\n" +
                "10 : Décodage prufer\n" +
                "11 : Dantzig\n");
        inputs = in.next();
        int tmp = Integer.parseInt(inputs);
        while(tmp < 1 || tmp > 11) {
            inputs = in.next();
            tmp = Integer.parseInt(inputs);
        }

        if(tmp == 1) {
            g.mat_dist();
        }

        if(tmp == 2) {
            g.mat_dist();
        }

        if(tmp == 3) {
            System.out.println("Not working");
        }

        if(tmp == 4) {
            g.mat_dist();
        }

        if(tmp == 5) {
            g.mat_dist();
        }

        if(tmp == 6) {
            g.mat_dist();
        }

        if(tmp == 7) {
            g.mat_dist();
        }

        if(tmp == 8) {
            g.mat_dist();
        }

        if(tmp == 9) {
            g.mat_dist();
        }

        if(tmp == 10) {
            g.mat_dist();
        }

        if(tmp == 11) {
            g.mat_dist();
        }
    }

}
