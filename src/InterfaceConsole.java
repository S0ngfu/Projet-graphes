import java.util.Scanner;

public class InterfaceConsole {

    Graphe g;

    public void run() {
        init();
        boolean quit;
        Scanner in;
        in = new Scanner(System.in);
        String inputs;

        inputs = in.next();
        quit = input(inputs);
        while(!quit) {
            inputs = in.next();
            quit = input(inputs);
        }

    }

    private void init() {
        // INIT GRAPHE!!!
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

        return false;
    }

    private void showHelp() {
        System.out.println("Quit \n" +
                "Help");
    }

}
