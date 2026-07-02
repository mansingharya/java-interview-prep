package Java.Collections.Set;

import Java.Collections.Student;

import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {

    public static void main() {

        /*
         * A HashSet is a collection class in Java that implements the Set interface and is backed by a hash table.
         * It does not allow duplicate elements and does not maintain any order of the elements.
         *
         * The HashSet class provides constant-time performance for basic operations such as add, remove, and contains,
         * assuming the hash function disperses the elements properly.
         *
         * Common operations on a HashSet include:
         * - add(E e): Adds the specified element to this set if it is not already present.
         * - remove(Object o): Removes the specified element from this set if it is present.
         * - contains(Object o): Returns true if this set contains the specified element.
         * - size(): Returns the number of elements in this set.
         *
         */

        Set<Integer> set = new HashSet<>();
        set.add(125);
        set.add(29);
        set.add(13);
        set.add(49);
        set.add(5);
        set.add(12);
        set.add(12); // No Duplicate element allowed
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
        System.out.println("-----------------------------\n");


        HashSet<Student> studentSet = new HashSet<>();
        studentSet.add(new Student(1, "Alice", 10, "1234567890"));
        studentSet.add(new Student(2, "Bob", 20, "0987654321"));
        studentSet.add(new Student(3, "Charlie", 30, "5555555555"));

        studentSet.add(new Student(1, "Alice", 10, "1234567890")); // Duplicate element allowed because Student class does not override equals() and hashCode()
        System.out.println(studentSet);
        System.out.println();

        studentSet.add(new Student(1, "Charlie", 30, "5555555555"));
        System.out.println(studentSet);
        System.out.println();

        Student student1 = new Student(1, "Alice", 10, "1234567890");
        Student student2 = new Student(2, "Bob", 20, "0987654321");
        System.out.println(student1.equals(student2));

        Student student3 = new Student(1, "Alice", 10, "1234567890");
        Student student4 = new Student(1, "Bob", 20, "0987654321");
        System.out.println(student3.equals(student4));

    }

}
