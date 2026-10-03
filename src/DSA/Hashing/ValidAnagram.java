package DSA.Hashing;

import java.util.HashMap;
import java.util.Map;


// 2. https://leetcode.com/problems/valid-anagram/description/

public class ValidAnagram {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> charToFreq = new HashMap<>();

        for (char c : s.toCharArray()) {
            charToFreq.merge(c, 1, Integer::sum);
        }

        for (char c : t.toCharArray()) {
            if ( !charToFreq.containsKey(c)) {
                return false;
            }

            charToFreq.merge(c, -1, Integer::sum);
            charToFreq.remove(c, 0);
        }

        return true;
    }


    static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println("\n'" + s + "' and '" + t + "' are anagram to each others? : " + isAnagram(s, t));

        s = "rat";
        t = "car";
        System.out.println("\n'" + s + "' and '" + t + "' are anagram to each others? : " + isAnagram(s, t));

        s = "rat";
        t = "tar";
        System.out.println("\n'" + s + "' and '" + t + "' are anagram to each others? : " + isAnagram(s, t));
    }
}
