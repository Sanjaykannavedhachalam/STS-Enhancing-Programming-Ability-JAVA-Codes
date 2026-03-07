import java.util.*;

public class DialsAlgorithm {
    private int V;
    private List<List<Pair>> adj;
    
    public DialsAlgorithm(int v) {
        V = v;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }
    
    public void addEdge(int u, int v, int w) {
        adj.get(u).add(new Pair(v, w));
    }
    
    public void shortestPath(int src, int maxWeight) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        // Create buckets for distances
        List<List<Integer>> buckets = new ArrayList<>();
        int maxDist = V * maxWeight;
        for (int i = 0; i <= maxDist; i++) buckets.add(new ArrayList<>());
        
        buckets.get(0).add(src);
        int idx = 0;
        
        while (idx <= maxDist) {
            while (idx <= maxDist && buckets.get(idx).isEmpty()) idx++;
            if (idx > maxDist) break;
            
            int u = buckets.get(idx).remove(buckets.get(idx).size() - 1);
            
            for (Pair p : adj.get(u)) {
                int v = p.node;
                int weight = p.weight;
                
                if (dist[u] + weight < dist[v]) {
                    if (dist[v] != Integer.MAX_VALUE) {
                        buckets.get(dist[v]).remove(Integer.valueOf(v));
                    }
                    dist[v] = dist[u] + weight;
                    buckets.get(dist[v]).add(v);
                }
            }
        }
        
        System.out.println("\nVertex\tDistance from Source " + src);
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t" + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }
    
    class Pair {
        int node, weight;
        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        DialsAlgorithm graph = new DialsAlgorithm(v);
        
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        
        System.out.println("Enter edges (u v weight):");
        int maxWeight = 0;
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v1 = sc.nextInt();
            int w = sc.nextInt();
            graph.addEdge(u, v1, w);
            maxWeight = Math.max(maxWeight, w);
        }
        
        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();
        
        graph.shortestPath(src, maxWeight);
        
        sc.close();
    }
}
