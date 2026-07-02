package DSA.SlidingWindow;

import java.util.HashMap;
import java.util.Map;


// https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/description/
public class MaximumSumOfDistinctSubArraysWithLengthK {

    static long maximumSubarraySum(int [] nums, int k) {
        long maxSum = 0;
        long windowSum = 0;

        int left = 0;
        Map<Integer, Integer> freq = new HashMap<>();

        for (int right=0; right<nums.length; right++) {

            windowSum += nums[right];
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            while (right - left + 1 > k) {

                freq.put(nums[left], freq.get(nums[left]) - 1);

                if (freq.get(nums[left]) == 0) {
                    freq.remove(nums[left]);
                }

                windowSum -= nums[left];
                left++;
            }

            if (right - left + 1 == k && freq.size() == k) {
                maxSum = Math.max(maxSum, windowSum);
            }
        }

        return maxSum;
    }


    static long maximumSubarraySum1(int[] nums, int k) {
        int left = 0, right = 0;
        long windowSum = 0, maxSum = 0;
        Map<Integer, Integer> freq = new HashMap<>();

        while (right < nums.length) {

            // Expand window
            windowSum += nums[right];
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            // Shrink if size exceeds k
            if (right - left + 1 > k) {
                freq.put(nums[left], freq.get(nums[left]) - 1);

                if (freq.get(nums[left]) == 0) {
                    freq.remove(nums[left]);
                }

                windowSum -= nums[left];
                ++ left;
            }

            // Process valid window
            if (right - left + 1 == k && freq.size() == k) {
                maxSum = Math.max(maxSum, windowSum);
            }
            ++ right;
        }

        return maxSum;
    }


    public static void main(String[] args) {
        System.out.println(maximumSubarraySum(new int[]{1, 5, 4, 2, 9, 9, 9}, 3));
        System.out.println(maximumSubarraySum(new int[]{4, 1, 4, 2, 4, 3, 1, 2, 3}, 3));

        System.out.println();
        System.out.println(maximumSubarraySum1(new int[]{1, 5, 4, 2, 9, 9, 9}, 3));
        System.out.println(maximumSubarraySum1(new int[]{4, 1, 4, 2, 4, 3, 1, 2, 3}, 3));
    }

}
