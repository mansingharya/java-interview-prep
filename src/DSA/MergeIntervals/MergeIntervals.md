# Merge Intervals

Merge-intervals problems treat each range as `[start, end]`. Sort by start (or sometimes by end), then walk left-to-right and decide: **merge**, **skip/remove**, or **count overlap**.

**When to use:** overlapping ranges, calendar/meetings, inserting a new range into a sorted list, and “how many rooms / how many to delete” questions.

**Overlap rule (closed intervals):** two intervals `[a, b]` and `[c, d]` overlap when `a <= d && c <= b`. After sorting by start, it is enough to check **`previous.end >= current.start`** (touching counts as merge) or **`previous.end > current.start`** (touching does not overlap — typical for meetings).

---

## Problems (Easy → Hard)

| # | Problem | Difficulty | Source |
|---|---------|------------|--------|
| 1 | [Meeting Rooms](#1-meeting-rooms) | Easy | `MeetingRooms1.java` |
| 2 | [Merge Intervals](#2-merge-intervals) | Medium | `MergeIntervals.java` |
| 3 | [Insert Interval](#3-insert-interval) | Medium | `InsertInterval.java` |
| 4 | [Non-overlapping Intervals](#4-non-overlapping-intervals) | Medium | `NonOverlappingIntervals.java` |
| 5 | [Meeting Rooms II](#5-meeting-rooms-ii) | Medium | `MeetingRooms2.java` |

---

## 1. Meeting Rooms

**LeetCode:** [252. Meeting Rooms](https://leetcode.com/problems/meeting-rooms/)

**Idea:** Sort by start time. If any meeting starts before the previous one ends (`prev.end > curr.start`), a person cannot attend all meetings. Adjacent meetings that only *touch* (`end == start`) are allowed.

```java
static boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    for (int i = 1; i < intervals.length; i++) {
        if (intervals[i - 1][1] > intervals[i][0]) {
            return false; // previous.end > current.start
        }
    }
    return true;
}
```

**Complexity:** O(n log n) time (sort) · O(1) extra space besides sort

---

## 2. Merge Intervals

**LeetCode:** [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)

**Idea:** Sort by start. Keep a running `previous` interval. If `previous.end >= current.start`, they overlap (or touch) — extend `previous.end`. Otherwise emit `previous` and start a new one. Append the last interval after the loop.

```java
static int[][] merge(int[][] intervals) {
    List<int[]> result = new ArrayList<>();
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    int[] previous = intervals[0];

    for (int i = 1; i < intervals.length; i++) {
        if (previous[1] >= intervals[i][0]) {
            previous[1] = Math.max(previous[1], intervals[i][1]);
        } else {
            result.add(previous);
            previous = intervals[i];
        }
    }
    result.add(previous);

    return result.toArray(new int[result.size()][]);
}
```

**Complexity:** O(n log n) time · O(n) space for the output

---

## 3. Insert Interval

**LeetCode:** [57. Insert Interval](https://leetcode.com/problems/insert-interval/)

**Idea:** `intervals` is already sorted and non-overlapping. Walk in **three phases** without a full sort:

1. Copy every interval that ends **before** the new one starts.
2. Merge every interval that overlaps the new one (`current.start <= new.end`).
3. Copy the rest.

```java
static int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> ans = new ArrayList<>();
    int i = 0;

    // 1. No overlap yet — current.end < new.start
    while (i < intervals.length && intervals[i][1] < newInterval[0]) {
        ans.add(intervals[i]);
        i++;
    }

    // 2. Merge overlapping — current.start <= new.end
    while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
        newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
        newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
        i++;
    }
    ans.add(newInterval);

    // 3. Remaining intervals
    while (i < intervals.length) {
        ans.add(intervals[i]);
        i++;
    }

    return ans.toArray(new int[ans.size()][]);
}
```

**Complexity:** O(n) time · O(n) space for the output

---

## 4. Non-overlapping Intervals

**LeetCode:** [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)

**Idea:** Greedy — **sort by end**, not start. Keep the interval that finishes first so later ones have more room. Count how many start before `prevEnd` (those are removals). Touching (`end == start`) is **not** overlap.

```java
static int eraseOverlapIntervals(int[][] intervals) {
    int removals = 0;
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[1])); // sort by end

    int prevEnd = intervals[0][1];

    for (int i = 1; i < intervals.length; i++) {
        if (prevEnd > intervals[i][0]) {
            removals++; // overlap — drop current
        } else {
            prevEnd = intervals[i][1]; // keep current
        }
    }
    return removals;
}
```

**Why sort by end?** Keeping `[1, 100]` over `[2, 3]` would force extra removals. The earliest finish maximizes how many you can keep.

**Complexity:** O(n log n) time · O(1) extra space besides sort

---

## 5. Meeting Rooms II

**LeetCode:** [253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)

**Idea:** Minimum rooms = maximum number of meetings happening at once. Split starts and ends, sort both, then sweep:

- `start < end` → a meeting begins → occupy a room.
- otherwise a meeting has already ended → free a room (advance `endIdx`).

A meeting that starts exactly when another ends can reuse that room (`start < end`, not `<=`).

```java
static int minMeetingRoomsEasy(int[][] intervals) {
    List<Integer> starts = new ArrayList<>(intervals.length);
    List<Integer> ends = new ArrayList<>(intervals.length);

    for (int[] interval : intervals) {
        starts.add(interval[0]);
        ends.add(interval[1]);
    }
    starts.sort(Comparator.naturalOrder());
    ends.sort(Comparator.naturalOrder());

    int startIdx = 0, endIdx = 0, rooms = 0, maxRooms = 0;

    while (startIdx < intervals.length) {
        if (starts.get(startIdx) < ends.get(endIdx)) {
            startIdx++;
            rooms++;          // meeting started
        } else {
            endIdx++;
            rooms--;          // meeting ended
        }
        maxRooms = Math.max(maxRooms, rooms);
    }
    return maxRooms;
}
```

An equivalent form only increments `rooms` when a start happens before the next free end; otherwise it reuses a room by advancing `endIdx`. Peak occupancy is then just the final `rooms` if you never decrement (you only count extra rooms needed):

```java
static int minMeetingRooms(int[][] intervals) {
    // ... same split + sort of starts and ends ...
    int endIdx = 0, rooms = 0;

    for (int startIdx = 0; startIdx < intervals.length; startIdx++) {
        if (starts.get(startIdx) < ends.get(endIdx)) {
            rooms++;          // no free room yet
        } else {
            endIdx++;         // reuse a room that just freed
        }
    }
    return rooms;
}
```

**Complexity:** O(n log n) time · O(n) space

---

## Pattern Cheat Sheet

| Pattern | Sort by | Overlap check | Problems |
|---------|---------|---------------|----------|
| Detect any overlap | start | `prev.end > curr.start` | Meeting Rooms |
| Merge overlapping | start | `prev.end >= curr.start` → extend end | Merge Intervals |
| Insert into sorted list | already sorted | 3 phases: before / merge / after | Insert Interval |
| Min removals (greedy keep) | **end** | `prevEnd > curr.start` → remove | Non-overlapping Intervals |
| Min rooms / max concurrent | starts & ends separately | sweep: start vs next end | Meeting Rooms II |

**Template (merge after sorting by start):**

```java
Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
int[] prev = intervals[0];

for (int i = 1; i < intervals.length; i++) {
    if (prev[1] >= intervals[i][0]) {
        prev[1] = Math.max(prev[1], intervals[i][1]); // merge
    } else {
        // emit prev, then prev = intervals[i]
    }
}
// emit last prev
```
