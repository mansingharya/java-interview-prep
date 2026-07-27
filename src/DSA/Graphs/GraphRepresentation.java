package DSA.Graphs;

import java.util.ArrayList;

public class GraphRepresentation {

    static void adjacencyMatrixRepresentation(int N, int M) {
        int [][] adj= new int[N+1][N+1];

        // Edges - 1-2, 1-3, 2-4, 2-5, 3-4, 4-5
        // 1 -- 2 \
        // |    |   5
        // 3 -- 4 /

        // Un-directed Graph
        // adj[u][v] = 1
        // adj[v][u] = 1

        // Un-directed Weighted Graph
        // adj[u][v] = wt
        // adj[v][u] = wt

        // Directed Graph
        // adj[u][v] = 1

        // Directed Weighted Graph
        // adj[u][v] = wt

        adj[1][2] = 1;
        adj[2][1] = 1;

        adj[1][3] = 1;
        adj[3][1] = 1;

        adj[2][4] = 1;
        adj[4][2] = 1;

        adj[2][5] = 1;
        adj[5][2] = 1;

        adj[3][4] = 1;
        adj[4][3] = 1;

        adj[4][5] = 1;
        adj[5][4] = 1;

        System.out.print("\n" + "X | ");
        for (int i=0; i<=N; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("---------------");

        for (int i=0; i<=N; i++) {
            for (int j=-1; j<=N; j++) {
                if (j == -1) {
                    System.out.print(i + " | ");
                    continue;
                }
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void adjacencyListRepresentation(int N, int M) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Initialization of graph
        for (int i=0; i<N+1; i++) {
            adj.add(new ArrayList<>());
        }

        // Edges - 1-2, 1-3, 2-4, 2-5, 3-4, 4-5

        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(1).add(3);
        adj.get(3).add(1);

        adj.get(2).add(4);
        adj.get(4).add(2);

        adj.get(2).add(5);
        adj.get(5).add(2);

        adj.get(3).add(4);
        adj.get(4).add(3);

        adj.get(4).add(5);
        adj.get(5).add(4);

        for (int i=0; i<=N; i++) {
            System.out.print( i + " --> ");
            System.out.println(adj.get(i));
        }

        System.out.println("---------------");

        for (int i=0; i<adj.size(); i++) {
            for (int j=-1; j<adj.get(i).size(); j++) {
                if (j == -1) {
                    System.out.print(i + " --> [");
                    continue;
                }
                System.out.print(adj.get(i).get(j) + ", ");
            }
            System.out.print("\b\b" + "]");
            System.out.println();
        }

    }

    static void main() {
        adjacencyMatrixRepresentation(5, 6);
        System.out.println("---------------");
        adjacencyListRepresentation(5, 6);
        System.out.println("---------------");
    }

}
