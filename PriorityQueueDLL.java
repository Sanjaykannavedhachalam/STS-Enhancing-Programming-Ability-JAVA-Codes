public class PriorityQueueDLL {

    // Front and Rear pointers
    static Node front = null;
    static Node rear = null;

    // Doubly Linked List Node
    static class Node {
        int data;
        int priority;
        Node prev, next;

        Node(int data, int priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    // Insert element based on priority
    static void push(int data, int priority) {
        Node newNode = new Node(data, priority);

        // Case 1: Empty queue
        if (front == null) {
            front = rear = newNode;
            return;
        }

        // Case 2: Insert at front (highest priority)
        if (priority < front.priority) {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
            return;
        }

        // Case 3: Insert in middle or at end
        Node current = front;
        while (current.next != null && current.next.priority <= priority) {
            current = current.next;
        }

        newNode.next = current.next;
        newNode.prev = current;

        if (current.next != null) {
            current.next.prev = newNode;
        } else {
            rear = newNode; // inserted at end
        }

        current.next = newNode;
    }

    // Remove highest priority element
    static int pop() {
        if (front == null) {
            System.out.println("Queue is empty");
            return -1;
        }

        int value = front.data;
        front = front.next;

        if (front != null)
            front.prev = null;
        else
            rear = null;

        return value;
    }

    // Peek highest priority element
    static int peek() {
        if (front == null)
            return -1;
        return front.data;
    }

    static boolean isEmpty() {
        return front == null;
    }

    // Driver code
    public static void main(String[] args) {
        push(2, 3);
        push(3, 4);
        push(4, 5);
        push(5, 6);
        push(6, 7);
        push(1, 2);

        System.out.println(pop());  // 1
        System.out.println(peek()); // 2
    }
}
