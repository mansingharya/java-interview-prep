package DSA.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class BFS {

    static ArrayList<Integer> bfsTraversal(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans = new ArrayList<>(V);
        Queue<Integer> queue = new ArrayDeque<>(V);  // ArrayDeque is faster than LinkedList for BFS
        boolean[] visited = new boolean[V];

        queue.offer(0);
        visited[0] = true;

        while ( !queue.isEmpty()) {
            System.out.println("\nVisited - " + Arrays.toString(visited));
            System.out.println("Queue --> " + queue);

            int ele = queue.poll();
            ans.add(ele);
            System.out.println("Current Element - " + ele);

            for (int neighbor : adj.get(ele)) {
                if ( !visited[neighbor]) {
                    queue.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return ans;
    }

    static void main() {
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

        ArrayList<Integer> ans = bfsTraversal(V, adj);
        System.out.println("\nBFS Traversal - " + ans);
    }

}
