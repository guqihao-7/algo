package data_structure;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 * 使用数组表示线段树(静态开点)
 * 1. 根的下标是 1
 * 2. 对于一个下标是 i 的节点而言，其左孩子的下标为 2*i+1，右孩子下标为 2*i+2
 * 3. 给定一个节点下标为 i，其父节点下标为 (i-1)/2
 * API 约定：
 * 1. i 一般指代原数组 nums 的下标，idx 一般指代线段树 tree 的下标（idx 可能是父节点下标，也可能是子节点下标，根据上下文判断）
 */
public class StaticSegmentTree {
    /**
     * 下标从 0 开始, 0 下标是根节点
     */
    private final TreeNode[] tree;

    /**
     * 原始数组，下标从 0 开始
     */
    private final int[] nums;

    /**
     * 节点值的聚合操作
     */
    private final BiFunction<Integer, Integer, Integer> function;


    /**
     * @param nums     原始数组
     * @param function 节点上的聚合方法，在pushUp 中调用
     *                 第一个参数是 tree 数组，后面三个分别是父节点及其左右子
     *                 可以根据题意灵活变化
     *                 常见的有 tree[i] = tree[leftSon(i)] + tree[rightSon(i)]           求区间和
     *                 tree[i] = Math.min(tree[leftSon(i)], tree[rightSon(i)])  求区间最小值
     */
    public StaticSegmentTree(int[] nums, BiFunction<Integer, Integer, Integer> function) {
        this.nums = nums;
        this.tree = new TreeNode[nums.length << 2];
        for (int i = 0; i < this.tree.length; i++)
            this.tree[i] = new TreeNode();
        for (TreeNode treeNode : this.tree)
            treeNode.lazyTag = new LazyTag();
        this.function = function;
        if (nums.length > 0)
            build(0, 0, nums.length - 1);
    }

    /**
     * 区间查询
     *
     * @param queryIntervalLeft  待查询的区间的左端点
     * @param queryIntervalRight 待查询的区间的右端点
     * @return 区间值
     */
    public int queryInterval(int queryIntervalLeft, int queryIntervalRight) {
        return this.queryInterval(queryIntervalLeft, queryIntervalRight, 0, 0, this.nums.length - 1);
    }

    /**
     * 单点查询
     *
     * @param i 原数组下标
     * @return 原数组中对应下标的值
     */
    public int queryPoint(int i) {
        return queryInterval(i, i);
    }

    /**
     * 区间更新，将 [intervalLeft, intervalRight] 上的元素值统一修改为 val
     *
     * @param intervalLeft  待修改的区间左端点
     * @param intervalRight 待修改区间右端点
     * @param val           修改后的值
     */
    public void updateInterval(int intervalLeft, int intervalRight, int val) {
        this.updateInterval(intervalLeft, intervalRight, val, 0, 0, this.nums.length - 1, Operation.UPDATE);
    }

    /**
     * 区间增加，将 [intervalLeft, intervalRight] 上的元素在原值基础上统一增加 val
     *
     * @param intervalLeft  待增加的区间左端点
     * @param intervalRight 待增加的区间右端点
     * @param val           差值
     */
    public void addInterval(int intervalLeft, int intervalRight, int val) {
        this.updateInterval(intervalLeft, intervalRight, val, 0, 0, this.nums.length - 1, Operation.ADD);
    }

    /**
     * 单点修改，将原数组下标为 i 的节点的值修改为 val
     *
     * @param i   原数组中下标为 i 的节点
     * @param val 修改后的值
     */
    public void updatePoint(int i, int val) {
        this.nums[i] = val;
        updatePoint(i, val, 0, 0, this.nums.length - 1);
    }

    /**
     * 单点修改，将原数组下标为 i 的节点的值增加 val
     *
     * @param i        原数组中下标为 i 的节点
     * @param addition 待增加的差值
     */
    public void addPoint(int i, int addition) {
        int src = this.nums[i];
        this.nums[i] = src + addition;
        updatePoint(i, src + addition, 0, 0, this.nums.length - 1);
    }

    /**
     * 寻找第一个非 0 元素下标
     *
     * @return 第一个非 0 元素下标
     */
    public int findFirstNonzeroItemIdx() {
        int idx = 0;
        pushDown(idx);
        while (this.tree[idx] != null
                && this.tree[idx].left != this.tree[idx].right) {
            if (this.tree[leftSon(idx)].val != 0)
                idx = leftSon(idx);
            else
                idx = rightSon(idx);
            pushDown(idx);
        }
        return this.tree[idx] == null ? -1 : this.tree[idx].left;
    }

    private int leftSon(int idx) {
        return idx * 2 + 1; // i*2+1
    }

    private int rightSon(int idx) {
        return idx * 2 + 2; // i*2+2
    }

    /**
     * 父节点值的更新逻辑，此时该节点的左右子节点值已经确定
     *
     * @param idx 父节点下标
     */
    private void pushUp(int idx) {
        this.tree[idx].val = function.apply(this.tree[leftSon(idx)].val, this.tree[rightSon(idx)].val);
    }

    /**
     * 构建线段树，[l, r] 左闭右闭
     *
     * @param idx 下标为 idx 的节点
     * @param l   idx 节点代表的区间左端点
     * @param r   idx 节点代表的区间右端点
     */
    private void build(int idx, int l, int r) {
        this.tree[idx].left = l;
        this.tree[idx].right = r;
        if (l == r) {
            this.tree[idx].val = nums[l];
            return;
        }

        int mid = l + (r - l) / 2;
        build(leftSon(idx), l, mid);
        build(rightSon(idx), mid + 1, r);
        pushUp(idx);        // 从下向上传递值
    }

