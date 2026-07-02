package Java.Collections.List;

import java.util.Stack;

public class StackDemo {

    public static void main(String[] args) {

        /*
         * A Stack is a linear data structure that follows the Last In First Out (LIFO) principle.
         * It is a collection of elements with two main operations: push, which adds an element to the top of the stack, and pop, which removes the top element from the stack.
         * The stack also has a peek operation that allows you to look at the top element without removing it.
         *
         * In Java, the Stack class is part of the java.util package and extends the Vector class.
         * It provides methods for standard stack operations such as push(), pop(), peek(), empty(), and search().
         *
         * Common operations on a Stack include:
         * - push(E item): Pushes an item onto the top of this stack.
         * - pop(): Removes the object at the top of this stack and returns that object as the value of this function.
         * - peek(): Looks at the object at the top of this stack without removing it from the stack.
         * - empty(): Tests if this stack is empty.
         * - search(Object o): Returns the 1-based position where an object is on this stack.
         *
         */

        // Example usage of Stack
        Stack<String> stack = new Stack<>();

        stack.push("Hello");
        stack.push("World");
        stack.push("Java");

        System.out.println(stack); // Output: [Hello, World, Java]

        System.out.println(stack.peek()); // Output: Java

        System.out.println(stack.pop()); // Output: Java

        System.out.println(stack); // Output: [Hello, World]

        Stack<String> animals = new Stack<>();

        animals.push("Dog");
        animals.push("Lion");
        animals.push("Cat");
        animals.push("Pig");
        animals.push("Rabbit");
        animals.push("Cow");

        System.out.println("Stack - " + animals);

        System.out.println("Top Element (Peek) : " + animals.peek());

        System.out.println("Top Element (Pop) : " + animals.pop());

        System.out.println("Top Element (Peek) : " + animals.peek());

    }

}
