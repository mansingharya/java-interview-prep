package DSA.SlidingWindow;


//https://leetcode.com/problems/longest-repeating-character-replacement/description/
public class LongestRepeatingCharacterReplacement {

    static int characterReplacement(String s, int k) {
        int maxLength=0;

        int left=0;
        int maxFreq=0;
        int[] freq = new int[32];

        for(int right=0; right<s.length(); right++) {

            char rightChar = s.charAt(right);
            freq[rightChar - 'A']++;
            maxFreq = Math.max(maxFreq, freq[rightChar - 'A']);

            while((right-left+1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right-left+1);
        }

        return maxLength;
    }


    static void main(String[] args) {

        String s = "ABAB";
        int k = 2;
        System.out.println();
        System.out.println("String: " + s);
        System.out.println("Can replace at max: " + k + " chars");
        System.out.println("Max Length after replacement: " + characterReplacement(s, k));

        s = "AABABBA";
        k = 1;
        System.out.println();
        System.out.println("String: " + s);
        System.out.println("Can replace at max: " + k + " chars");
        System.out.println("Max Length after replacement: " + characterReplacement(s, k));

    }

}
