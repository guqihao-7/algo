package leetcode.list.T200_249;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class T236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = getPath(root, p);
        List<TreeNode> qPath = getPath(root, q);

        Set<TreeNode> set = new HashSet<>(qPath);

        for (int i = pPath.size() - 1; i >= 0; i--) {
            TreeNode node = pPath.get(i);
            if (set.contains(node)) {
                return node;
            }
        }
        return root;
    }

    public List<TreeNode> getPath(TreeNode root, TreeNode node) {
        List<TreeNode> path = new ArrayList<>();
        dfs(root, node, path);
        return path;
    }

    public boolean dfs(TreeNode root, TreeNode node, List<TreeNode> path) {
        if (root == null) {
            return false;
        }

        path.add(root);

        if (root == node) {
            return true;
        }

        boolean find = dfs(root.left, node, path);
        if (find) return true;
        find = dfs(root.right, node, path);
        if (find) return true;
        path.remove(path.size() - 1);

        return false;
    }


    public static class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
