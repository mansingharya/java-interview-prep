package Java.Collections.List;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo {

    public static void main(String[] args) {

        /*
         * An ArrayList is a resizable array implementation of the List interface in Java.
         * It provides dynamic arrays that can grow as needed, allowing for efficient addition and removal of elements.
         * The ArrayList class is part of the java.util package and provides various methods for manipulating the list of elements.
         *
         * In an ArrayList, elements are stored in a contiguous block of memory, which allows for fast access to elements by index.
         * However, adding or removing elements from the middle of the list can be inefficient, as it may require shifting elements to maintain the order.
         *
         * Common operations on an ArrayList include:
         * - add(E e): Appends the specified element to the end of this list.
         * - add(int index, E element): Inserts the specified element at the specified position in this list.
         * - remove(int index): Removes the element at the specified position in this list.
         * - get(int index): Returns the element at the specified position in this list.
         * - size(): Returns the number of elements in this list.
         *
         */

        // Example usage of ArrayList
        List<String> arrayList = new ArrayList<>();

        arrayList.add("Hello");
        arrayList.add("World");
        arrayList.add("Java");

        System.out.println(arrayList); // Output: [Hello, World, Java]

        System.out.println(arrayList.get(1)); // Output: World

        arrayList.remove(2);

        System.out.println(arrayList); // Output: [Helo, World]

        // Iterating through an ArrayList using an Iterator
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
        }

        // Old School
        String[] students = new String[10];
        students[0] = "Student0";
        students[1] = "Student1";
        students[9] = "Student9";
        System.out.println(students[0]);
        System.out.println(students[1]);
        System.out.println(students[9]);

        // Problem with fixed size array.
        // students[10] = "Student10"; // This will throw ArrayIndexOutOfBoundsException


        // ArrayList
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("Java");
        System.out.println(list); // Output: [Hello, World, Java]

        // Accessing elements
        System.out.println(list.get(0)); // Output: Hello

        // Modifying elements
        list.set(1, "Everyone");
        System.out.println(list); // Output: [Hello, Everyone, Java]

        // Removing elements
        list.remove(2);
        System.out.println(list); // Output: [Hello, Everyone]

        // Iterating over the list
        for (String item : list) {
            System.out.println(item);
        }


        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        System.out.println(list2);

        list2.add(4);
        System.out.println(list2);

        list2.add(1, 50);
        System.out.println(list2);

        List<Integer> newList = new ArrayList<>();
        newList.add(150);
        newList.add(160);
        System.out.println(newList);

        list2.addAll(newList);
        System.out.println(list2);

        System.out.println(list2.get(1));

        List<Integer> list3 = new ArrayList<>();
        list3.add(10);
        list3.add(20);
        list3.add(30);
        list3.add(40);
        list3.add(50);
        list3.add(60);
        list3.add(70);
        list3.add(80);
        System.out.println(list3);

        System.out.print("\nMy ArrayList - ");
        for (int i=0; i<list3.size(); i++) {
            System.out.print(list3.get(i) + ", ");
        }
        System.out.println();

        System.out.print("My ArrayList - ");
        for(Integer item : list3) {
            System.out.print(item + ", ");
        }
        System.out.println();

        System.out.print("My ArrayList - ");
        Iterator<Integer> it = list3.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
        }
        System.out.println("\n");


        list3.remove(1);
        System.out.println(list3);

        list3.remove(Integer.valueOf(30));
        System.out.println(list3);

        list3.set(2, 100);
        System.out.println(list3);

        System.out.println(list3.contains(100));
        System.out.println(list3.contains(101));

        list3.clear();
        System.out.println(list3);

    }
}
