package Java.Collections.Set;

import java.util.LinkedHashSet;

public class LinkedHashSetDemo {

    public static void main() {

        /*
         * A LinkedHashSet is a collection class in Java that implements the Set interface and is backed by a hash table and a linked list.
         * It maintains the insertion order of the elements, which means that the elements are stored in the order they were added to the set.
         *
         * The LinkedHashSet class provides constant-time performance for basic operations such as add, remove, and contains,
         * assuming the hash function disperses the elements properly.
         *
         * Common operations on a LinkedHashSet include:
         * - add(E e): Adds the specified element to this set if it is not already present.
         * - remove(Object o): Removes the specified element from this set if it is present.
         * - contains(Object o): Returns true if this set contains the specified element.
         * - size(): Returns the number of elements in this set.
         *
         */

        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.add(125);
        set.add(29);
        set.add(13);
        set.add(49);
        set.add(5);
        set.add(12);
        set.add(12);
        System.out.println(set);

        set.add(2);
        set.add(1);
        System.out.println(set);

        set.remove(49);
        System.out.println(set);

        System.out.println(set.contains(10));
        System.out.println(set.contains(1));
        System.out.println(set.isEmpty());
        System.out.println(set.size());

        set.clear();
        System.out.println(set);
        System.out.println(set.size());
        System.out.println(set.isEmpty());

    }

}
