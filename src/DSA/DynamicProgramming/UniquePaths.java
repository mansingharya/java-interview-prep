package DSA.DynamicProgramming;


// 3.

public class UniquePaths {

    public static int uniquePaths(int rows, int cols) {
        int [][] dp = new int[rows][cols];

        // Setting first column as 1 for all rows
        for (int i=0; i<rows; i++) {
            dp[i][0] = 1;
        }

        // Setting first row as 1 for all columns
        for (int j=0; j<cols; j++) {
            dp[0][j] = 1;
        }

        for (int i=1; i<rows; i++) {
            for (int j=1; j<cols; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[rows-1][cols-1];
    }

    static void main(String[] args) {

        System.out.println("\nUnique Paths are : " + uniquePaths(3, 2));

        System.out.println("Unique Paths are : " + uniquePaths(3, 7));

    }
}
