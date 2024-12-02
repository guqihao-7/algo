package data_structure;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 * 动态开点线段树
 * API 约定：
 * 1. i 一般指代原数组 nums 的下标，idx 一般指代线段树 tree 的下标（idx 可能是父节点下标，也可能是子节点下标，根据上下文判断）
 */
public class DynamicSegmentTree {
    private final TreeNode root;
    private final BiFunction<Integer, Integer, Integer> function;

    public DynamicSegmentTree(BiFunction<Integer, Integer, Integer> function) {
        this.function = function;
        this.root = new TreeNode(0, Integer.MAX_VALUE);
    }

    public void updatePoint(int i, int val) {
        updatePoint(i, val, root);
    }

    public int queryPoint(int i) {
        return queryInterval(i, i);
    }

    public int queryInterval(int queryIntervalLeft, int queryIntervalRight) {
        return queryInterval(queryIntervalLeft, queryIntervalRight, this.root);
    }

    public void updateInterval(int intervalLeft, int intervalRight, int val) {
        this.updateInterval(intervalLeft, intervalRight, val, this.root, Operation.UPDATE);
    }

    public void addInterval(int intervalLeft, int intervalRight, int val) {
        this.updateInterval(intervalLeft, intervalRight, val, this.root, Operation.ADD);
    }

    private void updateInterval(int intervalLeft, int intervalRight, int val, TreeNode node, Operation tagOperation) {
        if (node.left >= intervalLeft && node.right <= intervalRight) {
            int intervalSize = node.right - node.left + 1;
            mergeTag(node.lazyTag, val, tagOperation);
            if (Objects.equals(tagOperation, Operation.UPDATE)) {
                node.val = intervalSize * val;
            }
            else if (Objects.equals(tagOperation, Operation.ADD)) {
                node.val += intervalSize * val;
            }

            if (intervalSize == 1) {
                resetLazyTag(node.lazyTag);
            }
            return;
        }

        if (node.right < intervalLeft || node.left > intervalRight)
            return;

        pushDown(node);

        int mid = node.left + (node.right - node.left) / 2;
        if (intervalLeft <= mid) {
            if (node.leftNode == null) {
                node.leftNode = new TreeNode(node.left, mid);
            }
            updateInterval(intervalLeft, intervalRight, val, node.leftNode, tagOperation);
        }
        if (node.right > mid) {
            if (node.rightNode == null) {
                node.rightNode = new TreeNode(mid + 1, node.right);
            }
            updateInterval(intervalLeft, intervalRight, val, node.rightNode, tagOperation);
        }
        pushUp(node);
    }

    private void pushDown(TreeNode node) {
        LazyTag lazyTag = node.lazyTag;
        if (lazyTag == null
                || lazyTag.tagVal == 0
                || lazyTag.operation == null) return;
        int mid = node.left + (node.right - node.left) / 2;
        if (node.leftNode == null) {
            node.leftNode = new TreeNode(node.left, mid);
        }
        if (node.rightNode == null) {
            node.rightNode = new TreeNode(mid + 1, node.right);
        }
        handleChildren(node.leftNode, lazyTag);
        handleChildren(node.rightNode, lazyTag);
        resetLazyTag(lazyTag);
    }

    private void handleChildren(TreeNode treeNode, LazyTag parentLazyTag) {
        mergeTag(treeNode.lazyTag, parentLazyTag);

        int size = treeNode.right - treeNode.left + 1;
        if (Objects.equals(parentLazyTag.operation, Operation.UPDATE)) {
            treeNode.val = parentLazyTag.tagVal * size;
        }
        else if (Objects.equals(parentLazyTag.operation, Operation.ADD)) {
            treeNode.val += parentLazyTag.tagVal * size;
        }

        if (size == 1) {
            resetLazyTag(treeNode.lazyTag);
        }
    }

    private void mergeTag(LazyTag localTag, LazyTag parentTag) {
        this.mergeTag(localTag, parentTag.tagVal, parentTag.operation);
    }

    private void mergeTag(LazyTag localTag, int parentTagVal, Operation parentTagOperation) {
        // 初始化 tag 或者合并
        if (localTag.tagVal == 0 && localTag.operation == null) {
            setLazyTag(localTag, parentTagVal, parentTagOperation);
        }
        else {
            if (Objects.equals(parentTagOperation, Operation.UPDATE)) {
                // 如果新的操作是 update，则不管前面的 tag 是 update 还是 add，都可以直接覆盖
                localTag.tagVal = parentTagVal;
                localTag.operation = Operation.UPDATE;
            }
            else if (Objects.equals(parentTagOperation, Operation.ADD)) {
                // 不论原先的 tag 是 add 还是 update，这里都可以直接 add，且不用更改原 tag 的类型
                localTag.tagVal += parentTagVal;
            }
        }
    }

    private void resetLazyTag(LazyTag lazyTag) {
        setLazyTag(lazyTag, 0, null);
    }

    private void setLazyTag(LazyTag lazyTag, int tagVal, Operation tagOperation) {
        lazyTag.tagVal = tagVal;
        lazyTag.operation = tagOperation;
    }

    private int queryInterval(int queryIntervalLeft, int queryIntervalRight, TreeNode node) {
        if (node.left >= queryIntervalLeft && node.right <= queryIntervalRight)
            return node.val;
        if (node.right < queryIntervalLeft || node.left > queryIntervalRight)
            return 0;
        pushDown(node);
        int mid = node.left + (node.right - node.left) / 2,
                leftRes = 0, rightRes = 0;
        if (queryIntervalLeft <= mid) {
            if (node.leftNode == null) {
                node.leftNode = new TreeNode(node.left, mid);
            }
            leftRes = queryInterval(queryIntervalLeft, queryIntervalRight, node.leftNode);
        }
        if (queryIntervalRight > mid) {
            if (node.rightNode == null) {
                node.rightNode = new TreeNode(mid + 1, node.right);
            }
            rightRes = queryInterval(queryIntervalLeft, queryIntervalRight, node.rightNode);
        }
        return function.apply(leftRes, rightRes);
    }

    private void updatePoint(int i, int val, TreeNode node) {
        if (node.left == node.right) {
            node.val = val;
            return;
        }

        int mid = node.left + (node.right - node.left) / 2;
        if (i <= mid) {
            if (node.leftNode == null) {
                node.leftNode = new TreeNode(node.left, mid);
            }
            updatePoint(i, val, node.leftNode);
        }
        else {
            if (node.rightNode == null) {
                node.rightNode = new TreeNode(mid + 1, node.right);
            }
            updatePoint(i, val, node.rightNode);
        }
        pushUp(node);
    }

    private void pushUp(TreeNode parent) {
        TreeNode leftNode = parent.leftNode;
        TreeNode rightNode = parent.rightNode;
        if (leftNode != null && rightNode != null) {
            parent.val = function.apply(leftNode.val, rightNode.val);
        }
    }

    public static class TreeNode {
        public int val;
        public int left = -1;
        public int right = -1;
        public LazyTag lazyTag = new LazyTag();
        public TreeNode leftNode;
        public TreeNode rightNode;

        public TreeNode(int left, int right) {
            this(left, right, 0);
        }

        public TreeNode(int left, int right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }

    public static class LazyTag {
        public int tagVal;
        public Operation operation;
    }

    public enum Operation {
        ADD, UPDATE
    }
}
