package data_structure;

import java.util.Random;

/**
 * Treap树堆、RankTree名次数
 */
public class Treap {

    static class Node {
        int key, w, cnt, size; // 键值、权值、相同键值的数量、子树的大小
        Node[] son = new Node[2]; // 左右子节点
    }

    Node root;

    /**
     * 插入键值为key的节点
     *
     * @param key 插入值
     */
    public void insert(int key) {
        // 插入节点并更新root
        root = insert(root, key);
    }

    /**
     * 删除键值为 key 的节点，若有多个则只删除一个
     *
     * @param key 待删除的 k
     */
    public void delete(int key) {
        // 删除节点并更新root
        root = delete(root, key);
    }

    /**
     * 根据key返回rank
     *  rank 定义为 排名定义为比当前数小的数的个数 + 1
     * @param key 待查询的 k
     * @return k 对应的排名
     */
    public int rank(int key) {
        return rank(root, key);
    }

    /**
     * 查询排名为 rank 的 key
     *
     * @param rank 待查询的排名
     * @return 排名对应的 k
     */
    public int key(int rank) {
        return key(root, rank);
    }

    /**
     * 获取 key 的前驱的值
     * 前驱定义为小于 x，且最大的数
     * @param key 待查询的 k
     * @return k 对应的前驱
     */
    public int prefix(int key) {
        return prefix(root, key);
    }

    /**
     * 获取 key 的后继的值
     * 后继定义为大于 x，且最小的数
     * @param key 待查询的 k
     * @return k 对应的后继
     */
    public int suffix(int key) {
        return suffix(root, key);
    }

    /**
     * 更新节点的size，只要insert/delete/rotate对节点修改就从下至上更新
     *
     * @param u 待更新的节点
     */
    private void update(Node u) {
        int sonSize = 0;
        if (u.son[0] != null) sonSize += u.son[0].size;
        if (u.son[1] != null) sonSize += u.son[1].size;
        u.size = u.cnt + sonSize;
    }

    /**
     * 创建一个键值为key的节点并返回
     *
     * @param key 待创建的节点的 k
     * @return 对应 node
     */
    private Node create(int key) { //
        Node u = new Node();
        u.key = key;
        // 权值随机。随机范围越大越好。
        u.w = (int) (Math.random() * 0x7fffffff);
        u.cnt = u.size = 1;
        return u;
    }

    /**
     * 对节点进行向下旋转。o=0时左旋，o=1时右旋
     *
     * @param u 待下旋的节点
     * @param o 取值为 0 表示左旋，1 为右旋
     * @return 旋转后代替 u 的节点
     */
    private Node rotate(Node u, int o) { //
        Node son = u.son[o ^ 1];
        u.son[o ^ 1] = son.son[o];
        son.son[o] = u;
        update(u);
        update(son);
        return son; // 返回旋转后代替u节点的节点
    }

    /**
     * 在节点u下进行搜索并插入key节点
     *
     * @param u   待搜索的节点
     * @param key 待插入的 k
     * @return 当前节点 u
     */
    private Node insert(Node u, int key) { //
        if (u == null) return create(key); // 直接创建节点
        if (key > u.key) {
            // 向右节点搜索
            u.son[1] = insert(u.son[1], key);
            // 如果右节点权值比当前节点权值大，则对当前节点左旋
            if (u.son[1].w > u.w) u = rotate(u, 0);
        } else if (key < u.key) {
            // 向左节点搜索
            u.son[0] = insert(u.son[0], key);
            // 如果左节点权值比当前节点权值大，则对当前节点右旋
            if (u.son[0].w > u.w) u = rotate(u, 1);
        } else {
            // 如果当前节点权值恰好为key，则直接令cnt+1即可
            u.cnt++;
        }
        // 更新当前节点
        update(u);
        // 返回当前节点
        return u;
    }

