package DSA.TwoPointers;

public class ContainersWithMostWater {

    public static int maxArea(int[] height) {
        int maxArea = 0;

        int left = 0;
        int right = height.length - 1;

        while (left <= right) {

            int distance = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int currentArea = distance * minHeight;

            maxArea = Math.max(maxArea, currentArea);

            // Move towards higher height.
            if (height[left] > height[right]) {
                --right;
            } else {
                ++ left;
            }

        }

        return maxArea;
    }


    static void main(String[] args) {

        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println("\nMax Water: " + maxArea(height));


        height = new int[] {1,1};
        System.out.println("Max Water: " + maxArea(height));

    }

}
