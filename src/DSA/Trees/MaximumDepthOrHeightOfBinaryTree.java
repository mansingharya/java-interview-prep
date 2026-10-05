package DSA.Trees;


// 2.

public class MaximumDepthOrHeightOfBinaryTree {

    public static int findHeightOrMaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = findHeightOrMaxDepth(root.left);
        int rightDepth = findHeightOrMaxDepth(root.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("\nMax Depth of Binary Tree is: " + findHeightOrMaxDepth(root));
    }
}
