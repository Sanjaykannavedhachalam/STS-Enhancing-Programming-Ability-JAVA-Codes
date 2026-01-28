package QuickCodes;

import java.util.Scanner;
import java.util.Stack;

public class MinimumStackSimple {
    
    Stack<Integer> mainStack;
    Stack<Integer> minStack;
    
    public MinimumStackSimple() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }
    
    // Push element
    public void push(int value) {
        mainStack.push(value);
        
        // Update min stack
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }
    
    // Pop element
    public void pop() {
        if (mainStack.isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        
        int popped = mainStack.pop();
        
        // Update min stack
        if (popped == minStack.peek()) {
            minStack.pop();
        }
        
        System.out.println("Popped: " + popped);
    }
    
    // Get top element
    public void top() {
        if (mainStack.isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        System.out.println("Top: " + mainStack.peek());
    }
    
    // Get minimum
    public void getMin() {
        if (minStack.isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        System.out.println("Minimum: " + minStack.peek());
    }
    
    // Display stack
    public void display() {
        if (mainStack.isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        System.out.println("Main Stack: " + mainStack);
        System.out.println("Min Stack: " + minStack);
        System.out.println("Current Min: " + minStack.peek());
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MinimumStackSimple stack = new MinimumStackSimple();
        
        System.out.println("=== Minimum Stack ===\n");
        System.out.println("Operations:");
        System.out.println("1. Push");
        System.out.println("2. Pop");
        System.out.println("3. Top");
        System.out.println("4. Get Minimum");
        System.out.println("5. Display");
        System.out.println("6. Exit\n");
        
        while (true) {
            System.out.print("\nEnter operation (1-6): ");
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter value to push: ");
                    int value = sc.nextInt();
                    stack.push(value);
                    System.out.println("Pushed: " + value);
                    break;
                    
                case 2:
                    stack.pop();
                    break;
                    
                case 3:
                    stack.top();
                    break;
                    
                case 4:
                    stack.getMin();
                    break;
                    
                case 5:
                    stack.display();
                    break;
                    
                case 6:
                    System.out.println("Exit!");
                    sc.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
