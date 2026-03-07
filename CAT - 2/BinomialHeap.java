import java.util.*;

public class BinomialHeap {
    
    class Node {
        int key, degree;
        Node parent, child, sibling;
        
        Node(int key) {
            this.key = key;
            this.degree = 0;
        }
    }
    
    private Node head;
    
    public BinomialHeap() {
        head = null;
    }
    
    // Insert a key
    public void insert(int key) {
        BinomialHeap temp = new BinomialHeap();
        temp.head = new Node(key);
        head = union(temp);
    }
    
    // Get minimum key
    public int getMin() {
        if (head == null) return Integer.MAX_VALUE;
        Node min = head, curr = head;
        while (curr != null) {
            if (curr.key < min.key) min = curr;
            curr = curr.sibling;
        }
        return min.key;
    }
    
    // Extract minimum
    public int extractMin() {
        if (head == null) return Integer.MAX_VALUE;
        
        Node min = head, prevMin = null, prev = null, curr = head;
        while (curr != null) {
            if (curr.key < min.key) {
                min = curr;
                prevMin = prev;
            }
            prev = curr;
            curr = curr.sibling;
        }
        
        if (prevMin == null) head = min.sibling;
        else prevMin.sibling = min.sibling;
        
        BinomialHeap newHeap = new BinomialHeap();
        Node child = min.child;
        while (child != null) {
            Node next = child.sibling;
            child.sibling = newHeap.head;
            child.parent = null;
            newHeap.head = child;
            child = next;
        }
        
        head = union(newHeap);
        return min.key;
    }
    
    // Union of two heaps
    private Node union(BinomialHeap h2) {
        Node newHead = merge(this.head, h2.head);
        if (newHead == null) return null;
        
        Node prev = null, curr = newHead, next = newHead.sibling;
        
        while (next != null) {
            if (curr.degree != next.degree || 
                (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else {
                if (curr.key <= next.key) {
                    curr.sibling = next.sibling;
                    link(next, curr);
                } else {
                    if (prev == null) newHead = next;
                    else prev.sibling = next;
                    link(curr, next);
                    curr = next;
                }
            }
            next = curr.sibling;
        }
        return newHead;
    }
    
    // Merge two binomial heaps
    private Node merge(Node h1, Node h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        
        Node head = (h1.degree <= h2.degree) ? h1 : h2;
        Node curr = head, p1 = (head == h1) ? h1.sibling : h1, p2 = (head == h2) ? h2.sibling : h2;
        
        while (p1 != null && p2 != null) {
            if (p1.degree <= p2.degree) {
                curr.sibling = p1;
                p1 = p1.sibling;
            } else {
                curr.sibling = p2;
                p2 = p2.sibling;
            }
            curr = curr.sibling;
        }
        curr.sibling = (p1 != null) ? p1 : p2;
        return head;
    }
    
    // Link two trees
    private void link(Node child, Node parent) {
        child.parent = parent;
        child.sibling = parent.child;
        parent.child = child;
        parent.degree++;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinomialHeap heap = new BinomialHeap();
        
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++) {
            heap.insert(sc.nextInt());
        }
        
        System.out.println("\nMinimum element: " + heap.getMin());
        
        System.out.println("\nExtracting elements in sorted order:");
        for (int i = 0; i < n; i++) {
            System.out.print(heap.extractMin() + " ");
        }
        System.out.println();
        
        sc.close();
    }
}
