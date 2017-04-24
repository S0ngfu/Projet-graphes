
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

    @Override
    public GrapheFsAps getFsaps() {
        return getFsaps(this);
    }

    @Override
    public GrapheMatrice getMatrice() {
        return getMatrice(this);
    }

    @Override
    public GrapheListes getListes() {
        return getListes(this);
    }

    @Override
    public void addEdge(double weight, int s1, int s2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addEdge(int s1, int s2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addVertex(String names) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addVertex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
