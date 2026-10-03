# Hashing

Hashing uses a `Set` or `Map` for **O(1)** average lookup, insert, and count. Typical jobs: “have I seen this?”, frequency, grouping by a key, and prefix-sum lookups.

**When to use:** duplicates, anagrams, first unique, top-k by frequency, subarray sum in one pass, and consecutive sequences without sorting.

**Core maps:**

| Structure | Job |
|-----------|-----|
| `HashSet` | presence / uniqueness |
| `HashMap` value → freq | counts, anagrams, top-k |
| `HashMap` prefixSum → index or count | subarray sum problems |
| Sorted string / count signature as key | group anagrams |

---

## Problems (Easy → Hard)

| # | Problem | Difficulty | Source |
|---|---------|------------|--------|
| 1 | [Contains Duplicate](#1-contains-duplicate) | Easy | `ContainsDuplicate.java` |
| 2 | [Valid Anagram](#2-valid-anagram) | Easy | `ValidAnagram.java` |
| 3 | [First Unique Character](#3-first-unique-character) | Easy | `FirstUniqueCharacter.java` |
| 4 | [Group Anagrams](#4-group-anagrams) | Medium | `GroupAnagrams.java` |
| 5 | [Top K Frequent Elements](#5-top-k-frequent-elements) | Medium | `TopKFrequentElements.java` |
| 6 | [Subarray Sum Equals K](#6-subarray-sum-equals-k) | Medium | `SubarraySumEqualsK.java` |
| 7 | [Longest Subarray With Sum K](#7-longest-subarray-with-sum-k) | Medium | `LongestSubarrayWithSumK.java` |
| 8 | [Longest Consecutive Sequence](#8-longest-consecutive-sequence) | Medium | `LongestConsecutiveSequence.java` |

---

## 1. Contains Duplicate

**LeetCode:** [217. Contains Duplicate](https://leetcode.com/problems/contains-duplicate/)

**Idea:** Put each number in a set. If it is already there, a duplicate exists.

```java
public static boolean containDuplicate(int[] nums) {
    Set<Integer> numSet = new HashSet<>();

    for (int num : nums) {
        if (numSet.contains(num)) {
            return true;
        }
        numSet.add(num);
    }
    return false;
}
```

**Complexity:** O(n) time · O(n) space

---

## 2. Valid Anagram

**LeetCode:** [242. Valid Anagram](https://leetcode.com/problems/valid-anagram/)

**Idea:** Same length is required. Count characters in `s`, then decrement while scanning `t`. A missing key means not an anagram. Removing a key when its count hits 0 keeps the map clean.

```java
public static boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;

    Map<Character, Integer> charToFreq = new HashMap<>();
    for (char c : s.toCharArray()) {
        charToFreq.merge(c, 1, Integer::sum);
    }

    for (char c : t.toCharArray()) {
        if (!charToFreq.containsKey(c)) return false;
        charToFreq.merge(c, -1, Integer::sum);
        charToFreq.remove(c, 0);
    }
    return true;
}
```

**Complexity:** O(n) time · O(1) space for lowercase English (O(k) for a larger alphabet)

---

## 3. First Unique Character

**LeetCode:** [387. First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/)

**Idea:** Two passes — count frequencies, then walk the string left-to-right and return the first index whose count is 1.

```java
public static int firstUniqChar(String s) {
    if (s == null || s.isEmpty()) return -1;

    Map<Character, Integer> charToFreq = new HashMap<>();
    for (char c : s.toCharArray()) {
        charToFreq.merge(c, 1, Integer::sum);
    }

    for (int i = 0; i < s.length(); i++) {
        if (charToFreq.get(s.charAt(i)) == 1) {
            return i;
        }
    }
    return -1;
}
```

**Complexity:** O(n) time · O(1) space for a fixed alphabet

---

## 4. Group Anagrams

**LeetCode:** [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)

**Idea:** Anagrams share the same characters, so a **sorted string** is a grouping key. `computeIfAbsent` appends each word to its bucket.

```java
public static List<List<String>> groupAnagrams(String[] strings) {
    Map<String, List<String>> groups = new HashMap<>();

    for (String s : strings) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String key = new String(chars);

        groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(groups.values());
}
```

**Complexity:** O(n · m log m) time if each string has length m · O(n · m) space

---

## 5. Top K Frequent Elements

**LeetCode:** [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)

**Idea:** Count frequencies, then sort unique values by frequency descending and take the first `k`.

```java
public static int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();
    for (int n : nums) {
        freq.merge(n, 1, Integer::sum);
    }

    List<Integer> values = new ArrayList<>(freq.keySet());
    values.sort((a, b) -> Integer.compare(freq.get(b), freq.get(a)));

    int[] result = new int[k];
    for (int i = 0; i < k; i++) {
        result[i] = values.get(i);
    }
    return result;
}
```

**Complexity:** O(n + u log u) time where u is unique count · O(n) space  
*(Heap or bucket sort can make this O(n) / O(n log k).)*

---

## 6. Subarray Sum Equals K

**LeetCode:** [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

**Idea:** If prefix sum at `i` is `P`, a subarray ending at `i` sums to `k` whenever some earlier prefix equals `P - k`. Store **how many times** each prefix has appeared. Seed the map with `{0 → 1}` so a prefix that itself equals `k` is counted.

```java
public static int subarraySum(int[] nums, int k) {
    int count = 0, prefixSum = 0;
    Map<Integer, Integer> prefixSumCount = new HashMap<>();
    prefixSumCount.put(0, 1); // so a prefix that equals k is counted

    for (int n : nums) {
        prefixSum += n;
        count += prefixSumCount.getOrDefault(prefixSum - k, 0);
        prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
    }
    return count;
}
```

**Why this works with negatives:** shrinking a sliding window is wrong when values can be negative. Prefix + hashmap still works.

**Complexity:** O(n) time · O(n) space

---

## 7. Longest Subarray With Sum K

**Pattern:** prefix sum → first index (GFG / interview variant)

**Idea:** Same prefix identity: `P[i] - P[j] = k` means the subarray `(j+1 … i)` sums to `k`. Store the **first** index of each prefix so the window is as long as possible. Seed `{0 → -1}` so a prefix that equals `k` has length `i - (-1) = i + 1`.

```java
public static int longestSubarraySumK(int[] nums, int k) {
    int maxLen = 0, prefixSum = 0;
    Map<Integer, Integer> firstIdxOfPrefixSum = new HashMap<>();
    firstIdxOfPrefixSum.put(0, -1);

    for (int i = 0; i < nums.length; i++) {
        prefixSum += nums[i];
        int required = prefixSum - k;

        if (firstIdxOfPrefixSum.containsKey(required)) {
            maxLen = Math.max(maxLen, i - firstIdxOfPrefixSum.get(required));
        }

        if (!firstIdxOfPrefixSum.containsKey(prefixSum)) {
            firstIdxOfPrefixSum.put(prefixSum, i); // first occurrence only
        }
    }
    return maxLen;
}
```

Two pointers work **only if all numbers are non-negative** (sum only grows as the window expands):

```java
public static int longestSubarraySumK_UsingTwoPointers(int[] nums, int k) {
    int maxLen = 0, sum = 0, left = 0;

    for (int right = 0; right < nums.length; right++) {
        sum += nums[right];
        if (sum == k) maxLen = Math.max(maxLen, right - left + 1);

        while (sum > k) {
            sum -= nums[left];
            left++;
        }
    }
    return maxLen;
}
```

**Complexity:** Hash map O(n) time · O(n) space. Two pointers O(n) time · O(1) space (non-negative only).

---

## 8. Longest Consecutive Sequence

**LeetCode:** [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)

**Idea:** Put all values in a set. Only start a streak from `n` when `n - 1` is **not** in the set (true sequence start). Then walk `n + 1`, `n + 2`, … while those values exist. Each number is visited at most twice.

```java
public static int longestConsecutive(int[] nums) {
    Set<Integer> numSet = new HashSet<>();
    for (int n : nums) numSet.add(n);

    int longest = 0;
    for (int n : numSet) {
        if (!numSet.contains(n - 1)) { // start of a streak
            int len = 1;
            while (numSet.contains(n + 1)) {
                n++;
                len++;
            }
            longest = Math.max(longest, len);
        }
    }
    return longest;
}
```

**Complexity:** O(n) time · O(n) space

---

## Pattern Cheat Sheet

| Pattern | Map / set | Problems |
|---------|-----------|----------|
| Presence | `HashSet` | Contains Duplicate, Longest Consecutive Sequence |
| Frequency count | `HashMap` or `int[26]` | Valid Anagram, First Unique Character, Top K Frequent |
| Canonical key | sorted string / count tuple | Group Anagrams |
| Prefix sum → count | `Map<prefix, count>` | Subarray Sum Equals K |
| Prefix sum → first index | `Map<prefix, index>` | Longest Subarray With Sum K |

**Prefix-sum identity:**

```text
sum(i … j) = prefix[j] - prefix[i - 1]
sum(i … j) == k  ⇔  prefix[i - 1] == prefix[j] - k
```

**Templates:**

```java
// Seen before
Set<T> seen = new HashSet<>();
if (!seen.add(x)) { /* duplicate */ }

// Frequency
map.merge(key, 1, Integer::sum);

// Prefix count of subarrays summing to k
prefixCount.put(0, 1);
prefix += x;
count += prefixCount.getOrDefault(prefix - k, 0);
prefixCount.merge(prefix, 1, Integer::sum);
```
