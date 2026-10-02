package DSA.MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


// 5. https://leetcode.com/problems/meeting-rooms-ii/description/

public class MeetingRooms2 {

    static int minMeetingRoomsEasy(int [][] intervals) {
        ArrayList<Integer> startList = new ArrayList<>(intervals.length);
        ArrayList<Integer> endList = new ArrayList<>(intervals.length);

        for (int[] interval : intervals) {
            startList.add(interval[0]);
            endList.add(interval[1]);
        }

        startList.sort(Comparator.naturalOrder());
        endList.sort(Comparator.naturalOrder());

        int startIdx = 0, endIdx = 0;
        int rooms = 0, maxRooms = 0;

        while (startIdx < intervals.length) {

            // Meeting started, room occupied
            if (startList.get(startIdx) < endList.get(endIdx)) {
                startIdx ++;
                rooms ++;

            } else { // Meeting ends, room gets free
                endIdx ++;
                rooms --;
            }

            maxRooms = Math.max(maxRooms, rooms);
        }

        return maxRooms;
    }


    static int minMeetingRooms(int[][] intervals) {

        ArrayList<Integer> startList = new ArrayList<>();
        ArrayList<Integer> endList = new ArrayList<>();

        for (int[] interval : intervals) {
            startList.add(interval[0]);
            endList.add(interval[1]);
        }

        startList.sort(Comparator.naturalOrder());
        System.out.println("Meeting start list: " + startList);

        endList.sort(Comparator.naturalOrder());
        System.out.println("Meeting  end  list: " + endList);

        int endIdx = 0;
        int rooms = 0;
        for (int startIdx = 0; startIdx < intervals.length; startIdx++) {

            // Meeting Started, Room Needed.
            if (startList.get(startIdx) < endList.get(endIdx)) {
                rooms++;

            } else { // Meeting Ended, Room gets free.
                endIdx++;
            }
        }

        return rooms;
    }


    static void main(String[] args) {

        int[][] intervals = {{8,10}, {1,3}, {2,6}, {15,18}};
        System.out.println();
        System.out.println("Initial intervals are: " + Arrays.deepToString(intervals));
        System.out.println("Minimum meeting rooms required: " + minMeetingRooms(intervals));
        System.out.println("Minimum meeting rooms required: " + minMeetingRoomsEasy(intervals));


        intervals = new int[][] {{4,6}, {15,18}, {2,3}, {1,2}};
        System.out.println();
        System.out.println("Initial intervals are: " + Arrays.deepToString(intervals));
        System.out.println("Minimum meeting rooms required: " + minMeetingRooms(intervals));
        System.out.println("Minimum meeting rooms required: " + minMeetingRoomsEasy(intervals));


        intervals = new int[][] {{1,100},{2,3},{3,4}};
        System.out.println();
        System.out.println("Initial intervals are: " + Arrays.deepToString(intervals));
        System.out.println("Minimum meeting rooms required: " + minMeetingRooms(intervals));
        System.out.println("Minimum meeting rooms required: " + minMeetingRoomsEasy(intervals));


        intervals = new int[][] {{1,2},{1,3},{1,2}};
        System.out.println();
        System.out.println("Initial intervals are: " + Arrays.deepToString(intervals));
        System.out.println("Minimum meeting rooms required: " + minMeetingRooms(intervals));
        System.out.println("Minimum meeting rooms required: " + minMeetingRoomsEasy(intervals));

    }

}