    /**
     * 从节点 u 开始搜素，最后删除键值为 key 的节点
     * @param u     待搜索的节点
     * @param key   待删除的 k
     * @return      更新后的节点
     */
    private Node delete(Node u, int key) {
        // 如果找不到，则退出
        if (u == null)
            return null;
        else if (key > u.key)
            u.son[1] = delete(u.son[1], key); // 递归向右查找，最后回溯更新
        else if (key < u.key)
            u.son[0] = delete(u.son[0], key); // 递归向左查找，最后回溯更新
        else if (u.cnt >= 2)
            u.cnt--; // 如果当前节点保存两个以上相同键值
        else if (u.son[0] == null && u.son[1] == null)
            return null; // 如果没有左右子节点，则直接退出即可
        else {
            int o;
            // 如果只存在一个左右节点中，那么o的值就代表存在的那一个节点
            if (u.son[0] == null || u.son[1] == null)
                o = u.son[0] == null ? 0 : 1;
            else
                o = u.son[0].w < u.son[1].w ? 0 : 1; // 如果两个左右节点都存在，那么o的值就代表权值小的那一个
            // 对u节点进行旋转并更新u
            u = rotate(u, o);
            // 将u节点递归删除。最终被删除的节点将会旋转到最底部，然后直接删除。
            u.son[o] = delete(u.son[o], key);
        }
        update(u); // 回溯更新u节点
        return u;
    }

    /**
     * 从u节点开始搜素键值为key的节点的排名
     * @param u         带搜索的节点 u
     * @param key       带搜索的 key
     * @return          返回 key 的排名
     */
    private int rank(Node u, int key) {
        if (u == null) return 0x7fffffff; // 如果不存在键值为key的节点
        // 获取左子树的大小
        int lSize = u.son[0] == null ? 0 : u.son[0].size;
        // 返回左子树的大小+当前节点键值的数量+递归搜素右子节点的结果
        if (key > u.key)
            return lSize + u.cnt + rank(u.son[1], key);
        else if (key < u.key)
            return rank(u.son[0], key); // 递归搜素左子树
        else
            return lSize + 1; // 如果找到此节点，则返回左子树的数量并加上本身的结果就是u子树中key节点的排名
    }

    /**
     * 从u节点开始时搜素排名为rank的节点的值
     * @param u         开始搜索的节点 u
     * @param rank      带搜索的排名
     * @return          对应排名的值
     */
    private int key(Node u, int rank) {
        if (u == null)
            return 0x7fffffff; // 如果不存在，则返回无穷大
        int lSize = u.son[0] == null ? 0 : u.son[0].size; // 获取左子树的大小
        if (rank <= lSize)
            return key(u.son[0], rank); // 如果rank小于等于左子树的大小，则递归向左子节点搜素。
        else if (lSize + u.cnt >= rank)
            return u.key; // 如果左子树的大小加当前节点key的数量又恰好比大于等于当前的rank，则直接返回当前节点的key即可
        else
            return key(u.son[1], rank - lSize - u.cnt); // 否则递归向右子树查找，并且此时只需要搜素右子树中排名为rank-lSize-u.cnt的节点即可。
    }

    /**
     * 搜素key节点的前驱。前驱位于key节点（可能不存在）的左节点的右子树中键值最大的节点
     * @param u         开始搜索的节点 u
     * @param key       带搜索的键值 k
     * @return          返回键值 k 对应的排名的前驱
     */
    private int prefix(Node u, int key) {
        if (u == null)
            return -0x7fffffff; // 如果不存在，则返回负无穷
        else if (key <= u.key)
            return prefix(u.son[0], key); // 递归向左子树查找key
        else
            return Math.max(u.key, prefix(u.son[1], key)); // 递归向右子树查找key时，每次都要取最大值
    }

    /**
     * 搜素key节点的后继。后继位于key节点（可能不存在）的右节点的左子树中键值最小的节点
     * @param u         开始搜索的节点 u
     * @param key       带搜索的键值 k
     * @return          返回键值 k 对应的排名的后继
     */
    private int suffix(Node u, int key) {
        if (u == null)
            return 0x7fffffff; // 如果不存在，则返回正无穷
        else if (key >= u.key)
            return suffix(u.son[1], key); // 递归向右子树查找key
        else
            return Math.min(u.key, suffix(u.son[0], key)); // 递归向左子树查找key时，每次都要取最小值
    }
}