package DSA.Hashing;

import java.util.HashMap;
import java.util.Map;


// 3. https://leetcode.com/problems/first-unique-character-in-a-string/description/

public class FirstUniqueCharacter {

    public static int firstUniqChar(String s) {
        if (null == s || s.isEmpty()) {
            return -1;
        }

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

    public static void main(String[] args) {
        System.out.println("\nFirst Uniq Char at index: " + firstUniqChar("leetcode"));
        System.out.println("\nFirst Uniq Char at index: " + firstUniqChar("loveleetcode"));
    }
}
