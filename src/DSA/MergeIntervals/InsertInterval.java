package DSA.MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// 2. https://leetcode.com/problems/insert-interval/description/

public class InsertInterval {

    static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ansList = new ArrayList<>();

        int i = 0;

        // No overlap before new interval --> Simply add interval.
        // current.end < new.start --> Keep Going
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            ansList.add(intervals[i]);
            i++;
        }

        // Merge overlapping intervals.
        // current.start <= new.end --> continue merge overlapping
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        ansList.add(newInterval);

        // Add remaining intervals.
        while (i < intervals.length) {
            ansList.add(intervals[i]);
            i++;
        }

        return ansList.toArray(new int[ansList.size()][]);
    }


    static void main(String[] args) {

        int[][] intervals = {{1,3},{6,9}};
        int[] newInterval = {2,5};

        System.out.println();
        System.out.println("Existing Intervals: " + Arrays.deepToString(intervals));
        System.out.println("Insert New Interval: " + Arrays.toString(newInterval));
        int [][] ans = insert(intervals, newInterval);
        System.out.println("Final Intervals after insertion: " + Arrays.deepToString(ans));

    }

}
