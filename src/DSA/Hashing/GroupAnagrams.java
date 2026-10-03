package DSA.Hashing;

import java.util.*;


// 4. https://leetcode.com/problems/group-anagrams/description/a

public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strings) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String s : strings) {

            char[] chars = s.toCharArray();

            Arrays.sort(chars);

            String key = new String(chars);

            groups.computeIfAbsent(key, k -> new ArrayList<>())
                    .add(s);
        }

        return new ArrayList<>(groups.values());
    }

    static void main(String[] args) {

        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strings));

    }
}
