

public class StackPermutation {
    public static boolean isStackPermutation(int[] input, int[] output) {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        int j = 0;

        for (int i = 0; i < input.length; i++) {
            stack.push(input[i]);

            while (!stack.isEmpty() && stack.peek() == output[j]) {
                stack.pop();
                j++;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5};
        int[] output = {4, 5, 3, 2, 1};

        if (isStackPermutation(input, output)) {
            System.out.println("The output sequence is a valid stack permutation of the input sequence.");
        } else {
            System.out.println("The output sequence is NOT a valid stack permutation of the input sequence.");
        }
    }

}
