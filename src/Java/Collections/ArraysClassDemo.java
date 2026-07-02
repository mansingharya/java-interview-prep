package Java.Collections;

import java.util.Arrays;
import java.util.Collections;

public class ArraysClassDemo {

    public static void main(String[] args) {
        //int[] arr1 = {5, 2, 8, 1, 3, 7, 10, 6, 9, 4};     OR
        Integer[] arr1 = {5, 2, 8, 1, 3, 7, 10, 6, 9, 4};

        // Sorting the array
        Arrays.sort(arr1);
        System.out.println("Sorted array: " + Arrays.toString(arr1));

        // Sorting the array
        Arrays.sort(arr1, Collections.reverseOrder());
        System.out.println("Sorted array: " + Arrays.toString(arr1));

        // Searching for an element
        int index = Arrays.binarySearch(arr1, 3);
        if (index >= 0) {
            System.out.println("Element found at index: " + index);
        } else {
            System.out.println("Element not found.");
        }

        // Filling an array with a specific value
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 7);
        System.out.println("Filled array: " + Arrays.toString(filledArray));
    }

}
