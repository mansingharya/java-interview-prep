package Java.Collections.List;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class VectorDemo {

    public static void main(String[] args) {

        /*
         * A Vector is a dynamic array that can grow or shrink in size as needed.
         * It is part of the java.util package and implements the List interface.
         * The Vector class is synchronized, which means that it is thread-safe and can be used in multithreaded environments without the need for external synchronization.
         *
         * In a Vector, elements are stored in a contiguous block of memory, similar to an ArrayList.
         * However, because it is synchronized, it may have some performance overhead compared to an ArrayList when used in single-threaded applications.
         *
         * Common operations on a Vector include:
         * - add(E e): Appends the specified element to the end of this vector.
         * - add(int index, E element): Inserts the specified element at the specified position in this vector.
         * - remove(int index): Removes the element at the specified position in this vector.
         * - get(int index): Returns the element at the specified position in this vector.
         * - size(): Returns the number of elements in this vector.
         *
         */

        Vector<String> vector = new Vector<>();
        vector.add("Hello");
        vector.add("World");
        System.out.println(vector);

        List<Integer> v = new Vector<>();
        v.add(1);
        v.add(2);
        v.add(3);
        v.add(33);
        v.add(4);
        v.add(5);
        v.add(6);
        v.add(7);
        v.add(8);
        v.add(9);
        System.out.println(v);

        v.remove(3);
        System.out.println(v);

        v.set(3, 44);
        System.out.println(v);

        v.remove(3);
        System.out.println(v);

        v.add(3, 4);
        System.out.println(v);

        v.add(10);
        System.out.println(v);

        v.sort(Comparator.reverseOrder());
        System.out.println(v);

        v.sort(Comparator.naturalOrder());
        System.out.println(v);

    }

}
