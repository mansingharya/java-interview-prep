# Sliding Window

Sliding window maintains a contiguous subarray or substring with `left` and `right` pointers. Expand by moving `right`, shrink by moving `left` — reusing previous work instead of recomputing from scratch.

**When to use:** fixed-size subarrays, longest/shortest valid window, frequency/count constraints, and subarray problems asking for max/min over all windows.

---

## Problems (Easy → Hard)

| # | Problem | Difficulty | Source |
|---|---------|------------|--------|
| 1 | [Maximum Sum Subarray of Size K](#1-maximum-sum-subarray-of-size-k) | Easy | `MaximumSumSubarrayOfSizeK.java` |
| 2 | [Maximum Average Subarray I](#2-maximum-average-subarray-i) | Easy | `MaximumAverageSubArray1.java` |
| 3 | [Longest Substring Without Repeating Characters](#3-longest-substring-without-repeating-characters) | Medium | `LongestSubstringWithoutRepeatingCharacters.java` |
| 4 | [Minimum Size Subarray Sum](#4-minimum-size-subarray-sum) | Medium | `MinimumSizeSubArraySum.java` |
| 5 | [Fruit Into Baskets](#5-fruit-into-baskets) | Medium | `FruitIntoBaskets.java` |
| 6 | [Permutation in String](#6-permutation-in-string) | Medium | `PermutationInString.java` |
| 7 | [Find All Anagrams in a String](#7-find-all-anagrams-in-a-string) | Medium | `FindAllAnagramsInAString.java` |
| 8 | [Maximum Sum of Distinct Subarrays With Length K](#8-maximum-sum-of-distinct-subarrays-with-length-k) | Medium | `MaximumSumOfDistinctSubArraysWithLengthK.java` |
| 9 | [Longest Repeating Character Replacement](#9-longest-repeating-character-replacement) | Medium | `LongestRepeatingCharacterReplacement.java` |
| 10 | [Sliding Window Maximum](#10-sliding-window-maximum) | Hard | `SlidingWindowMaximum.java` |

---

## 1. Maximum Sum Subarray of Size K

**Pattern:** Fixed-size window (foundational)

**Idea:** Add each new element at `right`. Once the window reaches size `k`, track the max sum and slide by subtracting `nums[left]` and advancing `left`.

```java
public static int maxSumSubarray(int[] nums, int k) {
    int left = 0, currentSum = 0, maxSum = Integer.MIN_VALUE;

    for (int right = 0; right < nums.length; right++) {
        currentSum += nums[right];

        if (right - left + 1 == k) {
            maxSum = Math.max(maxSum, currentSum);
            currentSum -= nums[left];
            left++;
        }
    }
    return maxSum;
}
```

**Complexity:** O(n) time · O(1) space

---

## 2. Maximum Average Subarray I

**LeetCode:** [643. Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/)

**Idea:** Same fixed-window pattern as above — maintain a running sum over windows of size `k` and track the maximum average.

```java
static double findMaxAverage(int[] nums, double k) {
    double maxAvg = Integer.MIN_VALUE;
    int left = 0, sum = 0;

    for (int right = 0; right < nums.length; right++) {
        sum += nums[right];

        if (right - left + 1 == k) {
            maxAvg = Math.max(maxAvg, sum / k);
            sum -= nums[left];
            left++;
        }
    }
    return maxAvg;
}
```

**Complexity:** O(n) time · O(1) space

---

## 3. Longest Substring Without Repeating Characters

**LeetCode:** [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

**Idea:** Expand with `right`. On a duplicate, jump `left` past the previous index of that character. Track the longest valid window.

```java
static int lengthOfLongestSubstringUsingMap(String s) {
    int maxLength = 0, left = 0;
    Map<Character, Integer> charToIndex = new HashMap<>();

    for (int right = 0; right < s.length(); right++) {
        char rightChar = s.charAt(right);

        if (charToIndex.containsKey(rightChar)) {
            left = Math.max(left, charToIndex.get(rightChar) + 1);
        }

        maxLength = Math.max(maxLength, right - left + 1);
        charToIndex.put(rightChar, right);
    }
    return maxLength;
}
```

**Complexity:** O(n) time · O(min(n, alphabet)) space

---

## 4. Minimum Size Subarray Sum

**LeetCode:** [209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)

**Idea:** Variable window — expand until sum ≥ target, then shrink from the left to find the smallest valid window.

```java
static int minSubArrayLen(int target, int[] nums) {
    int minLength = Integer.MAX_VALUE, left = 0, windowSum = 0;

    for (int right = 0; right < nums.length; right++) {
        windowSum += nums[right];

        while (windowSum >= target) {
            minLength = Math.min(minLength, right - left + 1);
            windowSum -= nums[left];
            left++;
        }
    }
    return minLength == Integer.MAX_VALUE ? 0 : minLength;
}
```

**Complexity:** O(n) time · O(1) space

---

## 5. Fruit Into Baskets

**LeetCode:** [904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/)

**Idea:** Longest subarray with at most 2 distinct values. Expand with `right`; shrink from `left` when the frequency map has more than 2 keys.

```java
static int totalFruit(int[] fruits) {
    int maxFruits = 0, left = 0;
    Map<Integer, Integer> freq = new HashMap<>();

    for (int right = 0; right < fruits.length; right++) {
        int fruit = fruits[right];
        freq.put(fruit, freq.getOrDefault(fruit, 0) + 1);

        while (freq.size() > 2) {
            int leftFruit = fruits[left];
            int leftFreq = freq.get(leftFruit);
            if (leftFreq <= 1) freq.remove(leftFruit);
            else freq.put(leftFruit, leftFreq - 1);
            left++;
        }
        maxFruits = Math.max(maxFruits, right - left + 1);
    }
    return maxFruits;
}
```

**Complexity:** O(n) time · O(1) space (at most 3 keys in the map)

---

## 6. Permutation in String

**LeetCode:** [567. Permutation in String](https://leetcode.com/problems/permutation-in-string/)

**Idea:** Fixed window of size `s1.length()` over `s2`. Compare character frequency arrays; slide by decrementing the outgoing char and incrementing the incoming char.

```java
static boolean checkInclusion(String s1, String s2) {
    if (s2.length() < s1.length()) return false;

    int[] target = new int[26], window = new int[26];

    for (int i = 0; i < s1.length(); i++) {
        target[s1.charAt(i) - 'a']++;
        window[s2.charAt(i) - 'a']++;
    }
    if (haveIdenticalFrequencyCounts(target, window)) return true;

    int left = 0;
    for (int right = s1.length(); right < s2.length(); right++) {
        window[s2.charAt(left) - 'a']--;
        left++;
        window[s2.charAt(right) - 'a']++;
        if (haveIdenticalFrequencyCounts(target, window)) return true;
    }
    return false;
}
```

**Complexity:** O(n) time · O(1) space

---

## 7. Find All Anagrams in a String

**LeetCode:** [438. Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/)

**Idea:** Same fixed-window frequency pattern as Permutation in String, but collect every starting index where window frequencies match `p`.

```java
static List<Integer> findAnagrams(String s, String p) {
    List<Integer> ans = new ArrayList<>();
    if (p.length() > s.length()) return ans;

    int[] pFreq = new int[26], wFreq = new int[26];

    for (int i = 0; i < p.length(); i++) {
        pFreq[p.charAt(i) - 'a']++;
        wFreq[s.charAt(i) - 'a']++;
    }
    if (isAnagrams(pFreq, wFreq)) ans.add(0);

    int left = 0;
    for (int right = p.length(); right < s.length(); right++) {
        wFreq[s.charAt(right) - 'a']++;
        wFreq[s.charAt(left) - 'a']--;
        left++;

        if (isAnagrams(pFreq, wFreq)) ans.add(left);
    }
    return ans;
}
```

**Complexity:** O(n) time · O(1) space

---

## 8. Maximum Sum of Distinct Subarrays With Length K

**LeetCode:** [2461. Maximum Sum of Distinct Subarrays With Length K](https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/)

**Idea:** Fixed window of size `k` where all elements must be distinct. Use a frequency map — a valid window has `freq.size() == k`.

```java
static long maximumSubarraySum(int[] nums, int k) {
    long maxSum = 0, windowSum = 0;
    int left = 0;
    Map<Integer, Integer> freq = new HashMap<>();

    for (int right = 0; right < nums.length; right++) {
        windowSum += nums[right];
        freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

        while (right - left + 1 > k) {
            freq.put(nums[left], freq.get(nums[left]) - 1);
            if (freq.get(nums[left]) == 0) freq.remove(nums[left]);
            windowSum -= nums[left];
            left++;
        }

        if (right - left + 1 == k && freq.size() == k) {
            maxSum = Math.max(maxSum, windowSum);
        }
    }
    return maxSum;
}
```

**Complexity:** O(n) time · O(k) space

---

## 9. Longest Repeating Character Replacement

**LeetCode:** [424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)

**Idea:** Window is valid when `(windowSize - maxFreq) <= k` — at most `k` characters need replacing. Shrink when invalid.

```java
static int characterReplacement(String s, int k) {
    int maxLength = 0, left = 0, maxFreq = 0;
    int[] freq = new int[26];

    for (int right = 0; right < s.length(); right++) {
        freq[s.charAt(right) - 'A']++;
        maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

        while ((right - left + 1) - maxFreq > k) {
            freq[s.charAt(left) - 'A']--;
            left++;
        }
        maxLength = Math.max(maxLength, right - left + 1);
    }
    return maxLength;
}
```

**Complexity:** O(n) time · O(1) space

---

## 10. Sliding Window Maximum

**LeetCode:** [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)

**Idea:** Monotonic deque stores indices in decreasing value order. Front is always the max of the current window; drop indices that fall out of the window or are smaller than the new element.

```java
static int[] maxSlidingWindow(int[] nums, int k) {
    int[] result = new int[nums.length - k + 1];
    Deque<Integer> dq = new LinkedList<>(); // stores indices
    int resultIndex = 0;

    for (int right = 0; right < nums.length; right++) {
        if (!dq.isEmpty() && dq.peekFirst() <= right - k) {
            dq.pollFirst();
        }

        while (!dq.isEmpty() && nums[dq.peekLast()] < nums[right]) {
            dq.pollLast();
        }

        dq.offerLast(right);

        if (right >= k - 1) {
            result[resultIndex++] = nums[dq.peekFirst()];
        }
    }
    return result;
}
```

**Complexity:** O(n) time · O(k) space

---

## Pattern Cheat Sheet

| Pattern | When to use | Problems |
|---------|-------------|----------|
| Fixed window (size `k`) | Sum/average over every window of length `k` | Max Sum Subarray of Size K, Max Average Subarray I |
| Variable window — maximize | Expand freely; shrink on invalid state | Longest Substring, Fruit Into Baskets, Character Replacement |
| Variable window — minimize | Expand until valid; shrink while valid | Minimum Size Subarray Sum |
| Fixed window + frequency | Anagram/permutation match | Permutation in String, Find All Anagrams |
| Fixed window + distinct constraint | All elements unique in window | Max Sum of Distinct Subarrays |
| Monotonic deque | Max/min in each sliding window | Sliding Window Maximum |

**Template:**

```java
for (int right = 0; right < n; right++) {
    // expand: add nums[right] to window state

    while (windowIsInvalid()) {
        // shrink: remove nums[left] from window state
        left++;
    }

    // update answer using current window [left, right]
}
```
