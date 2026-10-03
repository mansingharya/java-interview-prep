package DSA.Hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


// 7.

public class LongestSubarrayWithSumK {

    public static int longestSubarraySumK_UsingTwoPointers(int[] nums, int k) {
        int maxLen = 0;

        int sum = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            while (sum > k) {
                sum -= nums[left];
                left++;
            }
        }

        return maxLen;
    }


    public static int longestSubarraySumK(int[] nums, int k) {
        int maxLen = 0;

        Map<Integer, Integer> firstIdxOfPrefixSum = new HashMap<>();
        firstIdxOfPrefixSum.put(0, -1);

        int len = 0;
        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {

            prefixSum += nums[i];
            int required = prefixSum - k;

            if (firstIdxOfPrefixSum.containsKey(required)) {
                len = i - firstIdxOfPrefixSum.get(required);
                maxLen = Math.max(maxLen, len);
            }

            // Store only the first occurrence.
            if ( !firstIdxOfPrefixSum.containsKey(prefixSum)) {
                firstIdxOfPrefixSum.put(prefixSum, i);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {

        int[] nums = {10, 5, 2, 7, 1, 9};
        int k = 15;
        System.out.println("\nLength of the longest sub-array in " + Arrays.toString(nums) + " which has sum equal to " + k + " is: " + longestSubarraySumK(nums, k));
        System.out.println("Length of the longest sub-array in " + Arrays.toString(nums) + " which has sum equal to " + k + " is: " + longestSubarraySumK_UsingTwoPointers(nums, k));

        nums = new int[] {1, 2, 3, 1, 1, 1, 1, 4, 2, 3};
        k = 3;
        System.out.println("\nLength of the longest sub-array in " + Arrays.toString(nums) + " which has sum equal to " + k + " is: " + longestSubarraySumK(nums, k));
        System.out.println("Length of the longest sub-array in " + Arrays.toString(nums) + " which has sum equal to " + k + " is: " + longestSubarraySumK_UsingTwoPointers(nums, k));

        nums = new int[] {1, 2, 3, 1, 1, 1, 1, 4, 2, 3};
        k = 7;
        System.out.println("\nLength of the longest sub-array in " + Arrays.toString(nums) + " which has sum equal to " + k + " is: " + longestSubarraySumK(nums, k));
        System.out.println("Length of the longest sub-array in " + Arrays.toString(nums) + " which has sum equal to " + k + " is: " + longestSubarraySumK_UsingTwoPointers(nums, k));

    }
}
