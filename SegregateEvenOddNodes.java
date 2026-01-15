public class SegregateEvenOddNodes {
    
    // Node class for linked list
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    // Method to segregate even and odd nodes
    public static Node segregateEvenOdd(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        // Create dummy nodes for even and odd lists
        Node evenHead = new Node(0);  // Dummy head for even list
        Node oddHead = new Node(0);   // Dummy head for odd list
        
        Node even = evenHead;  // Pointer for even list
        Node odd = oddHead;    // Pointer for odd list
        Node current = head;
        
        // Traverse the original list
        while (current != null) {
            if (current.data % 2 == 0) {
                // Even node - add to even list
                even.next = current;
                even = even.next;
            } else {
                // Odd node - add to odd list
                odd.next = current;
                odd = odd.next;
            }
            current = current.next;
        }
        
        // Important: Set the last node's next to null to avoid cycles
        odd.next = null;
        
        // Merge even and odd lists (even first, then odd)
        even.next = oddHead.next;
        
        // Return the merged list (skip dummy node)
        return evenHead.next;
    }
    
    // Alternative: Segregate by position (even positions and odd positions)
    public static Node segregateByPosition(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        Node odd = head;
        Node even = head.next;
        Node evenHead = even;
        
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        
        odd.next = evenHead;
        return head;
    }
    
    // Method to insert node at the end
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
        return head;
    }
    
    // Method to print the linked list
    public static void printList(Node head) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
    
    // Method to count nodes
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
        // Test Case 1: Mixed even and odd numbers
        System.out.println("=== Test Case 1: Segregate by Value ===");
        Node head1 = null;
        head1 = insert(head1, 8);
        head1 = insert(head1, 12);
        head1 = insert(head1, 10);
        head1 = insert(head1, 5);
        head1 = insert(head1, 4);
        head1 = insert(head1, 1);
        head1 = insert(head1, 6);
        
        System.out.print("Original List: ");
        printList(head1);
        
        head1 = segregateEvenOdd(head1);
        System.out.print("After Segregation (Even first, then Odd): ");
        printList(head1);
        
        // Test Case 2: All even numbers
        System.out.println("\n=== Test Case 2: All Even Numbers ===");
        Node head2 = null;
        head2 = insert(head2, 2);
        head2 = insert(head2, 4);
        head2 = insert(head2, 6);
        head2 = insert(head2, 8);
        
        System.out.print("Original List: ");
        printList(head2);
        
        head2 = segregateEvenOdd(head2);
        System.out.print("After Segregation: ");
        printList(head2);
        
        // Test Case 3: All odd numbers
        System.out.println("\n=== Test Case 3: All Odd Numbers ===");
        Node head3 = null;
        head3 = insert(head3, 1);
        head3 = insert(head3, 3);
        head3 = insert(head3, 5);
        head3 = insert(head3, 7);
        
        System.out.print("Original List: ");
        printList(head3);
        
        head3 = segregateEvenOdd(head3);
        System.out.print("After Segregation: ");
        printList(head3);
        
        // Test Case 4: Alternating even and odd
        System.out.println("\n=== Test Case 4: Alternating Pattern ===");
        Node head4 = null;
        head4 = insert(head4, 1);
        head4 = insert(head4, 2);
        head4 = insert(head4, 3);
        head4 = insert(head4, 4);
        head4 = insert(head4, 5);
        head4 = insert(head4, 6);
        
        System.out.print("Original List: ");
        printList(head4);
        
        head4 = segregateEvenOdd(head4);
        System.out.print("After Segregation: ");
        printList(head4);
        
        // Test Case 5: Segregate by position (bonus)
        System.out.println("\n=== Test Case 5: Segregate by Position ===");
        Node head5 = null;
        head5 = insert(head5, 1);
        head5 = insert(head5, 2);
        head5 = insert(head5, 3);
        head5 = insert(head5, 4);
        head5 = insert(head5, 5);
        head5 = insert(head5, 6);
        head5 = insert(head5, 7);
        
        System.out.print("Original List: ");
        printList(head5);
        
        head5 = segregateByPosition(head5);
        System.out.print("After Position Segregation (Odd positions first): ");
        printList(head5);
        
        // Test Case 6: Single node
        System.out.println("\n=== Test Case 6: Single Node ===");
        Node head6 = new Node(5);
        System.out.print("Original List: ");
        printList(head6);
        
        head6 = segregateEvenOdd(head6);
        System.out.print("After Segregation: ");
        printList(head6);
    }
}
