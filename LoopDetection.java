class LoopDetection {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Function to detect loop
    static boolean detectLoop(Node head) {

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;          // 1 step
            fast = fast.next.next;    // 2 steps

            if (slow == fast) {
                return true;           // loop found
            }
        }
        return false;                  // no loop
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // Creating a loop: 5 → 3
        head.next.next.next.next.next = head.next.next;

        System.out.println(detectLoop(head)); // true
    }

    /* complete code */
    
    // Function to create a linked list from array with optional loop
    static Node createLinkedListWithLoop(int[] values, int loopIndex) {
        if (values == null || values.length == 0) {
            return null;
        }

        Node head = new Node(values[0]);
        Node current = head;
        Node[] nodes = new Node[values.length];
        nodes[0] = head;

        // Create the linked list and store references
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
            nodes[i] = current;
        }

        // Create loop if loopIndex is valid (0-based)
        if (loopIndex >= 0 && loopIndex < values.length) {
            current.next = nodes[loopIndex];
        }

        return head;
    }

    // Example with dynamic input
    static void testWithDynamicInput() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = scanner.nextInt();

        int[] values = new int[n];
        System.out.print("Enter node values: ");
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        System.out.print("Enter loop index (0-based, -1 for no loop): ");
        int loopIndex = scanner.nextInt();

        Node head = createLinkedListWithLoop(values, loopIndex);
        
        System.out.println("Loop detected: " + detectLoop(head));
        
        scanner.close();
    }
}
