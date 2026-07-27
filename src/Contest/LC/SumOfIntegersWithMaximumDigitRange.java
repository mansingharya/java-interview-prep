package Contest.LC;

import java.util.Arrays;

public class SumOfIntegersWithMaximumDigitRange {

    public static int getDigitRange(int num) {
        int large = 0;
        int small = 9;

        int d;
        while(num > 0) {
            d = num % 10;
            num = num / 10;

            large = Math.max(large, d);
            small = Math.min(small, d);
        }

        return large - small;
    }

    static int maxDigitRange(int[] nums) {
        int maxRangeSoFar = 0;
        int sumSoFar = 0;
        int currentRange;

        for (int num : nums) {
            currentRange = getDigitRange(num);

            if (currentRange == maxRangeSoFar) {
                sumSoFar += num;
            } else if (currentRange > maxRangeSoFar) {
                sumSoFar = num;
                maxRangeSoFar = currentRange;
            }
        }
        return sumSoFar;
    }

    static void main(String[] args) {

        int[] nums = new int[] {5724,111,350};
        System.out.println();
        System.out.println("Nums Array: " + Arrays.toString(nums));
        System.out.println("Ans: " + maxDigitRange(nums));

    }
}
