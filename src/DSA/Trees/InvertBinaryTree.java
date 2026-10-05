package DSA.Trees;


// 4.

public class InvertBinaryTree {

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("\nBefore Pre-Order Traversal - ");
        TreeTraversal.preOrderTraversal(root);

        root = invertTree(root);

        System.out.println("\n\nAfter Pre-Order Traversal - ");
        TreeTraversal.preOrderTraversal(root);

    }
}
