public class GrapheFsAps extends Graphe {

    int nbEdges;

    public Vertices[] fs;
    public int[] aps;

    GrapheFsAps() {

    }

    GrapheFsAps(String filename) {

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
        for(int i = aps[s]; fs[i].id != 0; i++)
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
    
}
