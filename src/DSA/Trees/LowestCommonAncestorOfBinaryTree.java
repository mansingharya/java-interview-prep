package DSA.Trees;


// 14.

public class LowestCommonAncestorOfBinaryTree {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Passing 4 and 5
        TreeNode lca = lowestCommonAncestor(root, root.left.left, root.left.right);
        System.out.println("\nLCA is - " + lca.val);

        // Passing 4 and 3
        lca = lowestCommonAncestor(root, root.left.left, root.right);
        System.out.println("LCA is - " + lca.val);

        // Passing 2 and 5
        lca = lowestCommonAncestor(root, root.left, root.left.right);
        System.out.println("LCA is - " + lca.val);
    }
}
