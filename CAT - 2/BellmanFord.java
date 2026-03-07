import java.util.*;

public class BellmanFord {
    private int V, E;
    private Edge[] edges;
    
    class Edge {
        int src, dest, weight;
        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
    
    public BellmanFord(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[e];
    }
    
    public void shortestPath(int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        // Relax all edges V-1 times
        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < E; j++) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int w = edges[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }
        
        // Check for negative weight cycles
        boolean hasNegCycle = false;
        for (int j = 0; j < E; j++) {
            int u = edges[j].src;
            int v = edges[j].dest;
            int w = edges[j].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                hasNegCycle = true;
                break;
            }
        }
        
        if (hasNegCycle) {
            System.out.println("\nGraph contains negative weight cycle!");
        } else {
            System.out.println("\nVertex\tDistance from Source " + src);
            for (int i = 0; i < V; i++) {
                System.out.println(i + "\t" + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        
        BellmanFord graph = new BellmanFord(v, e);
        
        System.out.println("Enter edges (src dest weight):");
        for (int i = 0; i < e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            graph.edges[i] = graph.new Edge(src, dest, weight);
        }
        
        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();
        
        graph.shortestPath(src);
        
        sc.close();
    }
}
