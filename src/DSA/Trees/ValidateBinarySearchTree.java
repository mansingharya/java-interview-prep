package DSA.Trees;


// 12.

public class ValidateBinarySearchTree {

    public static boolean isValidBST(TreeNode root) {
        return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, int minRangeStartFrom, int  maxRangeEndAt) {
        if (node == null) {
            return true;
        }

        if (node.val <= minRangeStartFrom || node.val >= maxRangeEndAt) {
            return false;
        }

        return validate(node.left, minRangeStartFrom, node.val)
                && validate(node.right, node.val, maxRangeEndAt);

    }


    static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("\nGiven BST is valid ?? - " + isValidBST(root));

        TreeNode root1 = new TreeNode(20);

        root1.left = new TreeNode(10);
        root1.right = new TreeNode(30);

        root1.left.left = new TreeNode(5);
        root1.left.right = new TreeNode(15);

        System.out.println("\nGiven BST is valid ?? - " + isValidBST(root1));

    }
}
