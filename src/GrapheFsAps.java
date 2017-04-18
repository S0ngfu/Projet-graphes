
public class GrapheFsAps extends Graphe {

    public int nbEdges;

    Vertices[] fs;
    int[] aps;

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

}
