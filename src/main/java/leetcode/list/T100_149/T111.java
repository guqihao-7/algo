package leetcode.list.T100_149;

import leetcode.list.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class T111 {
    public int minDepth(TreeNode root) {
        return dfs(root);
    }

    int dfs(TreeNode root) {
        if (root == null) return 0;
        int leftMinDepth = minDepth(root.left);
        int rightMinDepth = minDepth(root.right);
        if (leftMinDepth == 0 && rightMinDepth == 0) return 1;
        else if (leftMinDepth == 0) return rightMinDepth + 1;
        else if (rightMinDepth == 0) return leftMinDepth + 1;
        else return Math.min(leftMinDepth, rightMinDepth) + 1;
    }

    int bfs(TreeNode root) {
        int depth = 0;
        if (root == null) return depth;
        depth++;

        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                TreeNode head = q.removeFirst();
                if (head.left == null && head.right == null)
                    return depth;
                if (head.left != null) q.addLast(head.left);
                if (head.right != null) q.addLast(head.right);
            }
            depth++;
        }

        return depth;
    }
}
