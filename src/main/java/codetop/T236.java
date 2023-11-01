package codetop;

/**
 * 最经公共祖先
 */
public class T236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode l = dfs(root.left, p, q);
        TreeNode r = dfs(root.right, p, q);

        if (l != null && r != null)
            return root;
        else if (l != null)
            return l;
        else if (r != null)
            return r;
        else
            return null;
    }
}
