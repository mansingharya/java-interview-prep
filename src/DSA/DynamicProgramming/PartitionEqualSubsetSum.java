package DSA.DynamicProgramming;

public class PartitionEqualSubsetSum {

    public static boolean canPartition(int[] nums) {
        int totalSum = 0;

        for (int n : nums) {
            totalSum += n;
        }

        if (totalSum % 2 != 0) {
            return false;
        }

        int target = totalSum / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int n : nums) {
            for (int sum = target; sum >= n; sum--) {
                dp[sum] = dp[sum] || dp[sum - n];
            }
        }

        return dp[target];
    }


    static void main(String[] args) {

        int[] nums = {1, 5, 11, 5};
        System.out.println("\nPartition into equal sub-set sum is possible ?? " + canPartition(nums));

        nums = new int[] {1,2,3,5};
        System.out.println("Partition into equal sub-set sum is possible ?? " + canPartition(nums));
    }
}
