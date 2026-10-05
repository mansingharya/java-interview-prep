package DSA.Trees;


// 1. Tree Traversal

public class TreeTraversal {

    public static void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");

        preOrderTraversal(root.left);

        preOrderTraversal(root.right);
    }

    public static void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left);

        System.out.print(root.val + " ");

        inOrderTraversal(root.right);
    }

    public static void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        postOrderTraversal(root.left);

        postOrderTraversal(root.right);

        System.out.print(root.val + " ");
    }


    /*
                1
          2           3
       4    5

Pre-Order (NLR) --> 1, 2, 4, 5, 3

In-Order (LNR)  --> 4, 2, 5, 1, 3

Post-Order (LRN) -> 4, 5, 2, 3, 1

     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("\nPre-Order Traversal of BST: ");
        preOrderTraversal(root);


        System.out.println("\n\nIn-Order Traversal of BST: ");
        inOrderTraversal(root);

        System.out.println("\n\nPost-Order Traversal of BST: ");
        postOrderTraversal(root);

    }

}
