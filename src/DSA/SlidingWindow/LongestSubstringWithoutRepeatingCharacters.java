package DSA.SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


// 3. https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

public class LongestSubstringWithoutRepeatingCharacters {

    // Better Solution
    static int lengthOfLongestSubstringUsingSet(String s) {
        int maxLength = 0;

        int left = 0;
        Set<Character> charSet = new HashSet<>();

        for (int right=0; right<s.length(); right++) {
            Character rightChar = s.charAt(right);

            while (charSet.contains(rightChar)) {
                charSet.remove(s.charAt(left));
                left++;
            }

            charSet.add(rightChar);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }


    static int lengthOfLongestSubstringUsingMap(String s) {
        int maxLength = 0;

        Map<Character, Integer> charToIndex = new HashMap<>();
        int left = 0;
        int position = 0;

        for (int right=0; right<s.length(); right++) {

            Character rightChar = s.charAt(right);

            if (charToIndex.containsKey(rightChar)) {
                position = charToIndex.get(rightChar);

                left = Math.max(left, position + 1);

                // charToIndex.remove(rightChar); // This is redundant and can be omitted for cleaner and slightly more efficient code.
            }

            maxLength = Math.max(maxLength, right - left + 1);
            charToIndex.put(rightChar, right);
        }

        return maxLength;
    }


    static void main(String[] args) {

        String s = "abcabcbb";
        System.out.println();
        System.out.println(s);
        System.out.println(lengthOfLongestSubstringUsingMap(s));
        System.out.println(lengthOfLongestSubstringUsingSet(s));

        s = "abba";
        System.out.println();
        System.out.println(s);
        System.out.println(lengthOfLongestSubstringUsingMap(s));
        System.out.println(lengthOfLongestSubstringUsingSet(s));

        s = "bbbbb";
        System.out.println();
        System.out.println(s);
        System.out.println(lengthOfLongestSubstringUsingMap(s));
        System.out.println(lengthOfLongestSubstringUsingSet(s));

        s = "pwwkew";
        System.out.println();
        System.out.println(s);
        System.out.println(lengthOfLongestSubstringUsingMap(s));
        System.out.println(lengthOfLongestSubstringUsingSet(s));

    }

}
