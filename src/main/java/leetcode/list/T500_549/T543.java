package leetcode.list.T500_549;

import leetcode.list.TreeNode;

public class T543 {
    private int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) return -1;
        int lLen = dfs(node.left) + 1;
        int rLen = dfs(node.right) + 1;
        ans = Math.max(ans, lLen + rLen);
        return Math.max(lLen, rLen);
    }

}
