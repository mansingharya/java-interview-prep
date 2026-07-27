package DSA.Graphs;

import java.util.ArrayList;


// https://leetcode.com/problems/number-of-provinces/description/
public class NumberOfProvinces {

    /*
    1 1 0
    1 1 0
    0 0 1
     */

    static int countConnectedOnAdjMatrix(int[][] isConnected) {
        int ans = 0;
        boolean[] visited = new boolean[isConnected.length];
        for (int i=0; i<isConnected.length; i++) {
            if ( !visited[i]) {
                ++ ans;
                dfsOnAdjMatrix(i, visited, isConnected);
            }
        }

        return ans;
    }

    static void dfsOnAdjMatrix(int i, boolean[] visited, int[][] isConnected) {
        visited[i] = true;
        for (int j=0; j<isConnected[0].length; ++j) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                dfsOnAdjMatrix(j, visited, isConnected);
            }
        }
    }

    static int countConnectedOnAdjList(int V, ArrayList<ArrayList<Integer>> adj) {

        for (int i=0; i<V; i++) {
            System.out.print( i + " --> ");
            System.out.println(adj.get(i));
        }

        int ans = 0;
        boolean[] visited = new boolean[V+1];

        for (int i=0; i<V; i++) {
            if ( !visited[i]) {
                ++ ans;
                dfsOnAdjList(i, visited, adj);
            }
        }

        return ans;
    }

    static void dfsOnAdjList(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true;

        for (int n : adj.get(node)) {
            if ( !visited[n]) {
                dfsOnAdjList(n, visited, adj);
            }
        }
    }

    static void main() {

        // Test - 1
        System.out.println("----------");
        int[][] mat = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        for (int i=0; i<mat.length; ++i) {
            for (int j=0; j<mat[0].length; ++j) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------");
        System.out.println("Connected Provinces are : " + countConnectedOnAdjMatrix(mat));


        // Test - 2
        System.out.println("----------");
        int[][] mat1 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        for (int i=0; i<mat1.length; ++i) {
            for (int j=0; j<mat1[0].length; ++j) {
                System.out.print(mat1[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------");
        System.out.println("Connected Provinces are : " + countConnectedOnAdjMatrix(mat1));


        // Test - 3
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i=0; i<V; ++i) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(1).add(0);

        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(3).add(4);
        adj.get(4).add(3);

        System.out.println("----------");
        System.out.println("Connected Provinces are : " + countConnectedOnAdjList(V, adj));


        // Test - 5
        int V1 = 7;
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        for (int i=0; i<V1; ++i) {
            adj1.add(new ArrayList<>());
        }
        adj1.get(0).add(1);
        adj1.get(1).add(0);

        adj1.get(0).add(6);
        adj1.get(6).add(0);

        adj1.get(2).add(3);
        adj1.get(3).add(2);

        adj1.get(2).add(4);
        adj1.get(4).add(2);

        adj1.get(3).add(4);
        adj1.get(4).add(3);

        System.out.println("----------");
        System.out.println("Connected Provinces are : " + countConnectedOnAdjList(V1, adj1));

    }

}
