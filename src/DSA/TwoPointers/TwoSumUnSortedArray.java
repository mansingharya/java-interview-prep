package DSA.TwoPointers;

import java.util.*;


// 4. https://leetcode.com/problems/two-sum/description/

public class TwoSumUnSortedArray {

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> valueToIndexMap = new HashMap<>();

        int x, y;
        int size = nums.length;

        for (int i=0; i<size; ++i) {
            x = nums[i];
            y = target - x;

            if (valueToIndexMap.containsKey(y) && valueToIndexMap.get(y) != i) {
                return new int[] {valueToIndexMap.get(y), i};
            }

            valueToIndexMap.put(x, i);
        }

        return new int[]{};
    }

    static void main(String[] args) {
        int[] arr = {2, 15, 11, 7};
        int target = 18;

        System.out.println("\n" + Arrays.toString(arr) + " Target: " + target);
        int[] ans = twoSum(arr, target);
        System.out.println(Arrays.toString(ans));
        System.out.println(arr[ans[0]] + " + " + arr[ans[1]] + " = " + target);

        // --------------------------------------------------------------------

        arr = new int[] {11, 2, 7, 15};
        target = 9;

        System.out.println("\n" + Arrays.toString(arr) + " Target: " + target);
        ans = twoSum(arr, target);
        System.out.println(Arrays.toString(ans));
        System.out.println(arr[ans[0]] + " + " + arr[ans[1]] + " = " + target);

        // --------------------------------------------------------------------

        arr = new int[] {4, 3, 2};
        target = 6;

        System.out.println("\n" + Arrays.toString(arr) + " Target: " + target);
        ans = twoSum(arr, target);
        System.out.println(Arrays.toString(ans));
        System.out.println(arr[ans[0]] + " + " + arr[ans[1]] + " = " + target);

        // --------------------------------------------------------------------

        arr = new int[] {3, 4};
        target = 7;

        System.out.println("\n" + Arrays.toString(arr) + " Target: " + target);
        ans = twoSum(arr, target);
        System.out.println(Arrays.toString(ans));
        System.out.println(arr[ans[0]] + " + " + arr[ans[1]] + " = " + target);
    }

}
