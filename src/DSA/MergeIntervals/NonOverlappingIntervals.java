package DSA.MergeIntervals;

import java.util.Arrays;
import java.util.Comparator;


// https://leetcode.com/problems/non-overlapping-intervals/

public class NonOverlappingIntervals {

    static public int eraseOverlapIntervals(int[][] intervals) {
        int min = 0;

        // Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        System.out.println("Current Sorted Intervals: " + Arrays.deepToString(intervals));

        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] < prevEnd) {
                min++;

            } else {
                prevEnd = intervals[i][1];
            }

        }

        return min;
    }


    static void main(String[] args) {

        int[][] intervals = new int[][] {{1,3},{1,2},{2,3},{3,4}};
        System.out.println();
        System.out.println("Current Intervals: " + Arrays.deepToString(intervals));
        System.out.println("Minimum intervals to remove so that no intervals overlap: " + eraseOverlapIntervals(intervals));


        intervals = new int[][] {{1,100},{2,3},{3,4}};
        System.out.println();
        System.out.println("Current Intervals: " + Arrays.deepToString(intervals));
        System.out.println("Minimum intervals to remove so that no intervals overlap: " + eraseOverlapIntervals(intervals));


        intervals = new int[][] {{1,2},{1,2},{1,2}};
        System.out.println();
        System.out.println("Current Intervals: " + Arrays.deepToString(intervals));
        System.out.println("Minimum intervals to remove so that no intervals overlap: " + eraseOverlapIntervals(intervals));


        intervals = new int[][] {{1,2},{2,3},{3,4}};
        System.out.println();
        System.out.println("Current Intervals: " + Arrays.deepToString(intervals));
        System.out.println("Minimum intervals to remove so that no intervals overlap: " + eraseOverlapIntervals(intervals));

    }

}
