import java.util.Stack;

public class IterativeTowerOfHanoi {
    
    // Method to perform iterative Tower of Hanoi
    public static void towerOfHanoi(int n) {
        // Create three stacks representing the three pegs
        Stack<Integer> source = new Stack<>();
        Stack<Integer> auxiliary = new Stack<>();
        Stack<Integer> destination = new Stack<>();
        
        // Total number of moves required
        int totalMoves = (int) (Math.pow(2, n) - 1);
        
        // Push disks onto source peg in decreasing order (largest at bottom)
        for (int i = n; i >= 1; i--) {
            source.push(i);
        }
        
        // If number of disks is even, swap destination and auxiliary
        Stack<Integer> temp;
        if (n % 2 == 0) {
            temp = destination;
            destination = auxiliary;
            auxiliary = temp;
        }
        
        // Perform the moves
        for (int i = 1; i <= totalMoves; i++) {
            if (i % 3 == 1) {
                // Move between source and destination
                moveDisk(source, destination, 'A', 'C');
            } else if (i % 3 == 2) {
                // Move between source and auxiliary
                moveDisk(source, auxiliary, 'A', 'B');
            } else if (i % 3 == 0) {
                // Move between auxiliary and destination
                moveDisk(auxiliary, destination, 'B', 'C');
            }
        }
    }
    
    // Method to move disk between two pegs
    private static void moveDisk(Stack<Integer> source, Stack<Integer> destination, 
                                  char s, char d) {
        // If source is empty, move from destination to source
        if (source.isEmpty() && !destination.isEmpty()) {
            int disk = destination.pop();
            source.push(disk);
            System.out.println("Move disk " + disk + " from peg " + d + " to peg " + s);
        }
        // If destination is empty, move from source to destination
        else if (destination.isEmpty() && !source.isEmpty()) {
            int disk = source.pop();
            destination.push(disk);
            System.out.println("Move disk " + disk + " from peg " + s + " to peg " + d);
        }
        // If both are non-empty, move the smaller top disk
        else if (!source.isEmpty() && !destination.isEmpty()) {
            if (source.peek() > destination.peek()) {
                int disk = destination.pop();
                source.push(disk);
                System.out.println("Move disk " + disk + " from peg " + d + " to peg " + s);
            } else {
                int disk = source.pop();
                destination.push(disk);
                System.out.println("Move disk " + disk + " from peg " + s + " to peg " + d);
            }
        }
    }
    
    public static void main(String[] args) {
        int n = 3; // Number of disks
        
        System.out.println("Tower of Hanoi with " + n + " disks:");
        System.out.println("Total moves required: " + (int)(Math.pow(2, n) - 1));
        System.out.println("\nMoves:");
        
        towerOfHanoi(n);
        
        System.out.println("\n--- Testing with 4 disks ---");
        n = 4;
        System.out.println("Tower of Hanoi with " + n + " disks:");
        System.out.println("Total moves required: " + (int)(Math.pow(2, n) - 1));
        System.out.println("\nMoves:");
        
        towerOfHanoi(n);
    }
}
