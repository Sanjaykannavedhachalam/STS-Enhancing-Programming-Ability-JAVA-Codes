package QuickCodes;

import java.util.Scanner;
import java.util.Stack;

public class StackPermutationSimple {
    
    // Check if output is valid stack permutation of input
    public static boolean isValidPermutation(int[] input, int[] output) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;  // Index for output array
        
        for (int i = 0; i < input.length; i++) {
            // Push input element to stack
            stack.push(input[i]);
            
            // Pop elements that match output sequence
            while (!stack.isEmpty() && stack.peek() == output[j]) {
                stack.pop();
                j++;
            }
        }
        
        // If stack is empty, all elements matched
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Stack Permutation Checker ===\n");
        
        // Input sequence
        System.out.print("Enter input sequence (space separated): ");
        String inputStr = sc.nextLine();
        String[] inputTokens = inputStr.split(" ");
        
        int[] input = new int[inputTokens.length];
        for (int i = 0; i < inputTokens.length; i++) {
            input[i] = Integer.parseInt(inputTokens[i]);
        }
        
        // Output sequence
        System.out.print("Enter output sequence (space separated): ");
        String outputStr = sc.nextLine();
        String[] outputTokens = outputStr.split(" ");
        
        int[] output = new int[outputTokens.length];
        for (int i = 0; i < outputTokens.length; i++) {
            output[i] = Integer.parseInt(outputTokens[i]);
        }
        
        // Check if valid permutation
        if (input.length != output.length) {
            System.out.println("\nError: Sequences must have same length!");
        } else {
            boolean isValid = isValidPermutation(input, output);
            
            System.out.print("\nInput:  ");
            for (int x : input) System.out.print(x + " ");
            System.out.print("\nOutput: ");
            for (int x : output) System.out.print(x + " ");
            
            System.out.println("\n\nResult: " + (isValid ? "VALID" : "NOT VALID") + " stack permutation");
        }
        
        sc.close();
    }
}
