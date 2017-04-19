
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

    // Retourne un arbre recouvrant minimal
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

    public int[] codagePrufer() {
        int[] p = new int[nbVertices - 1];
        p[0] = nbVertices - 2;

        for (int i = 1; i <= nbVertices; i++) {
            for (int j = i + 1; j <= nbVertices; j++) {
                edges[i][0] += edges[i][j];
            }
        }

        int k = 1;
        while (k <= nbVertices) {
            int i;
            for (i = 1; edges[i][0] != 1; i++);
            int j;
            for (j = 1; edges[i][j] != 1; j++);
            p[k++] = j;

            edges[i][0] = 0;
            edges[i][j] = 0;
            edges[j][i] = 0;
            edges[j][0]--;
        }
        return p;
    }

    public void decodagePrufer(int p[]) {
        int m = p[0], n = m + 2;
        edges[0][0] = n;
        edges[0][1] = n - 1;

        int[] s = new int[n + 1];
        boolean[] b = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            s[i] = 0;
            b[i] = true;
        }
        for (int i = 1; i <= n; i++) {
            s[p[i]]++;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
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
}
