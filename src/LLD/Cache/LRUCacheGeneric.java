package LLD.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCacheGeneric <K, V> {

    private static class Node<K, V> {
        K key;
        V val;

        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private final int capacity;
    private final Map<K, Node<K, V>> cache;

    private final Node<K, V> head;
    private final Node<K, V> tail;

    private final ReentrantLock lock = new ReentrantLock();


    LRUCacheGeneric(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }

        this.capacity = capacity;
        this.cache = new HashMap<>();

        head = new Node<>(null, null);
        tail = new Node<>(null, null);

        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        lock.lock();
        try {
            Node<K, V> node = cache.get(key);

            if (node == null) {
                return null;
            }

            moveToFront(node);
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

    public void put(K key, V val) {
        lock.lock();
        try {
            Node<K, V> node = cache.get(key);

            if (node != null) {
                node.val = val;
                moveToFront(node);
                return;
            }

            if (cache.size() == capacity) {
                Node<K, V> lruNode = tail.prev;
                removeNode(lruNode);
                cache.remove(lruNode.key);
            }

            Node<K, V> newNode = new Node<>(key, val);
            cache.put(key, newNode);
            addToFront(newNode);

        } finally {
            lock.unlock();
        }
    }

    public V remove(K key) {
        lock.lock();
        try {
            Node<K, V> node = cache.remove(key);
            if (node == null) {
                return null;
            }

            removeNode(node);
            return node.val;

        } finally {
            lock.unlock();
        }
    }

    public void clear() {
        lock.lock();
        try {
            cache.clear();
            head.next = tail;
            tail.prev = head;
        } finally {
            lock.unlock();
        }
    }


    private void addToFront(Node<K, V> node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev = null;
        node.next = null;
    }

    private void moveToFront(Node<K, V> node) {
        removeNode(node);
        addToFront(node);
    }


    public static void main(String[] args) {

        LRUCacheGeneric<Integer, String> cacheGeneric = new LRUCacheGeneric<>(2);

        cacheGeneric.put(1, "10");
        cacheGeneric.put(2, "20");
        System.out.println(cacheGeneric.get(1)); // 10

        cacheGeneric.put(3, "30"); // Evicts key 2

        System.out.println(cacheGeneric.get(2)); // -1
        System.out.println(cacheGeneric.get(3)); // 30

        cacheGeneric.put(4, "40"); // Evicts key 1

        System.out.println(cacheGeneric.get(1)); // -1
        System.out.println(cacheGeneric.get(3)); // 30
        System.out.println(cacheGeneric.get(4)); //

    }

}
