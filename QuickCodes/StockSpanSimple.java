package QuickCodes;

import java.util.Scanner;
import java.util.Stack;

public class StockSpanSimple {
    
    // Calculate stock span using stack
    public static int[] calculateSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            // Remove days with price <= current day
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }
            
            // Calculate span
            if (stack.isEmpty()) {
                span[i] = i + 1;  // All previous days
            } else {
                span[i] = i - stack.peek();  // Days since last higher price
            }
            
            // Push current day index
            stack.push(i);
        }
        
        return span;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Stock Span Problem ===\n");
        
        // Input
        System.out.print("Enter stock prices (space separated): ");
        String input = sc.nextLine();
        String[] tokens = input.split(" ");
        
        int[] prices = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            prices[i] = Integer.parseInt(tokens[i]);
        }
        
        // Calculate span
        int[] span = calculateSpan(prices);
        
        // Output
        System.out.println("\nDay    Price    Span");
        System.out.println("------------------------");
        for (int i = 0; i < prices.length; i++) {
            System.out.printf("%-6d %-8d %d\n", (i + 1), prices[i], span[i]);
        }
        
        System.out.print("\nSpan array: ");
        for (int i = 0; i < span.length; i++) {
            System.out.print(span[i]);
            if (i < span.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
        
        sc.close();
    }
}
