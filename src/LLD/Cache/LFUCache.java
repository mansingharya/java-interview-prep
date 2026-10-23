package LLD.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;


public class LFUCache {

    private static class Node {
        int key;
        int value;
        int frequency;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }

    private static class DoublyLinkedList {
        private final Node head;
        private final Node tail;
        private int size;

        DoublyLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);

            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // Insert at the front: most recently used in this frequency.
        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;

            size++;
        }

        // Remove a node from this list.
        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.prev = null;
            node.next = null;

            size--;
        }

        // Remove the least recently used node.
        Node removeLast() {
            if (size == 0) {
                return null;
            }

            Node node = tail.prev;
            remove(node);
            return node;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }

    private final int capacity;

    private final Map<Integer, Node> keyToNode;
    private final Map<Integer, DoublyLinkedList> frequencyToList;

    private int minFrequency;

    // One lock protects all cache state.
    private final ReentrantLock lock = new ReentrantLock();

    public LFUCache(int capacity) {
        this.capacity = Math.max(0, capacity);

        this.keyToNode = new HashMap<>();
        this.frequencyToList = new HashMap<>();

        this.minFrequency = 0;
    }

    public int get(int key) {
        lock.lock();
        try {
            Node node = keyToNode.get(key);

            if (node == null) {
                return -1;
            }

            increaseFrequency(node);
            return node.value;

        } finally {
            lock.unlock();
        }
    }

    public void put(int key, int value) {
        lock.lock();
        try {
            if (capacity == 0) {
                return;
            }

            // Updating an existing key also counts as an access.
            Node existingNode = keyToNode.get(key);

            if (existingNode != null) {
                existingNode.value = value;
                increaseFrequency(existingNode);
                return;
            }

            // Evict when the cache is full.
            if (keyToNode.size() >= capacity) {
                DoublyLinkedList minList =
                        frequencyToList.get(minFrequency);

                Node evictedNode = minList.removeLast();
                keyToNode.remove(evictedNode.key);

                if (minList.isEmpty()) {
                    frequencyToList.remove(minFrequency);
                }
            }

            // Every newly inserted entry starts at frequency 1.
            Node newNode = new Node(key, value);

            keyToNode.put(key, newNode);

            frequencyToList
                    .computeIfAbsent(
                            1, frequency -> new DoublyLinkedList())
                    .addFirst(newNode);

            minFrequency = 1;

        } finally {
            lock.unlock();
        }
    }

    private void increaseFrequency(Node node) {
        int oldFrequency = node.frequency;

        DoublyLinkedList oldList =
                frequencyToList.get(oldFrequency);

        oldList.remove(node);

        if (oldList.isEmpty()) {
            frequencyToList.remove(oldFrequency);

            if (minFrequency == oldFrequency) {
                minFrequency = oldFrequency + 1;
            }
        }

        node.frequency++;

        frequencyToList
                .computeIfAbsent(
                        node.frequency,
                        frequency -> new DoublyLinkedList())
                .addFirst(node);
    }

    public int size() {
        lock.lock();
        try {
            return keyToNode.size();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(3);

        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);

        System.out.println(cache.get(1)); // 10
        System.out.println(cache.get(2)); // 20

        cache.put(4, 40); // Evicts key 3

        System.out.println(cache.get(3)); // -1
        System.out.println(cache.get(1)); // 10
        System.out.println(cache.get(2)); // 20
        System.out.println(cache.get(4)); // 40

        System.out.println("Cache size: " + cache.size()); // 3
    }
}
