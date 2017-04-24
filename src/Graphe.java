
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class GrapheReader extends FileInputStream {

    private char c;

    public GrapheReader(String filename) throws FileNotFoundException {
        super(filename);
    }

    public boolean matchKey(String key) throws IOException {
        byte[] buf = new byte[key.length()];
        read(buf, 0, key.length());
        return key.compareTo(new String(buf)) == 0;
    }

    public void getChar() throws IOException {
        c = (char) read();
    }

    public int getInt() throws IOException {
        String s = "";
        while ((c != '\n') && Character.isSpaceChar(c)) {
            getChar();
        }
        while ((c != '\n') && !Character.isSpaceChar(c)) {
            s = s + c;
            getChar();
        }
        return Integer.parseInt(s);
    }

    public void skipLine() throws IOException {
        while (c != '\n') {
            getChar();
        }
    }

    public void skipComment(char code) throws IOException {
        getChar();
        while (c == code) {
            skipLine();
            getChar();
        }
    }

    public byte[] loadData(int size) throws IOException {
        byte[] data = new byte[size];
        read(data, 0, size);
        return data;
    }

    @Override
    public void close() {
        try {
            super.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

public abstract class Graphe {

    protected String KEY;
    protected int nbVertices, nbEdges;

    public class Vertices {

        String names;

        public Vertices(String names) {
            this.names = names;
        }

        public String tostring() {
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

    public String getEdgeName(int id) throws ArrayIndexOutOfBoundsException {
        return vertices[id].names;
    }

    public void setEdgeName(int id, String name) throws ArrayIndexOutOfBoundsException {
        vertices[id].names = name;
    }
}
