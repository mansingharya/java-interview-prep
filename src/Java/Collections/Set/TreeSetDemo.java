package Java.Collections.Set;

import java.util.TreeSet;

public class TreeSetDemo {

    public static void main(String[] args) {

        /*
         * A TreeSet is a collection class in Java that implements the Set interface and is backed by a TreeMap.
         * It stores elements in a sorted order, which means that the elements are stored in their natural ordering
         * (if they implement Comparable) or by a Comparator provided at the time of creation.
         *
         * The TreeSet class provides log(n) time cost for the basic operations (add, remove, contains).
         *
         * Common operations on a TreeSet include:
         * - add(E e): Adds the specified element to this set if it is not already present.
         * - remove(Object o): Removes the specified element from this set if it is present.
         * - contains(Object o): Returns true if this set contains the specified element.
         * - size(): Returns the number of elements in this set.
         *
         */

        TreeSet<Integer> set = new TreeSet<>();
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
