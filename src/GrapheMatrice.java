
import java.util.ArrayList;

public class GrapheMatrice extends Graphe {

    double[][] edges;

    GrapheMatrice() {

    }

    GrapheMatrice(String filename) {

    }

    // Retourne l'index de l'arrête qui a le poids le plus faible
    public int getIndexPoidsMin(double[] matrice) {
        double min = Double.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < nbVertices; i++) {
            if (matrice[i] > 0 && matrice[i] < min) {
                min = matrice[i];
                index = i;
            }
        }

        return index;
    }

    // Retourne un arbre recouvrant minimal d'un graphe non orienté
    // Version modifiée de : http://www.script-tout-fait.com/fr/fichier-Algorithme-de-Kruskal-obtenir-un-abre-couvrant-minimal-692.html
    public GrapheMatrice kruskal() {
        double[] matrice = new double[nbVertices * nbVertices];
        int index = -2;
        int row, col, rowListe, colListe, rowIndex, colIndex;
        boolean pasBon;

        ArrayList<Integer> res = new ArrayList<>();

        // Initialisation d'une matrice à une dimension à partir du graphe
        for (int i = 0; i < nbVertices; i++) {
            System.arraycopy(edges[i], 0, matrice, i * nbVertices, nbVertices);
        }

        while (res.size() < nbVertices - 1 || index == -1) {
            index = getIndexPoidsMin(matrice);

            if (index >= 0) {
                // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa ligne en divisant nb par n
                rowIndex = index / nbVertices;
                // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa colone en prenant nb modulo
                colIndex = index % nbVertices;
                pasBon = false;

                for (Integer nb : res) {
                    // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa ligne en divisant nb par n
                    rowListe = nb / nbVertices;
                    // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa colone en prenant nb modulo n
                    colListe = nb % nbVertices;

                    if (colListe == colIndex && rowIndex != rowListe) {
                        pasBon = true;
                    }

                    // Ne pas prendre A<->B ET B<->A
                    if (rowIndex == colListe && colIndex == rowListe) {
                        pasBon = true;
                    }
                }

                // Ne pas prendre A<->B ET B<->A
                if (res.contains((colIndex * nbVertices) + rowIndex)) {
                    pasBon = true;
                }

                // On ajoute ce vecteur à la liste
                if (!pasBon) {
                    res.add(index);
                }

                // On marque ce vecteur comme traité
                matrice[index] = -1;
            }
        }

        // On transforme le résultat sous forme de GrapheMatrice puis on le retourne
        GrapheMatrice resultat = new GrapheMatrice();
        resultat.nbVertices = nbVertices;
        resultat.nbEdges = nbVertices - 1;
        resultat.edges = new double[nbVertices][nbVertices];
        for (int nb : res) {
            row = nb / nbVertices;
            col = nb % nbVertices;
            resultat.edges[row][col] = edges[row][col];
            // Copie des noms
            // /!\ A VERIFIER /!\
            System.arraycopy(vertices, 0, resultat.vertices, 0, nbVertices);
        }
        return resultat;
    }

    // Retourne un arbre recouvrant minimal d'un graphe non orienté
    public GrapheMatrice prim() {
        boolean[] visité = new boolean[nbVertices];
        int[] précédent = new int[nbVertices];
        int courant, total;
        double[] distance = new double[nbVertices];
        double coûtmin;
        courant = 0;
        distance[courant] = 0;
        total = 0;
        while (total != nbVertices) {
            // Pour chaque sommet
            for (int i = 0; i < nbVertices; i++) {
                // S'il existe un arc qui relie le sommet courant au sommet i
                if (edges[courant][i] != 0.0) {
                    // Et si le sommet i n'a pas encore été visité
                    if (!visité[i]) {
                        // On détermine sa distance avec le sommet courant 
                        if (distance[i] > edges[courant][i]) {
                            distance[i] = edges[courant][i];
                            précédent[i] = courant;
                        }
                    }
                }
            }
            coûtmin = Double.MAX_VALUE;
            // On prend ensuite comme sommet courant l'arc de valeur minimal
            for (int i = 0; i < nbVertices; i++) {
                if (!visité[i]) {
                    if (distance[i] < coûtmin) {
                        coûtmin = distance[i];
                        courant = i;
                    }
                }
            }
            visité[courant] = true;
            total++;
        }
        // On convertit le résultat en GrapheMatrice
        GrapheMatrice resultat = new GrapheMatrice();
        resultat.nbVertices = nbVertices;
        resultat.nbEdges = nbVertices - 1;
        resultat.edges = new double[nbVertices][nbVertices];
        for (int i = 1; i < nbVertices; i++) {
            resultat.edges[i][précédent[i]] = distance[i];
            resultat.edges[précédent[i]][i] = distance[i];
            // Copie des noms
            // /!\ A VERIFIER /!\
            System.arraycopy(vertices, 0, resultat.vertices, 0, nbVertices);
        }
        return resultat;
    }

    public int[] codagePrufer() {
        int[] p = new int[nbVertices + 1];
        p[0] = nbVertices;

        for (int i = 1; i <= nbVertices; i++) {
            for (int j = i + 1; j <= nbVertices; j++) {
                edges[i][0] += edges[i][j];
            }
        }

        int k = 1;
        while (k <= nbVertices) {
            int i;
            for (i = 1; edges[i][0] != 1 && i < nbVertices; i++);
            int j;
            for (j = 1; edges[i][j] != 1 && j < nbVertices; j++);
            p[k++] = j;

            edges[i][0] = 0;
            edges[i][j] = 0;
            edges[j][i] = 0;
            edges[j][0]--;
        }
        return p;
    }

    public void decodagePrufer(int p[]) {
        int nbEdges = p[0] - 2, nbVertices = nbEdges + 2;

        int[] s = new int[nbVertices + 1];
        boolean[] b = new boolean[nbVertices + 1];

        for (int i = 1; i <= nbVertices; i++) {
            s[i] = 0;
            b[i] = true;
        }
        for (int i = 1; i <= nbVertices; i++) {
            s[p[i]]++;
        }
        for (int k = 1; k <= nbVertices; k++) {
            for (int i = 1; i <= nbVertices; i++) {
                if ((b[i]) && (s[p[i]] == 0)) {
                    edges[i][p[k]] = 1;
                    edges[p[k]][i] = 1;
                    b[i] = false;
                    s[p[k]]--;
                    break;
                }
            }
        }
        int i;
        for (i = 1; !b[i]; i++);
        //b[i] = false;
        int j;
        for (j = i + 1; !b[j]; j++);
        edges[i][j] = 1;
        edges[j][i] = 1;

    }

    public boolean dantzig() {
        double x;
        for (int k = 0; k < nbVertices; k++) {
            for (int i = 0; i <= k; i++) {
                for (int j = 0; j <= k; j++) {
                    if ((x = edges[i][j] + edges[j][k + 1]) < edges[i][k + 1]) {
                        edges[i][k + 1] = x;
                    }
                    if ((x = edges[k + 1][j] + edges[j][i]) < edges[k + 1][i]) {
                        edges[k + 1][i] = x;
                    }
                }
                if (edges[k + 1][i] + edges[i][k + 1] < 0) {
                    return false; //circuit absorbant
                }
            }
            for (int i = 0; i <= k; i++) {
                for (int j = 0; j <= k; j++) {
                    if ((x = edges[i][k + 1] + edges[k + 1][j]) < edges[i][j]) {
                        edges[i][j] = x;
                    }
                }
            }
        }
        return true;
    }
}
