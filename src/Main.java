/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chackal
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        GrapheFsAps test = new GrapheFsAps();
        int[] apstest = new int[6];
        test.nbVertices = 5;
        test.nbEdges = 8;
        apstest[1] = 1;
        apstest[2] = 4;
        apstest[3] = 7;
        apstest[4] = 10;
        apstest[5] = 12;
        
        test.aps = apstest;
        
        Edges test0 = new Edges(0,-1);
        Edges test1 = new Edges(2,1);
        Edges test2 = new Edges(3,1);
        Edges test3 = new Edges(0,1);
        Edges test4 = new Edges(4,1);
        Edges test5 = new Edges(5,1);
        Edges test6 = new Edges(0,1);
        Edges test7 = new Edges(2,1);
        Edges test8 = new Edges(5,1);
        Edges test9 = new Edges(0,1);
        Edges test10 = new Edges(5,1);
        Edges test11 = new Edges(0,1);
        Edges test12 = new Edges(3,1);
        Edges test13 = new Edges(0,1);
        
        Edges[] fstest = new Edges[14];
        
        fstest[0] = test0;
        fstest[1] = test1;
        fstest[2] = test2;
        fstest[3] = test3;
        fstest[4] = test4;
        fstest[5] = test5;
        fstest[6] = test6;
        fstest[7] = test7;
        fstest[8] = test8;
        fstest[9] = test9;
        fstest[10] = test10;
        fstest[11] = test11;
        fstest[12] = test12;
        fstest[13] = test13;
        
        test.fs = fstest;
        
        System.out.println(test.toString());
        
        double[][] yolo = new double[test.nbVertices + 1][test.nbVertices + 1];
        yolo = test.mat_dist();
        
        for(int i = 1; i < test.nbVertices + 1; i++)
        {
            for(int j = 1; j < test.nbVertices + 1; j++)
                System.out.print(yolo[i][j] + " ");
            System.out.println("");
        }
        
        test.parcourspostordre(1);
        
        System.out.println("");
        
        GrapheMatrice testmat = new GrapheMatrice();
        testmat = test.fsaps2matrice();
        
        for(int i = 1; i < testmat.nbVertices + 1; i++)
        {
            for(int j = 1; j < testmat.nbVertices + 1; j++)
            {
                System.out.print(testmat.edges[i][j] + " ");
            }
            System.out.println("");
        }
        
        GrapheListes prout = testmat.mat2list();
        
        System.out.println(prout.toString());
        
        
        
        /*
        GrapheMatrice test = new GrapheMatrice();
        double[][] mat = new double[15][15];
        
        for(int i = 1; i < 15; i++)
            for(int j = 1; j < 15; j++)
                mat[i][j] = 0;
        
        mat[1][2] = 1;
        mat[2][3] = 1;
        mat[4][3] = 1;
        mat[5][3] = 1;
        mat[3][6] = 1;
        mat[6][7] = 1;
        mat[8][7] = 1;
        mat[9][7] = 1;
        mat[7][10] = 1;
        mat[11][10] = 1;
        mat[12][10] = 1;
        mat[10][13] = 1;
        mat[13][14] = 1;
        
        test.edges = mat;
        test.nbVertices = 14;
        test.nbEdges = 13;
        for(int i = 1; i < 15; i++)
        {
            for(int j = 1; j < 15; j++)
            {
                System.out.print(test.edges[i][j] + " ");
            }
            System.out.println("");
        }
        int[] p = test.codagePrufer();
        for(int i = 0; i < p.length; i++)
            System.out.print(p[i] + " ");
        System.out.println("");
        test.decodagePrufer(p);
        for(int i = 1; i < 15; i++)
        {
            for(int j = 1; j < 15; j++)
            {
                System.out.print(test.edges[i][j] + " ");
            }
            System.out.println("");
        }*/
        
    }
}
