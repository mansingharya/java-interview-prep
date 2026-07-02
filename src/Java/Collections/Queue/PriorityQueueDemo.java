package Java.Collections.Queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

    public static void main(String[] args) {

        /*
         * A Priority Queue is a special type of queue in which each element is associated with a priority and is served according to its priority.
         * If two elements have the same priority, they are served according to their order in the queue.

         * In Java, the PriorityQueue class is part of the java.util package and implements the Queue interface.
         * It uses a heap data structure to store the elements, which allows for efficient retrieval of the highest (or lowest) priority element.

         * The elements in a PriorityQueue are ordered according to their natural ordering (if they implement Comparable) or by a Comparator provided at the time of creation.
         * The head of the queue is the least element with respect to the specified ordering.

         * Common operations on a PriorityQueue include:
         * - offer(E e): Inserts the specified element into this priority queue.
         * - poll(): Retrieves and removes the head of this queue, or returns null if this queue is empty.
         * - peek(): Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
         *
         */

        // Example usage of PriorityQueue

        // Min Heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(100);

        pq.offer(10);

        pq.offer(40);

        pq.offer(20);
        pq.offer(30);
        pq.offer(50);

        pq.offer(90);

        pq.offer(60);
        pq.offer(70);
        pq.offer(80);

        // Implemented Min Heap Internally
        System.out.println(pq);

        System.out.println(pq.poll());
        System.out.println(pq);

        System.out.println(pq.peek());



        // Max Heap
        System.out.println();
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Comparator.reverseOrder());

        pq1.offer(10);
        pq1.offer(100);
        pq1.offer(40);
        pq1.offer(20);
        pq1.offer(30);
        pq1.offer(50);
        pq1.offer(90);
        pq1.offer(60);
        pq1.offer(70);
        pq1.offer(80);

        // Implemented Max Heap Internally
        System.out.println(pq1);

        System.out.println(pq1.poll());
        System.out.println(pq1);

        System.out.println(pq1.peek());

    }

}
