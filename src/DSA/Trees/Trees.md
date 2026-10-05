# Trees

Binary-tree problems walk a `TreeNode` with **DFS (recursion / stack)** or **BFS (queue)**. Recursion returns a value from children (height, validity, path gain); BFS processes one level at a time.

**When to use:** hierarchy, ancestor queries, BST order, root-to-leaf paths, and level-by-level views.

```java
class TreeNode {
    int val;
    TreeNode left, right;
}
```

**DFS orders:** Pre-order `NLR` · In-order `LNR` (sorted on a BST) · Post-order `LRN` (children first — height, diameter, path sum).

---

## Problems (Easy → Hard)

| # | Problem | Difficulty | Source |
|---|---------|------------|--------|
| 1 | [Tree Traversal](#1-tree-traversal) | Easy | `TreeTraversal.java` |
| 2 | [Maximum Depth / Height](#2-maximum-depth--height) | Easy | `MaximumDepthOrHeightOfBinaryTree.java` |
| 3 | [Invert Binary Tree](#3-invert-binary-tree) | Easy | `InvertBinaryTree.java` |
| 4 | [Same Tree](#4-same-tree) | Easy | `SameTree.java` |
| 5 | [Path Sum](#5-path-sum) | Easy | `PathSum.java` |
| 6 | [Binary Tree Paths](#6-binary-tree-paths) | Easy | `BinaryTreePaths.java` |
| 7 | [Balanced Binary Tree](#7-balanced-binary-tree) | Easy | `BalancedBinaryTree.java` |
| 8 | [Diameter of Binary Tree](#8-diameter-of-binary-tree) | Easy | `DiameterOfBinaryTree.java` |
| 9 | [Level Order Traversal](#9-level-order-traversal) | Medium | `BinaryTreeLevelOrderTraversal.java` |
| 10 | [Right Side View](#10-right-side-view) | Medium | `BinaryTreeRightSideView.java` |
| 11 | [Zigzag Level Order](#11-zigzag-level-order) | Medium | `BinaryTreeZigZagLevelOrderTraversal.java` |
| 12 | [Validate BST](#12-validate-bst) | Medium | `ValidateBinarySearchTree.java` |
| 13 | [Kth Smallest in BST](#13-kth-smallest-in-bst) | Medium | `KthSmallestElementInBST.java` |
| 14 | [LCA of BST](#14-lca-of-bst) | Medium | `LowestCommonAncestorOfBST.java` |
| 15 | [LCA of Binary Tree](#15-lca-of-binary-tree) | Medium | `LowestCommonAncestorOfBinaryTree.java` |
| 16 | [Maximum Path Sum](#16-maximum-path-sum) | Hard | `BinaryTreeMaximumPathSum.java` |

---

## 1. Tree Traversal

**Pattern:** Recursive DFS (foundational)

**Idea:** Visit node vs children in three orders. Base case is `null`.

```
        1
      2   3
     4 5

Pre-order  (NLR) → 1 2 4 5 3
In-order   (LNR) → 4 2 5 1 3
Post-order (LRN) → 4 5 2 3 1
```

```java
public static void preOrderTraversal(TreeNode root) {
    if (root == null) return;
    System.out.print(root.val + " ");
    preOrderTraversal(root.left);
    preOrderTraversal(root.right);
}

public static void inOrderTraversal(TreeNode root) {
    if (root == null) return;
    inOrderTraversal(root.left);
    System.out.print(root.val + " ");
    inOrderTraversal(root.right);
}

public static void postOrderTraversal(TreeNode root) {
    if (root == null) return;
    postOrderTraversal(root.left);
    postOrderTraversal(root.right);
    System.out.print(root.val + " ");
}
```

**Complexity:** O(n) time · O(h) stack space (height)

---

## 2. Maximum Depth / Height

**LeetCode:** [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

**Idea:** Height of a node is `1 + max(left height, right height)`. Empty tree has height 0.

```java
public static int findHeightOrMaxDepth(TreeNode root) {
    if (root == null) return 0;

    int leftDepth = findHeightOrMaxDepth(root.left);
    int rightDepth = findHeightOrMaxDepth(root.right);
    return 1 + Math.max(leftDepth, rightDepth);
}
```

**Complexity:** O(n) time · O(h) space

---

## 3. Invert Binary Tree

**LeetCode:** [226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/)

**Idea:** Swap `left` and `right` at every node, then invert both subtrees.

```java
public static TreeNode invertTree(TreeNode root) {
    if (root == null) return null;

    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;

    invertTree(root.left);
    invertTree(root.right);
    return root;
}
```

**Complexity:** O(n) time · O(h) space

---

## 4. Same Tree

**LeetCode:** [100. Same Tree](https://leetcode.com/problems/same-tree/)

**Idea:** Both null → same. One null → different. Values must match, then both left subtrees and both right subtrees.

```java
public static boolean isSameTree(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) return true;
    if (root1 == null || root2 == null) return false;
    if (root1.val != root2.val) return false;

    return isSameTree(root1.left, root2.left)
            && isSameTree(root1.right, root2.right);
}
```

**Complexity:** O(n) time · O(h) space

---

## 5. Path Sum

**LeetCode:** [112. Path Sum](https://leetcode.com/problems/path-sum/)

**Idea:** Subtract `node.val` on the way down. At a **leaf**, check whether the remaining target equals the leaf value.

```java
public static boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) return false;

    if (root.left == null && root.right == null) {
        return targetSum == root.val;
    }

    int remaining = targetSum - root.val;
    return hasPathSum(root.left, remaining)
            || hasPathSum(root.right, remaining);
}
```

**Complexity:** O(n) time · O(h) space

---

## 6. Binary Tree Paths

**LeetCode:** [257. Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/)

**Idea:** DFS while building a string. When you hit a leaf, add the path (`"1->2->4"`).

```java
public static List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();
    dfs(root, "", result);
    return result;
}

private static void dfs(TreeNode node, String path, List<String> result) {
    if (node == null) return;

    path = path.isEmpty() ? String.valueOf(node.val) : path + "->" + node.val;

    if (node.left == null && node.right == null) {
        result.add(path);
        return;
    }
    dfs(node.left, path, result);
    dfs(node.right, path, result);
}
```

**Complexity:** O(n) time · O(n · h) space for the path strings

---

## 7. Balanced Binary Tree

**LeetCode:** [110. Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/)

**Idea:** Height-balanced means `|leftHeight - rightHeight| ≤ 1` at **every** node. Return `-1` as a sentinel when a subtree is unbalanced so you can stop early (one pass, not a separate height call per node).

```java
public static boolean isBalanced(TreeNode root) {
    return checkHeight(root) != -1;
}

private static int checkHeight(TreeNode node) {
    if (node == null) return 0;

    int leftHeight = checkHeight(node.left);
    if (leftHeight == -1) return -1;

    int rightHeight = checkHeight(node.right);
    if (rightHeight == -1) return -1;

    if (Math.abs(leftHeight - rightHeight) > 1) return -1;
    return 1 + Math.max(leftHeight, rightHeight);
}
```

**Complexity:** O(n) time · O(h) space

---

## 8. Diameter of Binary Tree

**LeetCode:** [543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/)

**Idea:** Diameter = longest path between any two nodes (edge count). At each node, `leftHeight + rightHeight` is a candidate. Recursion still returns height for the parent.

```java
private static int diameter = 0;

public static int diameterOfBinaryTree(TreeNode root) {
    height(root);
    return diameter;
}

private static int height(TreeNode root) {
    if (root == null) return 0;

    int leftHeight = height(root.left);
    int rightHeight = height(root.right);
    diameter = Math.max(diameter, leftHeight + rightHeight);

    return 1 + Math.max(leftHeight, rightHeight);
}
```

**Complexity:** O(n) time · O(h) space

---

## 9. Level Order Traversal

**LeetCode:** [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)

**Idea:** BFS. Capture `queue.size()` at the start of each level — that many nodes belong to the current level.

```java
public static List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        int size = queue.size();
        List<Integer> level = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            if (node == null) continue;

            level.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        result.add(level);
    }
    return result;
}
```

**Complexity:** O(n) time · O(n) space (widest level)

---

## 10. Right Side View

**LeetCode:** [199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)

**Idea:** Same BFS as level order. The last node on each level (`i == size - 1`) is what you see from the right.

```java
public static List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            if (node == null) continue;

            if (i == size - 1) result.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }
    return result;
}
```

**Complexity:** O(n) time · O(n) space

---

## 11. Zigzag Level Order

**LeetCode:** [103. Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)

**Idea:** BFS like level order. Alternate a `leftToRight` flag; reverse the level list when going right-to-left.

```java
public static List<List<Integer>> zigZagLevelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    boolean leftToRight = true;

    while (!queue.isEmpty()) {
        int size = queue.size();
        List<Integer> level = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            if (node == null) continue;
            level.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        if (!leftToRight) Collections.reverse(level);
        leftToRight = !leftToRight;
        result.add(level);
    }
    return result;
}
```

**Complexity:** O(n) time · O(n) space

---

## 12. Validate BST

**LeetCode:** [98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)

**Idea:** Every node must lie in `(min, max)`. Going left tightens `max` to `node.val`; going right tightens `min`. Checking only vs parent is **not** enough (a right-grandchild can still violate an ancestor).

```java
public static boolean isValidBST(TreeNode root) {
    return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
}

private static boolean validate(TreeNode node, int min, int max) {
    if (node == null) return true;
    if (node.val <= min || node.val >= max) return false;

    return validate(node.left, min, node.val)
            && validate(node.right, node.val, max);
}
```

*(For values at `Integer.MIN_VALUE` / `MAX_VALUE`, use `Long` bounds instead.)*

**Complexity:** O(n) time · O(h) space

---

## 13. Kth Smallest in BST

**LeetCode:** [230. Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/)

**Idea:** In-order traversal of a BST visits values in sorted order. Count nodes; when `count == k`, that value is the answer.

```java
private static int count = 0;
private static int answer;

public static int kthSmallest(TreeNode root, int k) {
    count = 0;
    answer = 0;
    inOrder(root, k);
    return answer;
}

private static void inOrder(TreeNode node, int k) {
    if (node == null) return;

    inOrder(node.left, k);
    count++;
    if (count == k) {
        answer = node.val;
        return;
    }
    inOrder(node.right, k);
}
```

**Complexity:** O(h + k) time typical · O(h) space

---

## 14. LCA of BST

**LeetCode:** [235. Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)

**Idea:** Walk from the root. If both `p` and `q` are smaller, go left; both larger, go right; otherwise the split (or a node that is `p`/`q`) is the LCA.

```java
public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    while (root != null) {
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
```

**Complexity:** O(h) time · O(1) space

---

## 15. LCA of Binary Tree

**LeetCode:** [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)

**Idea:** No BST order. If `root` is null, `p`, or `q`, return `root`. Recurse both sides. If both sides return non-null, `p` and `q` sit in different subtrees → `root` is LCA. Otherwise bubble up the non-null side.

```java
public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;

    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);

    if (left != null && right != null) return root;
    return left != null ? left : right;
}
```

**Complexity:** O(n) time · O(h) space

---

## 16. Maximum Path Sum

**LeetCode:** [124. Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)

**Idea:** A path can bend at a node (`left + node + right`). Recursion returns the **best gain going up** — only one child, and negative gains are dropped (`Math.max(0, …)`). Track a global max of the bent path at every node.

```java
private static int maxSum = Integer.MIN_VALUE;

public static int maxPathSum(TreeNode root) {
    maxGain(root);
    return maxSum;
}

private static int maxGain(TreeNode node) {
    if (node == null) return 0;

    int leftGain = Math.max(0, maxGain(node.left));
    int rightGain = Math.max(0, maxGain(node.right));

    maxSum = Math.max(maxSum, node.val + leftGain + rightGain);
    return node.val + Math.max(leftGain, rightGain);
}
```

**Complexity:** O(n) time · O(h) space

---

## Pattern Cheat Sheet

| Pattern | Recursion returns | Problems |
|---------|-------------------|----------|
| Visit / print | void DFS | Traversal, Invert, Paths |
| Height from children | `1 + max(L, R)` | Max Depth, Balanced (`-1` sentinel), Diameter |
| Boolean AND/OR of subtrees | true/false | Same Tree, Path Sum, Validate BST |
| BFS by level (`queue.size()`) | — | Level Order, Right Side View, Zigzag |
| In-order = sorted | — | Kth Smallest (BST) |
| Range `(min, max)` | boolean | Validate BST |
| Split vs same side | node pointer | LCA of BST |
| Both sides non-null → root | node pointer | LCA of Binary Tree |
| Gain up vs global bend | one-child gain | Maximum Path Sum |

**DFS template:**

```java
int dfs(TreeNode node) {
    if (node == null) return 0;          // or true / null
    int left = dfs(node.left);
    int right = dfs(node.right);
    // combine left + right + node.val
    return 1 + Math.max(left, right);    // typical height-style return
}
```

**BFS level template:**

```java
Queue<TreeNode> q = new LinkedList<>();
q.offer(root);
while (!q.isEmpty()) {
    int size = q.size();                 // freeze current level width
    for (int i = 0; i < size; i++) {
        TreeNode node = q.poll();
        if (node.left != null) q.offer(node.left);
        if (node.right != null) q.offer(node.right);
    }
}
```
