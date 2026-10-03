package DSA.Hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


// 6. https://leetcode.com/problems/subarray-sum-equals-k/description/

public class SubarraySumEqualsK {

    public static int subarraySum(int [] nums, int k) {
        int count = 0;

        Map<Integer, Integer> prefixSumCountMap = new HashMap<>();
        prefixSumCountMap.put(0, 1); // VV-IMP

        int prefixSum = 0;

        for (int n : nums) {

            prefixSum += n;

            count += prefixSumCountMap.getOrDefault((prefixSum - k), 0);

            prefixSumCountMap.put(prefixSum, prefixSumCountMap.getOrDefault(prefixSum, 0) + 1);

        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;
        System.out.println("\nTotal sub-arrays in " + Arrays.toString(nums) + " which have sum equals to " + k + " are: " + subarraySum(nums, k));

        nums = new int[] {1, 2, 3, -3, 1, 1, 1, 4, 2, -3};
        k = 3;
        System.out.println("\nTotal sub-arrays in " + Arrays.toString(nums) + " which have sum equals to " + k + " are: " + subarraySum(nums, k));

        nums = new int[] {3, -3, 1, 1, 1, 4, 2, -3};
        k = 3;
        System.out.println("\nTotal sub-arrays in " + Arrays.toString(nums) + " which have sum equals to " + k + " are: " + subarraySum(nums, k));
    }
}
