import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // Remove indices that are out of the current window
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove elements smaller than the current element from the deque
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // Add the current element's index to the deque
            deque.offerLast(i);

            // If we have filled at least one window, record the maximum
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
    public static void main(String[] args) {
        MaxSlidingWindow solution = new MaxSlidingWindow();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] result = solution.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(result)); // Output: [3,3,5,5,6,7]
    }
    
}

//import java.util.ArrayList;
//import java.util.*;
/* 
class MaxSlidingWindow {

    // Method to find the maximum for each
    // and every contiguous subarray of size k.
    static ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        int n = arr.length;

        // to store the results
        ArrayList<Integer> res = new ArrayList<Integer>();
      
        for (int i = 0; i <= n - k; i++) {
      
            // Find maximum of subarray beginning
            // with arr[i]
            int max = arr[i];
            for (int j = 1; j < k; j++) {
                if (arr[i + j] > max)
                    max = arr[i + j];
            }
            res.add(max);
        }
      
        return res;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };
        int k = 3;
        ArrayList<Integer> res = maxOfSubarrays(arr, k);
        for (int maxVal : res) {
            System.out.print(maxVal + " ");
        }
    }
}
*/

