import java.util.Scanner;

public class Celebrity {

    int celebrity(int[][] M, int n) {
        int a = 0, b = n - 1;

        while (a < b) {
            if (M[a][b] == 1) a++;
            else b--;
        }

        for (int i = 0; i < n; i++) {
            if (i != a && (M[a][i] == 1 || M[i][a] == 0))
                return -1;
        }
        return a;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Celebrity Problem Solver ===\n");
        System.out.println("Celebrity Definition:");
        System.out.println("  - Celebrity knows nobody");
        System.out.println("  - Everyone knows the celebrity\n");
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
        Celebrity celebFinder = new Celebrity();
        int celeb = celebFinder.celebrity(matrix, n);   
        if (celeb == -1) {
            System.out.println("\n❌ No celebrity found!");
        } else {
            System.out.println("\n✓ Celebrity found: Person " + celeb);
        }
        sc.close();
    }
}
