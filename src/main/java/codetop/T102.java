package codetop;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class T102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.addFirst(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            List<Integer> tmp = new ArrayList<>();
            while (sz-- > 0) {
                TreeNode last = q.removeLast();
                tmp.add(last.val);
                if (last.left != null)
                    q.addFirst(last.left);
                if (last.right != null)
                    q.addFirst(last.right);
            }
            res.add(tmp);
        }
        return res;
    }
}
