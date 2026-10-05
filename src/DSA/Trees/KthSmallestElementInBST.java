package DSA.Trees;


// 13.

public class KthSmallestElementInBST {

    private static int count = 0;
    private static int answer;

    public static int kthSmallest(TreeNode root, int k) {
        count = 0;
        answer = 0;

        inOrder(root, k);

        return answer;
    }

    private static void inOrder(TreeNode node, int k) {
        if (node == null) {
            return;
        }

        inOrder(node.left, k);

        count++;
        if (count == k) {
            answer = node.val;
            return;
        }

        inOrder(node.right, k);
    }


    static void main(String[] args) {

        TreeNode root = new TreeNode(20);

        root.left = new TreeNode(10);
        root.right = new TreeNode(30);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(15);

        int k = 1;
        System.out.println("\n" + k +"th Smallest Element is - " + kthSmallest(root, k));

        k=2;
        System.out.println(k +"th Smallest Element is - " + kthSmallest(root, k));

        k=3;
        System.out.println(k +"th Smallest Element is - " + kthSmallest(root, k));

        k=4;
        System.out.println(k +"th Smallest Element is - " + kthSmallest(root, k));
    }
}
