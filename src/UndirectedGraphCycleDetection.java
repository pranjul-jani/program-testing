import java.util.*;

public class UndirectedGraphCycleDetection {


    public static void main(String[] args) {
        UndirectedGraphCycleDetection ob = new UndirectedGraphCycleDetection();
        Map<Integer, List<Integer>> adj = new HashMap<>();

        ob.addEdge(adj, 0, 1);
        ob.addEdge(adj, 1, 2);
        ob.addEdge(adj, 2, 3);
        ob.addEdge(adj, 3, 4);
//        ob.addEdge(adj, 0, 1);

        if (ob.isCyclicDfs(adj, 5)) {
            System.out.println("DFS has detected cycle in the graph");
        } else {
            System.out.println("DFS has not found cycle in the graph");
        }

        if (ob.isCyclicBfs(adj, 5)) {
            System.out.println("BFS has detected cycle in the graph");
        } else {
            System.out.println("BFS has not found cycle in the graph");
        }


    }

    private boolean isCyclicBfs(Map<Integer, List<Integer>> adj, int N) {
        boolean[] visited = new boolean[N];
        for (int i=0;i<N;i++) {
            if (!visited[i]) {
                if (bfs(i, -1, visited, adj)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean bfs(int current, int parent, boolean[] visited, Map<Integer, List<Integer>> adj) {
        visited[current] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{current, parent});

        while (!q.isEmpty()) {
            int size = q.size();
            while(size -- > 0) {
                int[] currentPair = q.poll();

                int src = currentPair[0];
                int currentParent = currentPair[1];
                List<Integer> neighbours  = adj.getOrDefault(src, new ArrayList<>());

                for (int neighbour : neighbours) {
                    if (neighbour == currentParent) {
                        continue;
                    }
                    if (!visited[neighbour]) {
                        visited[neighbour] = true;
                        q.add(new int[]{neighbour, src});
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isCyclicDfs(Map<Integer, List<Integer>> adj, int N) {
        boolean[] visited = new boolean[N];

        for (int i=0;i<N;i++) {
            if (!visited[i]) {
                if (dfs(i, -1, visited, adj)) {
                    return true;
                }
            }
        }

        return false;

    }

    private boolean dfs(int current, int parent, boolean[] visited, Map<Integer, List<Integer>> adj) {
        if (!visited[current]) {
            visited[current] = true;

            List<Integer> neighbours = adj.getOrDefault(current, new ArrayList<>());

            for(int neighbour : neighbours) {
                if (neighbour != parent && dfs(neighbour, current, visited, adj)) {
                    return true;
                }
            }

            return false;
        }

        return true;
    }

    private void addEdge(Map<Integer, List<Integer>> adj, int src, int des) {
        List<Integer> neighbours = adj.getOrDefault(src, new ArrayList<>());
        neighbours.add(des);
        adj.put(src, neighbours);

        neighbours = adj.getOrDefault(des, new ArrayList<>());
        neighbours.add(src);
        adj.put(des, neighbours);
    }


}