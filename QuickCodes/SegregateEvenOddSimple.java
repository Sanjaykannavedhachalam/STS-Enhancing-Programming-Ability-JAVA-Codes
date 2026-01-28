package QuickCodes;

import java.util.Scanner;

public class SegregateEvenOddSimple {
    
    // Method to segregate even and odd numbers
    public static int[] segregate(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        
        // First, add all even numbers
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == 0) {
                result[index] = arr[i];
                index++;
            }
        }
        
        // Then, add all odd numbers
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 != 0) {
                result[index] = arr[i];
                index++;
            }
        }
        
        return result;
    }
    
    // Method to print array
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
        
        System.out.println("=== Segregate Even and Odd Numbers ===\n");
        
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
        
        // Segregate
        int[] result = segregate(arr);
        
        // Print result
        System.out.print("Segregated (Even first, then Odd): ");
        printArray(result);
        
        sc.close();
    }
}
