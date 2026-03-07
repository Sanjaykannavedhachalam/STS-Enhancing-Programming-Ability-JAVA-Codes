import java.util.*;

public class TopologicalSort {
    private int V;
    private List<List<Integer>> adj;
    
    public TopologicalSort(int v) {
        V = v;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }
    
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }
    
    // DFS-based Topological Sort
    public void topologicalSortDFS() {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack);
            }
        }
        
        System.out.print("Topological Sort (DFS): ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
    
    private void dfs(int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, stack);
            }
        }
        stack.push(node);
    }
    
    // Kahn's Algorithm (BFS-based)
    public void topologicalSortBFS() {
        int[] indegree = new int[V];
        
        for (int i = 0; i < V; i++) {
            for (int node : adj.get(i)) {
                indegree[node]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) q.add(i);
        }
        
        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            result.add(node);
            
            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) q.add(neighbor);
            }
        }
        
        if (result.size() != V) {
            System.out.println("Topological Sort (BFS): Graph has a cycle!");
        } else {
            System.out.println("Topological Sort (BFS): " + result);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        TopologicalSort graph = new TopologicalSort(v);
        
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        
        System.out.println("Enter edges (u v):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v1 = sc.nextInt();
            graph.addEdge(u, v1);
        }
        
        System.out.println();
        graph.topologicalSortDFS();
        graph.topologicalSortBFS();
        
        sc.close();
    }
}
