package Java.Collections.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedListQueueDemo {

    public static void main(String[] args) {

        /*
         * LinkedList is a class in Java that implements the List and Deque interfaces.
         * It is a doubly-linked list implementation, which means that each element in the list has a reference to both the previous and next elements.
         * This allows for efficient insertion and deletion of elements at both ends of the list.
         *
         * In addition to being used as a List, LinkedList can also be used as a Queue or Deque (double-ended queue1).
         * When used as a Queue, it follows the First-In-First-Out (FIFO) principle, where elements are added at the end of the queue1 and removed from the front.
         *
         * Common operations on a LinkedList include:
         * - add(E e): Appends the specified element to the end of this list.
         * - addFirst(E e): Inserts the specified element at the beginning of this list.
         * - addLast(E e): Appends the specified element to the end of this list.
         * - remove(): Retrieves and removes the head (first element) of this list.
         * - removeFirst(): Removes and returns the first element from this list.
         * - removeLast(): Removes and returns the last element from this list.
         * - peek(): Retrieves, but does not remove, the head (first element) of this list.
         *
         */

        // Example usage of LinkedList as a Queue
        LinkedList<String> queue = new LinkedList<>();

        queue.offer("Hello");
        queue.offer("World");
        queue.offer("Java");

        System.out.println(queue); // Output: [Hello, World, Java]

        System.out.println(queue.peek()); // Output: Hello

        System.out.println(queue.poll()); // Output: Hello

        System.out.println(queue); // Output: [World, Java]

        Queue<Integer> queue1 = new LinkedList<>();

        queue1.offer(10);
        queue1.offer(20);
        queue1.offer(30);
        queue1.offer(40);
        queue1.offer(50);
        queue1.offer(60);
        queue1.offer(70);
        queue1.offer(80);
        queue1.offer(90);
        System.out.println(queue1);

        System.out.println("Poll : " + queue1.poll());
        System.out.println(queue1);

        System.out.println("Peek : " + queue1.peek());
        System.out.println(queue1);

    }

}
