
public class Main {

    public static void main(String[] args) {
        GrapheMatrice gm = new GrapheMatrice("graphe.txt");
        gm.afficherGraphe();
        System.out.println(gm);

        InterfaceConsole in = new InterfaceConsole();
        in.run();
    }
}
