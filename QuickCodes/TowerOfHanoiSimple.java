package QuickCodes;

import java.util.Scanner;

public class TowerOfHanoiSimple {
    
    // Counter for number of moves
    static int moveCount = 0;
    
    // Simple recursive method for Tower of Hanoi
    public static void solve(int n, char source, char destination, char auxiliary) {
        // Base case: if only 1 disk, move it directly
        if (n == 1) {
            moveCount++;
            System.out.println("Move " + moveCount + ": Disk 1 from " + source + " to " + destination);
            return;
        }
        
        // Step 1: Move n-1 disks from source to auxiliary (using destination)
        solve(n - 1, source, auxiliary, destination);
        
        // Step 2: Move the largest disk from source to destination
        moveCount++;
        System.out.println("Move " + moveCount + ": Disk " + n + " from " + source + " to " + destination);
        
        // Step 3: Move n-1 disks from auxiliary to destination (using source)
        solve(n - 1, auxiliary, destination, source);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Tower of Hanoi ===\n");
        
        // Input
        System.out.print("Enter number of disks: ");
        int n = sc.nextInt();
        
        // Calculate total moves
        int totalMoves = (int) Math.pow(2, n) - 1;
        System.out.println("\nTotal moves required: " + totalMoves);
        System.out.println("\nMoves:\n");
        
        // Reset counter
        moveCount = 0;
        
        // Solve (A = source, C = destination, B = auxiliary)
        solve(n, 'A', 'C', 'B');
        
        System.out.println("\nDone! All " + n + " disks moved from A to C.");
        
        sc.close();
    }
}
