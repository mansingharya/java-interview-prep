package LLD.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;


public class LRUCache {

    private static class Node {
        int key;
        int val;

        Node prev;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }


    private final int capacity;
    private final Map<Integer, Node> cache;

    private final Node head;
    private final Node tail;

    private final ReentrantLock lock = new ReentrantLock();


    LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }

        this.capacity = capacity;
        cache = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        lock.lock();
        try {
            Node node = cache.get(key);

            if (node == null) {
                return -1;
            }

            moveToFront(node);
            return node.val;

        } finally {
            lock.unlock();
        }
    }

    public void put(int key, int val) {
        lock.lock();
        try {
            Node node = cache.get(key);

            if (node != null) {
                node.val = val;
                moveToFront(node);
                return;
            }

            if (cache.size() == capacity) {
                Node lru = tail.prev;
                removeNode(lru);
                cache.remove(lru.key);
            }

            Node newNode = new Node(key, val);
            cache.put(key, newNode);
            addToFront(newNode);

        } finally {
            lock.unlock();
        }
    }

    public int remove(int key) {
        lock.lock();
        try {
            Node node = cache.remove(key);

            if (node == null) {
                return -1;
            }

            removeNode(node);
            return node.val;

        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return cache.size();
        } finally {
            lock.unlock();
        }
    }

    private void addToFront(Node node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev = null;
        node.next = null;
    }

    private void moveToFront(Node node) {
        removeNode(node);
        addToFront(node);
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);
        System.out.println(cache.get(1)); // 10

        cache.put(3, 30); // Evicts key 2

        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 30

        cache.put(4, 40); // Evicts key 1

        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 30
        System.out.println(cache.get(4)); // 40


    }

}
