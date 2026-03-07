import java.util.*;

public class KaryHeap {
    private int[] heap;
    private int size;
    private int k;  // k-ary (k children per node)
    
    public KaryHeap(int capacity, int k) {
        this.heap = new int[capacity];
        this.size = 0;
        this.k = k;
    }
    
    private int parent(int i) {
        return (i - 1) / k;
    }
    
    private int child(int i, int j) {
        return k * i + j + 1;
    }
    
    // Insert element
    public void insert(int value) {
        heap[size] = value;
        heapifyUp(size);
        size++;
    }
    
    // Extract minimum
    public int extractMin() {
        if (size == 0) return Integer.MAX_VALUE;
        
        int min = heap[0];
        heap[0] = heap[--size];
        heapifyDown(0);
        return min;
    }
    
    // Get minimum
    public int getMin() {
        return size > 0 ? heap[0] : Integer.MAX_VALUE;
    }
    
    // Heapify up (for insert)
    private void heapifyUp(int i) {
        while (i > 0 && heap[parent(i)] > heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
    
    // Heapify down (for extract)
    private void heapifyDown(int i) {
        while (true) {
            int minChild = -1;
            int minValue = heap[i];
            
            // Find minimum among k children
            for (int j = 0; j < k; j++) {
                int childIdx = child(i, j);
                if (childIdx < size && heap[childIdx] < minValue) {
                    minValue = heap[childIdx];
                    minChild = childIdx;
                }
            }
            
            if (minChild == -1) break;
            
            swap(i, minChild);
            i = minChild;
        }
    }
    
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    public void display() {
        System.out.print("Heap: ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter k value (children per node): ");
        int k = sc.nextInt();
        
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        
        KaryHeap kHeap = new KaryHeap(n, k);
        
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++) {
            kHeap.insert(sc.nextInt());
        }
        
        System.out.println("\nAfter insertions:");
        kHeap.display();
        
        System.out.println("\nMinimum element: " + kHeap.getMin());
        
        System.out.println("\nExtracting elements in sorted order:");
        for (int i = 0; i < n; i++) {
            System.out.print(kHeap.extractMin() + " ");
        }
        System.out.println();
        
        sc.close();
    }
}
