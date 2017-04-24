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
        g = new GrapheMatrice("graphe.txt");
        g.afficherGraphe();
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

        if("show".startsWith(inputs.toLowerCase()))
            g.afficherGraphe();

        if("conversions".startsWith(inputs.toLowerCase()))
            //ToDo
            ;

        return false;
    }

    private void showHelp() {
        System.out.println("Quit \n" +
                "Help\n" +
                "Algos\n" +
                "Show\n");
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
                "11 : Dantzig\n" +
                "12 : Prim");
        inputs = in.next();
        int tmp = Integer.parseInt(inputs);
        while(tmp < 1 || tmp > 12) {
            inputs = in.next();
            tmp = Integer.parseInt(inputs);
        }

        if(tmp == 1) {
            double[][] tmptab = g.mat_dist();
            for (int i = 0 ; i < tmptab.length ; i++) {
                for (int j = 0 ; j < tmptab[i].length ; j++) {
                    System.out.print(tmptab[i][j] + "\t");
                }
                System.out.println();
            }
        }

        if(tmp == 2) {
            System.out.println("Does not work");
            int[] tmptab = g.det_rang();
            for(int i = 0 ; i < tmptab.length ; i++) {
                System.out.print(tmptab[i] + "\t");
            }
            System.out.println();
        }

        if(tmp == 3) {
            System.out.println("Not working");
            //g.cheminsCritiques();
        }

        if(tmp == 4) {
            int s;
            System.out.println("Entrez un nombre entre 1 et "+g.nbVertices+"compris");
            s = Integer.parseInt(in.next());
            while(s < 1 || s > g.nbVertices) {
                s = Integer.parseInt(in.next());
            }
            g.parcourspreordre(s);
        }

        if(tmp == 5) {
            int s;
            System.out.println("Entrez un nombre entre 1 et "+g.nbVertices+"compris");
            s = Integer.parseInt(in.next());
            while(s < 1 || s > g.nbVertices) {
                s = Integer.parseInt(in.next());
            }
            g.parcourspostordre(s);
        }

        if(tmp == 6) {
            int s;
            System.out.println("Entrez un nombre entre 1 et "+g.nbVertices+"compris");
            s = Integer.parseInt(in.next());
            while(s < 1 || s > g.nbVertices) {
                s = Integer.parseInt(in.next());
            }
            int[] tmptab = g.dijkstra(s);

            for(int i = 0 ; i < tmptab.length ; i++) {
                System.out.print(tmptab[i] + "\t");
            }
            System.out.println();
        }

        if(tmp == 7) {
            int s;
            System.out.println("Entrez un nombre entre 1 et "+g.nbVertices+"compris");
            s = Integer.parseInt(in.next());
            while(s < 1 || s > g.nbVertices) {
                s = Integer.parseInt(in.next());
            }
            int[] tmptab = g.bellmanford(s);

            for(int i = 0 ; i < tmptab.length ; i++) {
                System.out.print(tmptab[i] + "\t");
            }
            System.out.println();
        }

        if(tmp == 8) {
            GrapheMatrice tmpg = g.kruskal();
            tmpg.afficherGraphe();
        }

        if(tmp == 9) {
            int[] tmptab = g.codagePrufer();

            for(int i = 0 ; i < tmptab.length ; i++) {
                System.out.print(tmptab[i] + "\t");
            }
            System.out.println();
        }

        if(tmp == 10) {
            g.decodagePrufer(g.codagePrufer());
        }

        if(tmp == 11) {
            boolean tmpb = g.dantzig();
            if(tmpb) {
                System.out.println("Vrai");
            } else {
                System.out.println("Faux");
            }
        }

        if(tmp == 12) {
            GrapheMatrice tmpg = g.prim();
            tmpg.afficherGraphe();
        }
    }

}
