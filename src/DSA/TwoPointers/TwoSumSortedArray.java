package DSA.TwoPointers;

import java.util.Arrays;


// 5. https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/

public class TwoSumSortedArray {

    public static int[] twoSum(int[] nums, int target) {

        int sum;
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            sum = nums[left] + nums[right];

            if (sum == target) {
                return new int[] {left, right};
                // return new int[] {left+1, right+1}; for 1-indexed array
            }

            if (sum < target) {
                left ++;
            } else {
                right--;
            }

        }

        return new int[]{};
    }

    static void main(String[] args) {
        int[] arr = {2,7,11,15};
        int target = 18;

        System.out.println("\n" + Arrays.toString(arr) + " Target: " + target);
        int[] ans = twoSum(arr, target);
        System.out.println(Arrays.toString(ans));
        System.out.println(arr[ans[0]] + " + " + arr[ans[1]] + " = " + target);

        // --------------------------------------------------------------------

        arr = new int[]{2, 7, 11, 15};
        target = 9;

        System.out.println("\n" + Arrays.toString(arr) + " Target: " + target);
        ans = twoSum(arr, target);
        System.out.println(Arrays.toString(ans));
        System.out.println(arr[ans[0]] + " + " + arr[ans[1]] + " = " + target);

        // --------------------------------------------------------------------

        arr = new int[]{2, 3, 4};
        target = 6;

        System.out.println("\n" + Arrays.toString(arr) + " Target: " + target);
        ans = twoSum(arr, target);
        System.out.println(Arrays.toString(ans));
        System.out.println(arr[ans[0]] + " + " + arr[ans[1]] + " = " + target);
    }

}
