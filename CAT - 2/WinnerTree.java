import java.util.*;

public class WinnerTree {
    private int[] tree;
    private int[] data;
    private int n;
    
    public WinnerTree(int[] arr) {
        n = arr.length;
        data = arr.clone();
        tree = new int[2 * n - 1];
        buildTree(0, 0, n - 1);
    }
    
    // Build winner tree (min tournament)
    private int buildTree(int node, int left, int right) {
        if (left == right) {
            tree[node] = left;
            return left;
        }
        
        int mid = (left + right) / 2;
        int leftWinner = buildTree(2 * node + 1, left, mid);
        int rightWinner = buildTree(2 * node + 2, mid + 1, right);
        
        tree[node] = (data[leftWinner] <= data[rightWinner]) ? leftWinner : rightWinner;
        return tree[node];
    }
    
    // Get overall winner (minimum element)
    public int getWinner() {
        return tree[0];
    }
    
    // Replace winner and rebuild path
    public void replaceWinner(int newValue) {
        int winnerIdx = tree[0];
        data[winnerIdx] = newValue;
        updateTree(0, 0, n - 1, winnerIdx);
    }
    
    // Update tree after replacement
    private void updateTree(int node, int left, int right, int idx) {
        if (left == right) return;
        
        int mid = (left + right) / 2;
        
        if (idx <= mid) {
            updateTree(2 * node + 1, left, mid, idx);
        } else {
            updateTree(2 * node + 2, mid + 1, right, idx);
        }
        
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;
        int leftWinner = tree[leftChild];
        int rightWinner = tree[rightChild];
        
        tree[node] = (data[leftWinner] <= data[rightWinner]) ? leftWinner : rightWinner;
    }
    
    // Display tree
    public void display() {
        System.out.println("\nWinner Tree (indices): " + Arrays.toString(tree));
        System.out.println("Data values: " + Arrays.toString(data));
        System.out.println("Winner index: " + tree[0] + ", Winner value: " + data[tree[0]]);
    }
    
    // Perform k extractions (heap sort using winner tree)
    public void sortedExtraction(int k) {
        System.out.print("\nTop " + k + " minimum elements: ");
        for (int i = 0; i < k && i < n; i++) {
            int winnerIdx = getWinner();
            System.out.print(data[winnerIdx] + " ");
            replaceWinner(Integer.MAX_VALUE);
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        WinnerTree wt = new WinnerTree(arr);
        wt.display();
        
        System.out.print("\nEnter k for top-k extraction: ");
        int k = sc.nextInt();
        wt.sortedExtraction(k);
        
        sc.close();
    }
}
