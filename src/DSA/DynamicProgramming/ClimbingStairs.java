package DSA.DynamicProgramming;


// 1.

public class ClimbingStairs {

    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int prev2 = 1; // dp[i-2]
        int prev1 = 2; // dp[i-1]

        for (int i = 3; i <= n; i++) {
            int current  = prev1 + prev2;

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    static void main(String[] args) {

        int n = 1;
        System.out.println("\nDistinct ways to climb to the top of " + n + " size staircase are : " + climbStairs(n));

        n = 2;
        System.out.println("Distinct ways to climb to the top of " + n + " size staircase are : " + climbStairs(n));

        n = 3;
        System.out.println("Distinct ways to climb to the top of " + n + " size staircase are : " + climbStairs(n));

        n = 4;
        System.out.println("Distinct ways to climb to the top of " + n + " size staircase are : " + climbStairs(n));

        n = 5;
        System.out.println("Distinct ways to climb to the top of " + n + " size staircase are : " + climbStairs(n));
    }

}
