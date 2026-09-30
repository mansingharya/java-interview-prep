package DSA.SlidingWindow;

import java.util.ArrayList;
import java.util.List;


// 7. https://leetcode.com/problems/find-all-anagrams-in-a-string/description/

public class FindAllAnagramsInAString {

    static boolean isAnagrams(int[] pFreq, int[] wFreq) {
        for (int i=0; i<26; i++) {
            if (pFreq[i] != wFreq[i]) {
                return false;
            }
        }
        return true;
    }

    static List<Integer> findAnagrams(String s, String p) {

        List<Integer> ans = new ArrayList<>();
        if (p.length() > s.length()) {
            return ans;
        }

        int[] pFreq = new int[26];
        int[] wFreq = new int[26];

        for (int i=0; i<p.length(); i++) {
            pFreq[p.charAt(i) - 'a']++;
            wFreq[s.charAt(i) - 'a']++;
        }

        if (isAnagrams(pFreq, wFreq)) {
            ans.add(0);
        }

        int left = 0;
        for (int right=p.length(); right<s.length(); right++) {

            wFreq[s.charAt(right) - 'a']++;

            wFreq[s.charAt(left) - 'a']--;
            left++;

            if (right-left+1 == p.length() && isAnagrams(pFreq, wFreq)) {
                ans.add(left);
            }
        }

        return ans;
    }

    static void main(String[] args) {

        String s = "cbaebabacd";
        String p = "abc";
        System.out.println();
        System.out.println("Anagram Of : " + p + " in String : " + s);
        System.out.println(findAnagrams(s, p));


        s = "abab";
        p = "ab";
        System.out.println();
        System.out.println("Anagram Of : " + p + " in String : " + s);
        System.out.println(findAnagrams(s, p));

    }
}
