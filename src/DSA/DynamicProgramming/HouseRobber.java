package DSA.DynamicProgramming;


// 2.

public class HouseRobber {

    public static int rob(int[] nums) {
        int prev2 = 0;
        int prev1 = 0;

        for (int n : nums) {
            int current  = Math.max(
                    prev1,
                    prev2 + n
            );

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    static void main(String[] args) {

        int[] houses = {1, 2, 3, 1};
        System.out.println("\nMaximum amount robbed : " + rob(houses));

        houses = new int[] {2,7,9,3,1};
        System.out.println("Maximum amount robbed : " + rob(houses));
    }
}
