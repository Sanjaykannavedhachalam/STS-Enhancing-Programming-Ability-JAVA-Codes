public class MergeSortDLL {
    
    // Node class for doubly linked list
    static class Node {
        int data;
        Node next;
        Node prev;
        
        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    // Main function to perform merge sort on doubly linked list
    public static Node mergeSort(Node head) {
        // Base case: if list is empty or has only one node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Step 1: Find the middle node using slow and fast pointer approach
        Node middle = getMiddle(head);
        Node nextToMiddle = middle.next;
        
        // Step 2: Split the list into two halves
        middle.next = null;
        if (nextToMiddle != null) {
            nextToMiddle.prev = null;
        }
        
        // Step 3: Recursively sort both halves
        Node left = mergeSort(head);
        Node right = mergeSort(nextToMiddle);
        
        // Step 4: Merge the sorted halves
        Node sortedList = merge(left, right);
        
        return sortedList;
    }
    
    // Function to find the middle node using slow and fast pointer
    private static Node getMiddle(Node head) {
        if (head == null) {
            return head;
        }
        
        Node slow = head;
        Node fast = head;
        
        // Move fast pointer twice as fast as slow pointer
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    // Function to merge two sorted doubly linked lists
    private static Node merge(Node left, Node right) {
        // If one list is empty, return the other
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        
        Node result;
        
        // Pick the smaller value and recursively merge the rest
        if (left.data <= right.data) {
            result = left;
            result.next = merge(left.next, right);
            if (result.next != null) {
                result.next.prev = result;
            }
            result.prev = null;
        } else {
            result = right;
            result.next = merge(left, right.next);
            if (result.next != null) {
                result.next.prev = result;
            }
            result.prev = null;
        }
        
        return result;
    }
    
    // Alternative iterative merge function (more efficient)
    private static Node mergeIterative(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;
        
        Node dummy = new Node(0);
        Node current = dummy;
        
        while (left != null && right != null) {
            if (left.data <= right.data) {
                current.next = left;
                left.prev = current;
                left = left.next;
            } else {
                current.next = right;
                right.prev = current;
                right = right.next;
            }
            current = current.next;
        }
        
        // Attach remaining nodes
        if (left != null) {
            current.next = left;
            left.prev = current;
        }
        if (right != null) {
            current.next = right;
            right.prev = current;
        }
        
        Node result = dummy.next;
        if (result != null) {
            result.prev = null;
        }
        
        return result;
    }
    
    // Function to insert node at the end
    public static Node insert(Node head, int data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            return newNode;
        }
        
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        
        current.next = newNode;
        newNode.prev = current;
        
        return head;
    }
    
    // Function to print the doubly linked list (forward)
    public static void printListForward(Node head) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }
        System.out.println();
    }
    
    // Function to print the doubly linked list (backward)
    public static void printListBackward(Node head) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        
        // Go to the last node
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        
        // Print backward
        while (current != null) {
            System.out.print(current.data);
            if (current.prev != null) {
                System.out.print(" <-> ");
            }
            current = current.prev;
        }
        System.out.println();
    }
    
    // Function to verify if list is sorted
    public static boolean isSorted(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        Node current = head;
        while (current.next != null) {
            if (current.data > current.next.data) {
                return false;
            }
            current = current.next;
        }
        return true;
    }
    
    // Function to count nodes
    public static int countNodes(Node head) {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
    public static void main(String[] args) {
        // Test Case 1: Random unsorted list
        System.out.println("=== Test Case 1: Random Unsorted List ===");
        Node head1 = null;
        head1 = insert(head1, 8);
        head1 = insert(head1, 3);
        head1 = insert(head1, 1);
        head1 = insert(head1, 7);
        head1 = insert(head1, 0);
        head1 = insert(head1, 10);
        head1 = insert(head1, 2);
        
        System.out.print("Original List (Forward): ");
        printListForward(head1);
        System.out.print("Original List (Backward): ");
        printListBackward(head1);
        
        head1 = mergeSort(head1);
        
        System.out.print("Sorted List (Forward): ");
        printListForward(head1);
        System.out.print("Sorted List (Backward): ");
        printListBackward(head1);
        System.out.println("Is Sorted: " + isSorted(head1));
        
        // Test Case 2: Already sorted list
        System.out.println("\n=== Test Case 2: Already Sorted List ===");
        Node head2 = null;
        head2 = insert(head2, 1);
        head2 = insert(head2, 2);
        head2 = insert(head2, 3);
        head2 = insert(head2, 4);
        head2 = insert(head2, 5);
        
        System.out.print("Original List: ");
        printListForward(head2);
        
        head2 = mergeSort(head2);
        
        System.out.print("Sorted List: ");
        printListForward(head2);
        System.out.println("Is Sorted: " + isSorted(head2));
        
        // Test Case 3: Reverse sorted list
        System.out.println("\n=== Test Case 3: Reverse Sorted List ===");
        Node head3 = null;
        head3 = insert(head3, 9);
        head3 = insert(head3, 7);
        head3 = insert(head3, 5);
        head3 = insert(head3, 3);
        head3 = insert(head3, 1);
        
        System.out.print("Original List: ");
        printListForward(head3);
        
        head3 = mergeSort(head3);
        
        System.out.print("Sorted List: ");
        printListForward(head3);
        System.out.println("Is Sorted: " + isSorted(head3));
        
        // Test Case 4: Duplicate values
        System.out.println("\n=== Test Case 4: Duplicate Values ===");
        Node head4 = null;
        head4 = insert(head4, 5);
        head4 = insert(head4, 2);
        head4 = insert(head4, 5);
        head4 = insert(head4, 1);
        head4 = insert(head4, 2);
        head4 = insert(head4, 8);
        
        System.out.print("Original List: ");
        printListForward(head4);
        
        head4 = mergeSort(head4);
        
        System.out.print("Sorted List: ");
        printListForward(head4);
        System.out.println("Is Sorted: " + isSorted(head4));
        
        // Test Case 5: Single element
        System.out.println("\n=== Test Case 5: Single Element ===");
        Node head5 = new Node(42);
        
        System.out.print("Original List: ");
        printListForward(head5);
        
        head5 = mergeSort(head5);
        
        System.out.print("Sorted List: ");
        printListForward(head5);
        System.out.println("Is Sorted: " + isSorted(head5));
        
        // Test Case 6: Two elements
        System.out.println("\n=== Test Case 6: Two Elements ===");
        Node head6 = null;
        head6 = insert(head6, 10);
        head6 = insert(head6, 5);
        
        System.out.print("Original List: ");
        printListForward(head6);
        
        head6 = mergeSort(head6);
        
        System.out.print("Sorted List: ");
        printListForward(head6);
        System.out.println("Is Sorted: " + isSorted(head6));
    }
}
