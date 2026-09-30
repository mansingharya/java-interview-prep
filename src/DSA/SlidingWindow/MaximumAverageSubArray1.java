package DSA.SlidingWindow;

import java.util.Arrays;


// 2. https://leetcode.com/problems/maximum-average-subarray-i/description/

public class MaximumAverageSubArray1 {

    static double findMaxAverage(int[] nums, double k) {
        double maxAvg = Integer.MIN_VALUE;

        int left = 0;
        int sum = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            if (right - left + 1 == k) {
                maxAvg = Math.max(maxAvg, sum / k);

                sum -= nums[left];
                left++;
            }
        }

        return maxAvg;
    }

    static void main(String[] args) {

        int[] nums = {1,12,-5,-6,50,3};
        int k = 4;
        System.out.println();
        System.out.println(Arrays.toString(nums));
        System.out.println(findMaxAverage(nums, k));

        nums = new int[] {2,1,5,1,3,2,10};
        k = 3;
        System.out.println();
        System.out.println(Arrays.toString(nums));
        System.out.println(findMaxAverage(nums, k));

        nums = new int[] {15,2,1,5,1,3,2,10};
        k = 3;
        System.out.println();
        System.out.println(Arrays.toString(nums));
        System.out.println(findMaxAverage(nums, k));

    }
}
