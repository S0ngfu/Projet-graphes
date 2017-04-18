import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class GrapheFsAps extends Graphe {

    public int nbEdges;

    public Vertices[] fs;
    public int[] aps;

    GrapheFsAps() {

    }

    GrapheFsAps(String filename) {

    }

    @Override
    public String toString() {
        String ret =  "Nombre de sommets : "+nbEdges+"\n Arêtes :\n";
        int j = 0;
        for (int i = 0 ; i < nbEdges ; i++) {
            if(super.edges != null)
                ret += i+1 + "("+super.edges[i] + ") : ";
            else
                ret += i+1 + " : ";

            while(fs.length > j && fs[j] != null) {
                ret += fs[j].id+1 +";";
                j++;
            }
            j++;
            ret+="\n";
        }

        return ret;
    }

    public double[] distance(int s)
    {
	int dist = 0;
        double[] d = new double[aps[0] + 1];
        for(int i = 0; i < d.length; i++)
            d[i] = -1;
        d[s] = dist;
        distrec(s, dist, d);
        return d;
    }

    public double[] distrec(int s, double dist, double[] d)
    {
        for(int i = aps[s]; fs[i].id != 0; i++) // should be fs[i] != null non ??
        {
            if((d[fs[i].id] > (dist + fs[i].weight)) || d[fs[i].id] == -1)
            {
                d[fs[i].id] = dist + fs[i].weight;
                d = distrec(fs[i].id, d[fs[i].id], d);
            }
        }
        return d;
    }

    public double[][] mat_dist()
    {
        int n = aps[0];
        double[][] mat = new double[n+1][n+1];
        for(int i = 1; i < n+1; i++){
		mat[i] = distance(i);
	}
        return mat;
    }


    public int[] det_rang() {

        // il faut que le graphe soit orienté!!!


        int[] rang = new int[nbEdges];
        int[] ddi = ddi();

        Stack<Integer> pile = new Stack<>();
        boolean circtuit = true;

        for(int i = 0 ; i < nbEdges ; i++)
            if(ddi[i] == 0) {
                circtuit = false;
                rang[i] = 0;
                pile.add(i);
            }



        if(!circtuit) {
            int r = 1; //correspond au rang actuel

            for(int i = 0 ; i < nbEdges ; i++) {

            }


        } else {
            for(int i = 0 ; i < rang.length ; i++)
                rang[i] = Integer.MAX_VALUE;
        }





        return rang;
    }

    public int[] ddi() {
        int[] ddi = new int[nbEdges];
        for(int i = 0 ; i < ddi.length ; i++)
            ddi[i] = 0;

        for(int i = 0 ; i < fs.length ; i++) {
            if(fs[i] != null)
                ddi[fs[i].id]++;
        }
        return ddi;
    }

}
