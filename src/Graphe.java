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
        while (c != '\n')
            getChar();
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

    private String[] edgesNames;

    Graphe() {

    }

    public Graphe open(String filename) {
        //Si le fichier finit avec .fsaps
        if(true) {
            return new GrapheFsAps(filename);

            //Si le fichier finit avec .matrice
        } else if(true) {
            return new GrapheMatrice(filename);

            //Si le fichier finit avec .listes
        } else if(true) {
            return new GrapheListes(filename);
        } else {
            return null;
        }
    }

    public String getEdgeName(int id) throws ArrayIndexOutOfBoundsException {
        return edgesNames[id];
    }

    public void setEdgeName(int id, String name) throws ArrayIndexOutOfBoundsException {
        edgesNames[id] = name;
    }









    public static GrapheFsAps getGrapheFsAps(GrapheFsAps data) {

        return new GrapheFsAps();
    }

    public static GrapheFsAps getGrapheFsAps(GrapheMatrice data) {

        return new GrapheFsAps();
    }

    public static GrapheFsAps getGrapheFsAps(GrapheListes data) {

        return new GrapheFsAps();
    }


    public static GrapheMatrice getGrapheMatrice(GrapheFsAps data) {

        return new GrapheMatrice();
    }

    public static GrapheMatrice getGrapheMatrice(GrapheMatrice data) {

        return new GrapheMatrice();
    }

    public static GrapheMatrice getGrapheMatrice(GrapheListes data) {

        return new GrapheMatrice();
    }


    public static GrapheListes getGrapheListes(GrapheFsAps data) {

        return new GrapheListes();
    }

    public static GrapheListes getGrapheListes(GrapheMatrice data) {

        return new GrapheListes();
    }

    public static GrapheListes getGrapheListes(GrapheListes data) {

        return new GrapheListes();
    }
}
