package DSA.Graphs;

import java.util.ArrayList;

public class DFS {

    static void dfs(int n, boolean[] visited, ArrayList<Integer> ans, ArrayList<ArrayList<Integer>> adj) {
        ans.add(n);
        visited[n] = true;

        for (Integer ele : adj.get(n)) {
            if ( !visited[ele]) {
                dfs(ele, visited, ans, adj);
            }
        }
    }

    static ArrayList<Integer> dfsTraversal(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        ArrayList<Integer> ans = new ArrayList<>();

        dfs(0, visited, ans, adj);

        return ans;
    }

    static void main() {
        {
            int V = 9;

            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }

            // Building graph (0-indexed)
            adj.get(0).add(1);
            adj.get(1).add(0);

            adj.get(0).add(5);
            adj.get(5).add(0);

            adj.get(1).add(2);
            adj.get(2).add(1);

            adj.get(1).add(3);
            adj.get(3).add(1);

            adj.get(3).add(4);
            adj.get(4).add(3);

            adj.get(4).add(7);
            adj.get(7).add(4);

            adj.get(5).add(6);
            adj.get(6).add(5);

            adj.get(5).add(8);
            adj.get(8).add(5);

            adj.get(6).add(7);
            adj.get(7).add(6);

            for (int i=0; i<V; i++) {
                System.out.println(i + " --> " + adj.get(i));
            }

            ArrayList<Integer> ans = dfsTraversal(V, adj);
            System.out.println("\nDFS Traversal - " + ans);
        }
    }

}
