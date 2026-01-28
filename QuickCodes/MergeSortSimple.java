package QuickCodes;

import java.util.Scanner;

public class MergeSortSimple {
    
    // Merge sort method
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // Find middle
            int mid = (left + right) / 2;
            
            // Sort first half
            mergeSort(arr, left, mid);
            
            // Sort second half
            mergeSort(arr, mid + 1, right);
            
            // Merge both halves
            merge(arr, left, mid, right);
        }
    }
    
    // Merge two sorted parts
    public static void merge(int[] arr, int left, int mid, int right) {
        // Create temp arrays
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < n2; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }
        
        // Merge temp arrays back
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
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
        
        System.out.println("=== Merge Sort ===\n");
        
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
        
        // Sort
        mergeSort(arr, 0, arr.length - 1);
        
        // Print sorted
        System.out.print("Sorted: ");
        printArray(arr);
        
        sc.close();
    }
}
