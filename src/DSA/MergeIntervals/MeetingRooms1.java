package DSA.MergeIntervals;

import java.util.Arrays;
import java.util.Comparator;


// 4. https://leetcode.com/problems/meeting-rooms/description/

public class MeetingRooms1 {

    static boolean canAttendMeetings(int[][] intervals) {

        //Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        System.out.println("Sorted intervals are: " + Arrays.deepToString(intervals));

        for (int i=1; i<intervals.length; i++) {

            // previous.end > current.start
            if (intervals[i-1][1] > intervals[i][0]) {
                return false;
            }

        }

        return true;
    }

    static void main(String[] args) {

        int[][] intervals = {{8,10}, {1,3}, {2,6}, {15,18}};
        System.out.println();
        System.out.println("Initial intervals are: " + Arrays.deepToString(intervals));
        System.out.println("All meetings can be attended?? - " + canAttendMeetings(intervals));


        intervals = new int[][] {{4,6}, {15,18}, {2,3}, {1,2}};
        System.out.println();
        System.out.println("Initial intervals are: " + Arrays.deepToString(intervals));
        System.out.println("All meetings can be attended?? - " + canAttendMeetings(intervals));

    }

}
