package Java.Collections.Map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {

    public static void main(String[] args) {

        /*
         * A LinkedHashMap is a collection class in Java that implements the Map interface and is backed by a hash table and a linked list.
         * It maintains the insertion order of the elements, which means that the elements are stored in the order they were added to the map.
         *
         * The LinkedHashMap class provides constant-time performance for basic operations such as get and put,
         * assuming the hash function disperses the elements properly.
         *
         * Common operations on a LinkedHashMap include:
         * - put(K key, V value): Associates the specified value with the specified key in this map.
         * - get(Object key): Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
         * - remove(Object key): Removes the mapping for a key from this map if it is present.
         * - containsKey(Object key): Returns true if this map contains a mapping for the specified key.
         * - size(): Returns the number of key-value mappings in this map.
         *
         */

        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);
        map.put("Five", 5);
        map.put("Six", 6);
        map.put("Seven", 7);
        map.put("Eight", 8);
        map.put("Nine", 9);
        System.out.println(map);

        map.put("One", 11);
        System.out.println(map);
        map.put("One", 1);

        if ( !map.containsKey("Two")) {
            map.put("Two", 22);
        }
        System.out.println(map);
        map.putIfAbsent("Two", 22);
        System.out.println(map);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry);
            System.out.println(entry.getKey() + "=" + entry.getValue() + "\n");
        }

        System.out.println(map.keySet());
        for(String key : map.keySet()) {
            System.out.println(key + "=" + map.get(key));
        }

        System.out.println(map.values());
        for(Integer integer : map.values()) {
            System.out.println(integer);
        }

        System.out.println("\nSize: " + map.size());
        System.out.println("Is Map Empty : " + map.isEmpty());
        System.out.println("Is Map Contains Key 'One' : " + map.containsKey("One"));
        System.out.println("Is Map Contains Key 'Ten' : " + map.containsKey("Ten"));

        System.out.println("\nIs Map Contains Value '1' : " + map.containsValue(1));
        System.out.println("Is Map Contains Value '11' : " + map.containsValue(11));
        System.out.println("Is Map Contains Value '22' : " + map.containsValue(22));

        map.remove("One");
        map.remove("Two");
        map.remove("Three");
        System.out.println(map);

        map.clear();
        System.out.println(map);

        System.out.println(map.isEmpty());
        System.out.println(map.size());

    }

}
