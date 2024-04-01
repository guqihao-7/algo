package leetcode.list.T1900_1949;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T1948 {
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        buildTree(paths);
        dfsAndCalculateChildrenHash(root);
        findSubRoot(root);
        mark();
        dfsAndPrint(root);
        collect(root);
        return res;
    }

    public void collect(TreeNode root) {
        if (root == null) return;
        List<TreeNode> nodes = root.children.values().stream().toList();
        for (TreeNode node : nodes) {
            if (node.deleted == 1) {
                continue;
            }

            // 前序
            path.add(node.dirName);
            res.add(new ArrayList<>(path));
            collect(node);
            path.remove(path.size() - 1);
        }
    }

    private void buildTree(List<List<String>> paths) {
        for (List<String> path : paths) {
            TreeNode p = root;
            for (String content : path) {
                if (p.children.containsKey(content)) {
                    p = p.children.get(content);
                }
                else {
                    TreeNode treeNode = new TreeNode(content);
                    p.children.put(content, treeNode);
                    p = treeNode;
                }
            }
        }
    }

    private void dfsAndCalculateChildrenHash(TreeNode root) {
        if (root.children.isEmpty()) {
            root.childrenHash = root.dirName.intern().hashCode();
            System.out.println(root.dirName + " " + root.childrenHash);
            return;
        }

        List<TreeNode> children = root.children.values().stream().toList();
        for (TreeNode node : children) {
            dfsAndCalculateChildrenHash(node);
            node.childrenHash = calculateHashOfTreeNode(node.children.values().stream().toList());
            System.out.println(root.dirName + " " + root.childrenHash);
        }
    }

    private long calculateHashOfTreeNode(List<TreeNode> children) {
        long ans = 0;
        for (TreeNode child : children) {
            ans |= child.hashCode();
        }
        return ans;
    }

    private void findSubRoot(TreeNode root) {
        if (root == null) return;
        List<TreeNode> nodes = root.children.values().stream().toList();
        for (TreeNode node : nodes) {
            // 前序判重
            if (hashes.containsKey(node.childrenHash)) {
                // 相同的两个都要删除
                nodesNeed2Delete.add(node);
                nodesNeed2Delete.add(hashes.get(node.childrenHash));
                return;
            }
            else {
                hashes.put(node.childrenHash, node);
            }

            findSubRoot(node);
        }
    }

    public void mark() {
        for (TreeNode treeNode : nodesNeed2Delete) {
            dfsAndMark(treeNode);
        }
    }

    private void dfsAndMark(TreeNode node) {
        if (node == null) return;
        List<TreeNode> nodes = node.children.values().stream().toList();
        for (TreeNode treeNode : nodes) {
            treeNode.deleted = 1;
            dfsAndMark(treeNode);
        }
    }

    Map<Long, TreeNode> hashes = new HashMap<>();
    List<TreeNode> nodesNeed2Delete = new ArrayList<>();
    TreeNode root = new TreeNode("/");
    List<List<String>> res = new ArrayList<>();
    List<String> path = new ArrayList<>();
    public static class TreeNode {
        public long childrenHash;
        public String dirName;
        public byte deleted;
        public Map<String, TreeNode> children = new HashMap<>();

        public TreeNode(String dirName) {
            this.dirName = dirName;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "childrenHash=" + childrenHash +
                    ", dirName='" + dirName + '\'' +
                    ", deleted=" + deleted +
                    '}';
        }
    }

    public void dfsAndPrint(TreeNode root) {
        if (root == null) return;
        List<TreeNode> list = root.children.values().stream().toList();
        for (TreeNode treeNode : list) {
            System.out.println(treeNode);
            dfsAndPrint(treeNode);
        }
    }

    public static void main(String[] args) {
        T1948 solution = new T1948();
        List<List<String>> paths = new ArrayList<>();
        paths.add(new ArrayList<>() {{add("a");}});
        paths.add(new ArrayList<>() {{add("c");}});
        paths.add(new ArrayList<>() {{add("d");}});
        paths.add(new ArrayList<>() {{add("a");add("b");}});
        paths.add(new ArrayList<>() {{add("c");add("b");}});
        paths.add(new ArrayList<>() {{add("d");add("a");}});
        System.out.println(paths);
        System.out.println(solution.deleteDuplicateFolder(paths));
    }
}
