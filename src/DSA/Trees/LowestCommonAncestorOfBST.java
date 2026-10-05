package DSA.Trees;


// 15.

public class LowestCommonAncestorOfBST {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        while (root != null) {

            // Both (P and Q) are in less than Root. LCA is left side.
            if (p.val < root.val && q.val < root.val) {
                root = root.left;

            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;

            } else {
                return root;
            }
        }

        return null;
    }

    static void main(String[] args) {

        TreeNode root = new TreeNode(20);

        root.left = new TreeNode(10);
        root.right = new TreeNode(40);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(15);

        root.right.left = new TreeNode(30);
        root.right.right = new TreeNode(50);

        // LCA of 10 and 40
        System.out.println("\nLCA in BST is - " + lowestCommonAncestor(root, root.left, root.right).val);

        // LCA of 10 and 15
        System.out.println("LCA in BST is - " + lowestCommonAncestor(root, root.left, root.left.right).val);

        // LCA of 10 and 5
        System.out.println("LCA in BST is - " + lowestCommonAncestor(root, root.left, root.left.left).val);

        // LCA of 10 and 30
        System.out.println("LCA in BST is - " + lowestCommonAncestor(root, root.left.left, root.right.left).val);

        // LCA of 40 and 30
        System.out.println("LCA in BST is - " + lowestCommonAncestor(root, root.right, root.right.left).val);

    }
}
