package DSA.Strings;

public class ValidPalindrome {

    public static boolean isPalindrome(String s) {
        int length = s.length();

        int l = 0;
        int r = length - 1;

        while (l <= r) {

            while (l <= r &&  !Character.isLetterOrDigit(s.charAt(l))) {
                ++l;
            }

            while (l <= r && !Character.isLetterOrDigit(s.charAt(r))) {
                --r;
            }

            if (l <= r && Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }

            ++l;
            --r;
        }

        return true;
    }

    static void main(String[] args) {

        String input = "A man, a plan, a canal: Panama";
        System.out.println("\nInput String: [" + input + "] is palindrome? - " + isPalindrome(input));

        input = "race a car";
        System.out.println("\nInput String: [" + input + "] is palindrome? - " + isPalindrome(input));

        input = "  ";
        System.out.println("\nInput String: [" + input + "] is palindrome? - " + isPalindrome(input));

    }
}
