package DSA.Trees;


// 10.

public class PathSum {

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        int remaining = targetSum - root.val;

        return hasPathSum(root.left, remaining)
                || hasPathSum(root.right, remaining);
    }

    static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("\nCheck if binary tree contain path sum - " + hasPathSum(root, 1));
        System.out.println("Check if binary tree contain path sum - " + hasPathSum(root, 4));
        System.out.println("Check if binary tree contain path sum - " + hasPathSum(root, 5));
        System.out.println("Check if binary tree contain path sum - " + hasPathSum(root, 6));
        System.out.println("Check if binary tree contain path sum - " + hasPathSum(root, 7));
        System.out.println("Check if binary tree contain path sum - " + hasPathSum(root, 8));

    }

}
