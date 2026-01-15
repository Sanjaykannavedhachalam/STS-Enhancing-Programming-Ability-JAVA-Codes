import java.util.Stack;

public class MinimumStack {
    
    // Main stack to store all elements
    private Stack<Integer> mainStack;
    
    // Minimum stack to track minimum elements
    private Stack<Integer> minStack;
    
    // Constructor to initialize both stacks
    public MinimumStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }
    
    // Push operation - O(1)
    public void push(int value) {
        // Always push to main stack
        mainStack.push(value);
        
        // Only push to min stack if value is smaller or equal to current min
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }
    
    // Pop operation - O(1)
    public void pop() {
        if (!mainStack.isEmpty()) {
            // Check if top element of main stack equals top of min stack
            if (mainStack.peek().equals(minStack.peek())) {
                minStack.pop();
            }
            mainStack.pop();
        }
    }
    
    // Get top element - O(1)
    public int top() {
        if (mainStack.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        return mainStack.peek();
    }
    
    // Get minimum element - O(1)
    public int getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        // Simply peek at the top of minimum stack
        return minStack.peek();
    }
    
    // Check if stack is empty
    public boolean isEmpty() {
        return mainStack.isEmpty();
    }
    
    // Get size of stack
    public int size() {
        return mainStack.size();
    }
    
    // Display both stacks for debugging
    public void display() {
        System.out.println("Main Stack: " + mainStack);
        System.out.println("Min Stack:  " + minStack);
    }
    
    public static void main(String[] args) {
        MinimumStack stack = new MinimumStack();
        
        System.out.println("=== Test Case 1: Basic Operations ===");
        System.out.println("Push: 5");
        stack.push(5);
        stack.display();
        System.out.println("Current Min: " + stack.getMin());
        
        System.out.println("\nPush: 3");
        stack.push(3);
        stack.display();
        System.out.println("Current Min: " + stack.getMin());
        
        System.out.println("\nPush: 7");
        stack.push(7);
        stack.display();
        System.out.println("Current Min: " + stack.getMin());
        
        System.out.println("\nPush: 2");
        stack.push(2);
        stack.display();
        System.out.println("Current Min: " + stack.getMin());
        
        System.out.println("\nPush: 8");
        stack.push(8);
        stack.display();
        System.out.println("Current Min: " + stack.getMin());
        
        System.out.println("\n--- Popping elements ---");
        System.out.println("Popped: " + stack.top());
        stack.pop();
        System.out.println("Current Min: " + stack.getMin());
        stack.display();
        
        System.out.println("\nPopped: " + stack.top());
        stack.pop();
        System.out.println("Current Min: " + stack.getMin());
        stack.display();
        
        System.out.println("\nPopped: " + stack.top());
        stack.pop();
        System.out.println("Current Min: " + stack.getMin());
        stack.display();
        
        // Test Case 2: Duplicate minimums
        System.out.println("\n\n=== Test Case 2: Duplicate Minimums ===");
        MinimumStack stack2 = new MinimumStack();
        
        int[] values = {3, 1, 4, 1, 5, 1, 2};
        for (int val : values) {
            System.out.println("Push: " + val);
            stack2.push(val);
            System.out.println("Current Min: " + stack2.getMin());
        }
        
        System.out.println("\n--- Popping all elements ---");
        while (!stack2.isEmpty()) {
            int popped = stack2.top();
            stack2.pop();
            System.out.println("Popped: " + popped + ", Current Min: " + 
                             (stack2.isEmpty() ? "N/A" : stack2.getMin()));
        }
        
        // Test Case 3: Decreasing sequence
        System.out.println("\n\n=== Test Case 3: Decreasing Sequence ===");
        MinimumStack stack3 = new MinimumStack();
        
        System.out.println("Pushing: 10, 9, 8, 7, 6");
        stack3.push(10);
        System.out.println("Min: " + stack3.getMin());
        stack3.push(9);
        System.out.println("Min: " + stack3.getMin());
        stack3.push(8);
        System.out.println("Min: " + stack3.getMin());
        stack3.push(7);
        System.out.println("Min: " + stack3.getMin());
        stack3.push(6);
        System.out.println("Min: " + stack3.getMin());
        
        stack3.display();
        
        // Test Case 4: Increasing sequence
        System.out.println("\n\n=== Test Case 4: Increasing Sequence ===");
        MinimumStack stack4 = new MinimumStack();
        
        System.out.println("Pushing: 1, 2, 3, 4, 5");
        stack4.push(1);
        System.out.println("Min: " + stack4.getMin());
        stack4.push(2);
        System.out.println("Min: " + stack4.getMin());
        stack4.push(3);
        System.out.println("Min: " + stack4.getMin());
        stack4.push(4);
        System.out.println("Min: " + stack4.getMin());
        stack4.push(5);
        System.out.println("Min: " + stack4.getMin());
        
        stack4.display();
        
        // Test Case 5: Operations sequence
        System.out.println("\n\n=== Test Case 5: Mixed Operations ===");
        MinimumStack stack5 = new MinimumStack();
        
        stack5.push(15);
        System.out.println("Push 15, Min: " + stack5.getMin());
        
        stack5.push(10);
        System.out.println("Push 10, Min: " + stack5.getMin());
        
        stack5.push(20);
        System.out.println("Push 20, Min: " + stack5.getMin());
        
        stack5.push(5);
        System.out.println("Push 5, Min: " + stack5.getMin());
        
        System.out.println("Top: " + stack5.top());
        int popped1 = stack5.top();
        stack5.pop();
        System.out.println("Pop: " + popped1 + ", Min: " + stack5.getMin());
        int popped2 = stack5.top();
        stack5.pop();
        System.out.println("Pop: " + popped2 + ", Min: " + stack5.getMin());
        
        stack5.push(8);
        System.out.println("Push 8, Min: " + stack5.getMin());
        
        stack5.display();
    }
}
