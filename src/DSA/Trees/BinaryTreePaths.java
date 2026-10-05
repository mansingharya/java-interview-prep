package DSA.Trees;

import java.util.ArrayList;
import java.util.List;


// 11.

public class BinaryTreePaths {

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();

        dfs(root, "", result);

        return result;
    }

    private static void dfs(TreeNode node, String path, List<String> result) {
        if (node == null) {
            return;
        }

        if (path.isEmpty()) {
            path = String.valueOf(node.val);

        } else {
            path += "->" + node.val;
        }

        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }

        dfs(node.left, path, result);
        dfs(node.right, path, result);
    }


    static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("\nAll Paths are - " + binaryTreePaths(root));
    }
}
