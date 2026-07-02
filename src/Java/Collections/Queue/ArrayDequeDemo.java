package Java.Collections.Queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class ArrayDequeDemo {

    public static void main(String[] args) {

        /*
         * An ArrayDeque is a resizable array implementation of the Deque interface in Java.
         * It provides a double-ended queue that allows for efficient addition and removal of elements at both ends.
         * The ArrayDeque class is part of the java.util package and provides various methods for manipulating the deque of elements.
         *
         * In an ArrayDeque, elements are stored in a contiguous block of memory, which allows for fast access to elements by index.
         * However, adding or removing elements from the middle of the deque can be inefficient, as it may require shifting elements to maintain the order.
         *
         * Common operations on an ArrayDeque include:
         * - addFirst(E e): Inserts the specified element at the front of this deque.
         * - addLast(E e): Appends the specified element to the end of this deque.
         * - removeFirst(): Removes and returns the first element from this deque.
         * - removeLast(): Removes and returns the last element from this deque.
         * - peekFirst(): Retrieves, but does not remove, the first element of this deque, or returns null if this deque is empty.
         * - peekLast(): Retrieves, but does not remove, the last element of this deque, or returns null if this deque is empty.
         *
         */

        ArrayDeque<Integer> adq = new ArrayDeque<>();

        adq.offer(20);
        adq.offer(30);
        adq.offer(40);
        adq.offer(50);

        adq.addFirst(10);
        adq.offerFirst(0);

        adq.addLast(60);
        adq.offerLast(70);

        System.out.println(adq);

        System.out.println(adq.pollFirst());
        System.out.println(adq.pollLast());
        System.out.println(adq);

        System.out.println(adq.peekFirst());
        System.out.println(adq.peekLast());
        System.out.println(adq);

        System.out.println(adq.peek());
        System.out.println(adq);

        System.out.println(adq.poll());
        System.out.println(adq);

    }

}
