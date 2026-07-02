package DSA.SlidingWindow;

import java.util.Arrays;

public class MaximumSumSubarrayOfSizeK {

    static public int maxSumSubarray(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }

        int left = 0;
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int right = 0; right < nums.length; right++) {

            currentSum += nums[right];

            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, currentSum);

                currentSum -= nums[left];
                left++;
            }

        }

        return maxSum;
    }

    static void main(String[] args) {

        int[] nums = {2,1,5,1,3,2};
        int k = 3;
        System.out.println();
        System.out.println(Arrays.toString(nums));
        System.out.println(maxSumSubarray(nums, k));

        nums = new int[] {2,1,5,1,3,2,10};
        k = 3;
        System.out.println();
        System.out.println(Arrays.toString(nums));
        System.out.println(maxSumSubarray(nums, k));

        nums = new int[] {15,2,1,5,1,3,2,10};
        k = 3;
        System.out.println();
        System.out.println(Arrays.toString(nums));
        System.out.println(maxSumSubarray(nums, k));

    }

}
