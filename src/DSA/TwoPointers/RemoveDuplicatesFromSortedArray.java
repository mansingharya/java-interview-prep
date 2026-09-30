package DSA.TwoPointers;

import java.util.Arrays;


// 2. https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/

public class RemoveDuplicatesFromSortedArray {

    public static int removeDuplicates1(int[] nums) {
        int size = nums.length;

        if (size == 0) {
            return 0;
        }

        int slow = 0;

        for (int fast = 1; fast < size; ++fast) {

            if (nums[slow] != nums[fast]) {
                ++ slow;
                nums[slow] = nums[fast];
            }
        }

        return slow + 1;
    }

    public static int removeDuplicates(int[] nums) {
        int size = nums.length;

        int slow = 0;
        int fast = 1;

        while (fast < size) {

            if (nums[slow] != nums[fast]) {
                slow ++;
                nums[slow] = nums[fast];
            }

            fast ++;
        }

        return slow + 1;
    }

    static void main(String[] args) {

        int[] nums = {1,1,2};
        System.out.println("\nInput Array: " + Arrays.toString(nums));
        System.out.println("Distinct elements are: " + removeDuplicates(nums));
        nums = new int[] {1,1,2};
        System.out.println("Distinct elements are: " + removeDuplicates1(nums));
        System.out.println("Output Array: " + Arrays.toString(nums));


        nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        System.out.println("\nInput Array: " + Arrays.toString(nums));
        System.out.println("Distinct elements are: " + removeDuplicates(nums));
        nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        System.out.println("Distinct elements are: " + removeDuplicates1(nums));
        System.out.println("Output Array: " + Arrays.toString(nums));


        nums = new int[] {1,1,2,2,3,3,3,4,4};
        System.out.println("\nInput Array: " + Arrays.toString(nums));
        System.out.println("Distinct elements are: " + removeDuplicates(nums));
        nums = new int[] {1,1,2,2,3,3,3,4,4};
        System.out.println("Distinct elements are: " + removeDuplicates1(nums));
        System.out.println("Output Array: " + Arrays.toString(nums));
    }
}
