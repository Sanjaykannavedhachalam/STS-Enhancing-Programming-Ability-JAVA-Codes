import java.util.Stack;

public class CelebrityProblem {
    public static int findCelebrity(int[][] matrix){
        int n=matrix.length;
        Stack<Integer> stk = new Stack<>();
        for (int i=0;i<n;i++){
            stk.push(i);
        }

        while(stk.size()>1){
            int a = stk.pop();
            int b = stk.pop();
            
            if(matrix[a][b]==1){
                stk.push(b);
            }
            else{
                stk.push(a);
            }
        }

        int celebrity=stk.pop();
        for(int i=0;i<n;i++){
            if(i!= celebrity && (matrix[celebrity][i]==1 || matrix[i][celebrity]==0)){
                return -1;
            }
        }
        return celebrity;

    }
    public static void main(String[] args) {
        // Test Case 1: Celebrity exists (Person 1)
        int n1 = 4;
        int[][] matrix1 = {
            {0, 1, 1, 0},  // Person 0 knows 1, 2
            {0, 0, 0, 0},  // Person 1 knows nobody
            {1, 1, 0, 0},  // Person 2 knows 0, 1
            {0, 1, 0, 0}   // Person 3 knows 1
        };
        
        System.out.println("=== Test Case 1 ===");
        
        int celebrity1 = findCelebrity(matrix1);
        if (celebrity1 == -1) {
            System.out.println("\nNo celebrity found!");
        } else {
            System.out.println("\nCelebrity found: Person " + celebrity1);
        }

    
}
}



/* 
public class CelebrityProblem {
    
    // Method to check if person a knows person b
    // This simulates the API call - in real problem, this would be given
    private static boolean knows(int a, int b, int[][] matrix) {
        return matrix[a][b] == 1;
    }
    
    // Optimal solution using Stack - O(n) time, O(n) space
    public static int findCelebrity(int[][] matrix, int n) {
        // Step 1: Push all people onto the stack
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
        
        // Step 2: Eliminate non-celebrities
        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();
            
            if (knows(a, b, matrix)) {
                // If a knows b, then a cannot be celebrity
                // Push b back as potential celebrity
                stack.push(b);
            } else {
                // If a doesn't know b, then b cannot be celebrity
                // Push a back as potential celebrity
                stack.push(a);
            }
        }
        
        // Step 3: Verify the candidate
        int candidate = stack.pop();
        
        // Check if candidate knows anyone
        for (int i = 0; i < n; i++) {
            if (i != candidate && knows(candidate, i, matrix)) {
                return -1; // Candidate knows someone, so not a celebrity
            }
        }
        
        // Check if everyone knows the candidate
        for (int i = 0; i < n; i++) {
            if (i != candidate && !knows(i, candidate, matrix)) {
                return -1; // Someone doesn't know candidate, so not a celebrity
            }
        }
        
        return candidate;
    }
    
    // Alternative optimal solution without extra space - O(n) time, O(1) space
    public static int findCelebrityOptimized(int[][] matrix, int n) {
        // Step 1: Find potential celebrity using two pointers
        int candidate = 0;
        
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i, matrix)) {
                // If candidate knows i, candidate cannot be celebrity
                candidate = i;
            }
            // If candidate doesn't know i, then i cannot be celebrity
        }
        
        // Step 2: Verify the candidate
        for (int i = 0; i < n; i++) {
            if (i != candidate) {
                // Celebrity should not know anyone
                if (knows(candidate, i, matrix)) {
                    return -1;
                }
                // Everyone should know the celebrity
                if (!knows(i, candidate, matrix)) {
                    return -1;
                }
            }
        }
        
        return candidate;
    }
    
    // Helper method to print the matrix
    private static void printMatrix(int[][] matrix, int n) {
        System.out.println("\nKnows Matrix:");
        System.out.print("    ");
        for (int i = 0; i < n; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        for (int i = 0; i < n; i++) {
            System.out.print(i + " [ ");
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("]");
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: Celebrity exists (Person 1)
        int n1 = 4;
        int[][] matrix1 = {
            {0, 1, 1, 0},  // Person 0 knows 1, 2
            {0, 0, 0, 0},  // Person 1 knows nobody
            {1, 1, 0, 0},  // Person 2 knows 0, 1
            {0, 1, 0, 0}   // Person 3 knows 1
        };
        
        System.out.println("=== Test Case 1 ===");
        printMatrix(matrix1, n1);
        
        int celebrity1 = findCelebrity(matrix1, n1);
        if (celebrity1 == -1) {
            System.out.println("\nNo celebrity found!");
        } else {
            System.out.println("\nCelebrity found: Person " + celebrity1);
        }
        
        int celebrity1_opt = findCelebrityOptimized(matrix1, n1);
        System.out.println("Optimized method: Person " + celebrity1_opt);
        
        // Test Case 2: No celebrity (multiple people know nobody)
        int n2 = 3;
        int[][] matrix2 = {
            {0, 0, 0},  // Person 0 knows nobody
            {0, 0, 0},  // Person 1 knows nobody
            {1, 0, 0}   // Person 2 knows 0
        };
        
        System.out.println("\n\n=== Test Case 2 ===");
        printMatrix(matrix2, n2);
        
        int celebrity2 = findCelebrity(matrix2, n2);
        if (celebrity2 == -1) {
            System.out.println("\nNo celebrity found!");
        } else {
            System.out.println("\nCelebrity found: Person " + celebrity2);
        }
        
        // Test Case 3: No celebrity (everyone knows someone)
        int n3 = 3;
        int[][] matrix3 = {
            {0, 1, 0},  // Person 0 knows 1
            {0, 0, 1},  // Person 1 knows 2
            {1, 0, 0}   // Person 2 knows 0
        };
        
        System.out.println("\n\n=== Test Case 3 ===");
        printMatrix(matrix3, n3);
        
        int celebrity3 = findCelebrity(matrix3, n3);
        if (celebrity3 == -1) {
            System.out.println("\nNo celebrity found!");
        } else {
            System.out.println("\nCelebrity found: Person " + celebrity3);
        }
        
        // Test Case 4: Celebrity at position 0
        int n4 = 3;
        int[][] matrix4 = {
            {0, 0, 0},  // Person 0 knows nobody
            {1, 0, 0},  // Person 1 knows 0
            {1, 0, 0}   // Person 2 knows 0
        };
        
        System.out.println("\n\n=== Test Case 4 ===");
        printMatrix(matrix4, n4);
        
        int celebrity4 = findCelebrity(matrix4, n4);
        if (celebrity4 == -1) {
            System.out.println("\nNo celebrity found!");
        } else {
            System.out.println("\nCelebrity found: Person " + celebrity4);
        }
    }
}
*/