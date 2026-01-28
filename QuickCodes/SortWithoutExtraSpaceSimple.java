package QuickCodes;

import java.util.Scanner;

public class SortWithoutExtraSpaceSimple {
    
    // Sort array in-place (without extra space)
    public static void sort(int[] arr) {
        int n = arr.length;
        
        // Selection sort - sorts in-place
        for (int i = 0; i < n - 1; i++) {
            // Find minimum in remaining array
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Swap minimum with current position
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
    
    // Print array
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Sort Without Extra Space ===\n");
        
        // Input
        System.out.print("Enter elements (space separated): ");
        String input = sc.nextLine();
        String[] tokens = input.split(" ");
        
        int[] arr = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }
        
        // Print original
        System.out.print("\nOriginal: ");
        printArray(arr);
        
        // Sort in-place
        sort(arr);
        
        // Print sorted
        System.out.print("Sorted (in-place): ");
        printArray(arr);
        
        sc.close();
    }
}
