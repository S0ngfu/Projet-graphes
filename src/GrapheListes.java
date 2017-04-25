
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

    @Override
    public double[][] mat_dist() {
        return this.getFsaps().mat_dist();
    }

    @Override
    public int[] det_rang() {
        return this.getFsaps().det_rang();
    }

    @Override
    public void afficherGraphe() {
        this.getMatrice().afficherGraphe();
    }

    @Override
    public void cheminsCritiques(int[] lc, int[] fpc, int[] appc) {
        this.getFsaps().cheminsCritiques(lc, fpc, appc);
    }

    @Override
    public void parcourspreordre(int s) {
        this.getFsaps().parcourspreordre(s);
    }

    @Override
    public void parcourspostordre(int s) {
        this.getFsaps().parcourspostordre(s);
    }

    @Override
    public int[] dijkstra(int s) {
        return this.getFsaps().dijkstra(s);
    }

    @Override
    public int[] bellmanford(int s) {
        return this.getFsaps().bellmanford(s);
    }

    @Override
    public GrapheMatrice kruskal() {
        return this.getMatrice().kruskal();
    }

    @Override
    public GrapheMatrice prim() {
        return this.getMatrice().prim();
    }

    @Override
    public int[] codagePrufer() {
        return this.getMatrice().codagePrufer();
    }

    @Override
    public double[][] decodagePrufer(int p[]) {
        return this.getMatrice().decodagePrufer(p);
    }

    @Override
    public boolean dantzig() {
        return this.getMatrice().dantzig();
    }
}
