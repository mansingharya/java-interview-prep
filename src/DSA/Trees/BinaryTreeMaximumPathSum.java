package DSA.Trees;


// 16.

public class BinaryTreeMaximumPathSum {

    private static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private static int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));

        maxSum = Math.max(maxSum, (node.val + leftGain + rightGain));

        return node.val + Math.max(leftGain, rightGain);
    }

    static void main(String[] args) {

        TreeNode root = new TreeNode(-10);

        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("\nMax Path is : " + maxPathSum(root));
    }
}
