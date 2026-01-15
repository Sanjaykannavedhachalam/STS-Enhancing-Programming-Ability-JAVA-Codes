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
}
