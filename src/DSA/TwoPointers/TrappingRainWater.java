package DSA.TwoPointers;

import java.util.Arrays;


// 9. https://leetcode.com/problems/trapping-rain-water/description/

public class TrappingRainWater {

    static public int trap(int[] height) {
        int totalWater = 0;

        if (height.length == 0 ) {
            return totalWater;
        }

        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        while (left <= right) {

            if (height[left] < height[right]) {

                if (leftMax > height[left]) {
                    totalWater += leftMax - height[left];
                } else {
                    leftMax = height[left];
                }
                left ++;

            } else {

                if (rightMax > height[right]) {
                    totalWater += rightMax - height[right];
                } else {
                    rightMax = height[right];
                }
                right--;
            }
        }

        return totalWater;
    }

    static void main(String[] args) {

        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println();
        System.out.println(Arrays.toString(height));
        System.out.println("Total Trapped Water: " + trap(height));

        height = new int[] {4,2,0,3,2,5};
        System.out.println();
        System.out.println(Arrays.toString(height));
        System.out.println("Total Trapped Water: " + trap(height));

    }

}
