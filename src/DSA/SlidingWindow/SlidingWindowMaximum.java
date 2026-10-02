package DSA.SlidingWindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;


// 10. https://leetcode.com/problems/sliding-window-maximum/description/

public class SlidingWindowMaximum {

    static int[] maxSlidingWindow(int[] nums, int k) {

        int[] result = new int[nums.length - k + 1];

        // To Store Index of Array in Descending Order
        Deque<Integer> dq = new LinkedList<>();

        int resultIndex = 0;
        for (int right = 0; right < nums.length; right++) {

            // Remove indices out of current window
            if ( !dq.isEmpty() && dq.peekFirst() <= right - k) {
                dq.pollFirst();
            }

            // Remove smaller values from the back of deque
            while ( !dq.isEmpty() && nums[dq.peekLast()] < nums[right]) {
                dq.pollLast();
            }

            // Add current index
            dq.offerLast(right);


            // Add to result once the first window is formed
            if (right >= k-1 && !dq.isEmpty()) {
                result[resultIndex++] = nums[dq.peekFirst()];
            }
        }

        return  result;
    }

    static void main(String[] args) {

        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        System.out.println();
        System.out.println("Nums: " + Arrays.toString(nums) + "  K: " + k);
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));

        nums = new int[] {4,0,-1,3,5,3,6,8};
        k = 3;
        System.out.println();
        System.out.println("Nums: " + Arrays.toString(nums) + "  K: " + k);
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));

        nums = new int[] {4};
        k = 1;
        System.out.println();
        System.out.println("Nums: " + Arrays.toString(nums) + "  K: " + k);
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));

    }

}
