import java.io.FileInputStream;
import java.util.ArrayList;

public class GrapheListes extends Graphe {

    public class Edges {
        private int id;
        private double weight;
    }

    ArrayList<ArrayList<Edges>> data;

    GrapheListes() {

    }

    GrapheListes(String filename) {

    }

}
