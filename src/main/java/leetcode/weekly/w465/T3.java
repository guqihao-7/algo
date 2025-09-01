package leetcode.weekly.w465;

import leetcode.list.T200_249.T212;

public class T3 {
    static class TreeNode {
        private boolean isEnd;
        private TreeNode left;
        private TreeNode right;
    }

    public long maxProduct(int[] nums) {
        TreeNode root = buildTree(nums);
        long max = 0;
        int depth = getDepth(root) - 1;
        for (int num : nums) {
            String binaryString = Integer.toBinaryString(num);
            if (binaryString.length() < depth)
                binaryString = "0".repeat(depth - binaryString.length()) + binaryString;
            String res = query(root, binaryString);
            if (res != null)
                max = Math.max(max, (long) Integer.parseInt(res, 2) * num);
        }
        return max;
    }

    private TreeNode buildTree(int[] nums) {
        TreeNode root = new TreeNode();
        for (int num : nums) {
            String binaryString = Integer.toBinaryString(num);
            TreeNode p = root;
            for (char c : binaryString.toCharArray()) {
                if (c == '0') {
                    if (p.left == null) p.left = new TreeNode();
                    p = p.left;
                }
                else if (c == '1') {
                    if (p.right == null) p.right = new TreeNode();
                    p = p.right;
                }
            }
            p.isEnd = true;
        }

        return root;
    }

    private String query(TreeNode root, String binaryString) {
        if (root == null) return null;
        TreeNode p = root;
        int len = binaryString.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = binaryString.charAt(i);
            if (c == '0') {
                p = p.right;
                sb.append('1');
            }
            else if (c == '1') {
                p = p.left;
                sb.append('0');
            }

            if (p == null) return null;
        }

        return p.isEnd ? sb.toString() : null;
    }

    private int getDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        T3 solution = new T3();
        int[] nums = {64, 8, 32};
        for (int num : nums)
            System.out.println(num + ": " + Integer.toBinaryString(num));
        System.out.println(solution.maxProduct(nums));
    }
}
