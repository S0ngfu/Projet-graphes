
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
    
    public GrapheFsAps list2fsaps()
    {
        GrapheFsAps fsaps = new GrapheFsAps();
        fsaps.vertices = vertices;
        fsaps.nbEdges = nbEdges;
        fsaps.nbVertices = nbVertices;
        fsaps.aps = new int[nbEdges + 1];
        fsaps.fs = new Edges[nbVertices + nbEdges + 1];

        Edges blank = new Edges(0,-1.0);
        int i=0, j=1;
        for(ArrayList<Edges> tmp : data) {
            fsaps.fs[i] = blank;
            i++;
            fsaps.aps[j] = i;
            for(Edges tmpedg : tmp) {
                fsaps.fs[i] = tmpedg;
                i++;
            }
            j++;
        }
        fsaps.fs[fsaps.fs.length - 1] = blank;
        
        return fsaps;
    }
    
    public GrapheMatrice list2mat()
    {
        return this.list2fsaps().fsaps2matrice();
    }

}
