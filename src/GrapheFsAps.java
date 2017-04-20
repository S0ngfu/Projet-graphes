
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

    public double[] distance(int s) {
        int dist = 0;
        double[] d = new double[nbVertices];
        for (int i = 0; i < nbVertices; i++) {
            d[i] = -1;
        }
        d[s] = dist;
        distrec(s, dist, d);
        return d;
    }

    public double[] distrec(int s, double dist, double[] d) {
        for (int i = aps[s]; fs[i].id != 0; i++) {
            if ((d[fs[i].id] > (dist + fs[i].weight)) || d[fs[i].id] == -1) {
                d[fs[i].id] = dist + fs[i].weight;
                d = distrec(fs[i].id, d[fs[i].id], d);
            }
        }
        return d;
    }

    public double[][] mat_dist() {
        double[][] mat = new double[nbVertices][nbVertices];
        for (int i = 0; i < nbVertices; i++) {
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

    public void parcourspreordre(int s) {
        boolean[] alreadydone = new boolean[nbVertices + 1];
        parcourspreordrerec(s, alreadydone);
    }

    public void parcourspreordrerec(int s, boolean[] alreadydone) {
        for (int i = aps[s]; fs[i].id != 0; i++) {
            //Insérer une fonction de traitement
            //Exemple, un print
            System.out.print(fs[i].id + " ");
            alreadydone[s] = true;
            if (!alreadydone[fs[i].id]) {
                parcourspreordrerec(fs[i].id, alreadydone);
            }
        }
    }

    public void parcourspostordre(int s) {
        boolean[] alreadydone = new boolean[nbVertices + 1];
        parcourspostordrerec(s, alreadydone);
    }

    public void parcourspostordrerec(int s, boolean[] alreadydone) {
        if (s != aps.length - 1) {
            for (int i = aps[s + 1] - 2; fs[i].id != 0; i--) {

                //Insérer une fonction de traitement
                //Exemple, un print
                System.out.print(fs[i].id + " ");
                alreadydone[s] = true;
                if (!alreadydone[fs[i].id]) {
                    parcourspostordrerec(fs[i].id, alreadydone);
                }
            }
        } else {
            for (int i = fs.length - 2; fs[i].id != 0; i--) {

                //Insérer une fonction de traitement
                //Exemple, un print
                System.out.print(fs[i].id + " ");
                alreadydone[s] = true;
                if (!alreadydone[fs[i].id]) {
                    parcourspostordrerec(fs[i].id, alreadydone);
                }
            }
        }
    }

    public void parcoursordre(int s) {
        boolean[] alreadydone = new boolean[nbVertices + 1];
        for (int i = aps[s + 1] - 1; fs[i].id != 0; i--) {
            if (!alreadydone[fs[i].id]) {
                //Insérer un fonction de traitement
                //Exemple, un print
                System.out.print(fs[i].id + " ");
                alreadydone[fs[i].id] = true;
                parcoursordre(fs[i].id);
            }
        }
    }

    public int[] dijkstra(int s) {
        int cpt, j = 0, k, bsup;
        double max, v;
        double[] distances = new double[nbVertices];
        int[] pred = new int[nbVertices];
        boolean[] dansS = new boolean[nbVertices];
        if (s == nbVertices) {
            bsup = nbEdges;
        } else {
            bsup = aps[s + 1];
        }
        for (int i = fs[aps[s]].id; i < bsup; i++) {
            distances[i] = fs[i].weight;
            if (fs[i].weight != Double.MAX_VALUE) {
                pred[i] = s;
            } else {
                pred[i] = -1;
            }
            dansS[i] = true;
        }
        dansS[s] = false;
        distances[s] = 0;
        cpt = nbVertices - 1;
        while (cpt > 0) {
            max = Double.MAX_VALUE;
            for (int i = 0; i < nbVertices; i++) {
                if (dansS[i] && distances[i] < max) {
                    max = distances[i];
                    j = i;
                }
                if (max == Double.MAX_VALUE) {
                    break;
                }
                dansS[j] = false;
                cpt--;
                for (int h = aps[j]; (k = fs[h].id) != 0; h++) {
                    if (dansS[k]) {
                        v = distances[j] + fs[k].weight;
                        if (v < distances[k]) {
                            distances[k] = v;
                            pred[k] = j;
                        }
                    }
                }
            }
        }
        return pred;
    }

    public int[] bellmanford(int s) {
        double[] distances = new double[nbVertices];
        int[] pred = new int[nbVertices];
        int bsup;
        // Initialisation
        for (int i = 0; i < nbVertices; i++) {
            distances[i] = Double.MAX_VALUE;
            pred[i] = -1;
        }
        distances[s] = 0;
        // En enlèves des arrêtes en boucle
        for (int i = 0; i < nbVertices; i++) {
            if (i == nbVertices - 1) {
                bsup = nbEdges;
            } else {
                bsup = aps[i + 1];
            }
            for (int j = fs[aps[i]].id; j < bsup; j++) {
                if (distances[i] + fs[j].weight < distances[fs[j].id]) {
                    distances[fs[j].id] = distances[i] + fs[j].weight;
                    pred[fs[j].id] = i;
                }
            }
        }
        // On regarde s'il n'y a pas de cycles négatifs
        for (int i = 0; i < nbVertices; i++) {
            if (i == nbVertices - 1) {
                bsup = nbEdges;
            } else {
                bsup = aps[i + 1];
            }
            for (int j = fs[aps[i]].id; j < bsup; j++) {
                if (distances[i] + fs[j].weight < distances[fs[j].id]) {
                    System.out.println("Erreur, le graphe contient un cycle négatif");
                }
            }
        }

        return pred;
    }
}
