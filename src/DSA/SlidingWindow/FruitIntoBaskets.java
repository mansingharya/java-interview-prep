package DSA.SlidingWindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


// https://leetcode.com/problems/fruit-into-baskets/description/

public class FruitIntoBaskets {

    static int totalFruit(int[] fruits) {

        int maxFruits = 0;

        int left = 0;
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int right = 0; right < fruits.length; right++) {

            int fruit = fruits[right];
            int freq = hashMap.getOrDefault(fruit, 0);
            hashMap.put(fruit, freq + 1);

            while ( hashMap.size() > 2) {
                int leftFruit = fruits[left];
                int leftFreq = hashMap.get(leftFruit);

                if (leftFreq <= 1) {
                    hashMap.remove(leftFruit);
                } else {
                    hashMap.put(leftFruit, leftFreq - 1);
                }
                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }


    static void main(String[] args) {

        int[] fruits = new int[] {1,2,1};
        System.out.println();
        System.out.println("Fruits: " + Arrays.toString(fruits));
        System.out.println("maximum number of fruits you can pick : " + totalFruit(fruits));


        fruits = new int[] {0,1,2,2};
        System.out.println();
        System.out.println("Fruits: " + Arrays.toString(fruits));
        System.out.println("maximum number of fruits you can pick : " + totalFruit(fruits));


        fruits = new int[] {1,2,3,2,2};
        System.out.println();
        System.out.println("Fruits: " + Arrays.toString(fruits));
        System.out.println("maximum number of fruits you can pick : " + totalFruit(fruits));


        fruits = new int[] {3,3,3,1,2,1,1,2,3,3,4};
        System.out.println();
        System.out.println("Fruits: " + Arrays.toString(fruits));
        System.out.println("maximum number of fruits you can pick : " + totalFruit(fruits));

    }
}
