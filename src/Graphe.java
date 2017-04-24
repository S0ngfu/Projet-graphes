
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Graphe {

    protected String KEY;
    protected int nbVertices, nbEdges;

    public class Vertices {

        String names;

        public Vertices(String names) {
            this.names = names;
        }

        @Override
        public String toString() {
            return names;
        }
    }

    public Vertices[] vertices;

    Graphe() {

    }

    public abstract void addEdge(double weight, int s1, int s2);

    public abstract void addEdge(int s1, int s2);

    public abstract void addVertex(String names);

    public abstract void addVertex();

    public Graphe open(String filename) {
        //Si le fichier finit avec .fsaps
        if (filename.endsWith(".fsaps")) {
            return new GrapheFsAps(filename);

            //Si le fichier finit avec .matrice
        } else if (filename.endsWith(".matrice")) {
            return new GrapheMatrice(filename);

            //Si le fichier finit avec .listes
        } else if (filename.endsWith(".listes")) {
            return new GrapheListes(filename);
        } else {
            return null;
        }
    }


    //Conversions
    public abstract GrapheFsAps getFsaps();

    public abstract GrapheMatrice getMatrice();

    public abstract GrapheListes getListes();

    public static GrapheFsAps getFsaps(GrapheFsAps data) {
        return data;
    }

    public static GrapheFsAps getFsaps(GrapheMatrice data) {
        return data.getListes().getFsaps();
    }

    public static GrapheFsAps getFsaps(GrapheListes data) {
        GrapheFsAps fsaps = new GrapheFsAps();
        fsaps.vertices = data.vertices;
        fsaps.nbEdges = data.nbEdges;
        fsaps.nbVertices = data.nbVertices;
        fsaps.aps = new int[data.nbEdges + 1];
        fsaps.fs = new Edges[data.nbVertices + data.nbEdges + 1];

        Edges blank = new Edges(0, -1.0);
        int i = 0, j = 1;
        for (ArrayList<Edges> tmp : data.data) {
            fsaps.fs[i] = blank;
            i++;
            fsaps.aps[j] = i;
            for (Edges tmpedg : tmp) {
                fsaps.fs[i] = tmpedg;
                i++;
            }
            j++;
        }
        fsaps.fs[fsaps.fs.length - 1] = blank;

        return fsaps;
    }


    public static GrapheMatrice getMatrice(GrapheFsAps data) {
        GrapheMatrice tmp = new GrapheMatrice();
        double[][] tmpmat = new double[data.nbVertices + 1][data.nbVertices + 1];
        tmp.vertices = data.vertices;
        tmp.nbEdges = data.nbEdges;
        tmp.nbVertices = data.nbVertices;
        for (int i = 1; i < data.nbVertices + 1; i++) {
            for (int j = 1; j < data.nbVertices + 1; j++) {
                tmpmat[i][j] = 0;
            }
        }
        int k = 1;
        for (int i = 1; i < data.nbEdges + data.nbVertices; i++) {
            if (data.fs[i].id != 0) {
                tmpmat[k][data.fs[i].id] = data.fs[i].weight;
            } else {
                k++;
            }
        }
        tmp.edges = tmpmat;

        return tmp;
    }

    public static GrapheMatrice getMatrice(GrapheMatrice data) {
        return data;
    }

    public static GrapheMatrice getMatrice(GrapheListes data) {
        return data.getFsaps().getMatrice();
    }


    public static GrapheListes getListes(GrapheFsAps data) {
        return data.getMatrice().getListes();
    }

    public static GrapheListes getListes(GrapheMatrice data) {
        GrapheListes tmp = new GrapheListes();
        tmp.vertices = data.vertices;
        tmp.nbEdges = data.nbEdges;
        tmp.nbVertices = data.nbVertices;
        ArrayList<ArrayList<Edges>> tmpdata = new ArrayList<ArrayList<Edges>>(data.nbEdges);
        for (int i = 1; i < data.nbVertices + 1; i++) {
            ArrayList<Edges> tmpedge = new ArrayList<Edges>(data.nbVertices);
            for (int j = 1; j < data.nbVertices + 1; j++) {
                if (data.edges[i][j] != 0) {
                    Edges test = new Edges(j, data.edges[i][j]);
                    tmpedge.add(test);
                }
            }
            tmpdata.add(tmpedge);
        }
        tmp.data = tmpdata;
        return tmp;
    }

    public static GrapheListes getListes(GrapheListes data) {
        return data;
    }


    public String getEdgeName(int id) throws ArrayIndexOutOfBoundsException {
        return vertices[id].names;
    }

    public void setEdgeName(int id, String name) throws ArrayIndexOutOfBoundsException {
        vertices[id].names = name;
    }

    public abstract void afficherGraphe();

    public abstract double[][] mat_dist();

    public abstract int[] det_rang();

    public abstract void cheminsCritiques(int[] lc, int[] fpc, int[] appc);

    public abstract void parcourspreordre(int s);

    public abstract void parcourspostordre(int s);

}
