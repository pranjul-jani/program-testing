import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedGraphCycleDetection {

    public static void main(String[] args) {
        DirectedGraphCycleDetection ob = new DirectedGraphCycleDetection();

        Map<Integer, List<Integer>> adj = new HashMap<>();

        ob.addEdge(adj, 0, 1);
        ob.addEdge(adj, 0, 2);
        ob.addEdge(adj, 1, 2);
        ob.addEdge(adj, 2, 3);
        ob.addEdge(adj, 3, 4);


        int N = 5;

        if(isCyclicDfs(adj, N)) {
            System.out.println("The graph has a cycle");
        } else {
            System.out.println("The Graph is a Directed Acyclic Graph");
        }



    }

    private static boolean isCyclicDfs(Map<Integer, List<Integer>> adj, int N) {
        // uses 0, 1, 2 approach AKA white, grey, black approach
        int[] visited = new int[N];

        for (int i=0; i<N; i++) {
            if(dfs(0, visited, adj)) {
                return true;
            }
        }

        return false;
    }

    private static boolean dfs(int src, int[] visited, Map<Integer, List<Integer>> adj) {
        if (visited[src] == 2) {
            return false;
        }

        if(visited[src] == 1) {
            return true;
        }

        visited[src] = 1;

        List<Integer> neighbours = adj.getOrDefault(src, new ArrayList<>());

        for (int neighbour : neighbours) {
            if (dfs(neighbour, visited, adj)) {
                return true;
            }
        }

        visited[src] = 2;

        return false;
    }

    private void addEdge(Map<Integer, List<Integer>> adj, int src, int des) {
        List<Integer> neighbours = adj.getOrDefault(src, new ArrayList<>());
        neighbours.add(des);
        adj.put(src, neighbours);
    }
}