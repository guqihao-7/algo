package leetcode.list.T100_149;

import leetcode.list.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class T145 {
    private final List<Integer> res = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        dfs(root);
        return res;
    }

    public void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        dfs(root.right);
        res.add(root.val);
    }
}
