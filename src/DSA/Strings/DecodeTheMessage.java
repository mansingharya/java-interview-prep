package DSA.Strings;

public class DecodeTheMessage {

    static String decodeMessage(String key, String message) {
        char[] map = new char[26];

        char current = 'a';
        for (char c : key.toCharArray()) {
            if (c != ' ' && map[c-'a'] == 0) {
                map[c-'a'] = current++;
            }

            if (current > 'z') {
                break;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (c == ' ') {
                ans.append(c);
            } else {
                ans.append(map[c-'a']);
            }
        }
        
        return ans.toString();
    }

    static void main(String[] args) {

        String key = "the quick brown fox jumps over the lazy dog";
        String message = "vkbs bs t suepuv";
        System.out.println();
        System.out.println("Encoded Msg: " + message);
        System.out.println("Decoded Msg: " + decodeMessage(key, message));

        key = "eljuxhpwnyrdgtqkviszcfmabo";
        message = "zwx hnfx lqantp mnoeius ycgk vcnjrdb";
        System.out.println();
        System.out.println("Encoded Msg: " + message);
        System.out.println("Decoded Msg: " + decodeMessage(key, message));

    }

}
