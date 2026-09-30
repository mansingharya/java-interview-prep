package DSA.SlidingWindow;

import java.util.Arrays;


// 4. https://leetcode.com/problems/minimum-size-subarray-sum/description/

public class MinimumSizeSubArraySum {

    static int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;

        int left = 0;
        int windowSum = 0;

        for (int right=0; right<nums.length; right++) {

            windowSum += nums[right];

            while (windowSum >= target) {

                minLength = Math.min(minLength, right - left + 1);

                windowSum -= nums[left];

                left++;
            }
        }

        // System.gc();
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }


    static void main(String[] args) {

        int[] nums = {2,3,1,2,4,3};
        int target = 7;
        System.out.println();
        System.out.println("Array - " + Arrays.toString(nums));
        System.out.println("Target - " + target);
        System.out.println("Min Size Of SubArray - " + minSubArrayLen(target, nums));


        nums = new int[] {1,4,4};
        target = 4;
        System.out.println();
        System.out.println("Array - " + Arrays.toString(nums));
        System.out.println("Target - " + target);
        System.out.println("Min Size Of SubArray - " + minSubArrayLen(target, nums));


        nums = new int[] {1,1,1,1,1,1,1,1};
        target = 11;
        System.out.println();
        System.out.println("Array - " + Arrays.toString(nums));
        System.out.println("Target - " + target);
        System.out.println("Min Size Of SubArray - " + minSubArrayLen(target, nums));

    }

}