    // 区间查询，在线段树的 [l, r] 区间范围中搜索区间为 [queryIntervalLeft, queryIntervalRight] 的区间值
    private int queryInterval(int queryIntervalLeft, int queryIntervalRight, int idx, int l, int r) {
        if (l >= queryIntervalLeft && r <= queryIntervalRight)
            return this.tree[idx].val;
        if (r < queryIntervalLeft || l > queryIntervalRight)
            return 0;

        this.pushDown(idx);
        int mid = l + (r - l) / 2;
        int leftRes = 0, rightRes = 0;
        if (queryIntervalLeft <= mid)
            leftRes = this.queryInterval(queryIntervalLeft, queryIntervalRight, leftSon(idx), l, mid);
        if (queryIntervalRight > mid)
            rightRes = this.queryInterval(queryIntervalLeft, queryIntervalRight, rightSon(idx), mid + 1, r);
        return function.apply(leftRes, rightRes);
    }

    /**
     * 根据 tagOperation 将区间内的值进行处理
     *
     * @param intervalLeft  待更新区间的左端点
     * @param intervalRight 待更新区间的右端点
     * @param val           更新后的值
     * @param idx           线段树中对应节点的下标
     * @param l             线段树节点负责的区间的左端点
     * @param r             线段树节点负责的区间的右端点
     * @param tagOperation  lazyTag 对应的操作
     */
    private void updateInterval(int intervalLeft, int intervalRight, int val, int idx, int l, int r, Operation tagOperation) {
        if (l >= intervalLeft && r <= intervalRight) {
            int intervalSize = r - l + 1;
            TreeNode treeNode = this.tree[idx];

            // 初始化 tag 或者合并
            mergeTag(treeNode.lazyTag, val, tagOperation);

            // 更新节点值
            if (Objects.equals(tagOperation, Operation.UPDATE)) {
                treeNode.val = intervalSize * val;
            }
            else if (Objects.equals(tagOperation, Operation.ADD)) {
                treeNode.val += intervalSize * val;
            }

            if (intervalSize == 1) {
                resetLazyTag(treeNode.lazyTag);
            }

            return;
        }

        if (r < intervalLeft || l > intervalRight)
            return;

        this.pushDown(idx);

        int mid = l + (r - l) / 2;
        if (intervalLeft <= mid) {
            this.updateInterval(intervalLeft, intervalRight, val, leftSon(idx), l, mid, tagOperation);
        }
        if (intervalRight > mid) {
            this.updateInterval(intervalLeft, intervalRight, val, rightSon(idx), mid + 1, r, tagOperation);
        }
        this.pushUp(idx);
    }

    /**
     * lazy tag 下推时处理子节点的区间值
     *
     * @param idx 父节点下标
     */
    private void pushDown(int idx) {
        LazyTag lazyTag = this.tree[idx].lazyTag;
        if (lazyTag == null
                || lazyTag.tagVal == 0
                || lazyTag.operation == null) return;

        int leftSon = leftSon(idx);
        handleChildren(leftSon, lazyTag);

        int rightSon = rightSon(idx);
        handleChildren(rightSon, lazyTag);

        resetLazyTag(this.tree[idx].lazyTag);
    }

    /**
     * pushDown 时根据 parentLazyTag 处理子节点
     *
     * @param idx           子节点下标
     * @param parentLazyTag 父节点传递下来的 parentLazyTag
     */
    private void handleChildren(int idx, LazyTag parentLazyTag) {
        TreeNode treeNode = this.tree[idx];
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

    /**
     * 初始化或者合并 tag
     *
     * @param localTag  节点的 tag
     * @param parentTag 父节点 pushDown 下来的 tag
     */
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

    /**
     * 单点更新
     * 将原数组中下标为 i 的节点的值修改为 val 的过程中，需要对线段树上的节点进行同步更新
     *
     * @param i   原数组中待修改的节点的下标
     * @param val 修改后的值
     * @param idx 原数组中节点在线段树数组中对应的下标
     * @param l   节点区间的左端点
     * @param r   节点区间的右端点
     */
    private void updatePoint(int i, int val, int idx, int l, int r) {
        if (this.tree[idx].left == this.tree[idx].right) {
            this.tree[idx].val = val;
            return;
        }

        int mid = l + (r - l) / 2;
        if (i <= mid)
            this.updatePoint(i, val, leftSon(idx), l, mid);
        else
            this.updatePoint(i, val, rightSon(idx), mid + 1, r);
        this.pushUp(idx);
    }

    private void resetLazyTag(LazyTag lazyTag) {
        setLazyTag(lazyTag, 0, null);
    }

    private void setLazyTag(LazyTag lazyTag, int tagVal, Operation tagOperation) {
        lazyTag.tagVal = tagVal;
        lazyTag.operation = tagOperation;
    }

    public static class TreeNode {
        /**
         * 节点值
         */
        public int val;

        /**
         * 节点对应的区间左端点
         */
        public int left;

        /**
         * 节点对应的区间右端点
         */
        public int right;

        /**
         * lazy tag
         */
        public LazyTag lazyTag;
    }

    public static class LazyTag {
        /**
         * lazy tag 的值
         */
        public int tagVal;

        /**
         * 该 tag 对应的操作
         */
        public Operation operation;
    }

    public enum Operation {
        /**
         * 在原值的基础上增加
         */
        ADD,

        /**
         * 将值修改为某个值，覆盖原值
         */
        UPDATE
    }
}
