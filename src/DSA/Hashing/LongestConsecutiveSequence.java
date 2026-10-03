package DSA.Hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


// 8. https://leetcode.com/problems/longest-consecutive-sequence/description/

public class LongestConsecutiveSequence {

    public static int longestConsecutive (int [] nums) {
        int longest = 0;

        Set<Integer> numSet = new HashSet<>();
        for (int n : nums) {
            numSet.add(n);
        }

        for (int n : numSet) {

            if ( !numSet.contains( n - 1)) {

                int len = 1;
                while (numSet.contains(n + 1)) {
                    n ++;
                    len ++;
                }

                longest = Math.max(longest, len);
            }
        }

        return longest;
    }

    public static void main(String[] args) {

        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println("\nLongest Consecutive Sequence in Array: " + Arrays.toString(nums) + " Of Size : " + longestConsecutive(nums));

        nums = new int[] {14, 100, 1, 15, 12, 50, 11, 13};
        System.out.println("\nLongest Consecutive Sequence in Array: " + Arrays.toString(nums) + " Of Size : " + longestConsecutive(nums));
    }
}
