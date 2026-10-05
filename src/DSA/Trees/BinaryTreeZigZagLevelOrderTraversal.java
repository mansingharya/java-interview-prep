package DSA.Trees;

import java.util.*;


// 7.

public class BinaryTreeZigZagLevelOrderTraversal {

    public static List<List<Integer>> zigZagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean leftToRight = true;

        while ( !queue.isEmpty()) {

            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();

                if (node != null) {
                    level.add(node.val);

                    if (node.left != null) {
                        queue.offer(node.left);
                    }

                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }

            if ( !leftToRight) {
                Collections.reverse(level);
            }

            leftToRight = !leftToRight;

            result.add(level);
        }

        return result;
    }

    static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("\nZig Zag Level Order Traversal - " + zigZagLevelOrder(root));

    }
}
