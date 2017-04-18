
import java.util.ArrayList;

public class GrapheMatrice extends Graphe {

    public double[][] edges;

    GrapheMatrice() {

    }

    GrapheMatrice(String filename) {

    }
    
     // Retourne l'index de l'arrête qui a le poids le plus faible
    public int getIndexPoidsMin(double[] matrice) {
        double min = Double.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < n; i++) {
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
        double[] matrice = new double[n * n];
        int index = -2;
        int row, col, rowListe, colListe, rowIndex, colIndex;
        boolean pasBon;

        ArrayList<Integer> res = new ArrayList<>();

        // Initialisation d'une matrice à une dimension à partir du graphe
        for (int i = 0; i < n; i++) {
            System.arraycopy(edges[i], 0, matrice, i * n, n);
        }

        while (res.size() < n - 1 || index == -1) {
            index = getIndexPoidsMin(matrice);

            if (index >= 0) {
                // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa ligne en divisant nb par n
                rowIndex = index / n;
                // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa colone en prenant nb modulo
                colIndex = index % n;
                pasBon = false;

                for (Integer nb : res) {
                    // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa ligne en divisant nb par n
                    rowListe = nb / n;
                    // La nouvelle matrice n'étant plus qu'à une dimension on retrouve sa colone en prenant nb modulo n
                    colListe = nb % n;

                    if (colListe == colIndex && rowIndex != rowListe) {
                        pasBon = true;
                    }

                    // Ne pas prendre A<->B ET B<->A
                    if (rowIndex == colListe && colIndex == rowListe) {
                        pasBon = true;
                    }
                }

                // Ne pas prendre A<->B ET B<->A
                if (res.contains((colIndex * n) + rowIndex)) {
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
        resultat.n = n;
        resultat.m = n - 1;
        resultat.edges = new double[n][n];
        for (int nb : res) {
            row = nb / n;
            col = nb % n;
            resultat.edges[row][col] = edges[row][col];
            // Copie des noms
            // /!\ A VERIFIER /!\
            System.arraycopy(edges, 0, resultat.edges, 0, n);
        }
        return resultat;
    }
    
    public int[] codagePrufer()
    {
            int n = (int) edges[0][0];
            int[] p = new int[n-1];
            p[0] = n - 2;

            for(int i = 1; i <= n; i++)
                for(int j = i+1; j <= n; j++)
                    edges[i][0] += edges[i][j];

            int k = 1;
            while(k <= n)
            {
                int i;
                for(i = 1; edges[i][0] != 1; i++);
                int j;
                for(j = 1; edges[i][j] != 1; j++);
                p[k++] = j;

                edges[i][0] = 0;
                edges[i][j] = 0;
                edges[j][i] = 0;
                edges[j][0]--;
            }
            return p;
    }
    
    public void decodagePrufer(int p[])
    {
    int m = p[0], n = m + 2;
    edges[0][0] = n;
    edges[0][1] = n-1;

    int[] s = new int[n+1];
    boolean[] b = new boolean[n+1];

    for(int i = 1; i <= n; i++)
    {
            s[i] = 0;
            b[i] = true;
    }
    for(int i = 1; i <= n; i++)
            s[p[i]]++;
    for(int k = 1; k <= n; k++)
            for(int i = 1; i <= n; i++)
                    if((b[i]) && (s[p[i]] == 0))
                    {
                            edges[i][p[k]] = 1;
                            edges[p[k]][i] = 1;
                            b[i] = false;
                            s[p[k]]--;
                            break;
                    }
    int i;
    for(i = 1;!b[i]; i++);
    //b[i] = false;
    int j;
    for(j = i +1;!b[j]; j++);
    edges[i][j] = 1;
    edges[j][i] = 1;
	
    }
}
