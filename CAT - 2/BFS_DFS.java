import java.util.*;

public class BFS_DFS {
    private int V;
    private List<List<Integer>> adj;
    
    public BFS_DFS(int v) {
        V = v;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }
    
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }
    
    // BFS Traversal
    public void BFS(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        
        visited[start] = true;
        q.add(start);
        System.out.print("BFS: ");
        
        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");
            
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
        System.out.println();
    }
    
    // DFS Traversal (Recursive)
    public void DFS(int start) {
        boolean[] visited = new boolean[V];
        System.out.print("DFS: ");
        DFSUtil(start, visited);
        System.out.println();
    }
    
    private void DFSUtil(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited);
            }
        }
    }
    
    // DFS Iterative using Stack
    public void DFSIterative(int start) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        
        stack.push(start);
        System.out.print("DFS (Iterative): ");
        
        while (!stack.isEmpty()) {
            int node = stack.pop();
            
            if (!visited[node]) {
                visited[node] = true;
                System.out.print(node + " ");
                
                for (int i = adj.get(node).size() - 1; i >= 0; i--) {
                    int neighbor = adj.get(node).get(i);
                    if (!visited[neighbor]) stack.push(neighbor);
                }
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        BFS_DFS graph = new BFS_DFS(v);
        
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        
        System.out.println("Enter edges (u v):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v1 = sc.nextInt();
            graph.addEdge(u, v1);
        }
        
        System.out.print("Enter starting vertex: ");
        int start = sc.nextInt();
        
        System.out.println();
        graph.BFS(start);
        graph.DFS(start);
        graph.DFSIterative(start);
        
        sc.close();
    }
}
