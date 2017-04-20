
import java.util.Stack;

public class GrapheFsAps extends Graphe {

    public Edges[] fs;
    public int[] aps;

    GrapheFsAps() {

    }

    GrapheFsAps(String filename) {

    }

    @Override
    public String toString() {
        String ret = "Nombre de sommets : " + nbVertices + "\n Arêtes :\n";
        int j = 0;
        for (int i = 0; i < nbVertices; i++) {
            if (super.vertices != null) {
                ret += i + 1 + "(" + super.vertices[i] + ") : ";
            } else {
                ret += i + 1 + " : ";
            }

            while (fs.length > j && fs[j] != null) {
                ret += fs[j].id + 1 + ";";
                j++;
            }
            j++;
            ret += "\n";
        }

        return ret;
    }

    public double[] distance(int s) 
    {
        int dist = 0;
        double[] d = new double[nbVertices + 1];
        for (int i = 0; i < nbVertices + 1; i++) {
            d[i] = -1;
        }
        d[s] = dist;
        distrec(s, dist, d);
        return d;
    }

    public double[] distrec(int s, double dist, double[] d) 
    {
        for (int i = aps[s]; fs[i].id != 0; i++) // should be fs[i] != null non ??
        {
            if ((d[fs[i].id] > (dist + fs[i].weight)) || d[fs[i].id] == -1) {
                d[fs[i].id] = dist + fs[i].weight;
                d = distrec(fs[i].id, d[fs[i].id], d);
            }
        }
        return d;
    }

    public double[][] mat_dist() {
        double[][] mat = new double[nbVertices + 1][nbVertices + 1];
        for (int i = 1; i < nbVertices + 1; i++) 
        {
            mat[i] = distance(i);
        }
        return mat;
    }

    public int[] det_rang() {

        // il faut que le graphe soit orienté!!!
        int[] rang = new int[nbVertices];
        int[] ddi = ddi();

        Stack<Integer> pile = new Stack<>();
        boolean circtuit = true;

        for (int i = 0; i < nbVertices; i++) {
            if (ddi[i] == 0) {
                circtuit = false;
                rang[i] = 0;
                pile.add(i);
            }
        }

        if (!circtuit) {
            int r = 1; //correspond au rang actuel

            for (int i = 0; i < nbVertices; i++) {

            }

        } else {
            for (int i = 0; i < rang.length; i++) {
                rang[i] = Integer.MAX_VALUE;
            }
        }

        return rang;
    }

    public int[] ddi() {
        int[] ddi = new int[nbVertices];
        for (int i = 0; i < ddi.length; i++) {
            ddi[i] = 0;
        }

        for (Edges f : fs) {
            if (f != null) {
                ddi[f.id]++;
            }
        }
        return ddi;
    }

    public void parcourspreordre(int i)
    {
        //Insérer un fonction de traitement
        //exemple un println
        System.out.print(fs[i].id + " ");
        if (i < nbEdges + nbVertices - 1)
        {
            if(fs[i + 1].id != 0)
                parcourspreordre(i + 1);
            else
                parcourspreordre(i + 2);
        }
    }
    
    public void parcourspostordre(int i)
    {
        if (i < nbEdges + nbVertices - 1)
        {
            if(fs[i + 1].id != 0)
                parcourspreordre(i + 1);
            else
                parcourspreordre(i + 2);
            //Insérer un fonction de traitement
            //exemple un println
            System.out.print(fs[i].id + " ");
        }
    }
    
    public void parcoursordre(int i)
    {
        if (i < nbEdges + nbVertices - 1)
        {
            if(fs[i + 1].id != 0)
            {
                parcourspreordre(i + 1);
                //Insérer un fonction de traitement
                //exemple un println
                System.out.print(fs[i].id + " ");
            }
            else
                parcourspreordre(i + 2);
        }
    }
    
}
