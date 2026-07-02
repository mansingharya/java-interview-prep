package Java.Collections.List;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {

    public static void main(String[] args) {

        /*
         * A LinkedList is a data structure that consists of a sequence of nodes, where each node contains data and a reference (or link) to the next node in the sequence.
         * In Java, the LinkedList class is part of the java.util package and implements the List interface.
         * It provides methods for manipulating the list of elements, such as adding, removing, and accessing elements.
         *
         * In a LinkedList, elements are not stored in contiguous memory locations like in an ArrayList.
         * Instead, each element (node) contains a reference to the next element in the list.
         * This allows for efficient insertion and removal of elements at any position in the list, as it does not require shifting elements like in an ArrayList.
         *
         * Common operations on a LinkedList include:
         * - add(E e): Appends the specified element to the end of this list.
         * - add(int index, E element): Inserts the specified element at the specified position in this list.
         * - remove(int index): Removes the element at the specified position in this list.
         * - get(int index): Returns the element at the specified position in this list.
         * - size(): Returns the number of elements in this list.
         *
         */

        // Example usage of LinkedList
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("Hello");
        linkedList.add("World");
        linkedList.add("Java");

        System.out.println(linkedList); // Output: [Hello, World, Java]

        System.out.println(linkedList.get(1)); // Output: World

        linkedList.remove(2);

        System.out.println(linkedList); // Output: [Hello, World]

        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(33);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        System.out.println(list);

        list.remove(3);
        System.out.println(list);

        list.set(3, 44);
        System.out.println(list);

        list.remove(3);
        System.out.println(list);

        list.add(3, 4);
        System.out.println(list);

        list.add(10);
        System.out.println(list);

        list.sort(Comparator.reverseOrder());
        System.out.println(list);

        list.sort(Comparator.naturalOrder());
        System.out.println(list);

    }

}
