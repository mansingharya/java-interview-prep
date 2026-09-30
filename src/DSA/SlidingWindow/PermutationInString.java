package DSA.SlidingWindow;


// 7. https://leetcode.com/problems/permutation-in-string/

public class PermutationInString {

    static boolean haveIdenticalFrequencyCounts(int[] source, int[] window) {
        for (int i=0; i<26; i++) {
            if (source[i] != window[i]) {
                return false;
            }
        }
        return true;
    }

    static boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }

        int[] target = new int[26];
        int[] window = new int[26];

        for (int i=0;i<s1.length(); i++) {
            target[s1.charAt(i) - 'a']++;
            window[s2.charAt(i) - 'a']++;
        }

        if (haveIdenticalFrequencyCounts(target, window)) {
            return true;
        }

        int left = 0;
        for (int right = s1.length(); right<s2.length(); right++) {

            window[s2.charAt(left) - 'a']--;
            left++;

            window[s2.charAt(right) - 'a']++;

            if (haveIdenticalFrequencyCounts(target, window)) {
                return true;
            }
        }

        return false;
    }

    static void main(String[] args) {

        String s1 = "ab", s2 = "eidbaooo";
        System.out.println();
        System.out.println("S1: " + s1);
        System.out.println("S2: " + s2);
        System.out.println("Does s2 contains a permutation of s1? - " + checkInclusion(s1, s2));

        s1 = "ab";
        s2 = "eidboaoo";
        System.out.println();
        System.out.println("S1: " + s1);
        System.out.println("S2: " + s2);
        System.out.println("Does s2 contains a permutation of s1? - " + checkInclusion(s1, s2));

    }
}
