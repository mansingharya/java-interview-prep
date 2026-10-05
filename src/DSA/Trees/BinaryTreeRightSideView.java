package DSA.Trees;

import java.util.*;


// 6.

public class BinaryTreeRightSideView {

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while ( !queue.isEmpty()) {

            int size = queue.size();

            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();

                if (node != null) {

                    // IMP -->
                    if (i == size -1) {
                        result.add(node.val);
                    }

                    if (node.left != null) {
                        queue.offer(node.left);
                    }

                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
        }

        return result;
    }

    static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("\nRight Side View - " + rightSideView(root));

    }
}
