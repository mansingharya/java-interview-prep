package DSA.Hashing;

import java.util.*;


// 5. https://leetcode.com/problems/top-k-frequent-elements/description/

public class TopKFrequentElements {

    public static int[] topKFrequent(int [] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int n : nums) {
            freq.merge(n , 1, Integer::sum);
        }

        List<Integer> values = new ArrayList<>(freq.keySet());

        values.sort((a, b) -> Integer.compare(freq.get(b), freq.get(a)));

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = values.get(i);
        }

        return result;
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));

        nums = new int[] {1, 1, 1, 2, 2, 3, 3, 3, 5, 5, 5, 5, 5};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));
    }
}
