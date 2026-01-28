package QuickCodes;

import java.util.Scanner;

public class CelebrityProblemSimple {
    
    // Simple method to find celebrity using array logic
    public static int findCelebrity(int[][] matrix) {
        int n = matrix.length;
        
        // Step 1: Find potential celebrity candidate
        // Use elimination technique with two pointers
        int candidate = 0;
        
        for (int i = 1; i < n; i++) {
            // If candidate knows i, then candidate can't be celebrity
            // So i becomes new candidate
            if (matrix[candidate][i] == 1) {
                candidate = i;
            }
            // If candidate doesn't know i, then i can't be celebrity
            // candidate remains the same
        }
        
        // Step 2: Verify the candidate
        // Celebrity must know nobody and everyone must know celebrity
        
        // Check if candidate knows anyone
        for (int i = 0; i < n; i++) {
            if (matrix[candidate][i] == 1) {
                return -1; // Celebrity knows someone, invalid
            }
        }
        
        // Check if everyone knows the candidate
        for (int i = 0; i < n; i++) {
            if (i != candidate && matrix[i][candidate] == 0) {
                return -1; // Someone doesn't know candidate, invalid
            }
        }
        
        return candidate;
    }
    
    // Helper method to create matrix from dynamic input
    public static int[][] createMatrix(Scanner sc) {
        System.out.print("Enter number of people: ");
        int n = sc.nextInt();
        
        int[][] matrix = new int[n][n];
        
        System.out.println("\nEnter the 'knows' matrix (0 or 1):");
        System.out.println("(If person i knows person j, enter 1, else 0)");
        
        for (int i = 0; i < n; i++) {
            System.out.println("Person " + i + " knows:");
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matrix[i][j] = 0; // Person doesn't know themselves
                    System.out.print("  Person " + j + " (self): 0 (auto)\n");
                } else {
                    System.out.print("  Person " + j + ": ");
                    matrix[i][j] = sc.nextInt();
                }
            }
        }
        
        return matrix;
    }
    
    // Helper method to print matrix
    public static void printMatrix(int[][] matrix) {
        int n = matrix.length;
        
        System.out.println("\n=== Knows Matrix ===");
        System.out.print("     ");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + i + " ");
        }
        System.out.println();
        
        for (int i = 0; i < n; i++) {
            System.out.print("P" + i + " [ ");
            for (int j = 0; j < n; j++) {
                System.out.print(" " + matrix[i][j] + " ");
            }
            System.out.println("]");
        }
    }
    
    // Helper method to explain result
    public static void explainResult(int[][] matrix, int celebrity) {
        if (celebrity == -1) {
            System.out.println("\n❌ No celebrity found!");
            return;
        }
        
        int n = matrix.length;
        System.out.println("\n✓ Celebrity found: Person " + celebrity);
        System.out.println("\nVerification:");
        
        // Show that celebrity knows nobody
        System.out.print("  Person " + celebrity + " knows: ");
        boolean knowsNobody = true;
        for (int i = 0; i < n; i++) {
            if (matrix[celebrity][i] == 1) {
                knowsNobody = false;
                System.out.print(i + " ");
            }
        }
        if (knowsNobody) {
            System.out.println("Nobody ✓");
        } else {
            System.out.println("(Invalid)");
        }
        
        // Show who knows the celebrity
        System.out.print("  Everyone knows Person " + celebrity + ": ");
        for (int i = 0; i < n; i++) {
            if (i != celebrity) {
                System.out.print(i + "→" + celebrity + "=" + matrix[i][celebrity] + " ");
            }
        }
        System.out.println("✓");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Celebrity Problem Solver ===\n");
        System.out.println("Celebrity Definition:");
        System.out.println("  - Celebrity knows nobody");
        System.out.println("  - Everyone knows the celebrity\n");
        
        // Option to use predefined test cases or custom input
        System.out.print("Use test case? (1=Yes, 0=Custom): ");
        int useTest = sc.nextInt();
        
        int[][] matrix;
        
        if (useTest == 1) {
            // Predefined test case
            System.out.println("\nUsing Test Case: 4 people, Celebrity = Person 1");
            matrix = new int[][] {
                {0, 1, 1, 0},  // Person 0 knows 1, 2
                {0, 0, 0, 0},  // Person 1 knows nobody (Celebrity)
                {1, 1, 0, 0},  // Person 2 knows 0, 1
                {0, 1, 0, 0}   // Person 3 knows 1
            };
        } else {
            // Custom input
            matrix = createMatrix(sc);
        }
        
        // Print the matrix
        printMatrix(matrix);
        
        // Find celebrity
        System.out.println("\n=== Finding Celebrity ===");
        int celebrity = findCelebrity(matrix);
        
        // Explain result
        explainResult(matrix, celebrity);
        
        // Option to run again
        System.out.print("\nRun again? (1=Yes, 0=No): ");
        int again = sc.nextInt();
        if (again == 1) {
            main(args);
        } else {
            System.out.println("\nThank you!");
            sc.close();
        }
    }
}
