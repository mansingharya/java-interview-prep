package DSA.Hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


// 1. https://leetcode.com/problems/contains-duplicate/description/

public class ContainsDuplicate {

    public static boolean containDuplicate(int [] nums) {

        Set<Integer> numSet = new HashSet<>();

        for (int num : nums) {

            if (numSet.contains(num)) {
                return true;
            }

            numSet.add(num);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println("\n" + Arrays.toString(nums) + " contains duplicates?? " + containDuplicate(nums));


        nums = new int[] {1, 2, 3, 4};
        System.out.println("\n" + Arrays.toString(nums) + " contains duplicates?? " + containDuplicate(nums));

        nums = new int[] {1, 1, 2, 3};
        System.out.println("\n" + Arrays.toString(nums) + " contains duplicates?? " + containDuplicate(nums));
    }
}
