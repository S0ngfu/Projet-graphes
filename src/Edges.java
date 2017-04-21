
public class Edges {

    public int id;
    public double weight;

    Edges(int i, double w) {
        id = i;
        weight = w;
    }
    
    @Override
    public String toString() {
        String str = "";
        str += "Id :" + "\t" + id;
        str += "\t";
        str += "Poids :" + "\t" + weight + "\n";
        return str;
    }
}
