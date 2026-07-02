package DSA.TwoPointers;

import java.util.*;


// https://leetcode.com/problems/3sum/description/
public class ThreeSumUnSortedArray {

    static public List<List<Integer>> threeSumBest(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();

        Arrays.sort(nums);

        for (int i=0; i<nums.length; i++) {

            if ( i > 0 && nums[i-1] == nums[i]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    List<Integer> listFound = Arrays.asList(nums[i], nums[left], nums[right]);
                    // Collections.sort(listFound); // We can ignore this as well since we are moving to unique elements.
                    ans.add(listFound);

                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }

                    left++;
                    right--;

                } else if (sum < 0) {
                    left ++;

                } else {
                    right--;
                }
            }
        }

        return new ArrayList<>(ans);
    }

    static public List<List<Integer>> threeSumSecondBest(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();

        for (int i=0; i<nums.length; i++) {

            Set<Integer> valueSet = new HashSet<>();

            for (int j=i+1; j< nums.length; j++) {

                int third = -(nums[i] + nums[j]);

                if (valueSet.contains(third)) {
                    List<Integer> listFound = Arrays.asList(nums[i], nums[j], third);
                    Collections.sort(listFound);
                    ans.add(listFound);
                }

                valueSet.add(nums[j]);
            }
        }

        return new ArrayList<>(ans);
    }


    static void main(String[] args) {

        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println();
        System.out.println(Arrays.toString(nums));
        System.out.println("triplets with sum 0 are : " + threeSumSecondBest(nums));
        System.out.println("triplets with sum 0 are : " + threeSumBest(nums));

        nums = new int[] {0,0,0};
        System.out.println();
        System.out.println(Arrays.toString(nums));
        System.out.println("triplets with sum 0 are : " + threeSumSecondBest(nums));
        System.out.println("triplets with sum 0 are : " + threeSumBest(nums));

    }

}
