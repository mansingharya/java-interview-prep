package DSA.MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


// https://leetcode.com/problems/merge-intervals/
public class MergeIntervals {

    static public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();

        // Arrays.sort(intervals, (a,b)->Integer.compare(a[0], b[0]));
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        System.out.println(Arrays.deepToString(intervals));

        int[] current = intervals[0];

        for (int i=1; i<intervals.length; i++) {
            if (current[1] >= intervals[i][0]) {
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                result.add(current);
                current = intervals[i];
            }
        }

        // Adding Last Interval
        result.add(current);

        return result.toArray(new int[result.size()][]);
    }

    static void main(String[] args) {

        int[][] intervals = {{8,10}, {1,3}, {2,6}, {15,18}};
        System.out.println(Arrays.deepToString(intervals));
        int [][] ans = merge(intervals);
        System.out.println(Arrays.deepToString(ans));


    }
}
