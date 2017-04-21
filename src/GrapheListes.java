
import java.util.ArrayList;

public class GrapheListes extends Graphe {

    ArrayList<ArrayList<Edges>> data;

    GrapheListes() {

    }

    GrapheListes(String filename) {

    }
    
    @Override
    public String toString() {
        String str = "";
        str += data.toString();
        return str;
    }

}
