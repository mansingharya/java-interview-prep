# Two Pointers

Two pointers use two indices (or slow/fast nodes) moving through a sequence — often from opposite ends or at different speeds — to solve problems in **O(n)** time with **O(1)** extra space.

**When to use:** sorted arrays, palindromes, in-place removal, cycle detection, and shrinking/expanding a window from both sides.

---

## Problems (Easy → Hard)

| # | Problem | Difficulty | Source |
|---|---------|------------|--------|
| 1 | [Valid Palindrome](#1-valid-palindrome) | Easy | `StringsValidPalindrome.java` |
| 2 | [Remove Duplicates from Sorted Array](#2-remove-duplicates-from-sorted-array) | Easy | `RemoveDuplicatesFromSortedArray.java` |
| 3 | [Linked List Cycle](#3-linked-list-cycle) | Easy | `LinkedListCycle.java` |
| 4 | [Two Sum (Unsorted)](#4-two-sum-unsorted) | Easy | `TwoSumUnSortedArray.java` |
| 5 | [Two Sum II (Sorted Array)](#5-two-sum-ii-sorted-array) | Medium | `TwoSumSortedArray.java` |
| 6 | [Container With Most Water](#6-container-with-most-water) | Medium | `ContainersWithMostWater.java` |
| 7 | [Sort Colors (Dutch National Flag)](#7-sort-colors-dutch-national-flag) | Medium | `DutchNationalFlag.java` |
| 8 | [3Sum](#8-3sum) | Medium | `ThreeSumUnSortedArray.java` |
| 9 | [Trapping Rain Water](#9-trapping-rain-water) | Hard | `TrappingRainWater.java` |

---

## 1. Valid Palindrome

**LeetCode:** [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)

**Idea:** Place `l` at the start and `r` at the end. Skip non-alphanumeric characters, compare lowercase letters, and move inward.

```java
public static boolean isPalindrome(String s) {
    int l = 0, r = s.length() - 1;

    while (l <= r) {
        while (l <= r && !Character.isLetterOrDigit(s.charAt(l))) l++;
        while (l <= r && !Character.isLetterOrDigit(s.charAt(r))) r--;

        if (l <= r && Character.toLowerCase(s.charAt(l))
                      != Character.toLowerCase(s.charAt(r))) {
            return false;
        }
        l++;
        r--;
    }
    return true;
}
```

**Complexity:** O(n) time · O(1) space

---

## 2. Remove Duplicates from Sorted Array

**LeetCode:** [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)

**Idea:** `slow` marks the last unique element written; `fast` scans ahead. When `nums[fast]` differs, advance `slow` and copy the new value.

```java
public static int removeDuplicates(int[] nums) {
    int slow = 0, fast = 1;

    while (fast < nums.length) {
        if (nums[slow] != nums[fast]) {
            slow++;
            nums[slow] = nums[fast];
        }
        fast++;
    }
    return slow + 1; // length of unique prefix
}
```

**Complexity:** O(n) time · O(1) space

---

## 3. Linked List Cycle

**LeetCode:** [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)

**Idea:** Floyd's tortoise and hare — `slow` moves one step, `fast` moves two. If they meet, a cycle exists.

```java
public static boolean hasCycle(ListNode head) {
    if (head == null) return false;

    ListNode slow = head, fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) return true;
    }
    return false;
}
```

**Complexity:** O(n) time · O(1) space

---

## 4. Two Sum (Unsorted)

**LeetCode:** [1. Two Sum](https://leetcode.com/problems/two-sum/)

**Idea:** Single pass with a hash map. For each `x`, check if `target - x` was seen before. *(Uses a map rather than classic two pointers, but lives here as the complement-search pattern.)*

```java
public static int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> valueToIndex = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (valueToIndex.containsKey(complement)) {
            return new int[]{valueToIndex.get(complement), i};
        }
        valueToIndex.put(nums[i], i);
    }
    return new int[]{};
}
```

**Complexity:** O(n) time · O(n) space

---

## 5. Two Sum II (Sorted Array)

**LeetCode:** [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

**Idea:** Because the array is sorted, start `left` at 0 and `right` at the end. If the sum is too small, move `left` right; if too large, move `right` left.

```java
public static int[] twoSum(int[] nums, int target) {
    int left = 0, right = nums.length - 1;

    while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum == target) return new int[]{left, right};
        if (sum < target) left++;
        else right--;
    }
    return new int[]{};
}
```

**Complexity:** O(n) time · O(1) space

---

## 6. Container With Most Water

**LeetCode:** [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)

**Idea:** Area = width × min(left height, right height). Always move the pointer at the **shorter** wall inward — keeping the taller wall can only shrink width without improving the minimum height.

```java
public static int maxArea(int[] height) {
    int maxArea = 0, left = 0, right = height.length - 1;

    while (left <= right) {
        int area = (right - left) * Math.min(height[left], height[right]);
        maxArea = Math.max(maxArea, area);

        if (height[left] > height[right]) right--;
        else left++;
    }
    return maxArea;
}
```

**Complexity:** O(n) time · O(1) space

---

## 7. Sort Colors (Dutch National Flag)

**LeetCode:** [75. Sort Colors](https://leetcode.com/problems/sort-colors/)

**Idea:** Three pointers partition the array into `[0 … left-1]`, `[left … mid-1]`, `[mid … right]`, `[right+1 … end]` for values 0, 1, and 2.

```java
public static void sortColors(int[] nums) {
    int left = 0, mid = 0, right = nums.length - 1;

    while (mid <= right) {
        if (nums[mid] == 0) {
            swap(nums, left, mid);
            left++;
            mid++;
        } else if (nums[mid] == 1) {
            mid++;
        } else { // nums[mid] == 2
            swap(nums, mid, right);
            right--;
        }
    }
}
```

**Complexity:** O(n) time · O(1) space

---

## 8. 3Sum

**LeetCode:** [15. 3Sum](https://leetcode.com/problems/3sum/)

**Idea:** Sort first. Fix index `i`, then run two pointers on the remaining subarray to find pairs that complete `nums[i] + nums[left] + nums[right] == 0`. Skip duplicates at all three positions.

```java
public static List<List<Integer>> threeSum(int[] nums) {
    Set<List<Integer>> ans = new HashSet<>();
    Arrays.sort(nums);

    for (int i = 0; i < nums.length; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;

        int left = i + 1, right = nums.length - 1;

        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];

            if (sum == 0) {
                ans.add(Arrays.asList(nums[i], nums[left], nums[right]));

                while (left < right && nums[left] == nums[left + 1]) left++;
                while (left < right && nums[right] == nums[right - 1]) right--;

                left++;
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
    }
    return new ArrayList<>(ans);
}
```

**Complexity:** O(n²) time · O(n) space (for the result set)

---

## 9. Trapping Rain Water

**LeetCode:** [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)

**Idea:** Two pointers from both ends with running `leftMax` and `rightMax`. Process the shorter side — water trapped at a position is bounded by the max height on that side minus the current height.

```java
public static int trap(int[] height) {
    int totalWater = 0, left = 0, right = height.length - 1;
    int leftMax = 0, rightMax = 0;

    while (left <= right) {
        if (height[left] < height[right]) {
            if (leftMax > height[left]) totalWater += leftMax - height[left];
            else leftMax = height[left];
            left++;
        } else {
            if (rightMax > height[right]) totalWater += rightMax - height[right];
            else rightMax = height[right];
            right--;
        }
    }
    return totalWater;
}
```

**Complexity:** O(n) time · O(1) space

---

## Pattern Cheat Sheet

| Pattern | Problems |
|---------|----------|
| Opposite ends (`left` / `right`) | Two Sum II, Container With Most Water, Valid Palindrome, Trapping Rain Water |
| Same direction (`slow` / `fast`) | Remove Duplicates, Linked List Cycle |
| Three pointers | Dutch National Flag |
| Sort + two pointers | 3Sum |
| Complement lookup (hash map) | Two Sum (Unsorted) |
