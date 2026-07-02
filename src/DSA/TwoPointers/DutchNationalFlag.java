package DSA.TwoPointers;


import java.util.Arrays;

// https://leetcode.com/problems/sort-colors/description/
public class DutchNationalFlag {

    static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    static public void sortColors(int[] nums) {

        int left = 0;
        int mid = 0;
        int right = nums.length - 1;

        while (mid <= right) {

            if (nums[mid] == 0) {
                swap(nums, left, mid);
                left++;
                mid++;

            } else if (nums[mid] == 1) {
                mid++;

            } else {
                swap(nums, mid, right);
                right--;
            }
        }

    }


    static void main(String[] args) {

        int[] nums = {2,0,2,1,1,0};
        System.out.println(Arrays.toString(nums));
        sortColors(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] {2,0,1};
        System.out.println();
        System.out.println(Arrays.toString(nums));
        sortColors(nums);
        System.out.println(Arrays.toString(nums));

    }

}
